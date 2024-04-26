package com.jcm.portfolio.repository;

import com.jcm.portfolio.model.UserApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserApp, ObjectId> {

}
