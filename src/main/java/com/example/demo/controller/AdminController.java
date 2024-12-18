package com.example.demo.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin")
//@PostAuthorize("hashIpAddress("127.0.0.1")")
public class AdminController {


    @GetMapping("/vip")
    public String zoneVip() {
        return "zoneVip";
    }

    @GetMapping("/normal")
    public String zoneNormal() {
        return "zoneNormal";
    }
}
