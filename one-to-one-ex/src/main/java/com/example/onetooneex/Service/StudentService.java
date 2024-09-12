package com.example.onetooneex.Service;

import com.example.onetooneex.Api.ApiException;
import com.example.onetooneex.Model.Course;
import com.example.onetooneex.Model.Student;
import com.example.onetooneex.Repository.CourseRepository;
import com.example.onetooneex.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> getAllStudent(){

        return studentRepository.findAll();
    }


    public void addStudent(Student student){

        studentRepository.save(student);
    }

    public void updateStudent(Integer id,Student student){
        Student s=studentRepository.findStudentById(id);
        if(s==null){
            throw new ApiException("not found");
        }
        s.setName(student.getName());
        s.setAge(student.getAge());
        s.setMajor(student.getMajor());

        studentRepository.save(s);
    }

    public void deleteStudent(Integer id){
        Student s=studentRepository.findStudentById(id);
        if(s==null){
            throw new ApiException("not found");
        }


        studentRepository.delete(s);
    }

    public void changeMajor(Integer id,String newmajor){
        Student s=studentRepository.findStudentById(id);
        if(s==null){
            throw new ApiException("not found");
        }

       s=studentRepository.findByIdAndCoursesIsNotNull(id);
        if (s != null){
            s.setMajor(newmajor);
            s.getCourses().clear();
             studentRepository.save(s);
        }

    }


}
