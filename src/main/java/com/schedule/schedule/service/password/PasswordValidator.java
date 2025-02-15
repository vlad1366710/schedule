package com.schedule.schedule.service.password;

import com.schedule.schedule.exception.registrationException.RegistrationException;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidator {

    public boolean isValid(String password) {
        if (password.length() < 8) {
            throw new RegistrationException("Пароль должен содержать минимум 8 символов");
        }

        if (!password.matches("[a-zA-Z]+")) {
            throw new RegistrationException("Пароль должен содержать только английские буквы");
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
        throw new RegistrationException("Пароль должен содержать заглавные и строчные символы");
    }
}
