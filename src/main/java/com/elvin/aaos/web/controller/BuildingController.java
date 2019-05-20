package com.elvin.aaos.web.controller;

import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/building")
public class BuildingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/add")
    public String getAddPage() {
        if (AuthenticationUtil.isAdmin()) {
            return "building/add";
        } else {
            return "403";
        }
    }

}
