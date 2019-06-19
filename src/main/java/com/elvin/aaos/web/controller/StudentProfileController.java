package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.*;
import com.elvin.aaos.core.model.enums.MessageType;
import com.elvin.aaos.core.model.enums.Status;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.BatchService;
import com.elvin.aaos.core.service.NotificationService;
import com.elvin.aaos.core.service.StudentProfileService;
import com.elvin.aaos.core.service.UserService;
import com.elvin.aaos.core.validation.StudentProfileValidation;
import com.elvin.aaos.web.error.StudentProfileError;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentProfileController {

    private final UserService userService;
    private final AuthorizationUtil authorizationUtil;
    private final StudentProfileService studentProfileService;
    private final StudentProfileValidation studentProfileValidation;
    private final BatchService batchService;
    private final NotificationService notificationService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public StudentProfileController(
            @Autowired UserService userService,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired StudentProfileService studentProfileService,
            @Autowired StudentProfileValidation studentProfileValidation,
            @Autowired BatchService batchService,
            @Autowired NotificationService notificationService
    ) {
        this.userService = userService;
        this.authorizationUtil = authorizationUtil;
        this.studentProfileService = studentProfileService;
        this.studentProfileValidation = studentProfileValidation;
        this.batchService = batchService;
        this.notificationService = notificationService;
    }

    @GetMapping(value = "/profile")
    public String displayStudentProfile(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.STUDENT)) {
            return "403";
        }

        modelMap.put(StringConstants.STUDENT, studentProfileService.getByUserId(userDto.getId()));
        logger.info("GET:/student/profile");
        return "student/profile";
    }

    @GetMapping(value = "/edit")
    public String addStudentProfileForm(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (AuthenticationUtil.checkCurrentUserAuthority(UserType.STUDENT)) {
            StudentProfileDto studentProfileDto = studentProfileService.getByUserId(userDto.getId());
            if (studentProfileDto == null) {
                studentProfileDto = new StudentProfileDto();
                studentProfileDto.setUser(userDto);
                modelMap.put("newProfile", true);
            }
            // no matter what, set full name and email from userDto
            studentProfileDto.setFullName(userDto.getFullName());
            studentProfileDto.setEmail(userDto.getEmail());
            modelMap.put(StringConstants.STUDENT, studentProfileDto);
            logger.info("GET:/student/edit");
            return "student/edit";
        } else {
            return "403";
        }
    }

    @PostMapping(value = "/edit")
    public String addStudentProfile(@ModelAttribute StudentProfileDto studentProfileDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        if (AuthenticationUtil.checkCurrentUserAuthority(UserType.STUDENT)) {
            StudentProfileError studentProfileError = studentProfileValidation.saveOrEditValidation(studentProfileDto);
            if (!studentProfileError.isValid()) {
                logger.debug("Student Profile Detail is not valid");
                modelMap.put(StringConstants.ERROR, studentProfileError);
                modelMap.put(StringConstants.STUDENT, studentProfileDto);
                return "student/edit";
            }

            studentProfileService.save(studentProfileDto, authorizationUtil.getUser());
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Student Profile Updated Successfully");
            return "redirect:/student/profile";
        } else {
            return "403";
        }

    }

    @PostMapping(value = "/{profileId}/batch/{batchId}")
    @ResponseBody
    public ResponseEntity<ResponseDto> assignBatchToStudent(@PathVariable("profileId") long profileId, @PathVariable("batchId") long batchId) {
        ResponseDto responseDto = new ResponseDto();
        if (AuthenticationUtil.currentUserIsNull()) {
            responseDto.setMessage("Unauthenticated User");
            responseDto.setStatus("401");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        } else if (!AuthenticationUtil.isAdmin()) {
            responseDto.setMessage("You have no permission to access the URL");
            responseDto.setStatus("403");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }

        StudentProfileDto studentProfileDto = studentProfileService.getById(profileId);
        BatchDto batchDto = batchService.getBatch(batchId);

        if (studentProfileDto == null || batchDto == null) {
            responseDto.setMessage("Bad Request");
            responseDto.setStatus("400");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        studentProfileDto.setBatch(batchDto);
        studentProfileService.save(studentProfileDto, authorizationUtil.getUser());

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setUser(studentProfileDto.getUser());
        notificationDto.setStatus(Status.ACTIVE);
        notificationDto.setTitle(StringConstants.BATCH_NOTICE);
        notificationDto.setDescription("You are enrolled in batch: " + batchDto.getName());
        notificationDto.setBackground("bg-primary");
        notificationDto.setIcon("fa-users");
        notificationService.save(notificationDto, authorizationUtil.getUser());

        responseDto.setMessage("Successfully assigned " + studentProfileDto.getFullName() + " to Batch: " + batchDto.getName());
        responseDto.setStatus("200");
        responseDto.setObject(null);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping(value = "/{profileId}")
    @ResponseBody
    public ResponseEntity<ResponseDto> getStudent(@PathVariable("profileId") long profileId) {
        ResponseDto responseDto = new ResponseDto();
        if (AuthenticationUtil.currentUserIsNull()) {
            responseDto.setMessage("Unauthenticated User");
            responseDto.setStatus("401");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ADMISSION_STAFF)) {
            responseDto.setMessage("You have no permission to access the URL");
            responseDto.setStatus("403");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }

        StudentProfileDto studentProfileDto = studentProfileService.getById(profileId);

        if (studentProfileDto == null) {
            responseDto.setMessage("No student with given ID");
            responseDto.setStatus("400");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        responseDto.setMessage("Successfully retrieved student " + studentProfileDto.getFullName());
        responseDto.setStatus("200");
        responseDto.setMessageType(MessageType.SUCCESS);
        responseDto.setObject(studentProfileDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
