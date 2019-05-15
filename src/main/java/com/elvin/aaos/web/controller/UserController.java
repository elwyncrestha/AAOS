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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "User added successfully");
        logger.info("User added successfully");

        return "redirect:/user/display";
    }

    @GetMapping(value = "/display")
    public String displayUsers(ModelMap modelMap) {
        if (!AuthenticationUtil.isAdmin()) {
            return "redirect:/";
        }

        List<UserDto> userDtoList = userService.list();
        modelMap.put(StringConstants.USER_LIST, userDtoList);

        return "user/display";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long userId, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.isAdmin()) {
            userService.delete(userId);
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "User Deleted Successfully");
            logger.info("User Deleted Successfully");
            return "redirect:/user/display";
        }

        return "user/display";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEditForm(@PathVariable("id") long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(id);
        if (userDto == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "User Not Found");
            return "redirect:/user/list";
        }

        modelMap.put(StringConstants.USER, userDto);
        return "user/edit";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute UserDto userDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "redirect:/";
        }

        if (userDto == null || userDto.getUserId() < 0) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Bad Request");
            return "redirect:/user/list";
        }

        if (userService.getUser(userDto.getUserId()) == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "User Not Found");
            return "redirect:/user/list";
        }

        UserError userError = userValidation.updateValidation(userDto);
        if (!userError.isValid()) {
            logger.debug("User is not valid");
            modelMap.put(StringConstants.ERROR, userError);
            modelMap.put(StringConstants.USER, userDto);
            return "user/edit";
        }

        userService.update(userDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "User edited successfully");
        logger.info("User edited successfully");

        return "redirect:/user/display";
    }

}
