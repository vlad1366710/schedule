package com.schedule.schedule.service;

import com.schedule.schedule.RoleConstants;
import com.schedule.schedule.model.Role;
import com.schedule.schedule.model.Users;
import com.schedule.schedule.repository.RoleRepository;
import com.schedule.schedule.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Используем PasswordEncoder

    public void registerUser (Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Шифруем пароль
        user.setEnabled(true); // Устанавливаем пользователя как активного
        user.setRole(roleRepository.findByName(RoleConstants.ROLE_ADMIN));
        userRepository.save(user); // Сохраняем пользователя в базе данных
    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users authenticate(String username, String password) {
        Users user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) { // Проверка пароля
            return user;
        }
        return null; // Неверные учетные данные
    }
}
