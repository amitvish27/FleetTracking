package com.fleet.trucker.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {

	@Id
	@Column(columnDefinition = "VARCHAR(36)")
	private String id;

	@Column(unique = true, columnDefinition = "VARCHAR(17)")
	private String vin;

	private String make;
	private String model;

	@Column(columnDefinition = "NUMBER(4)")
	private int year;

	private int redLineRpm;
	private int maxFuelVolume;
	private String lastServiceDate;

	public Vehicle() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRedLineRpm() {
		return redLineRpm;
	}

	public void setRedLineRpm(int redLineRpm) {
		this.redLineRpm = redLineRpm;
	}

	public int getMaxFuelVolume() {
		return maxFuelVolume;
	}

	public void setMaxFuelVolume(int maxFuelVolume) {
		this.maxFuelVolume = maxFuelVolume;
	}

	public String getLastServiceDate() {
		return lastServiceDate;
	}

	public void setLastServiceDate(String lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}

}
