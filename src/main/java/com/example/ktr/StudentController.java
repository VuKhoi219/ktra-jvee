package com.example.ktr;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";
    }

    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute Student student,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {

        if (studentService.existsByStudentCode(student.getStudentCode())) {
            result.rejectValue("studentCode", "error.student", "Student code already exists");
        }

        if (result.hasErrors()) {
            return "students/add";
        }

        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("success", "Student added successfully!");
        return "redirect:/";
    }
}