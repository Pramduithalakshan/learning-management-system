package edu.icet.lms.service;

import edu.icet.lms.dto.UserDto;

import java.util.List;


public interface UserService {
    void registerUser(UserDto user);
    List<UserDto> getUsers();
}
