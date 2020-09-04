package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Error thrown when a vehicle attempts to choose a service bay but all
 * appropriate service bays are occupied.
 * 
 * @author Troy Boone
 */

public class NoAvailableBayException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception with default message
	 */
	public NoAvailableBayException() {
		super("There are no bay's free.");
	}

	/**
	 * Exception with custom message
	 * 
	 * @param message message to be thrown with exception.
	 */
	public NoAvailableBayException(String message) {
		super(message);
	}
}