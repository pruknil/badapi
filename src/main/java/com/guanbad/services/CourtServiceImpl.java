package com.guanbad.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guanbad.exception.ServiceException;
import com.guanbad.model.Court;
import com.guanbad.mongo.CourtRepository;

@Service
public class CourtServiceImpl extends DBService<Court> {

	@Autowired
	private CourtRepository repository;

	@Override
	public Court update(String id, Court obj) throws ServiceException {
		Optional<Court> m = repository.findById(id);
		Court entity = m.get();
		entity.setName(obj.getName());
		// entity.setUpdateDate(obj.getUpdateDate());
		return repository.save(entity);
	}

	public List<Court> searchByGeo(double lat, double lng, double range) throws ServiceException {
		List<Court> users = (List<Court>) repository.searchByGeo(lat, lng, range / 111.12);
		return users;
	}

}
