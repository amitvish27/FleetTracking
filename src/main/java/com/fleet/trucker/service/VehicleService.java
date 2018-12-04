package com.fleet.trucker.service;

import java.util.List;

import com.fleet.trucker.entity.Vehicle;

public interface VehicleService {
	public List<Vehicle> findAll();

	public Vehicle findById(String id);

	public Vehicle create(Vehicle veh);

	public void createAll(List<Vehicle> vehList);

	public Vehicle update(String id, Vehicle veh);

	public void delete(String id);

}
