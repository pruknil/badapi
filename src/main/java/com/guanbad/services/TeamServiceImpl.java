package com.guanbad.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guanbad.exception.ServiceException;
import com.guanbad.model.Team;

@Service
public class TeamServiceImpl extends DBService<Team> {
	
	@Override
	public Team update(String id, Team obj) throws ServiceException {
		Optional<Team> m = repository.findById(id);
		Team entity = m.get();
		entity.setName(obj.getName());
		//entity.setUpdateDate(obj.getUpdateDate());
		return repository.save(entity);
	}


}
