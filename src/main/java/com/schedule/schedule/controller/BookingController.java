package com.schedule.schedule.controller;

import com.schedule.schedule.model.Booking;
import com.schedule.schedule.model.Building;
import com.schedule.schedule.model.ClassTime;
import com.schedule.schedule.model.Room;
import com.schedule.schedule.repository.*;
import com.schedule.schedule.service.BookingService;
import com.schedule.schedule.service.ClassTimeService;
import com.schedule.schedule.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ClassTimeService classTimeService;

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ClassTimeRepository classTimeRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping("/booking")
    public String showBookingForm(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        List<ClassTime> classTimes = classTimeService.getAllClassTimes();
        model.addAttribute("buildings", buildingRepository.findAll());
        model.addAttribute("rooms", rooms);
        model.addAttribute("classTimes", classTimes);
        model.addAttribute("booking", new Booking());
        model.addAttribute("groups", studentGroupRepository.findAll());
        return "booking/form";
    }

    @PostMapping("/booking")
    public String saveBooking(@Valid Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Если есть ошибки валидации, возвращаем пользователя на форму с сообщениями об ошибках
            List<Room> rooms = roomService.getAllRooms();
            model.addAttribute("rooms", roomRepository.findAll());
            model.addAttribute("groups", studentGroupRepository.findAll());
            model.addAttribute("classTimes", classTimeRepository.findAll());
            return "booking/form";
        }

        // Проверка доступности аудитории
       /* if (!bookingService.isRoomAvailable(booking.getBuilding(),booking.getClassTime().getStartTime(), booking.getClassTime().getEndTime())) {
            model.addAttribute("errorMessage", "Аудитория уже занята в это время.");
            List<Room> rooms = roomService.getAllRooms();
            model.addAttribute("rooms", rooms);
            return "booking/form";
        }

        */

        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings")
    public String showAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "booking/list";
    }

    @GetMapping("/booking/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        List<Room> rooms = roomService.getAllRooms();
        List<ClassTime> classTimes = classTimeService.getAllClassTimes();
        model.addAttribute("booking", booking);
        model.addAttribute("rooms", rooms);
        model.addAttribute("classTimes", classTimes);
        return "booking/form";
    }

    @PostMapping("/booking/update/{id}")
    public String updateBooking(@PathVariable Long id, @Valid Booking booking, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Room> rooms = roomService.getAllRooms();
            List<ClassTime> classTimes = classTimeService.getAllClassTimes();
            model.addAttribute("rooms", rooms);
            model.addAttribute("classTimes", classTimes);
            return "booking/form";
        }

        booking.setId(id);
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/booking/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }


}
