package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.StudentProfileDto;
import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.StudentProfileService;
import com.elvin.aaos.core.service.UserService;
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

import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentProfileController {

    private final UserService userService;
    private final AuthorizationUtil authorizationUtil;
    private final StudentProfileService studentProfileService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public StudentProfileController(
            @Autowired UserService userService,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired StudentProfileService studentProfileService
    ) {
        this.userService = userService;
        this.authorizationUtil = authorizationUtil;
        this.studentProfileService = studentProfileService;
    }

    @GetMapping(value = "/profile")
    public String displayStudentProfile(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (!userDto.getUserType().equals(UserType.STUDENT)) {
            return "403";
        }

        modelMap.put(StringConstants.STUDENT, studentProfileService.getByUserId(userDto.getId()));
        return "student/profile";
    }

    @GetMapping(value = "/edit")
    public String addStudentProfileForm(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (userDto.getUserType().equals(UserType.ADMIN) || userDto.getUserType().equals(UserType.STUDENT)) {
            StudentProfileDto studentProfileDto = studentProfileService.getByUserId(userDto.getId());
            if (studentProfileDto == null) {
                studentProfileDto = new StudentProfileDto();
                studentProfileDto.setFullName(userDto.getFullName());
                studentProfileDto.setEmail(userDto.getEmail());
                studentProfileDto.setUser(userDto);
            }
            modelMap.put(StringConstants.STUDENT, studentProfileDto);
            modelMap.put(StringConstants.IS_ADMIN, userDto.getUserType().equals(UserType.ADMIN));
            return "student/edit";
        } else {
            return "403";
        }
    }

    @PostMapping(value = "/edit")
    public String addStudentProfile(@ModelAttribute StudentProfileDto studentProfileDto, BindingResult bindingResult) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.stream().forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (userDto.getUserType().equals(UserType.ADMIN) || userDto.getUserType().equals(UserType.STUDENT)) {
            studentProfileService.save(studentProfileDto, authorizationUtil.getUser());
            return "redirect:/student/profile";
        } else {
            return "403";
        }

    }

}
