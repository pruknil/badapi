package com.guanbad.services;

import java.util.List;

import com.guanbad.exception.ServiceException;
import com.querydsl.core.types.Predicate;

public interface CrudService<E> {
	public E create(E obj) throws ServiceException;
	public E read(String id) throws ServiceException;
	public E update(String id,E obj) throws ServiceException;
	public void delete(String id) throws ServiceException;
	public List<E> search(Predicate predicate) throws ServiceException;
	public List<E> list() throws ServiceException;
}
