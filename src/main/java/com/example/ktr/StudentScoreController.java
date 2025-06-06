package com.example.ktr;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/scores")
public class StudentScoreController {

    @Autowired
    private StudentScoreService studentScoreService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("studentScore", new StudentScore());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "scores/add";
    }

    @PostMapping("/add")
    public String addScore(@Valid @ModelAttribute StudentScore studentScore,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        if (studentScore.getStudent() != null && studentScore.getSubject() != null) {
            var existing = studentScoreService.findByStudentAndSubject(
                    studentScore.getStudent().getStudentId(),
                    studentScore.getSubject().getSubjectId());

            if (existing.isPresent()) {
                result.rejectValue("student", "error.score", "Score already exists for this student and subject");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("subjects", subjectService.getAllSubjects());
            return "scores/add";
        }

        studentScoreService.saveStudentScore(studentScore);
        redirectAttributes.addFlashAttribute("success", "Score added successfully!");
        return "redirect:/";
    }
}