package com.fleet.trucker.service;

import java.util.Optional;

import com.fleet.trucker.entity.Alert;
import com.fleet.trucker.entity.Priority;
import com.fleet.trucker.entity.Reading;
import com.fleet.trucker.entity.ReadingType;
import com.fleet.trucker.entity.Tires;
import com.fleet.trucker.entity.Vehicle;

public class RulesEngine {

	public static Optional<Alert> engineRPMThresholdCheck(Reading read, Vehicle veh) {
		Alert alert = null;
		if (read.getEngineRpm() > veh.getRedLineRpm()) {
			alert = new Alert();
			alert.setVin(read.getVin());
			alert.setPriority(Priority.HIGH.toString());
			alert.setReadingType(ReadingType.HIGH_RPM.toString());
			alert.setTimestamp(read.getTimestamp());
			alert.setDescription("EngineRPM is greater than RedLineRPM");
		}
		return Optional.ofNullable(alert);
	}

	public static Optional<Alert> lowFuelVolumeCheck(Reading read, Vehicle veh) {
		Alert alert = null;
		if (read.getFuelVolume() < (0.10 * veh.getMaxFuelVolume())) {
			alert = new Alert();
			alert.setVin(read.getVin());
			alert.setPriority(Priority.MED.toString());
			alert.setReadingType(ReadingType.LOW_FUEL.toString());
			alert.setTimestamp(read.getTimestamp());
			alert.setDescription("Fuel less than minimum threshold");
		}
		return Optional.ofNullable(alert);
	}

	public static Optional<Alert> tirePressureCheck(Reading read) {
		Alert alert = null;
		StringBuffer tireName=new StringBuffer();
		boolean flag=false;
		Tires tires = read.getTires();
		if (tires.getFrontLeft() < 32 || tires.getFrontLeft() > 36) {
			tireName.append("| FRONT_LEFT |");
			flag=true;
		}
		if (tires.getFrontRight() < 32 || tires.getFrontRight() > 36) {
			tireName.append("| FRONT_RIGHT |");
			flag=true;
		}
		if (tires.getRearLeft() < 32 || tires.getRearLeft() > 36) {
			tireName.append("| REAR_LEFT |");
			flag=true;
		}
		if (tires.getRearRight() < 32 || tires.getRearRight() > 36) {
			tireName.append("| REAR_RIGHT |");
			flag=true;
		}
		
		if(flag){
			alert = new Alert();
			alert.setVin(read.getVin());
			alert.setPriority(Priority.LOW.toString());
			alert.setReadingType(ReadingType.LOW_TIRE_PSI.toString());
			alert.setTimestamp(read.getTimestamp());
			alert.setDescription("Low Tire Pressure on " + tireName.toString());
		}
		return Optional.ofNullable(alert);
	}

	public static Optional<Alert> lowEngineCoolantCheck(Reading read) {
		Alert alert = null;
		if (read.isEngineCoolantLow()) {
			alert = new Alert();
			alert.setVin(read.getVin());
			alert.setPriority(Priority.LOW.toString());
			alert.setReadingType(ReadingType.LOW_COOLANT.toString());
			alert.setTimestamp(read.getTimestamp());
			alert.setDescription("Low Engine Coolant");
		}
		return Optional.ofNullable(alert);
	}

	public static Optional<Alert> checkEngineLightOnCheck(Reading read) {
		Alert alert = null;
		if (read.isCheckEngineLightOn()) {
			alert = new Alert();
			alert.setVin(read.getVin());
			alert.setPriority(Priority.LOW.toString());
			alert.setReadingType(ReadingType.CHECK_ENGINE_LIGHT_ON.toString());
			alert.setTimestamp(read.getTimestamp());
			alert.setDescription("Check Engine light is ON");
		}
		return Optional.ofNullable(alert);
	}
}
