package com.example.onetooneex.Repository;

import com.example.onetooneex.Model.Course;
import com.example.onetooneex.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    Course findCourseById(Integer id);

    @Query("select c.students from Course c where c.id = :cid ")
    List<Student> findStudentByCourseid(Integer cid);

}
