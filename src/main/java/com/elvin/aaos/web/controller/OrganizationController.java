package com.elvin.aaos.web.controller;

import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/display")
    public String displayOrganizationInfo() {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        return "organization/display";
    }

}
