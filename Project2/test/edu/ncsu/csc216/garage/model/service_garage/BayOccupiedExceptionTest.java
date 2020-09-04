package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the BayOccupiedException
 * 
 * @author Troy Boone
 *
 */
public class BayOccupiedExceptionTest {

	/**
	 * Test BayOccupiedException with default message
	 */
	@Test
	public void testBadVehicleInformationException() {
		BayOccupiedException bi = new BayOccupiedException();
		assertEquals("Service bay is already occupied.", bi.getMessage());
	}

	/**
	 * Test BayOccupiedException with custom message
	 */
	@Test
	public void testBadVehicleInformationExceptionString() {
		BayOccupiedException bi = new BayOccupiedException("Custom exception message");
		assertEquals("Custom exception message", bi.getMessage());
	}

}
