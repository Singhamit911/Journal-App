package com.learningRESTAPI.journalApp.repository;

import com.learningRESTAPI.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {
    //here i am learning "criteria" - a way to talk to Database without using the conventional ways

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){
        Query query = new Query();

        //this has by default AND criteria
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

//        query.addCriteria(new Criteria().orOperator(
//                Criteria.where("email").exists(true),
//                Criteria.where("sentimentAnalysis").is("false")
//        ));     //this is to use OR / norOperator() for NOR / .ne() or .not() for NOT

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
