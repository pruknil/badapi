package com.guanbad.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.guanbad.exception.ServiceException;
import com.querydsl.core.types.Predicate;

public abstract class DBService<E> implements CrudService<E> {
	@Autowired
	protected MongoRepository<E, String> repository;

	@Autowired
	QuerydslPredicateExecutor<E> searchRepo;
	@Override
	public E create(E obj) throws ServiceException {
		E monk = repository.save(obj);
		return monk;
	}

	@Override
	public E read(String id) throws ServiceException {
		Optional<E> op = repository.findById(id);
		return op.get();
	}

	@Override
	public void delete(String id) throws ServiceException {
		Optional<E> m = repository.findById(id);
		repository.delete(m.get());
	}

	@Override
	public List<E> list() throws ServiceException {
		return repository.findAll();
	}
	
	@Override
	public List<E> search(Predicate predicate) throws ServiceException {
		return (List<E>) searchRepo.findAll(predicate);
	}
}
