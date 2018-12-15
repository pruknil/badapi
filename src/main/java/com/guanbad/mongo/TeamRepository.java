package com.guanbad.mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.guanbad.model.Team;



public interface TeamRepository extends MongoRepository<Team, String>, QuerydslPredicateExecutor<Team> {

}