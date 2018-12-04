package com.fleet.trucker.repository;

import org.springframework.data.repository.CrudRepository;

import com.fleet.trucker.entity.Alert;

public interface AlertRepository extends CrudRepository<Alert, String> {

	public Iterable<Alert> findAllByVin(String vin);

	public Iterable<Alert> findAllByReadingType(String readingType);

	public Iterable<Alert> findAllByPriority(String priority);

	public Iterable<Alert> findAllByTimestamp(String timestamp);
}
