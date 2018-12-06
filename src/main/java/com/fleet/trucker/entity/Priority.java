package com.fleet.trucker.entity;

public enum Priority {
	HIGH(1), MED(2), LOW(3);
	
	private int n;
	
	Priority(int n) {
		this.n=n;
	}
	
	public int getN() {
		return this.n;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
