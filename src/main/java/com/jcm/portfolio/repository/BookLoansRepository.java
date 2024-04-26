package com.jcm.portfolio.repository;

import com.jcm.portfolio.model.BookLoans;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLoansRepository extends MongoRepository<BookLoans, ObjectId> {

}
