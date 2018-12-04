package com.fleet.trucker.service;

import java.util.List;

import com.fleet.trucker.entity.Alert;
import com.fleet.trucker.entity.Reading;

public interface AlertService {
	public List<Alert> findAll();

	public Alert findById(String id);

	public List<Alert> findAllByVin(String vin);

	public List<Alert> findAllByReadingType(String readingType);

	public List<Alert> findAllByPriority(String priority);

	public List<Alert> findAllByTimestamp(String timestamp);

	public void create(Reading read);

}
