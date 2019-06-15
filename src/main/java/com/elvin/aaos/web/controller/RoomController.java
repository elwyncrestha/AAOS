package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.RoomBuildingDto;
import com.elvin.aaos.core.model.dto.RoomDto;
import com.elvin.aaos.core.model.dto.RoomScheduleDetailDto;
import com.elvin.aaos.core.model.dto.RoomScheduleDto;
import com.elvin.aaos.core.model.enums.RoomType;
import com.elvin.aaos.core.service.*;
import com.elvin.aaos.core.validation.RoomScheduleValidation;
import com.elvin.aaos.core.validation.RoomValidation;
import com.elvin.aaos.web.error.RoomError;
import com.elvin.aaos.web.error.RoomScheduleError;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/room")
public class RoomController {

    private final RoomService roomService;
    private final BuildingService buildingService;
    private final RoomValidation roomValidation;
    private final AuthorizationUtil authorizationUtil;
    private final BatchService batchService;
    private final TeacherProfileService teacherProfileService;
    private final RoomScheduleValidation roomScheduleValidation;
    private final RoomScheduleService roomScheduleService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public RoomController(
            @Autowired RoomService roomService,
            @Autowired BuildingService buildingService,
            @Autowired RoomValidation roomValidation,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired BatchService batchService,
            @Autowired TeacherProfileService teacherProfileService,
            @Autowired RoomScheduleValidation roomScheduleValidation,
            @Autowired RoomScheduleService roomScheduleService
    ) {
        this.roomService = roomService;
        this.buildingService = buildingService;
        this.roomValidation = roomValidation;
        this.authorizationUtil = authorizationUtil;
        this.batchService = batchService;
        this.teacherProfileService = teacherProfileService;
        this.roomScheduleValidation = roomScheduleValidation;
        this.roomScheduleService = roomScheduleService;
    }

    private void roomCountForCards(ModelMap modelMap) {
        modelMap.put(StringConstants.LECTURE_ROOM_COUNT, roomService.countRoomsByRoomType(RoomType.LECTURE_ROOM));
        modelMap.put(StringConstants.LAB_ROOM_COUNT, roomService.countRoomsByRoomType(RoomType.LAB_ROOM));
    }

    @GetMapping(value = "/add")
    public String addRoomPage(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        roomCountForCards(modelMap);
        modelMap.put(StringConstants.BUILDING_LIST, buildingService.list());
        logger.info("GET:/room/add");
        return "room/add";
    }

    @PostMapping(value = "/add")
    public String addRoom(@ModelAttribute RoomDto roomDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
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
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        RoomBuildingDto roomBuildingDto = roomService.getById(roomId);
        if (roomBuildingDto == null) {
            logger.debug("Room Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Room Not Found");
            return "redirect:/room/display";
        }

        // remove associated room schedule first
        if (roomScheduleService.hasAssociatedRoom(roomId)) {
            logger.debug("Cannot delete room having associated room schedules");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Remove associated room schedules first");
            return "redirect:/room/display";
        }

        roomService.delete(roomId, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Room Deleted Successfully");
        logger.info("Room Deleted Successfully");
        return "redirect:/room/display";
    }

    @GetMapping(value = "/edit/{id}")
    public String displayEdit(@PathVariable("id") long roomId, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        RoomBuildingDto roomBuildingDto = roomService.getById(roomId);
        if (roomBuildingDto == null) {
            logger.debug("Room Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Room Not Found");
            return "redirect:/room/display";
        }

        roomCountForCards(modelMap);
        modelMap.put(StringConstants.BUILDING_LIST, buildingService.list());
        modelMap.put(StringConstants.ROOM, roomBuildingDto);
        logger.info("GET:/room/edit/{id}");
        return "room/edit";
    }

    @PostMapping(value = "/edit")
    public String editRoom(@ModelAttribute RoomDto roomDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
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

    @GetMapping(value = "/schedule/add")
    public String addRoomScheduleForm(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        roomCountForCards(modelMap);
        modelMap.put(StringConstants.ROOM_LIST, roomService.list());
        modelMap.put(StringConstants.BATCH_LIST, batchService.list());
        modelMap.put(StringConstants.TEACHER_PROFILE_LIST, teacherProfileService.list());
        logger.info("GET:/room/schedule/add");
        return "room/addSchedule";
    }

    @PostMapping(value = "/schedule/add")
    public String addRoomSchedule(@ModelAttribute RoomScheduleDto roomScheduleDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        RoomScheduleError roomScheduleError = roomScheduleValidation.saveOrUpdateValidation(roomScheduleDto);
        if (!roomScheduleError.isValid()) {
            logger.debug("room schedule is not valid");
            modelMap.put(StringConstants.ERROR, roomScheduleError);
            modelMap.put(StringConstants.ROOM_LIST, roomService.list());
            modelMap.put(StringConstants.BATCH_LIST, batchService.list());
            modelMap.put(StringConstants.TEACHER_PROFILE_LIST, teacherProfileService.list());
            modelMap.put(StringConstants.ROOM_SCHEDULE, roomScheduleDto);
            return "room/addSchedule";
        }

        roomScheduleService.save(roomScheduleDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Room Schedule added successfully");
        logger.info("Room Schedule added successfully");

        return "redirect:/room/schedule/display";
    }

    @GetMapping(value = "/schedule/display")
    public String displayRoomSchedule(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        roomCountForCards(modelMap);
        modelMap.put(StringConstants.ROOM_SCHEDULE_LIST, roomScheduleService.list());
        return "room/displaySchedules";
    }

    @GetMapping(value = "/schedule/delete/{id}")
    public String deleteRoomSchedule(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        RoomScheduleDetailDto roomScheduleDetailDto = roomScheduleService.getById(id);
        if (roomScheduleDetailDto == null) {
            logger.debug("Room Schedule Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Room Schedule Not Found");
            return "redirect:/room/schedule/display";
        }

        roomScheduleService.delete(id, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Room Schedule Deleted Successfully");
        logger.info("Room Schedule Deleted Successfully");
        return "redirect:/room/schedule/display";
    }

    @GetMapping(value = "/schedule/edit/{id}")
    public String displayEditSchedule(@PathVariable("id") long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        RoomScheduleDetailDto roomScheduleDetailDto = roomScheduleService.getById(id);
        if (roomScheduleDetailDto == null) {
            logger.debug("Room Schedule Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Room Schedule Not Found");
            return "redirect:/room/schedule/display";
        }

        roomCountForCards(modelMap);
        modelMap.put(StringConstants.ROOM_LIST, roomService.list());
        modelMap.put(StringConstants.BATCH_LIST, batchService.list());
        modelMap.put(StringConstants.TEACHER_PROFILE_LIST, teacherProfileService.list());
        RoomScheduleDto roomScheduleDto = new RoomScheduleDto(
                roomScheduleDetailDto.getDayOfWeek(),
                roomScheduleDetailDto.getStartTime().toString(),
                roomScheduleDetailDto.getEndTime().toString(),
                roomScheduleDetailDto.getRoom().getId(),
                roomScheduleDetailDto.getBatch().getId(),
                roomScheduleDetailDto.getTeacherProfile().getId(),
                roomScheduleDetailDto.getName(),
                roomScheduleDetailDto.getStatus()
        );
        roomScheduleDto.setId(roomScheduleDetailDto.getId());
        roomScheduleDto.setVersion(roomScheduleDetailDto.getVersion());
        modelMap.put(StringConstants.ROOM_SCHEDULE, roomScheduleDto);
        logger.info("GET:/room/schedule/edit/{id}");
        return "room/editSchedule";
    }

    @PostMapping(value = "/schedule/edit")
    public String editRoomSchedule(@ModelAttribute RoomScheduleDto roomScheduleDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        RoomScheduleError roomScheduleError = roomScheduleValidation.saveOrUpdateValidation(roomScheduleDto);
        if (!roomScheduleError.isValid()) {
            logger.debug("room schedule is not valid");
            modelMap.put(StringConstants.ERROR, roomScheduleError);
            modelMap.put(StringConstants.ROOM_LIST, roomService.list());
            modelMap.put(StringConstants.BATCH_LIST, batchService.list());
            modelMap.put(StringConstants.TEACHER_PROFILE_LIST, teacherProfileService.list());
            modelMap.put(StringConstants.ROOM_SCHEDULE, roomScheduleDto);
            return "room/editSchedule";
        }

        roomScheduleService.save(roomScheduleDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Room Schedule updated successfully");
        logger.info("Room Schedule updated successfully");

        return "redirect:/room/schedule/display";
    }

}
