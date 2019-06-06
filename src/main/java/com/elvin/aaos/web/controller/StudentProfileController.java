package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.StudentProfileService;
import com.elvin.aaos.core.service.UserService;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import com.elvin.aaos.web.utility.auth.AuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/student")
public class StudentProfileController {

    private UserService userService;
    private AuthorizationUtil authorizationUtil;
    private StudentProfileService studentProfileService;

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

}
