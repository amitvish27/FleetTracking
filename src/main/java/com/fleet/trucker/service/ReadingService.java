package com.fleet.trucker.service;

import java.util.List;

import com.fleet.trucker.entity.Reading;

public interface ReadingService {
	public List<Reading> findAll();

	public Reading findById(String id);
	
	public List<Reading> findAllByVin(String vin);
	
	public Reading create(Reading read);

	public Reading update(String id, Reading read);

	public void delete(String id);

	public List<String[]> findLastGeolocation(String vin);
}
