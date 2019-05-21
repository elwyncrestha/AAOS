package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.model.dto.BuildingDto;
import com.elvin.aaos.core.service.BuildingService;
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
@RequestMapping(value = "/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private AuthorizationUtil authorizationUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private void buildingPageCount(ModelMap modelMap) {
        modelMap.put(StringConstants.BUILDING_COUNT, buildingService.countAll());
    }

    @GetMapping(value = "/add")
    public String getAddPage(ModelMap modelMap) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        buildingPageCount(modelMap);
        logger.info("GET:/building/add");
        return "building/add";
    }

    @PostMapping(value = "/add")
    public String addBuilding(@ModelAttribute BuildingDto buildingDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            logger.error("/building/add has binding error");
        }

        buildingService.save(buildingDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Building Info added successfully");
        logger.info("Building Info added successfully");

        return "redirect:/building/display";
    }

    @GetMapping(value = "/display")
    public String displayBuildings(ModelMap modelMap) {
        if (AuthenticationUtil.currentUserIsNull()) {
            return "redirect:/";
        }

        buildingPageCount(modelMap);
        modelMap.put(StringConstants.BUILDING_LIST, buildingService.list());
        return "building/display";
    }

    @GetMapping(value = "/display/{id}")
    public String displayBuildingInfo(@PathVariable("id") long buildingId, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        BuildingDto buildingDto = buildingService.getById(buildingId);
        if (buildingDto == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Building Not Found");
            return "redirect:/building/display";
        }

        buildingPageCount(modelMap);
        modelMap.put(StringConstants.BUILDING, buildingDto);
        logger.info("GET:/building/display/{id}");
        return "building/displayBuilding";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteBuilding(@PathVariable("id") long buildingId, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        BuildingDto buildingDto = buildingService.getById(buildingId);
        if (buildingDto == null) {
            logger.debug("Building Not Found");
            redirectAttributes.addFlashAttribute("Building Not Found");
            return "redirect:/building/display";
        }

        buildingService.delete(buildingId);
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Building Deleted Successfully");
        logger.info("Building Deleted Successfully");
        return "redirect:/building/display";
    }

    @GetMapping(value = "/edit/{id}")
    public String displayEdit(@PathVariable("id") long buildingId, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        BuildingDto buildingDto = buildingService.getById(buildingId);
        if (buildingDto == null) {
            logger.debug("Building Not Found");
            redirectAttributes.addFlashAttribute("Building Not Found");
            return "redirect:/building/display";
        }

        buildingPageCount(modelMap);
        modelMap.put(StringConstants.BUILDING, buildingDto);
        logger.info("GET:/building/edit/{id}");
        return "building/edit";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute BuildingDto buildingDto, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        if (bindingResult.hasErrors()) {
            logger.error("/building/edit has binding error");
        }

        if (buildingDto == null || buildingDto.getId() < 0) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Bad Request");
            return "redirect:/building/display";
        }

        if (buildingService.getById(buildingDto.getId()) == null) {
            redirectAttributes.addFlashAttribute(StringConstants.FLASH_ERROR_MESSAGE, "Building Not Found");
            return "redirect:/building/display";
        }

        buildingService.update(buildingDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Building edited successfully");
        logger.info("Building edited successfully");

        return "redirect:/building/display";
    }

}
