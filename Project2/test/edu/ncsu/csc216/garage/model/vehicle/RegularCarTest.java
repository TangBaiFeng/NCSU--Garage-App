package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;

/**
 * Test the RegularCar occupy method
 * 
 * @author troy1
 *
 */
public class RegularCarTest {

	/**
	 * Test the pickServiceBay method
	 */
	@Test
	public void testPickServiceBay() {
		String license = "NC-584";
		// Test valid
		ServiceBay.startBayNumberingAt101();
		Garage home = new Garage();
		try {
			RegularCar testR1 = new RegularCar(license + 1 + "," + "fumi", 2);
			RegularCar testR2 = new RegularCar(license + 2 + "," + "tang", 2);
			RegularCar testR3 = new RegularCar(license + 3, "pio", 2);
			RegularCar testR4 = new RegularCar(license + 4, "hunter", 2);
			RegularCar testR5 = new RegularCar(license + 5, "coda", 2);
			assertEquals("R Gold      NC-5841   fumi", testR1.toString());

			testR1.pickServiceBay(home);
			testR2.pickServiceBay(home);
			testR3.pickServiceBay(home);
			testR4.pickServiceBay(home);
			testR5.pickServiceBay(home);

			assertEquals("108: NC-5841  fumi", home.getBayAt(0).toString());
			assertEquals("106: NC-5842  tang", home.getBayAt(1).toString());
			assertEquals("105: NC-5843  pio", home.getBayAt(2).toString());
			assertEquals("103: NC-5844  hunter", home.getBayAt(3).toString());
			assertEquals("102: NC-5845  coda", home.getBayAt(4).toString());
			assertEquals("E01: EMPTY", home.getBayAt(5).toString());
			assertEquals("E04: EMPTY", home.getBayAt(6).toString());
			assertEquals("E07: EMPTY", home.getBayAt(7).toString());

		} catch (Exception e) {
			e.getStackTrace();
		}

		try {
			RegularCar testR6 = new RegularCar(license + 6, "zelse", 2);
			testR6.pickServiceBay(home);
			fail();
		} catch (Exception e) {
			assertEquals("There are no bay's free.", e.getMessage());
		}
	}

}
