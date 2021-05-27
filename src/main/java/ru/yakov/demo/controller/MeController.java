package ru.yakov.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/about")
public class MeController {
    @GetMapping("/who")
    public String showWho() {
        return "about/who";
    }

    @GetMapping("/i")
    public String showI() {
        return "about/i";
    }
}
