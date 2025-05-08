package com.learningRESTAPI.journalApp.controller;

import com.learningRESTAPI.journalApp.api.response.WeatherResponse;
import com.learningRESTAPI.journalApp.entity.User;
import com.learningRESTAPI.journalApp.repository.UserRepository;
import com.learningRESTAPI.journalApp.service.UserService;
import com.learningRESTAPI.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

//    @GetMapping("/{myId}")
//    public ResponseEntity<?> getUsersById(@PathVariable ObjectId myId){
//        Optional<User> user = userService.getById(myId);
//
//        if(user.isPresent()){
//            return new ResponseEntity<>(user.get(),HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @GetMapping
//    public User getUserByName(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//         return userService.findByUserName(userName);
//    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Delhi");
        String greeting = "";
        if(weatherResponse != null){
            greeting = " , Weather feels like " + weatherResponse.getMain().getFeelsLike() + "°C";
        }
        return new ResponseEntity<>("Hi "+authentication.getName() + greeting,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User old = userService.findByUserName(userName);

        if(old != null){
            old.setUserName(user.getUserName());
            old.setPassword(user.getPassword());
            userService.saveNewUser(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
