package com.fleet.trucker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fleet.trucker.entity.Alert;

public interface AlertRepository extends CrudRepository<Alert, String> {

	public Iterable<Alert> findAllByVin(String vin);

	public Iterable<Alert> findAllByReadingType(String readingType);

	public Iterable<Alert> findAllByPriority(String priority);

	@Query("from Alert a where a.timestamp>=:timestamp and a.priority=:priority order by a.timestamp desc")
	public Iterable<Alert> findAllByPriorityAndTimestamp(@Param("priority") int priority, @Param("timestamp") Date timestamp);

	@Query("from Alert a order by a.priority desc")
	public List<Alert> sortAllVehByAlert();
}
