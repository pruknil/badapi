package com.guanbad.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.guanbad.exception.ServiceException;
import com.guanbad.google.map.model.Places;
import com.guanbad.google.map.model.Result;
import com.guanbad.model.Court;
import com.guanbad.model.Geo;
import com.guanbad.model.Member;
import com.guanbad.services.CrudService;


@Controller
@RequestMapping("/api/map")
public class MapApi {

	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private CrudService<Court> court;
	@Value( "${google.map.key}" )
	private String mapkey;

	@RequestMapping(value = "/court", method = { RequestMethod.GET })
	@ResponseBody
	public List<Court> list(@RequestParam("lat") String lat, @RequestParam("lng") String lng, @RequestParam(value="name",required=false) String name
			, @RequestParam(value="radius",required=false, defaultValue = "10000") String radius) throws UnsupportedEncodingException {
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("location", lat + "," + lng)
				.queryParam("radius", radius)
				.queryParam("key", mapkey)
				.queryParam("language", "th")
				.queryParam("name", name);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		String newUrl = URLDecoder.decode(builder.toUriString(), "UTF-8"); 
		HttpEntity<Places> response = restTemplate.exchange(newUrl, HttpMethod.GET, entity,
				Places.class);
		ArrayList<Court> list = new ArrayList<Court>();
		if (response.getBody().getResults() != null) {
			for (Result p : response.getBody().getResults()) {
				Geo geo = new Geo();
				Court e = new Court();
				e.setName(p.getName());
				//e.setLat(p.getGeometry().getLocation().getLat());
				//e.setLng(p.getGeometry().getLocation().getLng());
				geo.setLat(p.getGeometry().getLocation().getLat());
				geo.setLon(p.getGeometry().getLocation().getLng());
				e.setGeo(geo);
				e.setPlaceId(p.getPlaceId());
				e.setRating(p.getRating());
				e.setVicinity(p.getVicinity());
				e.setIcon(p.getIcon());
				try {
					court.create(e);
				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				list.add(e);
			}
		}
		return list;
	}

}
