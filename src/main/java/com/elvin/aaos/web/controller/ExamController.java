package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.ExamDto;
import com.elvin.aaos.core.model.dto.ExamModuleDto;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.BatchService;
import com.elvin.aaos.core.service.ExamService;
import com.elvin.aaos.core.service.ModuleService;
import com.elvin.aaos.core.validation.ExamValidation;
import com.elvin.aaos.web.error.ExamError;
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
@RequestMapping(value = "/exam")
public class ExamController {

    private final ModuleService moduleService;
    private final ExamValidation examValidation;
    private final ExamService examService;
    private final AuthorizationUtil authorizationUtil;
    private BatchService batchService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ExamController(
            @Autowired ModuleService moduleService,
            @Autowired ExamValidation examValidation,
            @Autowired ExamService examService,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired BatchService batchService
    ) {
        this.moduleService = moduleService;
        this.examValidation = examValidation;
        this.examService = examService;
        this.authorizationUtil = authorizationUtil;
        this.batchService = batchService;
    }

    @GetMapping(value = "/add")
    public String addExamForm(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ACADEMIC_STAFF)) {
            return "403";
        }

        modelMap.put(StringConstants.MODULE_LIST, moduleService.list());
        logger.info("GET:/exam/add");
        return "exam/add";
    }

    @PostMapping(value = "/add")
    public String addExam(@ModelAttribute ExamDto examDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ACADEMIC_STAFF)) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        ExamError examError = examValidation.saveValidation(examDto);
        if (!examError.isValid()) {
            logger.debug("exam is not valid");
            modelMap.put(StringConstants.ERROR, examError);
            modelMap.put(StringConstants.MODULE_LIST, moduleService.list());
            modelMap.put(StringConstants.EXAM, examDto);
            return "exam/add";
        }

        examService.save(examDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Exam added successfully");
        logger.info("Exam added successfully");

        return "redirect:/exam/display";
    }

    @GetMapping(value = "/display")
    public String displayExams(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        modelMap.put(StringConstants.EXAM_LIST, examService.list());
        logger.info("GET:/exam/display");
        return "exam/display";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteExam(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ACADEMIC_STAFF)) {
            return "403";
        }

        examService.delete(id, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Exam Deleted Successfully");
        logger.info("Exam Deleted Successfully");
        return "redirect:/exam/display";

    }

    @GetMapping(value = "/edit/{id}")
    public String editExamForm(@PathVariable("id") long id ,ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ACADEMIC_STAFF)) {
            return "403";
        }

        ExamModuleDto examModuleDto= examService.getById(id);
        if (examModuleDto == null) {
            logger.debug("Exam Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Exam Not Found");
            return "redirect:/exam/display";
        }

        modelMap.put(StringConstants.MODULE_LIST, moduleService.list());
        ExamDto examDto = new ExamDto(
                examModuleDto.getName(),
                examModuleDto.getStart(),
                examModuleDto.getEnd(),
                examModuleDto.getStartTime().toString(),
                examModuleDto.getEndTime().toString(),
                examModuleDto.getModule().getId(),
                examModuleDto.getStatus()
        );
        examDto.setId(examModuleDto.getId());
        examDto.setVersion(examModuleDto.getVersion());
        modelMap.put(StringConstants.EXAM, examDto);
        logger.info("GET:/exam/edit/{id}");
        return "exam/edit";
    }

    @PostMapping(value = "/edit")
    public String editExam(@ModelAttribute ExamDto examDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ACADEMIC_STAFF)) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        ExamError examError = examValidation.updateValidation(examDto);
        if (!examError.isValid()) {
            logger.debug("exam is not valid");
            modelMap.put(StringConstants.ERROR, examError);
            modelMap.put(StringConstants.MODULE_LIST, moduleService.list());
            modelMap.put(StringConstants.EXAM, examDto);
            return "exam/edit";
        }

        examService.update(examDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Exam edited successfully");
        logger.info("Exam edited successfully");

        return "redirect:/exam/display";
    }

    @GetMapping(value = "/assign")
    public String getEnroll(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ACADEMIC_STAFF)) {
            return "403";
        }

        modelMap.put(StringConstants.BATCH_LIST, batchService.list());
        return "batch/assignExam";
    }

}