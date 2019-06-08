package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.UserService;
import com.elvin.aaos.core.utility.StringUtils;
import com.elvin.aaos.core.validation.UserValidation;
import com.elvin.aaos.mail.MailSender;
import com.elvin.aaos.web.error.UserError;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import com.elvin.aaos.web.utility.auth.AuthorizationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserValidation userValidation;
    private final UserService userService;
    private final AuthorizationUtil authorizationUtil;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(
            @Autowired UserValidation userValidation,
            @Autowired UserService userService,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired MailSender mailSender,
            @Autowired PasswordEncoder passwordEncoder
    ) {
        this.userValidation = userValidation;
        this.userService = userService;
        this.authorizationUtil = authorizationUtil;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    private void userCountForCards(ModelMap modelMap) {
        modelMap.addAttribute(StringConstants.TEACHER_COUNT, userService.countByUserType(UserType.TEACHER));
        modelMap.addAttribute(StringConstants.STUDENT_COUNT, userService.countByUserType(UserType.STUDENT));
        modelMap.addAttribute(StringConstants.ACADEMIC_STAFF_COUNT, userService.countByUserType(UserType.ACADEMIC_STAFF));
        modelMap.addAttribute(StringConstants.OPERATIONAL_STAFF_COUNT, userService.countByUserType(UserType.OPERATIONAL_STAFF));
    }

    @GetMapping(value = "/add")
    public String addUser(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        logger.info("GET:/user/add");
        userCountForCards(modelMap);
        return "user/add";
    }

    @PostMapping(value = "/add")
    public String saveUser(@ModelAttribute UserDto userDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
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
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        userCountForCards(modelMap);
        List<UserDto> userDtoList = userService.list();
        modelMap.put(StringConstants.USER_LIST, userDtoList);
        logger.info("GET:/user/display");

        return "user/display";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long userId, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        UserDto userDto = userService.getUser(userId);
        if (userDto == null) {
            logger.debug("User Not Found");
            redirectAttributes.addFlashAttribute("User Not Found");
        }

        userService.delete(userId, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "User Deleted Successfully");
        logger.info("User Deleted Successfully");
        return "redirect:/user/display";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEditForm(@PathVariable("id") long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        UserDto userDto = userService.getUser(id);
        if (userDto == null) {
            logger.debug("User Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "User Not Found");
            return "redirect:/user/display";
        }

        modelMap.put(StringConstants.USER, userDto);
        logger.info("GET:/user/edit");
        return "user/edit";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute UserDto userDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        if (userDto == null || userDto.getId() < 0) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Bad Request");
            logger.debug("Bad Request");
            return "redirect:/user/display";
        }

        if (userService.getUser(userDto.getId()) == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "User Not Found");
            logger.debug("User Not Found");
            return "redirect:/user/display";
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

    @GetMapping(value = "/profile")
    public String getUserProfile(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (userDto.getUserType().equals(UserType.STUDENT)) {
            return "redirect:/student/profile";
        } else if (userDto.getUserType().equals(UserType.TEACHER)) {
            return "redirect:/teacher/profile";
        } else {
            modelMap.put(StringConstants.USER, userDto);
            return "user/profile";
        }
    }

    @PostMapping(value = "/password/reset")
    public String resetPassword(@RequestParam("email") String email, ModelMap modelMap) {

        UserDto userDto = userService.getUserByEmail(email);
        if (userDto == null) {
            modelMap.put(StringConstants.ERROR, "No user registered with provided email");
            return "resetPassword";
        } else {
            String newPassword = StringUtils.generate(15);
            userDto.setPassword(newPassword);
            userService.update(userDto, null);
            try {
                String subject = "AAOS: Reset Password";
                String message = "<p>Dear " + userDto.getFullName() + ",</p>" +
                        "<p>Your password has been reset.<br />" +
                        "Your new password is <b>" + newPassword + "</b></p>" +
                        "<p>Yours sincerely,<br />" +
                        "Automated Academic Organization System";
                mailSender.sendMail(userDto.getEmail(), subject, message);
            } catch (MessagingException e) {
                logger.error(e.getMessage());
            }
            modelMap.put(StringConstants.MESSAGE, "Password Reset Successful. Check email for new password");
            return "/login";
        }
    }

    @GetMapping(value = "/password/update")
    public String getUpdatePassword() {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        return "user/updatePassword";
    }

    @PostMapping(value = "/password/update")
    public String updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (passwordEncoder.matches(oldPassword, userDto.getPassword())) {
            userDto.setPassword(newPassword);
            userService.update(userDto, null);
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Password changed successfully");
        } else {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Wrong old password");
        }
        return "redirect:/user/password/update";
    }

}
