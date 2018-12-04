package com.fleet.trucker.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Tires {
	@Id
	@Column(columnDefinition = "VARCHAR(36)")
	private String id;
	private int frontLeft;
	private int frontRight;
	private int rearLeft;
	private int rearRight;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Reading reading;
	
	
	public Tires(){
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getFrontLeft() {
		return frontLeft;
	}

	public void setFrontLeft(int frontLeft) {
		this.frontLeft = frontLeft;
	}

	public int getFrontRight() {
		return frontRight;
	}

	public void setFrontRight(int frontRight) {
		this.frontRight = frontRight;
	}

	public int getRearLeft() {
		return rearLeft;
	}

	public void setRearLeft(int rearLeft) {
		this.rearLeft = rearLeft;
	}

	public int getRearRight() {
		return rearRight;
	}

	public void setRearRight(int rearRight) {
		this.rearRight = rearRight;
	}

}
