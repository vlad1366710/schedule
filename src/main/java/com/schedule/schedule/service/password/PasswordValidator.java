package com.schedule.schedule.service.password;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidator {

    public boolean isValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
            // Если оба условия выполнены, можно выйти из цикла
            if (hasUpperCase && hasLowerCase) {
                return true;
            }
        }
        return false; // Если не найдены оба типа букв
    }
}
