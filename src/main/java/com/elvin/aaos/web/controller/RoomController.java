package com.elvin.aaos.web.controller;

import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/room")
public class RoomController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private void roomCountForCards(ModelMap modelMap) {

    }

    @GetMapping(value = "/add")
    public String addRoomPage(ModelMap modelMap) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        roomCountForCards(modelMap);
        logger.info("GET:/room/add");
        return "room/add";
    }

}
