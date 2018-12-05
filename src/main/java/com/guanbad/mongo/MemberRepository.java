package com.guanbad.mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.guanbad.model.Member;



public interface MemberRepository extends MongoRepository<Member, String>, QuerydslPredicateExecutor<Member> {

}