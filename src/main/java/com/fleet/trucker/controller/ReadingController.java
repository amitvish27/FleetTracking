package com.fleet.trucker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fleet.trucker.entity.Reading;
import com.fleet.trucker.service.ReadingService;

@RestController
@RequestMapping(path = "readings")
public class ReadingController {
	
	@Autowired
	private ReadingService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Reading> findAll(){
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path="vin/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Reading> findAllByVin(@PathVariable("vin") String vin) {
		return service.findAllByVin(vin);
	}
	
	// requestmethod.post get objects from mocker
	@CrossOrigin(origins = "http://mocker.ennate.academy")
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Reading create(@RequestBody Reading read) {
		return service.create(read);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Reading udpate(@PathVariable("id") String id, @RequestBody Reading read) {
		return service.update(id, read);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

}
