package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.CourseDto;
import com.elvin.aaos.core.service.CourseService;
import com.elvin.aaos.core.service.ModuleService;
import com.elvin.aaos.core.validation.CourseValidation;
import com.elvin.aaos.web.error.CourseError;
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
@RequestMapping(value = "/course")
public class CourseController {

    private final CourseService courseService;
    private final CourseValidation courseValidation;
    private final AuthorizationUtil authorizationUtil;
    private final ModuleService moduleService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public CourseController(
            @Autowired CourseService courseService,
            @Autowired CourseValidation courseValidation,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired ModuleService moduleService
    ) {
        this.courseService = courseService;
        this.courseValidation = courseValidation;
        this.authorizationUtil = authorizationUtil;
        this.moduleService = moduleService;
    }

    private void courseCards(ModelMap modelMap) {
        modelMap.put(StringConstants.COURSE_COUNT, courseService.countTotalCourse());
    }

    @GetMapping(value = "/add")
    public String addCourseForm(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        courseCards(modelMap);
        logger.info("GET:/course/add");
        return "course/add";
    }

    @PostMapping(value = "/add")
    public String addBatch(@ModelAttribute CourseDto courseDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        CourseError courseError = courseValidation.saveValidation(courseDto);
        if (!courseError.isValid()) {
            logger.debug("Course is not valid");
            modelMap.put(StringConstants.ERROR, courseError);
            modelMap.put(StringConstants.COURSE, courseDto);
            return "batch/add";
        }

        courseService.save(courseDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Course added successfully");
        logger.info("Course added successfully");

        return "redirect:/course/display";
    }

    @GetMapping(value = "/display")
    public String displayCourse(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        courseCards(modelMap);
        List<CourseDto> courseDtoList = courseService.list();
        modelMap.put(StringConstants.COURSE_LIST, courseDtoList);
        logger.info("GET:/course/display");

        return "course/display";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteCourse(@PathVariable("id") long courseId, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        CourseDto courseDto = courseService.getById(courseId);
        if (courseDto == null) {
            logger.debug("Course Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Course Not Found");
        }

        // remove associated course first
        if (moduleService.hasAssociatedCourse(courseId)) {
            logger.debug("Cannot delete course having associated modules");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Remove associated modules first");
            return "redirect:/course/display";
        }

        courseService.delete(courseId, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Course Deleted Successfully");
        logger.info("Course Deleted Successfully");
        return "redirect:/course/display";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEditForm(@PathVariable("id") long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        CourseDto courseDto = courseService.getById(id);
        if (courseDto == null) {
            logger.debug("Course Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Course Not Found");
        }

        modelMap.put(StringConstants.COURSE, courseDto);
        logger.info("GET:/course/edit");
        return "course/edit";
    }

    @PostMapping(value = "/edit")
    public String editCourse(@ModelAttribute CourseDto courseDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        if (courseDto == null || courseDto.getId() < 0) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Bad Request");
            logger.debug("Bad Request");
            return "redirect:/course/display";
        }

        if (courseService.getById(courseDto.getId()) == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Course Not Found");
            logger.debug("Course Not Found");
            return "redirect:/course/display";
        }

        CourseError courseError = courseValidation.updateValidation(courseDto);
        if (!courseError.isValid()) {
            logger.debug("Course is not valid");
            modelMap.put(StringConstants.ERROR, courseError);
            modelMap.put(StringConstants.BATCH, courseDto);
            return "course/edit";
        }

        courseService.update(courseDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Course edited successfully");
        logger.info("Course edited successfully");

        return "redirect:/course/display";
    }

}
