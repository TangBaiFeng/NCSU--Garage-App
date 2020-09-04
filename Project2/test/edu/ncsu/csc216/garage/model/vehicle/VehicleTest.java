package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;

/**
 * Test the Vehicle object
 * 
 * @author Troy Boone
 *
 */
public class VehicleTest {

	/**
	 * Test the standard constructor
	 */
	@Test
	public void testVehicle() {
		String owner = "Fumi";
		String license = "NC-5843";
		// Invalid status
		try {
			new RegularCar(license, owner, -1);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("Invalid tier.", e.getMessage());
		}
		try {
			new RegularCar(license, owner, 4);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("Invalid tier.", e.getMessage());
		}
		// Invalid name
		try {
			new RegularCar(license, null, 2);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("Owner name cannot be blank.", e.getMessage());
		}
		try {
			new RegularCar(license, "", -1);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("Owner name cannot be blank.", e.getMessage());
		}
		// Invalid license
		try {
			new RegularCar("", owner, 2);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be blank.", e.getMessage());
		}
		try {
			new RegularCar(null, owner, 2);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be blank.", e.getMessage());
		}
		try {
			new RegularCar("nc 585", owner, 2);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot have a space in it.", e.getMessage());
		}
		try {
			new RegularCar("nc-5858585888", owner, 2);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("License can be at most 8 characters.", e.getMessage());
		}
		try {
			new RegularCar(" ", owner, 2);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be blank.", e.getMessage());
		}

	}

	/**
	 * Test the toString method
	 */
	@Test
	public void testToString() {
		String owner = "Fumi";
		String license = "NC-5843";
		// Test valid
		try {
			RegularCar testR = new RegularCar(license, owner, 2);
			assertEquals("R Gold      NC-5843   Fumi", testR.toString());
			HybridElectricCar testE = new HybridElectricCar(license, owner, 2);
			assertEquals("E Gold      NC-5843   Fumi", testE.toString());
			RegularCar testRR = new RegularCar(license + "5", owner, 3);
			assertEquals("R Platinum  NC-58435  Fumi", testRR.toString());
			HybridElectricCar testEE = new HybridElectricCar(license, "Seo" + owner, 0);
			assertEquals("E None      NC-5843   SeoFumi", testEE.toString());
			HybridElectricCar testEEE = new HybridElectricCar(license, "Seo" + owner, 1);
			assertEquals("E Silver    NC-5843   SeoFumi", testEEE.toString());
		} catch (BadVehicleInformationException e) {
			fail();

		}
	}

	/**
	 * Test the compareToTier method
	 */
	@Test
	public void testCompareToTier() {
		try {
			String owner = "Fumi";
			String license = "NC-5843";
			RegularCar testR = new RegularCar(license, owner, 2);
			HybridElectricCar testE = new HybridElectricCar(license, owner, 2);
			RegularCar testRR = new RegularCar(license + "5", owner, 3);
			HybridElectricCar testEEE = new HybridElectricCar(license, "Seo" + owner, 1);

			assertEquals(0, testR.compareToTier(testE));
			assertEquals(-1, testR.compareToTier(testRR));
			assertEquals(1, testR.compareToTier(testEEE));
		} catch (BadVehicleInformationException e) {
			fail();
		}

	}

	/**
	 * Test the compareToTier method
	 */
	@Test
	public void testMeetsFilter() {
		try {
			String owner = "Fumi";
			String license = "NC-5843";
			RegularCar testR = new RegularCar(license, owner, 2);
			HybridElectricCar testEE = new HybridElectricCar(license, "Seo" + owner, 0);

			assertTrue(testR.meetsFilter("Fumi"));
			assertFalse(testR.meetsFilter("Seo"));
			assertFalse(testEE.meetsFilter("Fumi"));
			assertTrue(testEE.meetsFilter("Seo"));

			RegularCar teachingTest = new RegularCar("MA-1", "My Jeep", 1);
			assertTrue(teachingTest.meetsFilter("my j"));
		} catch (BadVehicleInformationException e) {
			fail();
		}

	}

	/**
	 * Test the overall pickServiceBay with both classes
	 */
	@Test
	public void testPickServiceBay() {
		String license = "NC-584";
		ServiceBay.startBayNumberingAt101();
		Garage home = new Garage();
		try {
			RegularCar test1 = new RegularCar(license + 1, "tang", 2);
			HybridElectricCar test2 = new HybridElectricCar(license + 2, "gs", 2);
			RegularCar test3 = new RegularCar(license + 3, "slim", 3);
			HybridElectricCar test4 = new HybridElectricCar(license + 4, "Seo", 0);
			HybridElectricCar test5 = new HybridElectricCar(license + 5, "fumi", 1);
			HybridElectricCar test6 = new HybridElectricCar(license + 6, "kuni", 1);
			RegularCar test7 = new RegularCar(license + 7, "hunter", 3);
			RegularCar test8 = new RegularCar(license + 8, "gsk", 3);
			test1.pickServiceBay(home);
			test2.pickServiceBay(home);
			test3.pickServiceBay(home);
			test4.pickServiceBay(home);
			test5.pickServiceBay(home);
			test6.pickServiceBay(home);
			test7.pickServiceBay(home);
			test8.pickServiceBay(home);

			assertEquals("108: NC-5841  tang", home.getBayAt(0).toString());
			assertEquals("106: NC-5843  slim", home.getBayAt(1).toString());
			assertEquals("105: NC-5847  hunter", home.getBayAt(2).toString());
			assertEquals("103: NC-5848  gsk", home.getBayAt(3).toString());
			assertEquals("102: NC-5846  kuni", home.getBayAt(4).toString());
			assertEquals("E01: NC-5845  fumi", home.getBayAt(5).toString());
			assertEquals("E04: NC-5844  Seo", home.getBayAt(6).toString());
			assertEquals("E07: NC-5842  gs", home.getBayAt(7).toString());

		} catch (Exception e) {
			fail();
		}

	}

	/**
	 * Test the alternative Constructor
	 */
	@Test
	public void testVehicleInfoConstructor() {
		String owner = "Fumi";
		String license = "NC-5843";
		// Test valid
		try {
			RegularCar testR = new RegularCar(license + "," + owner, 2);
			assertEquals("R Gold      NC-5843   Fumi", testR.toString());
			HybridElectricCar testE = new HybridElectricCar(license + "," + owner, 2);
			assertEquals("E Gold      NC-5843   Fumi", testE.toString());
			RegularCar testRR = new RegularCar(license + "5" + "," + owner, 3);
			assertEquals("R Platinum  NC-58435  Fumi", testRR.toString());
			HybridElectricCar testEE = new HybridElectricCar(license + "," + "Seo" + owner, 0);
			assertEquals("E None      NC-5843   SeoFumi", testEE.toString());
			HybridElectricCar testEEE = new HybridElectricCar(license + "," + "Seo" + owner, 1);
			assertEquals("E Silver    NC-5843   SeoFumi", testEEE.toString());
		} catch (BadVehicleInformationException e) {
			fail();

		}
	}
	

}
