package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.ModuleCourseDto;
import com.elvin.aaos.core.model.dto.ModuleDto;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.service.CourseService;
import com.elvin.aaos.core.service.ModuleService;
import com.elvin.aaos.core.service.TeacherProfileService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/module")
public class ModuleController {

    private final CourseService courseService;
    private final ModuleService moduleService;
    private final ModuleValidation moduleValidation;
    private final AuthorizationUtil authorizationUtil;
    private final TeacherProfileService teacherProfileService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ModuleController(
            @Autowired CourseService courseService,
            @Autowired ModuleService moduleService,
            @Autowired ModuleValidation moduleValidation,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired TeacherProfileService teacherProfileService
    ) {
        this.courseService = courseService;
        this.moduleService = moduleService;
        this.moduleValidation = moduleValidation;
        this.authorizationUtil = authorizationUtil;
        this.teacherProfileService = teacherProfileService;
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

    @GetMapping(value = "/delete/{id}")
    public String deleteModule(@PathVariable("id") long moduleId, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        ModuleCourseDto moduleCourseDto = moduleService.getById(moduleId);
        if (moduleCourseDto == null) {
            logger.debug("Module Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Module Not Found");
            return "redirect:/module/display";
        }

        moduleService.delete(moduleId, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Module Deleted Successfully");
        logger.info("Module Deleted Successfully");
        return "redirect:/module/display";
    }

    @GetMapping(value = "/edit/{id}")
    public String displayEdit(@PathVariable("id") long moduleId, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        ModuleCourseDto moduleCourseDto = moduleService.getById(moduleId);
        if (moduleCourseDto == null) {
            logger.debug("Module Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Module Not Found");
            return "redirect:/module/display";
        }

        moduleCards(modelMap);
        modelMap.put(StringConstants.COURSE_LIST, courseService.list());
        modelMap.put(StringConstants.MODULE, moduleCourseDto);
        logger.info("GET:/module/edit/{id}");
        return "module/edit";
    }

    @PostMapping(value = "/edit")
    public String editModule(@ModelAttribute ModuleDto moduleDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        if (moduleDto == null || moduleDto.getId() < 0) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Bad Request");
            return "redirect:/module/display";
        }

        if (moduleService.getById(moduleDto.getId()) == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Module Not Found");
            return "redirect:/module/display";
        }

        ModuleError moduleError = moduleValidation.updateValidation(moduleDto);
        if (!moduleError.isValid()) {
            logger.debug("module is not valid");
            modelMap.put(StringConstants.ERROR, moduleError);
            modelMap.put(StringConstants.COURSE_LIST, courseService.list());
            modelMap.put(StringConstants.MODULE, moduleDto);
            return "room/edit";
        }

        moduleService.update(moduleDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Module edited successfully");
        logger.info("Module edited successfully");

        return "redirect:/module/display";
    }

    @GetMapping(value = "/assign")
    public String getAssign(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        modelMap.put(StringConstants.TEACHER_PROFILE_LIST, teacherProfileService.list());
        modelMap.put(StringConstants.MODULE_LIST, moduleService.list());
        modelMap.put(StringConstants.ASSIGNED_MODULE_COUNT, teacherProfileService.countModuleAssigned());
        modelMap.put(StringConstants.UNASSIGNED_MODULE_COUNT, teacherProfileService.countModuleUnassigned());
        return "teacher/assignModule";
    }

}
