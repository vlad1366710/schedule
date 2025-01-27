package com.schedule.schedule.controller;

import com.schedule.schedule.model.Subject;
import com.schedule.schedule.repository.SubjectRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectRepository.findAll());
        return "subject/list"; // путь к шаблону
    }

    @GetMapping("/add")
    public String addSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subject/add"; // путь к шаблону
    }

    @PostMapping("/add")
    public String addSubject(@ModelAttribute Subject subject) {
        subjectRepository.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/edit/{id}")
    public String editSubjectForm(@PathVariable Long id, Model model) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Недействительный ID: " + id));
        model.addAttribute("subject", subject);
        return "subject/edit"; // путь к шаблону редактирования
    }

    @PostMapping("/edit/{id}")
    public String editSubject(@PathVariable Long id, @ModelAttribute Subject subject) {
        subject.setId(id); // Устанавливаем ID для обновления
        subjectRepository.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Недействительный ID: " + id));
        subjectRepository.delete(subject);
        return "redirect:/subjects";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/subjects?error"; // обработка ошибки
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // Получаем первый лист
            for (Row row : sheet) {
                Subject subject = new Subject();
                subject.setName(row.getCell(0).getStringCellValue()); // Предполагаем, что название предмета в первой колонке
                // Установите другие поля, если необходимо
                subjectRepository.save(subject); // Сохраняем предмет в базе данных
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/subjects?error"; // обработка ошибки
        }

        return "redirect:/subjects"; // перенаправление после успешной загрузки
    }
}