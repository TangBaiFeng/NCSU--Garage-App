package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the BayCarMismatchException
 * 
 * @author Troy Boone
 *
 */
public class BayCarMismatchExceptionTest {

	/**
	 * Test BayCarMismatchException with default message
	 */
	@Test
	public void testBayCarMismatchException() {
		BayCarMismatchException bi = new BayCarMismatchException();
		assertEquals("This car cannot be serviced at this location.", bi.getMessage());
	}

	/**
	 * Test BayCarMismatchException with custom message
	 */
	@Test
	public void testBayCarMismatchExceptionString() {
		BayCarMismatchException bi = new BayCarMismatchException("Custom exception message");
		assertEquals("Custom exception message", bi.getMessage());
	}

}