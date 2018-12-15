package com.guanbad.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guanbad.model.Court;
import com.guanbad.model.Team;

@Controller
@RequestMapping("/guan")
public class Api {

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Team> list() {
		ArrayList<Team> list = new ArrayList<Team>();
		RandomUtils rand = new RandomUtils();
		for(int i=0;i<rand.nextInt(10,50);i++) {
			
			Team e = new Team();
			e.setName("abc");
			e.setPicture("https://ipfs.io/ipfs/QmeakCg5GsUeEvLncZW8Q5EU56iyMaiLznefgaBabGc8Db");
			e.setStarttime(Calendar.getInstance());
			list.add(e);
			
		}
		return list;
	}

	@RequestMapping(value = "/get/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Team get(@PathVariable String id) {
		Team e = new Team();
		e.setName("abc");
		e.setPicture("https://ipfs.io/ipfs/QmeakCg5GsUeEvLncZW8Q5EU56iyMaiLznefgaBabGc8Db");
		e.setStarttime(Calendar.getInstance());
		Court court = new Court();
		court.setVicinity("ABC def ghi");
		e.setCourt(court);
		return e;
	}
}