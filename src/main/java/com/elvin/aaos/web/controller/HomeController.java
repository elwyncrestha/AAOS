package com.elvin.aaos.web.controller;

import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import com.elvin.aaos.web.utility.auth.Authorities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage(HttpServletRequest request) throws IOException {
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getMainPage(HttpServletRequest request, ModelMap modelMap) throws Exception {

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

}
