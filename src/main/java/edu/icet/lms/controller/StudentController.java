package edu.icet.lms.controller;

import edu.icet.lms.dto.StudentDto;
import edu.icet.lms.service.impl.StudentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentImpl studentService;

    @PostMapping("/add-student")
    public void addStudent(@RequestBody StudentDto student){
        studentService.addStudent(student);
    }
    @GetMapping("/get-student")
    public List<StudentDto> getStudent(){
        return studentService.getStudent();
    }
    @DeleteMapping("/remove-student/{id}")
    public void deleteStudent(@PathVariable String id){
        studentService.deteleStudent(id);
    }
}
