package edu.ncsu.csc216.garage.model.vehicle;

/**
 * Error thrown when some field of Vehicle is filled with invalid information
 * 
 * @author Troy Boone
 */

public class BadVehicleInformationException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception with default message
	 */
	public BadVehicleInformationException() {
		super("Information about the vehicle was either blank or invalid.");
	}

	/**
	 * Exception with custom message
	 * 
	 * @param message message to be thrown with exception.
	 */
	public BadVehicleInformationException(String message) {
		super(message);
	}

}