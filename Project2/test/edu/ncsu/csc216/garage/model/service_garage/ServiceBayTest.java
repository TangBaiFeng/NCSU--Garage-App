package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Test the ServiceBay class
 * 
 * @author Troy Boone
 *
 */
public class ServiceBayTest {

	/**
	 * Test the constructor
	 */
	@Test
	public void testServiceBay() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay bay101 = new ServiceBay();
		ServiceBay bay102 = new ServiceBay();
		ServiceBay bay103 = new ServiceBay("");
		ServiceBay bay104 = new ServiceBay(null);
		ServiceBay bayE05 = new ServiceBay("E");
		ServiceBay bayE06 = new ServiceBay("E");
		ServiceBay bayE07 = new ServiceBay("E");
		ServiceBay bayE08 = new ServiceBay("E");
		assertEquals("101: EMPTY", bay101.toString());
		assertEquals("102: EMPTY", bay102.toString());
		assertEquals("103: EMPTY", bay103.toString());
		assertEquals("104: EMPTY", bay104.toString());
		assertEquals("E05: EMPTY", bayE05.toString());
		assertEquals("E06: EMPTY", bayE06.toString());
		assertEquals("E07: EMPTY", bayE07.toString());
		assertEquals("E08: EMPTY", bayE08.toString());
		assertEquals("class edu.ncsu.csc216.garage.model.service_garage.ServiceBay", bayE08.getClass().toString());

	}

	/**
	 * Test the occupy method and release method
	 * 
	 * @throws BadVehicleInformationException
	 */
	@Test
	public void testOccupyAndRelease() throws BadVehicleInformationException {
		ServiceBay.startBayNumberingAt101();
		ServiceBay bay101 = new ServiceBay();
		ServiceBay bay102 = new ServiceBay();

		assertEquals("101: EMPTY", bay101.toString());
		assertEquals("102: EMPTY", bay102.toString());

		String license = "NC-584";
		RegularCar testR = new RegularCar(license + 1, "Tang", 2);
		HybridElectricCar testE = new HybridElectricCar(license + 2, "Hunter", 2);
		RegularCar testRR = new RegularCar(license + 3, "Fumi", 3);

		assertFalse(bay101.isOccupied());
		assertFalse(bay102.isOccupied());
		try {
			bay101.occupy(testR);
			bay102.occupy(testE);
			assertTrue(bay101.isOccupied());
			assertTrue(bay102.isOccupied());
		} catch (BayOccupiedException | BayCarMismatchException e) {
			fail();
		}
		try {
			bay101.occupy(testRR);
			fail();
		} catch (Exception e) {
			assertEquals("This bay is already occupied.", e.getMessage());
		}
		assertTrue(bay101.isOccupied());
		assertTrue(bay102.isOccupied());
		assertEquals("101: NC-5841  Tang", bay101.toString());
		assertEquals("102: NC-5842  Hunter", bay102.toString());
		
		Vehicle testRReleased = bay101.release();
		Vehicle testEReleased = bay102.release();
		
		assertEquals(testRReleased, testR);
		assertEquals(testEReleased, testE);

	}
}
