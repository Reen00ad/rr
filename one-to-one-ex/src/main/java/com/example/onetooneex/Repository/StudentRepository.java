package com.example.onetooneex.Repository;

import com.example.onetooneex.Model.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findStudentById(Integer id);

   // List<Student> findStudentsByCourses(Integer courseid);

    Student findStudentByIdAndCoursesIsNotNull(Integer id);
    Student findByIdAndCoursesIsNotNull(Integer id);



}
