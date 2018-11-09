package com.guanbad.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guanbad.model.Team;

@Controller
@RequestMapping("/api")
public class Api {

	@RequestMapping(value = "/guan", method = { RequestMethod.GET })
	@ResponseBody
	public List<Team> list() {
		ArrayList<Team> list = new ArrayList<Team>();
		
		for(int i=0;i<10;i++) {
			
			Team e = new Team();
			e.setName("abc");
			e.setPicture("pic");
			e.setStarttime(Calendar.getInstance());
			list.add(e);
			
		}
		return list;
	}

}