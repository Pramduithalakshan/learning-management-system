package edu.icet.lms.service.impl;

import edu.icet.lms.dto.UserDto;
import edu.icet.lms.entity.User;
import edu.icet.lms.mapper.UserMapper;
import edu.icet.lms.repository.UserRepository;
import edu.icet.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserImpl implements UserService,UserDetailsService{
    private final UserRepository repository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserImpl(UserRepository repository, UserMapper userMapper,@Lazy PasswordEncoder passwordEncoder){
        this.repository=repository;
        this.userMapper=userMapper;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public void registerUser(UserDto user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(userMapper.toEntity(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole())
                .build();
    }
}
