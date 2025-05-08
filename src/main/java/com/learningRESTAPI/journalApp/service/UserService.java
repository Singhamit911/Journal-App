//Service – "Where business logic lives"
//		Contains the core business logic of your application.
//
//		Calls methods from the Repository and handles more complex operations.
//
//		Annotated with @Service.
//
//		Purpose:
//		Acts as a middle layer between controller and repository. Keeps logic out of the controller and promotes reusability

package com.learningRESTAPI.journalApp.service;

import com.learningRESTAPI.journalApp.entity.User;
import com.learningRESTAPI.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    //instead of writing this we can use @slf4j annotation
    public boolean saveNewUser(User user){
        try{
            user.setRoles(Arrays.asList("USER"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } catch (Exception e){
            log.error("Error occured for {} : ", user.getUserName(),e); //logger word if replaced by log in slf4j
            return false;
        }
    }

    public void saveAdmin(User user){
        user.setRoles(Arrays.asList("USER","ADMIN"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteById(ObjectId Id){
        userRepository.deleteById(Id);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId Id){
        return userRepository.findById(Id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}


//controller ---> service ----> repository