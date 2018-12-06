package com.fleet.trucker.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fleet.trucker.entity.Alert;
import com.fleet.trucker.entity.Reading;
import com.fleet.trucker.exception.ResourceNotFoundException;
import com.fleet.trucker.repository.AlertRepository;
import com.fleet.trucker.repository.VehicleRepository;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertRepository repository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Alert> findAll() {
		return (List<Alert>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Alert findById(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Alert with id {" + id + "} not found"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alert> findAllByVin(String vin) {
		return (List<Alert>) repository.findAllByVin(vin);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alert> findAllByReadingType(String readingType) {
		return (List<Alert>) repository.findAllByReadingType(readingType);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alert> findAllByPriority(String priority) {
		return (List<Alert>) repository.findAllByPriority(priority);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alert> findAllByPriorityAndTimestamp(int priority) {
		//Calculate timestamp for last 2 hours
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -2);
		Date date = new Date(cal.getTimeInMillis());
		return (List<Alert>) repository.findAllByPriorityAndTimestamp(priority, date);
	}

	@Override
	@Transactional
	public void create(Reading read) {
		vehicleRepository.findByVin(read.getVin()).ifPresent(veh -> {
			Optional<Alert> opt = RulesEngine.engineRPMThresholdCheck(read, veh);
			opt.ifPresent(alert -> repository.save(alert));

			opt = RulesEngine.checkEngineLightOnCheck(read);
			opt.ifPresent(alert -> repository.save(alert));

			opt = RulesEngine.lowEngineCoolantCheck(read);
			opt.ifPresent(alert -> repository.save(alert));

			opt = RulesEngine.lowFuelVolumeCheck(read, veh);
			opt.ifPresent(alert -> repository.save(alert));

			opt = RulesEngine.tirePressureCheck(read);
			opt.ifPresent(alert -> repository.save(alert));
		});
	}

	@Override
	@Transactional
	public List<Alert> sortAllVehByAlert() {
		return repository.sortAllVehByAlert();
	}
}
