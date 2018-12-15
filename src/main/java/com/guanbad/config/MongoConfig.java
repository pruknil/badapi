package com.guanbad.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories("com.guanbad.mongo")
public class MongoConfig  extends AbstractMongoConfiguration{
	
	@Value("${app.mongodb.host}")
	private String mongoAddress; 
	@Value("${app.mongodb.port}")
	private Integer mongoPort;
	@Value("${app.mongodb.database}")
	private String mongoDatabase;
	
	@Value("${app.mongodb.authentication-database}")
	private String authenticationDB;
	@Value("${app.mongodb.username}")
	private String username;
	@Value("${app.mongodb.password}")
	private String password;
	@Value("${app.mongodb.database}")
	private String database;	
	
/*	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
	    return new GridFsTemplate(new SimpleMongoDbFactory(
				new MongoClient(new ServerAddress(mongoAddress, mongoPort),
						Collections.singletonList(
								MongoCredential.createCredential(username, authenticationDB, password.toCharArray()))),
				database), mappingMongoConverter());
	}
*/
	@Override
	protected String getDatabaseName() {
		return mongoDatabase;
	}

	@Override
	public MongoClient mongoClient() {
		return new MongoClient(new ServerAddress(mongoAddress, mongoPort),
				Collections.singletonList(
						MongoCredential.createCredential(username, authenticationDB, password.toCharArray())));
	}

}