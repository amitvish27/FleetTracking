package com.fleet.trucker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fleet.trucker.entity.Alert;
import com.fleet.trucker.service.AlertService;

@RestController
@RequestMapping(path = "alerts")
public class AlertController {
	
	@Autowired
	private AlertService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Alert> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Alert findById(@PathVariable("id") String id) {
		return service.findById(id);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/vin/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Alert> findAllByVin(@PathVariable("vin") String vin) {
		return service.findAllByVin(vin);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/readingType/{readingType}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Alert> findAllByReadingType(@PathVariable("readingType") String readingType) {
		return service.findAllByReadingType(readingType);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/priority/{priority}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Alert> findAllByPriority(@PathVariable("priority") String priority) {
		return service.findAllByPriority(priority);
	}

	// public List<Alert> findAllByTimestamp(String timestamp);
}
