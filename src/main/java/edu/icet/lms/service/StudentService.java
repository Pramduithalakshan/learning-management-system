package edu.icet.lms.service;

import edu.icet.lms.dto.StudentDto;

import java.util.List;

public interface StudentService {
    void addStudent( StudentDto student);
    List<StudentDto> getStudent();
    void deteleStudent(String id);
}
