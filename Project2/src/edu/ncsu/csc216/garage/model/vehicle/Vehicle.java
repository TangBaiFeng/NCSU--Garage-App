package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Abstract class for the Vehicle object at the garage. This class contains
 * getters and setters for the license, name, and tier of the car, as well as
 * Implementing the Tiered interface. It has 2 child classes, RegularCar and
 * HybridElectricCar
 * 
 * @author Troy Boone
 *
 */
public abstract class Vehicle implements Tiered {
	/** The license of the vehicle owner */
	private String license;
	/** The name of the vehicle owner. */
	private String name;
	/** The tier of plan the owner has. */
	private int tierIndex;
	/** The string representation of tiers */
	static final String[] CUSTOMER_TIER = { "None", "Silver", "Gold", "Platinum" };

	/**
	 * Default constructor. Creates Vehicle with fields license, owner name, and
	 * tier status
	 * 
	 * @param license the cars license
	 * @param name    the owners name
	 * @param status  the owners tier of service
	 * @throws BadVehicleInformationException if any of the 3 fields in the method
	 *                                        are considered invalid
	 */
	public Vehicle(String license, String name, int status) throws BadVehicleInformationException {
		setLicense(license);
		setName(name);
		setTier(status);
	}

	/**
	 * Alternative constructor. Creates Vehicle with a string combining name and
	 * license, and tier status
	 * 
	 * @param info   the vehicle owners name and license in the format of
	 *               [license](some amount of white space)[name]
	 * @param status the owners tier of service
	 * @throws BadVehicleInformationException if any of the 3 fields in the method
	 *                                        are considered invalid
	 */
	public Vehicle(String info, int status) throws BadVehicleInformationException { // TODO figure out how to implement
																					// this
		if (info == null) {
			throw new BadVehicleInformationException();
		}
		info = info.trim();
		String[] userInfo = info.split(",", 2);
		setLicense(userInfo[0]);
		setName(userInfo[1]);
		setTier(status);
	}

	/**
	 * Abstract method to assign vehicle to bay. Each child class will have
	 * different requirements
	 * 
	 * @param g the garage holding the bays
	 * @throws NoAvailableBayException if there are no bays available for the
	 *                                 vehicle
	 */
	public abstract void pickServiceBay(Garage g) throws NoAvailableBayException;

	/**
	 * Checks to see is filter is equal to the prefix of the users name
	 * 
	 * @param filter the prefix to look for
	 * @return true if the field is empty or the prefix is found, false otherwise
	 */
	public boolean meetsFilter(String filter) {
		if (filter == null || filter.contentEquals("") || name.toLowerCase().startsWith(filter.toLowerCase().trim())) {
			return true;
		}
		return false;

	}

	/**
	 * Formats a string representation of the Vehicle Object. Child classes will add
	 * type field
	 * 
	 * @return a string that displays the vehicle in the format of type, tier,
	 *         license, and owners name.
	 */
	public String toString() {

		return String.format("%-10s", CUSTOMER_TIER[tierIndex]) + String.format("%-10s", getLicense()) + getName();

	}

	/**
	 * Simple getter for name
	 * 
	 * @return the owners name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Simple getter for license
	 * 
	 * @return the owners license
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * Compare the tier status of this object with another
	 * 
	 * @param tier the tier as a integer
	 * @return 0 if the tiers are the same, -1 if this object is lower then the
	 *         compared, and 1 for if this object is higher then the compared
	 */
	public int compareToTier(Tiered tier) {
		if (getTier() == tier.getTier())
			return 0;
		else if (getTier() < tier.getTier())
			return -1;
		else
			return 1;
	}

	/**
	 * Simple getter for service tier
	 * 
	 * @return the number representing the service tier
	 */
	public int getTier() {
		return tierIndex;
	}

	/**
	 * Simple setter for tier
	 * 
	 * @param status the number that represents the status
	 * @throws BadVehicleInformationException if status is not within the acceptable
	 *                                        range of (0-3)
	 */
	public void setTier(int status) throws BadVehicleInformationException {
		if (status > 3 || status < 0) {
			throw new BadVehicleInformationException("Invalid tier.");
		}
		this.tierIndex = status;
	}

	/**
	 * Simple setter for name
	 * 
	 * @param name the owners name
	 * @throws BadVehicleInformationException if the name is null or empty
	 */
	private void setName(String name) throws BadVehicleInformationException {
		if (name == null || name.trim().equals(""))
			throw new BadVehicleInformationException("Owner name cannot be blank.");
		this.name = name.trim();
	}

	/**
	 * Simple setter for license
	 * 
	 * @param license the owners license
	 * @throws BadVehicleInformationException if the license is null or empty, there
	 *                                        is a space in the string, or the
	 *                                        length is more then 8
	 */
	private void setLicense(String license) throws BadVehicleInformationException {
		if (license == null || license.trim().equals(""))
			throw new BadVehicleInformationException("License cannot be blank.");
		license = license.trim();

		if (license.indexOf(" ") != -1)
			throw new BadVehicleInformationException("License cannot have a space in it.");
		if (license.length() > 8)
			throw new BadVehicleInformationException("License can be at most 8 characters.");
		this.license = license;
	}
}
