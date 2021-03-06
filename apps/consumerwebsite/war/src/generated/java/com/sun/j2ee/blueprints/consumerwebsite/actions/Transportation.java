// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R2)
// Generated source version: 1.1.3

package com.sun.j2ee.blueprints.consumerwebsite.actions;

public class Transportation {
	protected java.lang.String carrier;
	protected java.util.Calendar departureDate;
	protected java.lang.String departureTime;
	protected java.lang.String destination;
	protected int headCount;
	protected java.lang.String origin;
	protected float price;
	protected java.lang.String transportationId;
	protected java.lang.String travelClass;

	public Transportation() {
	}

	public Transportation(java.lang.String carrier,
			java.util.Calendar departureDate, java.lang.String departureTime,
			java.lang.String destination, int headCount,
			java.lang.String origin, float price,
			java.lang.String transportationId, java.lang.String travelClass) {
		this.carrier = carrier;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.destination = destination;
		this.headCount = headCount;
		this.origin = origin;
		this.price = price;
		this.transportationId = transportationId;
		this.travelClass = travelClass;
	}

	public java.lang.String getCarrier() {
		return carrier;
	}

	public void setCarrier(java.lang.String carrier) {
		this.carrier = carrier;
	}

	public java.util.Calendar getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(java.util.Calendar departureDate) {
		this.departureDate = departureDate;
	}

	public java.lang.String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(java.lang.String departureTime) {
		this.departureTime = departureTime;
	}

	public java.lang.String getDestination() {
		return destination;
	}

	public void setDestination(java.lang.String destination) {
		this.destination = destination;
	}

	public int getHeadCount() {
		return headCount;
	}

	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}

	public java.lang.String getOrigin() {
		return origin;
	}

	public void setOrigin(java.lang.String origin) {
		this.origin = origin;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public java.lang.String getTransportationId() {
		return transportationId;
	}

	public void setTransportationId(java.lang.String transportationId) {
		this.transportationId = transportationId;
	}

	public java.lang.String getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(java.lang.String travelClass) {
		this.travelClass = travelClass;
	}
}
