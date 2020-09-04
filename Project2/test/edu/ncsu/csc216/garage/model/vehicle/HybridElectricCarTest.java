package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;

/**
 * Test the HybridElectricCar class
 * 
 * @author Troy Boone
 *
 */
public class HybridElectricCarTest {

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
			HybridElectricCar testR1 = new HybridElectricCar(license + 1 + "," + "fumi", 2);
			HybridElectricCar testR2 = new HybridElectricCar(license + 2 + "," + "tang", 2);
			HybridElectricCar testR3 = new HybridElectricCar(license + 3, "pio", 2);
			HybridElectricCar testR4 = new HybridElectricCar(license + 4, "hunter", 2);
			HybridElectricCar testR5 = new HybridElectricCar(license + 5, "coda", 2);
			assertEquals("E Gold      NC-5841   fumi", testR1.toString());

			testR1.pickServiceBay(home);
			testR2.pickServiceBay(home);
			testR3.pickServiceBay(home);
			testR4.pickServiceBay(home);
			testR5.pickServiceBay(home);

			assertEquals("108: EMPTY", home.getBayAt(0).toString());
			assertEquals("106: EMPTY", home.getBayAt(1).toString());
			assertEquals("105: EMPTY", home.getBayAt(2).toString());
			assertEquals("103: NC-5845  coda", home.getBayAt(3).toString());
			assertEquals("102: NC-5844  hunter", home.getBayAt(4).toString());
			assertEquals("E01: NC-5843  pio", home.getBayAt(5).toString());
			assertEquals("E04: NC-5842  tang", home.getBayAt(6).toString());
			assertEquals("E07: NC-5841  fumi", home.getBayAt(7).toString());

		} catch (Exception e) {
			e.getStackTrace();
		}

		try {
			HybridElectricCar testR6 = new HybridElectricCar(license + 6, "zelse", 2);
			HybridElectricCar testR7 = new HybridElectricCar(license + 7, "kuni", 2);
			HybridElectricCar testR8 = new HybridElectricCar(license + 8, "mike", 2);
			HybridElectricCar testR9 = new HybridElectricCar(license + 9, "abyss", 2);
			testR6.pickServiceBay(home);
			testR7.pickServiceBay(home);
			testR8.pickServiceBay(home);
			testR9.pickServiceBay(home); // Failure here
			fail();
		} catch (Exception e) {
			assertEquals("There are no bay's free.", e.getMessage());
		}

	}
}
