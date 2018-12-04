package com.fleet.trucker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fleet.trucker.entity.Vehicle;
import com.fleet.trucker.exception.BadRequestException;
import com.fleet.trucker.exception.ResourceNotFoundException;
import com.fleet.trucker.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Vehicle> findAll() {
		return (List<Vehicle>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Vehicle findById(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle with id{" + id + "} not found"));
	}

	@Override
	@Transactional
	public Vehicle create(Vehicle veh) {
		Optional<Vehicle> exists = repository.findByVin(veh.getVin());
		if (exists.isPresent()) {
			throw new BadRequestException("Vehicle with VIN {" + exists.get().getVin() + "} already exists");
		} else {
			return repository.save(veh);
		}
	}

	@Override
	@Transactional
	public Vehicle update(String id, Vehicle veh) {
		Optional<Vehicle> exists = repository.findByVin(veh.getVin());
		if (!exists.isPresent()) {
			throw new ResourceNotFoundException("Vehicle with VIN {" + exists.get().getVin() + "} does not exists");
		} else {
			return repository.save(veh);
		}
	}

	@Override
	@Transactional
	public void delete(String id) {
		Vehicle exists = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle with id {" + id + "} does not exists"));
		repository.delete(exists);
	}

	@Override
	@Transactional
	public void createAll(List<Vehicle> vehList) {
		repository.saveAll(vehList);
	}

}
