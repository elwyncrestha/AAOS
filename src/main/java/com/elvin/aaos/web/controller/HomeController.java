package com.elvin.aaos.web.controller;

import com.elvin.aaos.mail.MailSender;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import com.elvin.aaos.web.utility.auth.Authorities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class HomeController {

    private final MailSender mailSender;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public HomeController(
            @Autowired MailSender mailSender
    ) {
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

    @PostMapping(value = "/resetPassword")
    public String resetPassword(@RequestParam("email") String email) {
        try {
            String message = "<h1>Hello!!!</h1>";
            mailSender.sendMail("elwyncrestha@gmail.com", "AAOS Testing", message);
        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/login";
    }

}
