package com.madeline.sponsorshipsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

    @GetMapping("/")
    public String homepage() {
        return "forms";
    }
}
