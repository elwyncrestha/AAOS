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
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/display")
    public String displayBatch(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        batchCards(modelMap);
        List<BatchDto> batchDtoList = batchService.list();
        modelMap.put(StringConstants.BATCH_LIST, batchDtoList);
        logger.info("GET:/batch/display");

        return "batch/display";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBatch(@PathVariable("id") long batchId, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        BatchDto batchDto = batchService.getBatch(batchId);
        if (batchDto == null) {
            logger.debug("Batch Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Batch Not Found");
        }

        batchService.delete(batchId, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Batch Deleted Successfully");
        logger.info("Batch Deleted Successfully");
        return "redirect:/batch/display";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEditForm(@PathVariable("id") long id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        BatchDto batchDto = batchService.getBatch(id);
        if (batchDto == null) {
            logger.debug("Batch Not Found");
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Batch Not Found");
        }

        modelMap.put(StringConstants.BATCH, batchDto);
        logger.info("GET:/batch/edit");
        return "batch/edit";
    }

    @PostMapping(value = "/edit")
    public String editBatch(@ModelAttribute BatchDto batchDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        } else if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            objectErrors.forEach(objectError -> logger.warn(objectError.getDefaultMessage()));
        }

        if (batchDto == null || batchDto.getId() < 0) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Bad Request");
            logger.debug("Bad Request");
            return "redirect:/batch/display";
        }

        if (batchService.getBatch(batchDto.getId()) == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Batch Not Found");
            logger.debug("Batch Not Found");
            return "redirect:/batch/display";
        }

        BatchError batchError = batchValidation.updateValidation(batchDto);
        if (!batchError.isValid()) {
            logger.debug("Batch is not valid");
            modelMap.put(StringConstants.ERROR, batchError);
            modelMap.put(StringConstants.BATCH, batchDto);
            return "batch/edit";
        }

        batchService.update(batchDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Batch edited successfully");
        logger.info("Batch edited successfully");

        return "redirect:/batch/display";
    }

}
