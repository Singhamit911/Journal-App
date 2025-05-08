package com.learningRESTAPI.journalApp.controller;

import com.learningRESTAPI.journalApp.entity.User;
import com.learningRESTAPI.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers(){
        List<User> allUsers = userService.getAll();

        if(allUsers!=null){
            return new ResponseEntity<>(allUsers,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try {
            userService.saveNewUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
