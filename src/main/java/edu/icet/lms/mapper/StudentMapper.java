package edu.icet.lms.mapper;

import edu.icet.lms.dto.StudentDto;
import edu.icet.lms.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toEntity(StudentDto student);
    List<StudentDto> toDtos(List<Student> student);
}
