package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.ModuleDto;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.service.CourseService;
import com.elvin.aaos.core.service.ModuleService;
import com.elvin.aaos.core.validation.ModuleValidation;
import com.elvin.aaos.web.error.ModuleError;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import com.elvin.aaos.web.utility.auth.AuthorizationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/module")
public class ModuleController {

    private final CourseService courseService;
    private final ModuleService moduleService;
    private final ModuleValidation moduleValidation;
    private final AuthorizationUtil authorizationUtil;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ModuleController(
            @Autowired CourseService courseService,
            @Autowired ModuleService moduleService,
            @Autowired ModuleValidation moduleValidation,
            @Autowired AuthorizationUtil authorizationUtil
    ) {
        this.courseService = courseService;
        this.moduleService = moduleService;
        this.moduleValidation = moduleValidation;
        this.authorizationUtil = authorizationUtil;
    }

    private void moduleCards(ModelMap modelMap) {
        modelMap.put(StringConstants.MODULE_COUNT, moduleService.countModuleByStatus(Status.ACTIVE));
    }

    @GetMapping(value = "/add")
    public String addModulePage(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        moduleCards(modelMap);
        modelMap.put(StringConstants.COURSE_LIST, courseService.list());
        logger.info("GET:/module/add");
        return "module/add";
    }

    @PostMapping(value = "/add")
    public String addCourse(@ModelAttribute ModuleDto moduleDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        ModuleError moduleError = moduleValidation.saveValidation(moduleDto);
        if (!moduleError.isValid()) {
            logger.debug("module is not valid");
            modelMap.put(StringConstants.ERROR, moduleError);
            modelMap.put(StringConstants.COURSE_LIST, courseService.list());
            modelMap.put(StringConstants.MODULE, moduleDto);
            return "module/add";
        }

        moduleService.save(moduleDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Module added successfully");
        logger.info("Module added successfully");

        return "redirect:/module/display";
    }

    @GetMapping(value = "/display")
    public String displayModules(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        moduleCards(modelMap);
        modelMap.put(StringConstants.MODULE_LIST, moduleService.list());
        return "module/display";
    }

}
