package com.example.ktr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {

    @Query("SELECT ss FROM StudentScore ss " +
            "JOIN FETCH ss.student s " +
            "JOIN FETCH ss.subject sub " +
            "ORDER BY s.studentCode, sub.subjectCode")
    List<StudentScore> findAllWithStudentAndSubject();

    @Query("SELECT ss FROM StudentScore ss " +
            "JOIN FETCH ss.student s " +
            "JOIN FETCH ss.subject sub " +
            "WHERE s.studentId = :studentId")
    List<StudentScore> findByStudentId(@Param("studentId") Long studentId);

    Optional<StudentScore> findByStudentStudentIdAndSubjectSubjectId(Long studentId, Long subjectId);
}