package com.guanbad.api;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guanbad.exception.ServiceException;
import com.guanbad.model.Team;
import com.guanbad.services.CrudService;

@Controller
@RequestMapping("/api/team")
public class TeamApi {

	@Autowired
	private CrudService<Team> service;
	@RequestMapping(value = "/import", method = { RequestMethod.GET })
	@ResponseBody
	public List<Team> doImport() throws IOException {

		//ArrayList<Team> list = new ArrayList<Team>();
    	for(int i=0;i<2;i++) {
    		ArrayList<Team> response = fetch("2",i*50);

		if (response  != null) {
			for (Team p : response) {
				try {
					service.create(p);
				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				//list.add(p);
			}
    	}
		}
		return null;
	}
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Team> list() throws  ServiceException {
		ArrayList<Team> list = new ArrayList<Team>();
		for (Team m : service.list()) {
			list.add(m);
		}
		return list;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Team obj) {
		try {
			service.create(obj);
			return new ResponseEntity<>("Team is created successfully", HttpStatus.CREATED);
		} catch (ServiceException e) {
			return new ResponseEntity<>("Team is created fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Team obj) {
		try {
			service.update(id,obj);
			return new ResponseEntity<>("Team is updated successsfully", HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>("Team is updated fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Team is updated successsfully", HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<>("Team is updated fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
    private ArrayList<Team> fetch(String forum,int start) throws IOException {
        String url = "http://www.thaibadminton.com/main/modules/newbb_plus/viewforum.php?start="+start+"&forum="+forum;
       // print("Fetching %s...", url);
        ArrayList<Team> response = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

      //print("\nLinks: (%d)", links.size());
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

      LocalDate localDate = LocalDate.now();
      for (Element link : links) {
    	
    	  String str = link.text();
    	  if(str.matches(".*\\d{2}:\\d{2}$")) {
    		  if(str.startsWith("เมื่อวาน")) {
    			 str = localDate.minusDays(1).atTime(0, 0).format(formatter);
    		  }else if(str.startsWith("วันนี้")) {
    			  str = localDate.atTime(0, 0).format(formatter);
    		  }

    		  if(str.length() ==16) {
    			  Node bb = link.parent().parent().childNode(2).childNode(2).childNode(0);
 	    		 LocalDate txtDate = LocalDate.parse(str, formatter);
  	    	  long months = ChronoUnit.MONTHS.between(localDate, txtDate);
  	    	// System.out.println(months);
  	    	  if(!bb.toString().contains("หา") && !bb.toString().contains("รับสมัคร")&& !bb.toString().contains("ไหมครับ")) {
  	    		  Team e = new Team();
  	    		 e.setThbUrl(link.parent().parent().childNode(2).childNode(2).attr("abs:href"));
  	    		  e.setName(bb.toString());
  	    		  e.setThbLastUpd(txtDate);
  	    		response.add(e);
    			  print("<%s>  (%s) {%s}", link.parent().parent().childNode(2).childNode(2).attr("abs:href"), bb,months);  
  	    	  }

    		  }

    	  }
          
      }
      return response;
    }
    private void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

}
