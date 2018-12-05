package com.guanbad.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guanbad.exception.ServiceException;
import com.guanbad.model.Member;

@Service
public class MemberServiceImpl extends DBService<Member> {
	
	@Override
	public Member update(String id, Member obj) throws ServiceException {
		Optional<Member> m = repository.findById(id);
		Member entity = m.get();
		entity.setName(obj.getName());
		entity.setUpdateDate(obj.getUpdateDate());
		return repository.save(entity);
	}


}
