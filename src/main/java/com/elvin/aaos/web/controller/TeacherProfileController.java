package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.TeacherProfileService;
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
@RequestMapping(value = "/teacher")
public class TeacherProfileController {

    private UserService userService;
    private AuthorizationUtil authorizationUtil;
    private TeacherProfileService teacherProfileService;

    public TeacherProfileController(
            @Autowired UserService userService,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired TeacherProfileService teacherProfileService
    ) {
        this.userService = userService;
        this.authorizationUtil = authorizationUtil;
        this.teacherProfileService = teacherProfileService;
    }

    @GetMapping(value = "/profile")
    public String displayTeacherProfile(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (!userDto.getUserType().equals(UserType.TEACHER)) {
            return "403";
        }

        modelMap.put(StringConstants.TEACHER, teacherProfileService.getByUserId(userDto.getId()));
        return "teacher/profile";
    }

}
