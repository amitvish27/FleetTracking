package com.fleet.trucker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fleet.trucker.entity.Reading;

public interface ReadingRepository extends CrudRepository<Reading, String>  {
	
	public Iterable<Reading> findAllByVin(String vin);

	@Query("from Reading r where r.vin=:vin order by r.timestamp desc")
	public List<Reading> findLastGeolocation(@Param("vin") String vin);
}
