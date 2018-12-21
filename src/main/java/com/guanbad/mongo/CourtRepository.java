package com.guanbad.mongo;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.guanbad.model.Court;



public interface CourtRepository extends MongoRepository<Court, String>, QuerydslPredicateExecutor<Court> {
	@Query("{geo: {$near:[?1,?0],$maxDistance:?2}}")
	public List<Court> searchByGeo(double lat,double lng,double range);
}