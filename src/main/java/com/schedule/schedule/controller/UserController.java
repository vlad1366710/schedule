package com.schedule.schedule.controller;

import com.schedule.schedule.model.Users;
import com.schedule.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new Users()); // Добавляем новый объект User в модель
        return "login"; // имя HTML-шаблона
    }

    @PostMapping("/admin/login")
    public String login(Users user, RedirectAttributes redirectAttributes) {
        Users authenticatedUser   = userService.authenticate(user.getUsername(), user.getPassword());
        if (authenticatedUser  != null && "ADMIN".equals(authenticatedUser .getRole())) {
            return "index"; // перенаправление на страницу администрирования
        } else {
            redirectAttributes.addFlashAttribute("error", "Неверные учетные данные или недостаточно прав");
            return "redirect:/admin/login"; // перенаправление на страницу входа с сообщением об ошибке
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Users user, RedirectAttributes redirectAttributes) {
        if (userService.findByUsername(user.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("error", "Пользователь с таким именем уже существует!");
            return "redirect:/register"; // Перенаправление обратно на страницу регистрации
        }
        userService.registerUser (user);
        redirectAttributes.addFlashAttribute("success", "Регистрация прошла успешно!");
        return "redirect:/login"; // Перенаправление на страницу входа после успешной регистрации
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users()); // Создаем новый объект пользователя
        return "register"; // Возвращаем имя HTML-шаблона для регистрации
    }


}
