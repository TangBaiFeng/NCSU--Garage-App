package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Error thrown when a vehicle attempts to choose a service bay that is occupied
 * 
 * @author Troy Boone
 */

public class BayOccupiedException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception with default message
	 */
	public BayOccupiedException() {
		super("Service bay is already occupied.");
	}

	/**
	 * Exception with custom message
	 * 
	 * @param message message to be thrown with exception.
	 */
	public BayOccupiedException(String message) {
		super(message);
	}
}