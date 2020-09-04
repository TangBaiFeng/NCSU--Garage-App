package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the NoAvailableBayException
 * 
 * @author Troy Boone
 *
 */
public class NoAvailableBayExceptionTest {

	/**
	 * Test NoAvailableBayException with default message
	 */
	@Test
	public void testNoAvailableBayException() {
		NoAvailableBayException bi = new NoAvailableBayException();
		assertEquals("There are no bay's free.", bi.getMessage());
	}

	/**
	 * Test NoAvailableBayException with custom message
	 */
	@Test
	public void testNoAvailableBayExceptionString() {
		NoAvailableBayException bi = new NoAvailableBayException("Custom exception message");
		assertEquals("Custom exception message", bi.getMessage());
	}

}
