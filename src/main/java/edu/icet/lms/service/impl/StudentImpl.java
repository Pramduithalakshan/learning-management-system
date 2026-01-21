package edu.icet.lms.service.impl;

import edu.icet.lms.dto.StudentDto;
import edu.icet.lms.mapper.StudentMapper;
import edu.icet.lms.repository.StudentRepository;
import edu.icet.lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentImpl implements StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;
    @Override
    public void addStudent( StudentDto student) {
        repository.save(studentMapper.toEntity(student));
    }

    @Override
    public List<StudentDto> getStudent() {
        return studentMapper.toDtos(repository.findAll());
    }

    @Override
    public void deteleStudent(String id) {
        repository.deleteById(id);
    }
}
