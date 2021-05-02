package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class PowerOutage implements Comparable<PowerOutage>{
	
	private int poId;
	private Nerc Nerc;
	private int customersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	private long outageDuration;
	private int year;
	
	public PowerOutage(int poId, it.polito.tdp.poweroutages.model.Nerc nerc, int customersAffected,
			LocalDateTime dateEventBegan, LocalDateTime dateEventFinished) {
		super();
		this.poId = poId;
		Nerc = nerc;
		this.customersAffected = customersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
	}

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public Nerc getNerc() {
		return Nerc;
	}

	public void setNerc(Nerc nerc) {
		Nerc = nerc;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}

	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}

	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}

	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}

	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}

	public long getOutageDuration() {
		return outageDuration;
	}

	public void setOutageDuration(long outageDuration) {
		this.outageDuration = outageDuration;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + poId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (poId != other.poId)
			return false;
		return true;
	}

	@Override
	public int compareTo(PowerOutage po) {
		return this.getDateEventBegan().compareTo(po.getDateEventBegan());
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append(poId);
		return builder.toString();
		
	}
	
}
