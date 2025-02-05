package com.schedule.schedule.controller;

import com.schedule.schedule.model.Schedule;
import com.schedule.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public String getAllSchedules(Model model) {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        model.addAttribute("schedules", schedules);
        return "schedules"; // Имя HTML-шаблона
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        return "create_schedule"; // Имя HTML-шаблона для создания расписания
    }

    @PostMapping
    public String createSchedule(@ModelAttribute Schedule schedule) {
        scheduleService.createSchedule(schedule);
        return "redirect:/schedules"; // Перенаправление на страницу расписаний
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.getScheduleById(id).orElseThrow();
        model.addAttribute("schedule", schedule);
        return "edit_schedule"; // Имя HTML-шаблона для редактирования расписания
    }

    @PostMapping("/edit/{id}")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute Schedule schedule) {
        scheduleService.updateSchedule(id, schedule);
        return "redirect:/schedules"; // Перенаправление на страницу расписаний
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/schedules"; // Перенаправление на страницу расписаний
    }
}
