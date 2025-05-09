//Entity – "What is stored in the database"
//        Represents a table in your database.
//
//        Each instance of an entity class corresponds to a row.
//
//        Purpose:
//        Defines the structure of your data model — just like how a database table has columns.

package com.learningRESTAPI.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data  //this annotation is provided by lombok. This Generates Getters and Setters ine compile time. This is just used to keep the code clean.
@NoArgsConstructor
public class    User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    private List<String> roles;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private String city;

    //for learning criteria - visit UserRepositoryImpl
    private String email;
    private boolean sentimentAnalysis;
}
