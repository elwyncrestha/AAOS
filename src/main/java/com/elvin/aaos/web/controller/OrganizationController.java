package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.OrganizationDto;
import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.service.*;
import com.elvin.aaos.core.validation.OrganizationValidation;
import com.elvin.aaos.web.error.OrganizationError;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {

    private final UserService userService;
    private final BuildingService buildingService;
    private final RoomService roomService;
    private final OrganizationService organizationService;
    private final OrganizationValidation organizationValidation;
    private final AuthorizationUtil authorizationUtil;
    private final RoomScheduleService roomScheduleService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public OrganizationController(
            @Autowired UserService userService,
            @Autowired BuildingService buildingService,
            @Autowired RoomService roomService,
            @Autowired OrganizationService organizationService,
            @Autowired OrganizationValidation organizationValidation,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired RoomScheduleService roomScheduleService
    ) {
        this.userService = userService;
        this.buildingService = buildingService;
        this.roomService = roomService;
        this.organizationService = organizationService;
        this.organizationValidation = organizationValidation;
        this.authorizationUtil = authorizationUtil;
        this.roomScheduleService = roomScheduleService;
    }

    private void organizationCountForCards(ModelMap modelMap) {
        modelMap.addAttribute(StringConstants.BUILDING_COUNT, buildingService.countAll());
        modelMap.addAttribute(StringConstants.LECTURE_ROOM_COUNT, roomService.countRoomsByRoomType(RoomType.LECTURE_ROOM));
        modelMap.addAttribute(StringConstants.LAB_ROOM_COUNT, roomService.countRoomsByRoomType(RoomType.LAB_ROOM));
        modelMap.addAttribute(StringConstants.TOTAL_STAFF_COUNT, userService.countAllStaffs());
    }

    @PostMapping(value = "/add")
    public String addOrganizationInfo(@ModelAttribute OrganizationDto organizationDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        OrganizationError organizationError = organizationValidation.saveOrEditValidation(organizationDto);
        if (!organizationError.isValid()) {
            logger.debug("Organization detail is invalid");
            modelMap.put(StringConstants.ERROR, organizationError);
            modelMap.put(StringConstants.ORGANIZATION, organizationDto);
            modelMap.put(StringConstants.NEW_ORGANIZATION, true);
            return "organization/edit";
        }

        organizationService.save(organizationDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Organization Info added successfully");
        logger.info("Organization Info added successfully");

        return "redirect:/organization/display";
    }

    @GetMapping(value = "/display")
    public String displayOrganizationInfo(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        organizationCountForCards(modelMap);
        modelMap.put(StringConstants.ORGANIZATION, organizationService.getOrganizationDetail());
        modelMap.put(StringConstants.ROOM_SCHEDULE_LIST, roomScheduleService.list(RoomType.LECTURE_ROOM));
        modelMap.put(StringConstants.LAB_ROOM_SCHEDULE_LIST, roomScheduleService.list(RoomType.LAB_ROOM));
        logger.info("GET:/organization/display");
        return "organization/display";
    }

    @GetMapping(value = "/edit")
    public String editOrganizationInfo(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        organizationCountForCards(modelMap);
        OrganizationDto organizationDto = organizationService.getOrganizationDetail();
        if (organizationDto == null) {
            modelMap.put(StringConstants.NEW_ORGANIZATION, true);
        } else {
            modelMap.put(StringConstants.NEW_ORGANIZATION, false);
            modelMap.put(StringConstants.ORGANIZATION, organizationDto);
        }
        logger.info("GET:/organization/edit");
        return "organization/edit";
    }

    @PostMapping(value = "/edit")
    public String editOrganization(@ModelAttribute OrganizationDto organizationDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        if (organizationDto == null || organizationDto.getId() < 0) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Bad Request");
            return "redirect:/organization/display";
        }

        if (organizationService.getById(organizationDto.getId()) == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Organization Detail Not Found");
            return "redirect:/organization/display";
        }

        OrganizationError organizationError = organizationValidation.saveOrEditValidation(organizationDto);
        if (!organizationError.isValid()) {
            logger.debug("Organization detail is not valid");
            modelMap.put(StringConstants.ERROR, organizationError);
            modelMap.put(StringConstants.ORGANIZATION, organizationDto);
            return "organization/edit";
        }

        organizationService. update(organizationDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Organization detail edited successfully");
        logger.info("Organization detail edited successfully");

        return "redirect:/organization/display";
    }

}
