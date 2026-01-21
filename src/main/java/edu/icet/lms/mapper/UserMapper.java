package edu.icet.lms.mapper;

import edu.icet.lms.dto.UserDto;
import edu.icet.lms.entity.User;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto user);
    UserDto toDto(User user);
    List<UserDto> toDtos(List<User> user);
}
