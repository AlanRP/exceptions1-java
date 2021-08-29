package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.ecxeptions.DomainException;

public class Reservation {

	private int roomNumber;
	private Date checkIn;
	private Date checkout;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(int roomNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out must be after check-in date.");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkout = checkOut;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckout() {
		return checkout;
	}

	public long duration() {
		long diff = checkout.getTime() - checkIn.getTime(); // getTime() returns in ms
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // Convert ms to Days
	}

	public void updateDates(Date checkIn, Date checkOut) {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for updates must be future date.");
		} 
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out must be after check-in date.");
		}
		this.checkIn = checkIn;
		this.checkout = checkOut;
	}

	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkout)
				+ ", "
				+ duration()
				+ " nights.";
	}

}
