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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "readings")
@Api(value="Operations on the Readings of the vehicles")
public class ReadingController {
	
	@Autowired
	private ReadingService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value="Retrieve all the historical readings", response=List.class)
	public List<Reading> findAll(){
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path="vin/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value="Retrieve the readings for Vehicle with VIN", response=List.class)
	public List<Reading> findAllByVin(@PathVariable("vin") String vin) {
		return service.findAllByVin(vin);
	}
	
	// requestmethod.post get objects from mocker
	@CrossOrigin(origins = "http://mocker.ennate.academy")
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value="Add readings for vehicles", response=Reading.class)
	public Reading create(@RequestBody Reading read) {
		return service.create(read);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value="Update Readings based on the ID", response=Reading.class)
	public Reading udpate(@PathVariable("id") String id, @RequestBody Reading read) {
		return service.update(id, read);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	@ApiOperation(value="Delete readings with ID")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/geolocate/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value="Retrieve the Geolocation of the Vehicle", response=List.class)
	public List<String[]> findLastGeolocation(@PathVariable("vin") String vin) {
		return service.findLastGeolocation(vin);
	}
	
}
