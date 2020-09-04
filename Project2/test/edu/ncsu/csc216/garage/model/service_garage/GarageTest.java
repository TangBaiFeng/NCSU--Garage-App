package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Test Garage Class
 * 
 * @author Troy Boone
 *
 */
public class GarageTest {

	/**
	 * Test the Garage Constructor
	 */
	@Test
	public void testGarage() {
		ServiceBay.startBayNumberingAt101();
		Garage home = new Garage();
		assertEquals("108", home.getBayAt(0).getBayID());
		assertEquals("106", home.getBayAt(1).getBayID());
		assertEquals("105", home.getBayAt(2).getBayID());
		assertEquals("103", home.getBayAt(3).getBayID());
		assertEquals("102", home.getBayAt(4).getBayID());
		assertEquals("E01", home.getBayAt(5).getBayID());
		assertEquals("E04", home.getBayAt(6).getBayID());
		assertEquals("E07", home.getBayAt(7).getBayID());

		assertEquals(8, home.numberOfEmptyBays());
		assertEquals(8, home.getSize());
	}

	/**
	 * Test the Garage Constructor
	 */
	@Test
	public void testAddRepair() {
		ServiceBay.startBayNumberingAt101();
		Garage home = new Garage();
		assertEquals("108", home.getBayAt(0).getBayID());
		home.addRepairBay();
		assertEquals(9, home.numberOfEmptyBays());
		assertEquals(9, home.getSize());
	}

	/**
	 * Test the Garage Constructor
	 */
	@Test
	public void testRelease() {
		ServiceBay.startBayNumberingAt101();
		Garage home = new Garage();
		assertEquals("108", home.getBayAt(0).getBayID());
		assertEquals(8, home.numberOfEmptyBays());
		HybridElectricCar test1 = null;
		HybridElectricCar test2 = null;
		RegularCar test3 = null;
		try {
			test1 = new HybridElectricCar("NC-4851", "Seo", 3);
			test2 = new HybridElectricCar("NC-4854", "Fumi", 3);
			test3 = new RegularCar("NC-4855", "Tang", 3);
			test1.pickServiceBay(home);
			test2.pickServiceBay(home);
			test3.pickServiceBay(home);
		} catch (BadVehicleInformationException | NoAvailableBayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("E07: NC-4851  Seo", home.getBayAt(7).toString());
		assertEquals("E04: NC-4854  Fumi", home.getBayAt(6).toString());
		assertEquals("108: NC-4855  Tang", home.getBayAt(0).toString());

		assertEquals(5, home.numberOfEmptyBays());
		Vehicle test1Returned = home.release(7);
		Vehicle test2Returned = home.release(6);
		Vehicle test3Returned = home.release(0);
		assertEquals(8, home.numberOfEmptyBays());
		assertEquals(test1, test1Returned);
		assertEquals(test2, test2Returned);
		assertEquals(test3, test3Returned);
		assertEquals("E01: EMPTY", home.getBayAt(5).toString());
		assertEquals("E04: EMPTY", home.getBayAt(6).toString());
		assertEquals("108: EMPTY", home.getBayAt(0).toString());

	}

	/**
	 * Test the get Errors from ArrayList
	 */
	@Test
	public void testGet() {
		ServiceBay.startBayNumberingAt101();
		Garage home = new Garage();
		try {
			home.getBayAt(9);
		} catch (Exception e) {
			assertEquals("java.lang.IndexOutOfBoundsException", e.toString());
		}
	}
}
