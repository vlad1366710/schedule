package com.schedule.schedule.service;

import com.schedule.schedule.model.Booking;
import com.schedule.schedule.model.ClassTime;
import com.schedule.schedule.model.Room;
import com.schedule.schedule.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    public void saveBooking(Booking booking) {
        // Получение временных интервалов
        List<ClassTime> classTimes = classTimeService.getAllClassTimes();
        /**
        // Проверка, попадает ли время бронирования в доступные интервалы
        boolean isTimeValid = classTimes.stream().anyMatch(classTime ->
                booking.getClassTime().getStartTime().isEqual(LocalDateTime.parse(classTime.getStartTime())) &&
                        booking.getClassTime().getEndTime().isEqual(LocalDateTime.parse(classTime.getEndTime()))
        );

        if (!isTimeValid) {
            throw new IllegalArgumentException("Выберите допустимое время для бронирования.");
        }
         **/
/**
        // Проверка на наличие пересечения по времени
        List<Booking> existingBookings = bookingRepository.findByRoomAndStartTimeBetween(
                booking.getRoom(),
                booking.getClassTime().getStartTime(),
                booking.getClassTime().getEndTime()
        );


        if (!existingBookings.isEmpty()) {
            throw new IllegalArgumentException("Это время уже занято.");
        }
 **/

        // Сохранение нового бронирования
        bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Бронирование не найдено"));
    }


    // Проверка доступности аудитории
    public boolean isRoomAvailable(Room room, String startTime, String endTime)
    {
        return bookingRepository.findByRoomAndClassTime_StartTimeBetween(room, startTime, endTime).isEmpty();
    }


    public void deleteBooking(Long id) {
        logger.info("Удаление бронирования с ID: {}", id);
        bookingRepository.deleteById(id);
    }
}
