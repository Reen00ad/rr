package com.example.onetooneex.Controller;

import com.example.onetooneex.Api.ApiResponse;
import com.example.onetooneex.Model.Student;
import com.example.onetooneex.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudent(){

        return ResponseEntity.status(200).body(studentService.getAllStudent());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student){

        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id,@RequestBody @Valid Student student){
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));
    }

    @PutMapping("/change/{id}/{major}")
    public ResponseEntity changeMajor(@PathVariable Integer id,@PathVariable String major){
        studentService.changeMajor(id, major);
        return ResponseEntity.status(200).body("change done");
    }

}
