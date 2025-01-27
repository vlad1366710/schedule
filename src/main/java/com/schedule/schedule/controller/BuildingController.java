package com.schedule.schedule.controller;

import com.schedule.schedule.model.Building;
import com.schedule.schedule.model.Room;
import com.schedule.schedule.repository.BuildingRepository;
import com.schedule.schedule.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buildings")
public class BuildingController {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public String listBuildings(Model model) {
        model.addAttribute("buildings", buildingRepository.findAll());
        return "building/list"; // путь к шаблону
    }

    @GetMapping("/add")
    public String addBuildingForm(Model model) {
        model.addAttribute("building", new Building());
        return "building/add"; // путь к шаблону
    }

    @PostMapping("/add")
    public String addBuilding(@ModelAttribute Building building) {
        buildingRepository.save(building);
        return "redirect:/buildings";
    }

    @GetMapping("/{buildingId}/rooms/add")
    public String addRoomForm(@PathVariable Long buildingId, Model model) {
        Room room = new Room();
        room.setBuilding(buildingRepository.findById(buildingId).orElse(null));
        model.addAttribute("room", room);
        return "room/add"; // путь к шаблону
    }

    @PostMapping("/{buildingId}/rooms/add")
    public String addRoom(@PathVariable Long buildingId, @ModelAttribute Room room) {
        room.setBuilding(buildingRepository.findById(buildingId).orElse(null));
        roomRepository.save(room);
        return "redirect:/buildings";
    }

    // Другие методы для редактирования и удаления аудиторий
}
