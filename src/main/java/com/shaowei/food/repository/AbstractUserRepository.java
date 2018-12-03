package com.shaowei.food.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shaowei.food.domain.AbstractUser;


/**
 * Spring Data MongoDB repository for the AbstractUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbstractUserRepository extends MongoRepository<AbstractUser, String> {
	
	Optional<AbstractUser> findByEmail(String email);

}
