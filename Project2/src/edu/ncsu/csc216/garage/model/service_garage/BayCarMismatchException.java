package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Error thrown when a a vehicle attempts to choose a service bay that is empty
 * but is of the wrong type for that particular vehicle
 * 
 * @author Troy Boone
 */

public class BayCarMismatchException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception with default message
	 */
	public BayCarMismatchException() {
		super("This car cannot be serviced at this location.");
	}

	/**
	 * Exception with custom message
	 * 
	 * @param message message to be thrown with exception.
	 */
	public BayCarMismatchException(String message) {
		super(message);
	}
}