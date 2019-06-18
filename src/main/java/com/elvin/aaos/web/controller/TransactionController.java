package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.StudentTransactionDto;
import com.elvin.aaos.core.model.enums.UserType;
import com.elvin.aaos.core.service.CourseService;
import com.elvin.aaos.core.service.TransactionService;
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
@RequestMapping(value = "/transaction")
public class TransactionController {

    private final CourseService courseService;
    private final TransactionService transactionService;
    private final AuthorizationUtil authorizationUtil;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public TransactionController(
            @Autowired CourseService courseService,
            @Autowired TransactionService transactionService,
            @Autowired AuthorizationUtil authorizationUtil
    ) {
        this.courseService = courseService;
        this.transactionService = transactionService;
        this.authorizationUtil = authorizationUtil;
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

}
