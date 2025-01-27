package com.schedule.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String index() {
        return "index"; // Возвращает имя шаблона index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Возвращает имя шаблона login.html (страница входа)
    }
}
