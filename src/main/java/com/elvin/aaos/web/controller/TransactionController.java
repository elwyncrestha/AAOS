package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.ResponseDto;
import com.elvin.aaos.core.model.dto.StudentProfileDto;
import com.elvin.aaos.core.model.dto.StudentTransactionDetailDto;
import com.elvin.aaos.core.model.dto.StudentTransactionDto;
import com.elvin.aaos.core.model.enums.MessageType;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.CourseService;
import com.elvin.aaos.core.service.StudentProfileService;
import com.elvin.aaos.core.service.TransactionService;
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

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionController {

    private final CourseService courseService;
    private final TransactionService transactionService;
    private final AuthorizationUtil authorizationUtil;
    private final StudentProfileService studentProfileService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public TransactionController(
            @Autowired CourseService courseService,
            @Autowired TransactionService transactionService,
            @Autowired AuthorizationUtil authorizationUtil,
            @Autowired StudentProfileService studentProfileService
    ) {
        this.courseService = courseService;
        this.transactionService = transactionService;
        this.authorizationUtil = authorizationUtil;
        this.studentProfileService = studentProfileService;
    }

    @GetMapping(value = "/add")
    public String getAddForm(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ADMISSION_STAFF)) {
            return "403";
        }

        modelMap.put(StringConstants.COURSE_LIST, courseService.list());
        return "transaction/add";
    }

    @PostMapping(value = "/add")
    public String addTransaction(@ModelAttribute StudentTransactionDto studentTransactionDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ADMISSION_STAFF)) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        transactionService.save(studentTransactionDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Student Transaction saved successfully");
        logger.info("Student Transaction saved successfully");
        return "redirect:/transaction/add";
    }

    @GetMapping(value = "/display")
    public String displayForm() {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.checkCurrentUserAuthority(UserType.ADMIN) &&
                !AuthenticationUtil.checkCurrentUserAuthority(UserType.ADMISSION_STAFF)) {
            return "403";
        }

        return "transaction/display";
    }

    @PostMapping(value = "/display/student/{id}")
    @ResponseBody
    public ResponseEntity<ResponseDto> displayTransactions(@PathVariable("id") long id) {
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

        StudentProfileDto studentProfileDto = studentProfileService.getById(id);
        List<StudentTransactionDetailDto> allTransactions = transactionService.listByStudentProfileId(id);

        responseDto.setObject(new HashMap<String, Object>(){{
            put("StudentDetail", studentProfileDto);
            put("Transactions", allTransactions);
        }});
        responseDto.setMessage("Returned transaction list for student " + studentProfileDto.getFullName());
        responseDto.setStatus("200");
        responseDto.setMessageType(MessageType.SUCCESS);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
