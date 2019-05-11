package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.service.UserService;
import com.elvin.aaos.core.validation.UserValidation;
import com.elvin.aaos.web.error.UserError;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import com.elvin.aaos.web.utility.auth.AuthorizationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserValidation userValidation;
    @Autowired
    UserService userService;
    @Autowired
    AuthorizationUtil authorizationUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/add")
    public String addUser() {

        if (!AuthenticationUtil.currentUserIsNull()) {
            if (AuthenticationUtil.isAdmin()) {
                return "user/add";
            } else {
                return "403";
            }
        } else
            return "login";
    }

    @PostMapping(value = "/add")
    public String saveUser(@ModelAttribute UserDto userDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "redirect:/";
        }

        UserError userError = userValidation.saveValidation(userDto);
        if (!userError.isValid()) {
            logger.debug("User is not valid");
            modelMap.put(StringConstants.ERROR, userError);
            modelMap.put(StringConstants.USER, userDto);
            return "user/add";
        }

        userService.save(userDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute("message", "User added successfully");

        return "redirect:/user/display";
    }

    @GetMapping(value = "/display")
    public String displayUsers(ModelMap modelMap) {
        if (!AuthenticationUtil.isAdmin()) {
            return "redirect:/";
        }

        return "user/display";
    }

}
