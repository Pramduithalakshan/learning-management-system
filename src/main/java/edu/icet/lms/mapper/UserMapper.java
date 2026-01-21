package edu.icet.lms.mapper;

import edu.icet.lms.dto.UserDto;
import edu.icet.lms.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto user);
    UserDto toDtos(User user);
}
