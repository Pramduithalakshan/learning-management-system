package edu.icet.lms.controller;

import edu.icet.lms.dto.UserDto;
import edu.icet.lms.service.impl.UserImpl;
import edu.icet.lms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private final UserImpl userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtutil;
    @Autowired
    public UserController(UserImpl userService, AuthenticationManager authenticationManager, JwtUtil jwtutil){
        this.userService=userService;
        this.authenticationManager=authenticationManager;
        this.jwtutil=jwtutil;
    }
    @PostMapping("/register")
    public void registerUser(@RequestBody UserDto user){
     userService.registerUser(user);
    }
    @PostMapping("/login")
    public String loginUser(@RequestBody UserDto user){
                 authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()
                        )
                );
                 return jwtutil.generateToke(user.getUsername());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUsers")
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }


}
