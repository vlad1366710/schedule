package com.schedule.schedule.controller;

import com.schedule.schedule.exception.registrationException.RegistrationException;
import com.schedule.schedule.model.Users;
import com.schedule.schedule.service.UserService;
import com.schedule.schedule.service.password.PasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordValidator passwordValidator;

    @GetMapping("/admin/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new Users()); // Добавляем новый объект User в модель
        return "login"; // имя HTML-шаблона
    }

    @PostMapping("/admin/login")
    public String login(Users user, RedirectAttributes redirectAttributes) {
        Users authenticatedUser  = userService.authenticate(user.getUsername(), user.getPassword());
        if (authenticatedUser  != null && "ADMIN".equals(authenticatedUser .getRole())) {
            logger.info("Пользователь {} успешно вошел в систему.", user.getUsername());
            return "index"; // перенаправление на страницу администрирования
        } else {
            logger.warn("Неудачная попытка входа для пользователя {}.", user.getUsername());
            redirectAttributes.addFlashAttribute("error", "Неверные учетные данные или недостаточно прав");
            return "redirect:/admin/login"; // перенаправление на страницу входа с сообщением об ошибке
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Users user,
                           @RequestParam String confirmPassword,
                           RedirectAttributes redirectAttributes) {
        // Проверка существования пользователя
        if (userService.findByUsername(user.getUsername()) != null) {
            logger.error("Ошибка регистрации: пользователь с именем {} уже существует.", user.getUsername());
            throw new RegistrationException("Пользователь с таким именем уже существует!");
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            logger.error("Ошибка регистрации: пользователь с почтой {} уже существует.", user.getEmail());
            throw new RegistrationException("Пользователь с такой почтой уже существует!");
        }

        // Валидация пароля
        String password = user.getPassword();

        if (!password.equals(confirmPassword)) {
            logger.error("Ошибка регистрации: пароли не совпадают для пользователя {}.", user.getUsername());
            throw new RegistrationException("Пароли не совпадают");
        }

        if (!passwordValidator.isValid(password)) { // Использование PasswordValidator
            logger.warn("Ошибка регистрации: пароль не соответствует требованиям для пользователя {}.", user.getUsername());
            redirectAttributes.addFlashAttribute("error", "Пароль должен содержать минимум 8 символов, включая заглавные и строчные буквы.");
            return "redirect:/register"; // Перенаправление обратно на страницу регистрации
        }

        userService.registerUser (user);
        logger.info("Пользователь {} успешно зарегистрирован.", user.getUsername());
        redirectAttributes.addFlashAttribute("success", "Регистрация прошла успешно!");
        return "redirect:/login"; // Перенаправление на страницу входа после успешной регистрации
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users()); // Создаем новый объект пользователя
        return "register"; // Возвращаем имя HTML-шаблона для регистрации
    }
}
