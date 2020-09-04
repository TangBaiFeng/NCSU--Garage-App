package edu.ncsu.csc216.garage.model.dealer;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;
import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;

/**
 * Test the service manager class
 * 
 * @author troy1
 *
 */
public class ServiceManagerTest {

	/**
	 * Test the constructor that reads from a scanner
	 */
	@Test
	public void testConstructor() {
		Scanner listOfCars = null;
		try {
			listOfCars = new Scanner(new FileInputStream("test-files/cars.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServiceBay.startBayNumberingAt101();
		ServiceManager dealer = new ServiceManager(listOfCars); // Hand checked in GUI and then did copy and paste from
																// expected for formating
		assertEquals("108: EMPTY\n" + "106: EMPTY\n" + "105: EMPTY\n" + "103: EMPTY\n" + "102: EMPTY\n" + "E01: EMPTY\n"
				+ "E04: EMPTY\n" + "E07: EMPTY\n", dealer.printServiceBays());
		assertEquals("R Platinum  HI-01345  Rhyne, Lauren\n" + "R Platinum  NC-DUR1   Nicholson, Henry\n"
				+ "R Platinum  SC-RT098  Richards, Fiona\n" + "E Gold      NC-5678   Emerson, Jane\n"
				+ "R Gold      VIRG0122  Jones, Francis\n" + "E Gold      0987-NC   Nelson, Richard\n"
				+ "R Gold      IL20987   Masters, Burt\n" + "E Gold      FL-M3456  McKeel, Kenneth\n"
				+ "E Gold      ND-12345  Young, Blake\n" + "R Gold      AL-03456  Barber, David\n"
				+ "R Gold      IO-WA987  Ledbetter, Jeanne\n" + "E Gold      MA-0987   Wilson, Richard\n"
				+ "R Silver    VA-121A   Henderson, William\n" + "R Silver    MN-20134  McKeel, Robyn\n"
				+ "R Silver    SC-0I033  Smith, Amos\n" + "R Silver    678431    Bell, Frank\n"
				+ "E Silver    WX-01292  Wall, Susan\n" + "R Silver    NC-RAL0   Lamb, Bill\n"
				+ "E Silver    ND-34511  Young, Charlotte\n" + "R Silver    98234-RI  Bell, Richard\n"
				+ "R None      NC-122    Doe, John\n" + "E None      FL-09876  Peterson, Keith\n"
				+ "R None      SD-0      Young, Charles\n" + "R None      TN-3245   Lyons, Linda\n"
				+ "E None      ME-78653  Smith, Sandra\n" + "E None      CN09822   Hughes, Jack\n"
				+ "E None      NC-1233   Doe, Baby John", dealer.printWaitList(""));
	}

	/**
	 * Test the constructor that creates a new vehicle list
	 */
	@Test
	public void testAlternativeConstructor() {
		ServiceBay.startBayNumberingAt101();
		ServiceManager dealer = new ServiceManager();
		assertEquals("108: EMPTY\n" + "106: EMPTY\n" + "105: EMPTY\n" + "103: EMPTY\n" + "102: EMPTY\n" + "E01: EMPTY\n"
				+ "E04: EMPTY\n" + "E07: EMPTY\n", dealer.printServiceBays());
	}

	/**
	 * Test the putOnWaitingList method
	 */
	@Test
	public void testAdd() {
		ServiceBay.startBayNumberingAt101();
		ServiceManager dealer = new ServiceManager();
		dealer.addNewBay();
		assertEquals("109: EMPTY\n" + "108: EMPTY\n" + "106: EMPTY\n" + "105: EMPTY\n" + "103: EMPTY\n" + "102: EMPTY\n"
				+ "E01: EMPTY\n" + "E04: EMPTY\n" + "E07: EMPTY\n", dealer.printServiceBays());

		try {
			RegularCar test1 = new RegularCar("license", "Doe, Elec", 3);
			dealer.putOnWaitingList(test1);
			dealer.putOnWaitingList("R", "license", "Doe, John", 2);
			assertEquals("109: EMPTY\n" + "108: EMPTY\n" + "106: EMPTY\n" + "105: EMPTY\n" + "103: EMPTY\n"
					+ "102: EMPTY\n" + "E01: EMPTY\n" + "E04: EMPTY\n" + "E07: EMPTY\n", dealer.printServiceBays());
			assertEquals("R Platinum  license   Doe, Elec", dealer.getWaitingItem("", 0).toString());
			assertEquals("R Gold      license   Doe, John", dealer.getWaitingItem("", 1).toString());

		} catch (BadVehicleInformationException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Test the fill method
	 */
	@Test
	public void testFillBays() {
		ServiceBay.startBayNumberingAt101();
		ServiceManager dealer = new ServiceManager(null);
		assertEquals("108: EMPTY\n" + "106: EMPTY\n" + "105: EMPTY\n" + "103: EMPTY\n" + "102: EMPTY\n" + "E01: EMPTY\n"
				+ "E04: EMPTY\n" + "E07: EMPTY\n", dealer.printServiceBays());

		try {
			dealer.putOnWaitingList(new RegularCar("license", "Doe, Elec", 3));
			dealer.putOnWaitingList("R", "license", "Doe, John", 2);
			dealer.putOnWaitingList(new HybridElectricCar("license", "Dollar, Hank", 2));
			dealer.putOnWaitingList(new RegularCar("license", "Adams, Bob", 1));

			dealer.fillServiceBays();
			assertEquals("108: license  Doe, Elec\n" + "106: license  Doe, John\n" + "105: license  Adams, Bob\n"
					+ "103: EMPTY\n" + "102: EMPTY\n" + "E01: EMPTY\n" + "E04: EMPTY\n" + "E07: license  Dollar, Hank\n",
					dealer.printServiceBays());

		} catch (BadVehicleInformationException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Test the release method
	 */
	@Test
	public void testRelease() {
		ServiceBay.startBayNumberingAt101();
		ServiceManager dealer = new ServiceManager();
		assertEquals("108: EMPTY\n" + "106: EMPTY\n" + "105: EMPTY\n" + "103: EMPTY\n" + "102: EMPTY\n" + "E01: EMPTY\n"
				+ "E04: EMPTY\n" + "E07: EMPTY\n", dealer.printServiceBays());

		try {
			dealer.putOnWaitingList(new RegularCar("license", "Doe, Elec", 3));
			dealer.putOnWaitingList("R", "license", "Doe, John", 2);
			dealer.putOnWaitingList("e", "license", "Dollar, Hank", 2);
			dealer.putOnWaitingList(new RegularCar("license", "Adams, Bob", 1));

			dealer.fillServiceBays();

			assertEquals("108: license  Doe, Elec\n" + "106: license  Doe, John\n" + "105: license  Adams, Bob\n"
					+ "103: EMPTY\n" + "102: EMPTY\n" + "E01: EMPTY\n" + "E04: EMPTY\n" + "E07: license  Dollar, Hank\n",
					dealer.printServiceBays());

			assertEquals(new RegularCar("license", "Doe, Elec", 3).toString(), dealer.releaseFromService(0).toString());
			assertEquals(new HybridElectricCar("license", "Dollar, Hank", 2).toString(),
					dealer.releaseFromService(7).toString());

		} catch (BadVehicleInformationException e) {
			fail();
			e.printStackTrace();
		}
	}
	/**
	 * Test the remove method from vehicle list
	 */
	@Test
	public void testRemove() {
		ServiceBay.startBayNumberingAt101();
		ServiceManager dealer = new ServiceManager();
		dealer.addNewBay();

		try {
			dealer.putOnWaitingList(new RegularCar("license", "Doe, Elec", 3));
			dealer.putOnWaitingList("R", "license", "Doe, John", 2);
			dealer.putOnWaitingList("e", "license", "Dollar, Hank", 2);
			dealer.putOnWaitingList(new RegularCar("license", "Adams, Bob", 1));
			
			assertEquals("109: EMPTY\n" + "108: EMPTY\n" + "106: EMPTY\n" + "105: EMPTY\n" + "103: EMPTY\n"
					+ "102: EMPTY\n" + "E01: EMPTY\n" + "E04: EMPTY\n" + "E07: EMPTY\n", dealer.printServiceBays());
			assertEquals("R Platinum  license   Doe, Elec", dealer.remove("", 0).toString());
			assertEquals("R Gold      license   Doe, John", dealer.remove("", 0).toString());

		} catch (BadVehicleInformationException e) {
			fail();
			e.printStackTrace();
		}
	}
}
