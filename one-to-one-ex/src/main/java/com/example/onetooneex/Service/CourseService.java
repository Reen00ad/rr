package com.example.onetooneex.Service;

import com.example.onetooneex.Api.ApiException;
import com.example.onetooneex.Model.Course;
import com.example.onetooneex.Model.Student;
import com.example.onetooneex.Model.Teacher;
import com.example.onetooneex.Repository.CourseRepository;
import com.example.onetooneex.Repository.StudentRepository;
import com.example.onetooneex.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;


    public List<Course> getAllCourse(){

        return courseRepository.findAll();
    }

    public void addCourse(Integer teacher_id,Course course){
        Teacher t=teacherRepository.findTeacherById(teacher_id);
        if(t==null){
            throw new ApiException("can't assign");
        }
        course.setTeacher(t);

        courseRepository.save(course);
    }



    public void updateCourse(Integer id,Course course){
        Course c=courseRepository.findCourseById(id);
        if(c==null){
            throw new ApiException("not found");
        }
       c.setName(course.getName());

        courseRepository.save(c);
    }

    public void deleteCourse(Integer id){
        Course c=courseRepository.findCourseById(id);
        if(c==null){
            throw new ApiException("not found");
        }

        courseRepository.delete(c);
    }

    public void assignCourseToStudent(Integer courseid,Integer studentid){
        Course c=courseRepository.findCourseById(courseid);
        Student s=studentRepository.findStudentById(studentid);

        if(s==null || c==null){
            throw new ApiException("can't assign");
        }

        c.getStudents().add(s);
        s.getCourses().add(c);

       courseRepository.save(c);
       studentRepository.save(s);
    }

    public String getTeacherByCourseId(Integer id){
        Course c=courseRepository.findCourseById(id);
        if(c==null){
            throw new ApiException(" course not found");
        }


            return c.getTeacher().getName();
    }

    public List<Student> getStudentByCourseId(Integer id){
        Course c=courseRepository.findCourseById(id);
        if(c==null){
            throw new ApiException(" course not found");
        }


        return (List<Student>) c.getStudents();
    }


    }
