package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.ModuleDto;
import com.elvin.aaos.core.model.dto.ResponseDto;
import com.elvin.aaos.core.model.dto.TeacherProfileDto;
import com.elvin.aaos.core.model.dto.UserDto;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.ModuleService;
import com.elvin.aaos.core.service.TeacherProfileService;
import com.elvin.aaos.core.service.UserService;
import com.elvin.aaos.core.validation.TeacherProfileValidation;
import com.elvin.aaos.web.error.TeacherProfileError;
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
@RequestMapping(value = "/teacher")
public class TeacherProfileController {

    private final UserService userService;
    private final AuthorizationUtil authorizationUtil;
    private final TeacherProfileService teacherProfileService;
    private final TeacherProfileValidation teacherProfileValidation;
    private final ModuleService moduleService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public TeacherProfileController(
            @Autowired UserService userService,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired TeacherProfileService teacherProfileService,
            @Autowired TeacherProfileValidation teacherProfileValidation,
            @Autowired ModuleService moduleService
    ) {
        this.userService = userService;
        this.authorizationUtil = authorizationUtil;
        this.teacherProfileService = teacherProfileService;
        this.teacherProfileValidation = teacherProfileValidation;
        this.moduleService = moduleService;
    }

    @GetMapping(value = "/profile")
    public String displayTeacherProfile(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.TEACHER)) {
            return "403";
        }

        modelMap.put(StringConstants.TEACHER, teacherProfileService.getByUserId(userDto.getId()));
        logger.info("GET:/teacher/profile");
        return "teacher/profile";
    }

    @GetMapping(value = "/edit")
    public String addTeacherProfileForm(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        UserDto userDto = userService.getUser(authorizationUtil.getUser().getId());
        if (AuthenticationUtil.checkCurrentUserAuthority(UserType.TEACHER)) {
            TeacherProfileDto teacherProfileDto = teacherProfileService.getByUserId(userDto.getId());
            if (teacherProfileDto == null) {
                teacherProfileDto = new TeacherProfileDto();
                teacherProfileDto.setUser(userDto);
                modelMap.put("newProfile", true);
            }
            // no matter what, set full name and email from userDto
            teacherProfileDto.setFullName(userDto.getFullName());
            teacherProfileDto.setEmail(userDto.getEmail());
            modelMap.put(StringConstants.TEACHER, teacherProfileDto);
            logger.info("GET:/teacher/edit");
            return "teacher/edit";
        } else {
            return "403";
        }
    }

    @PostMapping(value = "/edit")
    public String addTeacherProfile(@ModelAttribute TeacherProfileDto teacherProfileDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        if (AuthenticationUtil.checkCurrentUserAuthority(UserType.TEACHER)) {
            TeacherProfileError teacherProfileError = teacherProfileValidation.saveOrEditValidation(teacherProfileDto);
            if (!teacherProfileError.isValid()) {
                logger.debug("Teacher Profile Detail is not valid");
                modelMap.put(StringConstants.ERROR, teacherProfileError);
                modelMap.put(StringConstants.TEACHER, teacherProfileDto);
                return "teacher/edit";
            }

            teacherProfileService.save(teacherProfileDto, authorizationUtil.getUser());
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Teacher Profile Updated Successfully");
            return "redirect:/teacher/profile";
        } else {
            return "403";
        }

    }

    @PostMapping(value = "/{profileId}/module/{moduleId}")
    @ResponseBody
    public ResponseEntity<ResponseDto> assignModuleToTeacher(@PathVariable("profileId") long profileId, @PathVariable("moduleId") long moduleId) {
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

        TeacherProfileDto teacherProfileDto = teacherProfileService.getById(profileId);
        ModuleDto moduleDto = moduleService.getModuleById(moduleId);

        if (teacherProfileDto == null || moduleDto == null) {
            responseDto.setMessage("Bad Request");
            responseDto.setStatus("400");
            responseDto.setObject(null);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        teacherProfileDto.setModule(moduleDto);
        teacherProfileService.save(teacherProfileDto, authorizationUtil.getUser());
        responseDto.setMessage("Successfully assigned " + teacherProfileDto.getFullName() + " to Module: " + moduleDto.getName());
        responseDto.setStatus("200");
        responseDto.setObject(null);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
