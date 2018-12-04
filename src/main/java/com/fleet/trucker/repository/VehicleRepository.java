package com.fleet.trucker.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fleet.trucker.entity.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {
	public Optional<Vehicle> findByVin(String vin);
}
