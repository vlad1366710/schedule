package com.schedule.schedule.controller;

import com.schedule.schedule.model.Subject;
import com.schedule.schedule.repository.SubjectRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@Controller
@RequestMapping("/subjects")
@Tag(name = "Subjects", description = "Operations related to subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @GetMapping
    @Operation(summary = "Получить список предметов", description = "Возвращает список предметов с пагинацией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен список предметов"),
            @ApiResponse(responseCode = "404", description = "Предметы не найдены")
    })
    public String listSubjects(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sort,
                               Model model) {
        logger.info("Listing subjects - Page: {}, Size: {}, Sort: {}", page, size, sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Subject> subjectPage = subjectRepository.findAll(pageable);

        model.addAttribute("subjects", subjectPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", subjectPage.getTotalPages());
        model.addAttribute("sort", sort);

        return "subject/list";
    }

    @GetMapping("/add")
    @Operation(summary = "Отобразить форму для добавления нового предмета")
    public String addSubjectForm(Model model) {
        logger.info("Displaying form to add a new subject");
        model.addAttribute("subject", new Subject());
        return "subject/add"; // путь к шаблону
    }

    @PostMapping("/add")
    @Operation(summary = "Добавить новый предмет")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "201", description  = "Предмет успешно добавлен"),
            @ApiResponse(responseCode  = "400", description  = "Некорректные данные")
    })
    public String addSubject(@ModelAttribute Subject subject) {
        logger.info("Adding new subject: {}", subject.getName());
        subjectRepository.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/edit/{id}")
    @Operation(summary = "Отобразить форму для редактирования предмета")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description  = "Форма редактирования предмета"),
            @ApiResponse(responseCode  = "404", description  = "Предмет не найден")
    })
    public String editSubjectForm(@PathVariable Long id, Model model) {
        logger.info("Editing subject with ID: {}", id);
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Недействительный ID: " + id));
        model.addAttribute("subject", subject);
        return "subject/edit"; // путь к шаблону редактирования
    }

    @PostMapping("/edit/{id}")
    @Operation(summary = "Обновить предмет")
    @ApiResponses(value = {
            @ApiResponse(responseCode  =  "200", description  = "Предмет успешно обновлен"),
            @ApiResponse(responseCode  =  "400", description  = "Предмет не найден")
    })
    public String editSubject(@PathVariable Long id, @ModelAttribute Subject subject) {
        logger.info("Updating subject with ID: {}", id);
        subject.setId(id); // Устанавливаем ID для обновления
        subjectRepository.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Удалить предмет по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode  =  "200", description  = "Предмет успешно удален"),
            @ApiResponse(responseCode  = "404", description  = "Предмет не найден")
    })
    public String deleteSubject(@PathVariable Long id) {
        logger.info("Deleting subject with ID: {}", id);
        subjectRepository.deleteById(id);
        return "redirect:/subjects";
    }

    @PostMapping("/upload")
    @Operation(summary = "Загрузить список предметов из Excel файла")
    @ApiResponses(value = {
            @ApiResponse(responseCode  =  "200", description  = "Файл успешно загружен"),
            @ApiResponse(responseCode  = "400", description  = "Некорректный файл")
    })
    public String uploadSubjects(@RequestParam("file") MultipartFile file) throws IOException {
        logger.info("Uploading subjects from file: {}", file.getOriginalFilename());
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            Subject subject = new Subject();
            subject.setName(row.getCell(0).getStringCellValue());
            subjectRepository.save(subject);
        }
        workbook.close();
        return "redirect:/subjects";
    }
}
