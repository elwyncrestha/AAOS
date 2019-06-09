package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.BatchDto;
import com.elvin.aaos.core.service.BatchService;
import com.elvin.aaos.core.validation.BatchValidation;
import com.elvin.aaos.web.error.BatchError;
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
@RequestMapping(value = "/batch")
public class BatchController {

    private final BatchService batchService;
    private final BatchValidation batchValidation;
    private final AuthorizationUtil authorizationUtil;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public BatchController(
            @Autowired BatchService batchService,
            @Autowired BatchValidation batchValidation,
            @Autowired AuthorizationUtil authorizationUtil
    ) {
        this.batchService = batchService;
        this.batchValidation = batchValidation;
        this.authorizationUtil = authorizationUtil;
    }

    private void batchCards(ModelMap modelMap) {
        modelMap.put(StringConstants.BATCH_COUNT, batchService.countTotalBatch());
    }

    @GetMapping(value = "/add")
    public String addBatchForm(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        batchCards(modelMap);
        logger.info("GET:/batch/add");
        return "batch/add";
    }

    @PostMapping(value = "/add")
    public String addBatch(@ModelAttribute BatchDto batchDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        BatchError batchError = batchValidation.saveValidation(batchDto);
        if (!batchError.isValid()) {
            logger.debug("Batch is not valid");
            modelMap.put(StringConstants.ERROR, batchError);
            modelMap.put(StringConstants.BATCH, batchDto);
            return "batch/add";
        }

        batchService.save(batchDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Batch added successfully");
        logger.info("Batch added successfully");

        return "redirect:/batch/display";
    }

}
