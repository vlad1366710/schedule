package com.schedule.schedule.service;

import com.schedule.schedule.model.Booking;
import com.schedule.schedule.model.ClassTime;
import com.schedule.schedule.model.Room;
import com.schedule.schedule.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ClassTimeService classTimeService; // Сервис для работы с ClassTime

    public void saveBooking(Booking booking) {
        // Получение временных интервалов
        List<ClassTime> classTimes = classTimeService.getAllClassTimes();

        // Проверка, попадает ли время бронирования в доступные интервалы
        boolean isTimeValid = classTimes.stream().anyMatch(classTime ->
                booking.getStartTime().isEqual(LocalDateTime.parse(classTime.getStartTime())) &&
                        booking.getEndTime().isEqual(LocalDateTime.parse(classTime.getEndTime()))
        );

        if (!isTimeValid) {
            throw new IllegalArgumentException("Выберите допустимое время для бронирования.");
        }

        // Проверка на наличие пересечения по времени
        List<Booking> existingBookings = bookingRepository.findByRoomAndStartTimeBetween(
                booking.getRoom(),
                booking.getStartTime(),
                booking.getEndTime()
        );

        if (!existingBookings.isEmpty()) {
            throw new IllegalArgumentException("Это время уже занято.");
        }

        bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
