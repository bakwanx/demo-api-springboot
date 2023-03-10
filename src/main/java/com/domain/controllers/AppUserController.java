package com.domain.controllers;

import com.domain.dto.AppUserData;
import com.domain.dto.ResponseData;
import com.domain.models.entities.AppUser;
import com.domain.services.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData userData) {
        ResponseData<AppUser> response = new ResponseData<>();
        AppUser appUser = modelMapper.map(userData, AppUser.class);
        try{
            response.setPayload(appUserService.registerAppUser(appUser));
            response.setStatus(true);
            response.getMessages().add("AppUser saved!");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.setStatus(false);
            response.getMessages().add("User with " + appUser.getEmail() + " already exists");
            return ResponseEntity.ok(response);
        }

    }
}
