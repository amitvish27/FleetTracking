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

import com.fleet.trucker.entity.Vehicle;
import com.fleet.trucker.service.VehicleService;

@RestController
@RequestMapping(path = "vehicles")
public class VehicleController {

	@Autowired
	private VehicleService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Vehicle> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Vehicle findById(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Vehicle create(@RequestBody Vehicle veh) {
		return service.create(veh);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Vehicle udpate(@PathVariable("id") String id, @RequestBody Vehicle veh) {
		return service.update(id, veh);
	}

	// requestmethod.put get array of objects from mocker
	@CrossOrigin(origins = "http://mocker.ennate.academy")
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void createAll(@RequestBody List<Vehicle> vehList) {
		service.createAll(vehList);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

}
