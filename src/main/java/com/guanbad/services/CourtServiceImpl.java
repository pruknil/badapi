package com.guanbad.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guanbad.exception.ServiceException;
import com.guanbad.model.Court;

@Service
public class CourtServiceImpl extends DBService<Court> {
	
	@Override
	public Court update(String id, Court obj) throws ServiceException {
		Optional<Court> m = repository.findById(id);
		Court entity = m.get();
		entity.setName(obj.getName());
		//entity.setUpdateDate(obj.getUpdateDate());
		return repository.save(entity);
	}


}
