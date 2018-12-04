package com.fleet.trucker.repository;

import org.springframework.data.repository.CrudRepository;

import com.fleet.trucker.entity.Reading;

public interface ReadingRepository extends CrudRepository<Reading, String>  {
	
	public Iterable<Reading> findAllByVin(String vin);
}
