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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        if (AuthenticationUtil.isAdmin()) {
            buildingPageCount(modelMap);
            return "building/add";
        } else {
            return "403";
        }
    }

    @PostMapping(value = "/add")
    public String addBuilding(@ModelAttribute BuildingDto buildingDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!AuthenticationUtil.isAdmin()) {
            return "403";
        }

        buildingService.save(buildingDto, authorizationUtil.getUser());
        redirectAttributes.addFlashAttribute(StringConstants.FLASH_MESSAGE, "Building Info added successfully");
        logger.info("Building Info added successfully");

        return "redirect:/building/display";
    }

}
