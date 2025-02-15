package com.schedule.schedule;

// Импортируем класс RegistrationException для обработки ошибок регистрации
import com.schedule.schedule.exception.registrationException.RegistrationException;
// Импортируем классы для работы с HTTP статусами и ответами
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// Импортируем аннотации для обработки исключений в Spring
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Аннотация @ControllerAdvice указывает, что этот класс будет обрабатывать исключения глобально для всех контроллеров
@ControllerAdvice
public class CustomExceptionHandler {

    // Аннотация @ExceptionHandler указывает, что этот метод будет вызываться при возникновении RegistrationException
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<CustomError> handleRegistrationException(RegistrationException ex) {
        // Создаем объект CustomError с сообщением об ошибке и статусом HTTP 400 (Bad Request)
        CustomError error = new CustomError(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        // Возвращаем объект ResponseEntity с ошибкой и статусом HTTP 400
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
