package edu.icet.lms.controller;

import edu.icet.lms.dto.UserDto;
import edu.icet.lms.service.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private final UserImpl userService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    public UserController(UserImpl userService, AuthenticationManager authenticationManager){
        this.userService=userService;
        this.authenticationManager=authenticationManager;
    }
    @PostMapping("/register")
    public void registerUser(@RequestBody UserDto user){
     userService.registerUser(user);
    }
    @PostMapping("/login")
    public void loginUser(@RequestBody UserDto user){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
    }


}
