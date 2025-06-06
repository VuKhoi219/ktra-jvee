package com.example.ktr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentScoreService {

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    public List<StudentScore> getAllStudentScores() {
        return studentScoreRepository.findAllWithStudentAndSubject();
    }

    public Optional<StudentScore> getStudentScoreById(Long id) {
        return studentScoreRepository.findById(id);
    }

    public StudentScore saveStudentScore(StudentScore studentScore) {
        return studentScoreRepository.save(studentScore);
    }

    public List<StudentScore> getScoresByStudentId(Long studentId) {
        return studentScoreRepository.findByStudentId(studentId);
    }

    public Optional<StudentScore> findByStudentAndSubject(Long studentId, Long subjectId) {
        return studentScoreRepository.findByStudentStudentIdAndSubjectSubjectId(studentId, subjectId);
    }

    public void deleteStudentScore(Long id) {
        studentScoreRepository.deleteById(id);
    }
}