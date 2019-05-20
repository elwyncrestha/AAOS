package com.elvin.aaos.web.controller;

import com.elvin.aaos.core.service.BuildingService;
import com.elvin.aaos.web.utility.StringConstants;
import com.elvin.aaos.web.utility.auth.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

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

}
