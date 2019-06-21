package com.elvin.aaos.web.controller;

import java.util.List;

import com.elvin.aaos.core.model.dto.NotificationDto;
import com.elvin.aaos.core.model.dto.TroubleShootDto;
import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.NotificationService;
import com.elvin.aaos.core.service.TroubleShootService;
import com.elvin.aaos.core.service.UserService;
import com.elvin.aaos.mail.MailSender;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import com.elvin.aaos.web.utility.auth.Authorities;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private final TroubleShootService troubleShootService;
    private final AuthorizationUtil authorizationUtil;
    private final UserService userService;
    private final NotificationService notificationService;
    private final MailSender mailSender;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public HomeController(
        @Autowired TroubleShootService troubleShootService,
        @Autowired AuthorizationUtil authorizationUtil,
        @Autowired UserService userService,
        @Autowired NotificationService notificationService,
        @Autowired MailSender mailSender
    ) {
        this.troubleShootService = troubleShootService;
        this.authorizationUtil = authorizationUtil;
        this.userService = userService;
        this.notificationService = notificationService;
        this.mailSender = mailSender;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        logger.info("GET:/");
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getMainPage() {

        if (!AuthenticationUtil.currentUserIsNull()) {
            String authorities = AuthenticationUtil.getCurrentUser().getAuthority();

            if (authorities.contains(Authorities.ROLE_AUTHENTICATED) && authorities.contains(Authorities.ROLE_ADMINISTRATOR)) {
                // filter the page
            }

            return "dashboard/index";

        } else {
            return "redirect:/login";
        }

    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        logger.info("GET:/logout");
        return "redirect:/";
    }

    @GetMapping(value = "login")
    public String getLogin(@RequestParam(required = false) String message, ModelMap modelMap) {
        if (!AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else {
            if (message != null)
                modelMap.put(StringConstants.ERROR, message);
        }

        return "login";
    }

    @GetMapping(value = "/resetPassword")
    public String getResetPassword() {

        return AuthenticationUtil.currentUserIsNull() ? "resetPassword" : "redirect:/";

    }

    @GetMapping(value = "/help")
    public String help() {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        return "help";
    }

    @PostMapping(value = "/help")
    public String troubleShoot(@ModelAttribute TroubleShootDto troubleShootDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        troubleShootService.save(troubleShootDto, authorizationUtil.getUser());
        logger.info("Added new troubleshoot problem.");
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Added new troubleshoot problem.");

        List<UserDto> administrators = userService.listByUserType(UserType.ADMIN);
        for (UserDto userDto : administrators) {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setStatus(Status.ACTIVE);
            notificationDto.setUser(userDto);
            notificationDto.setBackground("bg-warning");
            notificationDto.setIcon("fa-question");
            notificationDto.setTitle(StringConstants.TROUBLESHOOT_NOTIFICATION);
            notificationDto.setDescription("There is new troubleshoot problem.");
            notificationService.save(notificationDto, authorizationUtil.getUser());
        }

        try {
            String subject = "AAOS: Troubleshoot";
            String message = "<p>Dear " + authorizationUtil.getUser().getFullName() + ",</p>" +
                "<p>Thank you for contacting us.<br />" +
                "We will reply you soon to troubleshoot your problem.</p>" +
                "<p>Yours sincerely,<br />" +
                "Automated Academic Organization System";
            mailSender.sendMail(authorizationUtil.getUser().getEmail(), subject, message);
        } catch (Exception e) {
            logger.error(e.getMessage());
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Sorry some problem occurred. Try again");
            return "redirect:/help";
        }

        return "redirect:/help";
    }

}
