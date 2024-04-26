package com.jcm.portfolio.repository;

import com.jcm.portfolio.model.Subscription;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends MongoRepository<Subscription, UUID> {

}
