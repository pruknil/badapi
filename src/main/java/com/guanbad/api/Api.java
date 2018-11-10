package com.guanbad.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
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
		RandomUtils rand = new RandomUtils();
		for(int i=0;i<rand.nextInt(10,50);i++) {
			
			Team e = new Team();
			e.setName("abc");
			e.setPicture("pic");
			e.setStarttime(Calendar.getInstance());
			list.add(e);
			
		}
		return list;
	}

}