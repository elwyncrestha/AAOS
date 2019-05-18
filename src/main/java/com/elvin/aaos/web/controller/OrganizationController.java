package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.service.BuildingService;
import com.elvin.aaos.core.service.RoomService;
import com.elvin.aaos.core.service.UserService;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RoomService roomService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private void organizationCountForCards(ModelMap modelMap) {
        modelMap.addAttribute(StringConstants.BUILDING_COUNT, buildingService.countAll());
        modelMap.addAttribute(StringConstants.ROOM_COUNT, roomService.countRoomsByRoomType(RoomType.LECTURE_ROOM));
        modelMap.addAttribute(StringConstants.LAB_ROOM_COUNT, roomService.countRoomsByRoomType(RoomType.LAB_ROOM));
        modelMap.addAttribute(StringConstants.TOTAL_STAFF_COUNT, userService.countAllStaffs());
    }

    @GetMapping(value = "/display")
    public String displayOrganizationInfo(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        organizationCountForCards(modelMap);
        return "organization/display";
    }

}
