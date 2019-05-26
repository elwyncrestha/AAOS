package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.RoomBuildingDto;
import com.elvin.aaos.core.model.dto.RoomDto;
import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.service.BuildingService;
import com.elvin.aaos.core.service.RoomService;
import com.elvin.aaos.core.validation.RoomValidation;
import com.elvin.aaos.web.error.RoomError;
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

@Controller
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RoomValidation roomValidation;

    @Autowired
    private AuthorizationUtil authorizationUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private void roomCountForCards(ModelMap modelMap) {
        modelMap.put(StringConstants.LECTURE_ROOM_COUNT, roomService.countRoomsByRoomType(RoomType.LECTURE_ROOM));
        modelMap.put(StringConstants.LAB_ROOM_COUNT, roomService.countRoomsByRoomType(RoomType.LAB_ROOM));
    }

    @GetMapping(value = "/add")
    public String addRoomPage(ModelMap modelMap) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        roomCountForCards(modelMap);
        modelMap.put(StringConstants.BUILDING_LIST, buildingService.list());
        logger.info("GET:/room/add");
        return "room/add";
    }

    @PostMapping(value = "/add")
    public String addRoom(@ModelAttribute RoomDto roomDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            logger.error("/room/add has binding error");
        }

        RoomError roomError = roomValidation.saveValidation(roomDto);
        if (!roomError.isValid()) {
            logger.debug("room is not valid");
            modelMap.put(StringConstants.ERROR, roomError);
            modelMap.put(StringConstants.BUILDING_LIST, buildingService.list());
            modelMap.put(StringConstants.ROOM, roomDto);
            return "room/add";
        }

        roomService.save(roomDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Room Info added successfully");
        logger.info("Room Info added successfully");

        return "redirect:/room/display";
    }

    @GetMapping(value = "/display")
    public String displayRooms(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        roomCountForCards(modelMap);
        modelMap.put(StringConstants.ROOM_LIST, roomService.list());
        return "room/display";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBuilding(@PathVariable("id") long roomId, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        RoomBuildingDto roomBuildingDto = roomService.getById(roomId);
        if (roomBuildingDto == null) {
            logger.debug("Room Not Found");
            redirectAttributes.addFlashAttribute("Room Not Found");
            return "redirect:/room/display";
        }

        roomService.delete(roomId, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Room Deleted Successfully");
        logger.info("Room Deleted Successfully");
        return "redirect:/room/display";
    }

    @GetMapping(value = "/edit/{id}")
    public String displayEdit(@PathVariable("id") long roomId, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        RoomBuildingDto roomBuildingDto = roomService.getById(roomId);
        if (roomBuildingDto == null) {
            logger.debug("Room Not Found");
            redirectAttributes.addFlashAttribute("Room Not Found");
            return "redirect:/room/display";
        }

        roomCountForCards(modelMap);
        modelMap.put(StringConstants.BUILDING_LIST, buildingService.list());
        modelMap.put(StringConstants.ROOM, roomBuildingDto);
        logger.info("GET:/room/edit/{id}");
        return "room/edit";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute RoomDto roomDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            logger.error("/room/edit has binding error");
        }

        if (roomDto == null || roomDto.getId() < 0) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Bad Request");
            return "redirect:/room/display";
        }

        if (roomService.getById(roomDto.getId()) == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Room Not Found");
            return "redirect:/room/display";
        }

        RoomError roomError = roomValidation.updateValidation(roomDto);
        if (!roomError.isValid()) {
            logger.debug("room is not valid");
            modelMap.put(StringConstants.ERROR, roomError);
            modelMap.put(StringConstants.BUILDING_LIST, buildingService.list());
            modelMap.put(StringConstants.ROOM, roomDto);
            return "room/edit";
        }

        roomService.update(roomDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Room edited successfully");
        logger.info("Room edited successfully");

        return "redirect:/room/display";
    }

}
