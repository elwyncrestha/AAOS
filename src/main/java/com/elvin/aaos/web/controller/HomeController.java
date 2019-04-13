package com.elvin.aaos.web.controller;

import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthUtil;
import com.elvin.aaos.web.utility.auth.Authorities;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage(HttpServletRequest request) throws IOException {
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getMainPage(HttpServletRequest request, ModelMap modelMap) throws Exception {

        if (AuthUtil.getCurrentUser() != null) {
            String authorities = AuthUtil.getCurrentUser().getAuthority();

            if (authorities.contains(Authorities.ROLE_AUTHENTICATED) && authorities.contains(Authorities.ROLE_ADMINISTRATOR))
                return "dashboard/index";
            else
                return "dashboard/index";   // currently same for now
        } else {
            return "login";
        }

    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/dashboard";
    }

    @GetMapping(value = "login")
    public String getLogin(@RequestParam(required = false) String message, ModelMap modelMap) {
        if (AuthUtil.getCurrentUser() != null)
            return "dashboard/index";
        else {
            if (message != null)
                modelMap.put(StringConstants.ERROR, message);
        }

        return "login";
    }

    @GetMapping(value = "/resetPassword")
    public String getResetPassword() {
        if (AuthUtil.getCurrentUser() != null)
            return "dashboard/index";
        else
            return "resetPassword";
    }

}
