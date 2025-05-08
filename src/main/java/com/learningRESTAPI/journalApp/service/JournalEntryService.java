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

import com.learningRESTAPI.journalApp.entity.JournalEntry;
import com.learningRESTAPI.journalApp.entity.User;
import com.learningRESTAPI.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){

        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured",e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }

    @Transactional
    public boolean deleteById(ObjectId Id, String userName){
        boolean removed = false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(Id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(Id);
            }
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("Error occured : ", e);
        }
        return removed;
    }

//    public List<JournalEntry> findByUserName(String userName){
//
//    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId Id){
        return journalEntryRepository.findById(Id);
    }
    //we can directly use journalEntryRepository.finById(Id); also but it is a good practice pass all repository access go through
    //service layer for consitency, future proofing etc.
}


//controller ---> service ----> repository