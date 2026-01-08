package edu.icet.lms.service;

import edu.icet.lms.dto.StudentDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentService {
    void addStudent(@RequestBody StudentDto student);
    List<StudentDto> getStudent();
    void deteleStudent(String id);
}
