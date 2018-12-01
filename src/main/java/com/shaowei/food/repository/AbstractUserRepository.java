package com.shaowei.food.repository;

import com.shaowei.food.domain.AbstractUser;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the AbstractUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbstractUserRepository extends MongoRepository<AbstractUser, String> {

}
