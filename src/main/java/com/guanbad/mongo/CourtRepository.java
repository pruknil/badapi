package com.guanbad.mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.guanbad.model.Court;



public interface CourtRepository extends MongoRepository<Court, String>, QuerydslPredicateExecutor<Court> {

}