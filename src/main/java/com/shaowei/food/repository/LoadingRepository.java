package com.shaowei.food.repository;

import com.shaowei.food.domain.Loading;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Loading entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoadingRepository extends MongoRepository<Loading, String> {

}
