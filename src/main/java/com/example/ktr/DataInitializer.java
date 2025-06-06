package com.example.ktr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentScoreService studentScoreService;

    @Override
    public void run(String... args) throws Exception {
        // Initialize subjects if they don't exist
        if (subjectService.getAllSubjects().isEmpty()) {
            Subject java = new Subject("JAVA", "Java Programming", 4);
            Subject php = new Subject("PHP", "PHP Programming", 3);
            Subject wda = new Subject("WDA", "Web Development and Applications", 3);

            subjectService.saveSubject(java);
            subjectService.saveSubject(php);
            subjectService.saveSubject(wda);
        }

        // Initialize sample student if doesn't exist
        if (studentService.getAllStudents().isEmpty()) {
            Student student = new Student("2007A10", "Nguyễn Văn A", "Hà Nội");
            studentService.saveStudent(student);

            // Add sample score
            if (studentScoreService.getAllStudentScores().isEmpty()) {
                var javaSubject = subjectService.getAllSubjects().stream()
                        .filter(s -> "JAVA".equals(s.getSubjectCode()))
                        .findFirst().orElse(null);

                if (javaSubject != null) {
                    StudentScore score = new StudentScore(student, javaSubject,
                            new BigDecimal("8.5"), new BigDecimal("7.0"));
                    studentScoreService.saveStudentScore(score);
                }
            }
        }
    }
}