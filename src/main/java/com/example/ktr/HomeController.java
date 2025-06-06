package com.example.ktr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private StudentScoreService studentScoreService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("studentScores", studentScoreService.getAllStudentScores());
        return "home";
    }
}
