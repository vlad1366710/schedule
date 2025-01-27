package com.schedule.schedule.controller;

import com.schedule.schedule.model.Booking;
import com.schedule.schedule.model.ClassTime;
import com.schedule.schedule.model.Room;
import com.schedule.schedule.service.BookingService;
import com.schedule.schedule.service.ClassTimeService;
import com.schedule.schedule.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ClassTimeService classTimeService;

    @GetMapping("/booking")
    public String showBookingForm(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        List<ClassTime> classTimes = classTimeService.getAllClassTimes();
        model.addAttribute("rooms", rooms);
        model.addAttribute("classTimes", classTimes);
        model.addAttribute("booking", new Booking());
        return "booking/form";
    }

    @PostMapping("/booking")
    public String saveBooking(Booking booking) {
        bookingService.saveBooking(booking);
        return "redirect:/bookings"; // Перенаправление на страницу со списком бронирований
    }
}
