package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the BadVehicleInformationException
 * 
 * @author Troy Boone
 *
 */
public class BadVehicleInformationExceptionTest {

	/**
	 * Test BadVehicleInformationException with default message
	 */
	@Test
	public void testBadVehicleInformationException() {
		BadVehicleInformationException bi = new BadVehicleInformationException();
		assertEquals("Information about the vehicle was either blank or invalid.", bi.getMessage());
	}

	/**
	 * Test BadVehicleInformationException with custom message
	 */
	@Test
	public void testBadVehicleInformationExceptionString() {
		BadVehicleInformationException bi = new BadVehicleInformationException("Custom exception message");
		assertEquals("Custom exception message", bi.getMessage());
	}

}
