package com.example.ktr;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public boolean existsBySubjectCode(String subjectCode) {
        return subjectRepository.existsBySubjectCode(subjectCode);
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}