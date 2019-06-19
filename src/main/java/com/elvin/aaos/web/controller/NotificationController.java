package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.NotificationDto;
import com.elvin.aaos.core.model.dto.ResponseDto;
import com.elvin.aaos.core.model.enums.MessageType;
import com.elvin.aaos.core.service.NotificationService;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import com.elvin.aaos.web.utility.auth.AuthorizationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/notification")
public class NotificationController {

    private final NotificationService notificationService;
    private final AuthorizationUtil authorizationUtil;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public NotificationController(
            @Autowired NotificationService notificationService,
            @Autowired AuthorizationUtil authorizationUtil
    ) {
        this.notificationService = notificationService;
        this.authorizationUtil = authorizationUtil;
    }

    @PostMapping(value = "/count")
    @ResponseBody
    public ResponseEntity<ResponseDto> countNotifications() {
        ResponseDto responseDto = new ResponseDto();
        if (AuthenticationUtil.currentUserIsNull()) {
            responseDto.setMessage("Unauthenticated User");
            responseDto.setMessageType(MessageType.ERROR);
            responseDto.setStatus("401");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }

        responseDto.setMessageType(MessageType.SUCCESS);
        responseDto.setStatus("200");
        responseDto.setMessage("Responded with notifications count");
        responseDto.setObject(notificationService.count(authorizationUtil.getUser().getId()));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping(value = "/pageable/{count}")
    @ResponseBody
    public ResponseEntity<ResponseDto> getNotificationForTopBar(@PathVariable("count") long count) {
        ResponseDto responseDto = new ResponseDto();
        if (AuthenticationUtil.currentUserIsNull()) {
            responseDto.setMessage("Unauthenticated User");
            responseDto.setMessageType(MessageType.ERROR);
            responseDto.setStatus("401");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }

        List<NotificationDto> notificationDtoList = notificationService.getNotificationsByUserId(authorizationUtil.getUser().getId(), count);
        responseDto.setMessageType(MessageType.SUCCESS);
        responseDto.setStatus("200");
        responseDto.setMessage("Responded with notifications");
        responseDto.setObject(notificationDtoList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping(value = "/read/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto> markNotification(@PathVariable("id") long id) {
        ResponseDto responseDto = new ResponseDto();
        if (AuthenticationUtil.currentUserIsNull()) {
            responseDto.setMessage("Unauthenticated User");
            responseDto.setMessageType(MessageType.ERROR);
            responseDto.setStatus("401");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }

        NotificationDto notificationDto = notificationService.getById(id);
        if (notificationDto.getUser().getId() != authorizationUtil.getUser().getId()) {
            responseDto.setMessage("Forbidden");
            responseDto.setMessageType(MessageType.ERROR);
            responseDto.setStatus("403");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }

        notificationService.markAsRead(id, authorizationUtil.getUser());
        responseDto.setMessageType(MessageType.SUCCESS);
        responseDto.setStatus("200");
        responseDto.setMessage("Notification read");
        responseDto.setObject(null);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public String displayAll(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        List<NotificationDto> notificationDtoList = notificationService.getAll(authorizationUtil.getUser().getId());
        modelMap.put(StringConstants.NOTIFICATION_LIST, notificationDtoList);
        logger.info("GET:/notification/all");
        return "notification/display";
    }

    @GetMapping(value = "/read/{id}")
    public String markNotificationAsRead(@PathVariable("id") long id) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        NotificationDto notificationDto = notificationService.getById(id);
        if (notificationDto.getUser().getId() != authorizationUtil.getUser().getId()) {
            return "403";
        }

        notificationService.markAsRead(id, authorizationUtil.getUser());
        logger.info("GET:/notification/read/{id}");
        return "redirect:/notification/all";
    }

}
