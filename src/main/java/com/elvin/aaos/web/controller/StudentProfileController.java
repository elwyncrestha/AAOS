package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.StudentProfileDto;
import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.StudentProfileService;
import com.elvin.aaos.core.service.UserService;
import com.elvin.aaos.core.validation.StudentProfileValidation;
import com.elvin.aaos.web.error.StudentProfileError;
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
@RequestMapping(value = "/student")
public class StudentProfileController {

    private final UserService userService;
    private final AuthorizationUtil authorizationUtil;
    private final StudentProfileService studentProfileService;
    private final StudentProfileValidation studentProfileValidation;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public StudentProfileController(
            @Autowired UserService userService,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired StudentProfileService studentProfileService,
            @Autowired StudentProfileValidation studentProfileValidation
    ) {
        this.userService = userService;
        this.authorizationUtil = authorizationUtil;
        this.studentProfileService = studentProfileService;
        this.studentProfileValidation = studentProfileValidation;
    }

    @GetMapping(value = "/profile")
    public String displayStudentProfile(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.STUDENT)) {
            return "403";
        }

        modelMap.put(StringConstants.STUDENT, studentProfileService.getByUserId(userDto.getId()));
        logger.info("GET:/student/profile");
        return "student/profile";
    }

    @GetMapping(value = "/edit")
    public String addStudentProfileForm(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (AuthenticationUtil.checkCurrentUserAuthority(UserType.STUDENT)) {
            StudentProfileDto studentProfileDto = studentProfileService.getByUserId(userDto.getId());
            if (studentProfileDto == null) {
                studentProfileDto = new StudentProfileDto();
                studentProfileDto.setUser(userDto);
            }
            // no matter what, set full name and email from userDto
            studentProfileDto.setFullName(userDto.getFullName());
            studentProfileDto.setEmail(userDto.getEmail());
            modelMap.put(StringConstants.STUDENT, studentProfileDto);
            logger.info("GET:/student/edit");
            return "student/edit";
        } else {
            return "403";
        }
    }

    @PostMapping(value = "/edit")
    public String addStudentProfile(@ModelAttribute StudentProfileDto studentProfileDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        if (AuthenticationUtil.checkCurrentUserAuthority(UserType.STUDENT)) {
            StudentProfileError studentProfileError = studentProfileValidation.saveOrEditValidation(studentProfileDto);
            if (!studentProfileError.isValid()) {
                logger.debug("Student Profile Detail is not valid");
                modelMap.put(StringConstants.ERROR, studentProfileError);
                modelMap.put(StringConstants.STUDENT, studentProfileDto);
                return "student/edit";
            }

            studentProfileService.save(studentProfileDto, authorizationUtil.getUser());
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Student Profile Updated Successfully");
            return "redirect:/student/profile";
        } else {
            return "403";
        }

    }

}
