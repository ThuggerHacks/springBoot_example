package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.HashingUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController extends BaseController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers(HttpServletRequest request) {
        System.out.println(request.getAttribute("msg")); //receiving from interceptor
        return userService.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }


        //hashing password
        user.setPassword(
            HashingUtil.hashPassword(user.getPassword())
        );
        return ResponseEntity.ok(userService.save(user));
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if(this.userService.Login(user.getUsername(), user.getPassword()) != null){
            return ResponseEntity.ok("Successfully logged in");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
