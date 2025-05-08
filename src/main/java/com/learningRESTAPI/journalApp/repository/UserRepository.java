//Interface that extends JpaRepository or CrudRepository.
//
//        Spring auto-implements this to give you methods like save(), findById(), delete(), etc.
//
//        Purpose:
//        Provides data access methods (CRUD) so you don't have to write SQL.

        package com.learningRESTAPI.journalApp.repository;

import com.learningRESTAPI.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
        User findByUserName(String username);

        void deleteByUserName(String username);

}
