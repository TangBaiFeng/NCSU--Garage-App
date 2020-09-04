package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Class representing a service bay in the garage. Service bays of this type are
 * considered “regular,” and can handle both regular and hybrid/electric
 * vehicles. The garage will start with 8 bays initially
 * 
 * @author Troy Boone
 *
 */
public class ServiceBay {
	/** Boolean statement for if bay is full */
	private boolean occupied;
	/** The ID of the bay */
	private String bayID;
	/** The vehicle currently in the bay. */
	private Vehicle myVehicle;
	/** The next number of a created bay. */
	private static int nextNumber = 1;

	/**
	 * Resets the service bay numbering to start from 1
	 */
	public static void startBayNumberingAt101() {
		nextNumber = 1;
	}

	/**
	 * Default constructor. Creates a new empty service bay according to the current
	 * bay numbering, then increments that number. This bay accepts both hybrid and
	 * electric cars
	 */
	public ServiceBay() {
		this.occupied = false;
		this.myVehicle = null;
		String id = String.format("%02d", nextNumber);
		this.bayID = "1" + id;
		nextNumber++;
	}

	/**
	 * Alternative constructor. Creates a new empty service bay according to the
	 * current bay numbering and by attaching the prefix, then increments that
	 * number. This bay accepts both hybrid and electric cars
	 * 
	 * @param prefix the beginning of the bay numbering
	 */
	public ServiceBay(String prefix) {
		if (prefix == null || prefix.trim().equals("")) {
			prefix = "1";
		}
		this.occupied = false;
		this.myVehicle = null;
		String id = String.format("%02d", nextNumber);
		this.bayID = prefix.trim().substring(0, 1) + id;
		nextNumber++;
	}

	/**
	 * Simple getter for bayID
	 * 
	 * @return the bayID
	 */
	public String getBayID() {
		return bayID;
	}

	/**
	 * Simple getter for occupied state
	 * 
	 * @return true if the bay is filled, false otherwise
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Releases the bay to be occupied another vehicle
	 * 
	 * @return the vehicle that was in the bay.
	 */
	public Vehicle release() {
		this.occupied = false;
		Vehicle releasedVehicle = myVehicle;
		this.myVehicle = null;
		return releasedVehicle;

	}

	/**
	 * Occupies the service bay with the given vehicle
	 * 
	 * @param car the vehicle going into the bay.
	 * @throws BayOccupiedException if the bay already contains a vehicle
	 */
	public void occupy(Vehicle car) throws BayOccupiedException, BayCarMismatchException {
		if (isOccupied())
			throw new BayOccupiedException("This bay is already occupied.");
		this.myVehicle = car;
		this.occupied = true;
	}

	/**
	 * Formats the string representation of the service bay
	 * 
	 * @return a string representation of the service bay object
	 */
	public String toString() {
		String returnedString = getBayID() + ": ";
		if (occupied) {
			returnedString += String.format("%-8s", myVehicle.getLicense()) + " " + myVehicle.getName();
		} else {
			returnedString += "EMPTY";
		}
		return returnedString;

	}

}
