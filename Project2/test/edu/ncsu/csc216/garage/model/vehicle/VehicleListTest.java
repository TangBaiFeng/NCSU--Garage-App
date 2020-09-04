package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

/**
 * Test the VehicleList class
 * 
 * @author troy1
 *
 */
public class VehicleListTest {

	/**
	 * Test the default constructor and the add method
	 */
	@Test
	public void testVehicleListAdd() {
		String owner = "Fumi";
		String license = "NC-584";
		// This will not have any asserts for now, as I am just using it to debug and
		// make sure the add method is working
		VehicleList test = new VehicleList();
		try {
			HybridElectricCar testEEE = new HybridElectricCar(license + 1, "Seo" + owner, 1); // fourth
			HybridElectricCar testEE = new HybridElectricCar(license + 2, "Seo" + owner, 0); // fifth
			RegularCar testRR = new RegularCar(license + 3, owner, 3); // first
			HybridElectricCar testE = new HybridElectricCar(license + 4, owner, 2); // second
			RegularCar testR = new RegularCar(license + 5, owner, 2); // third
			test.add(testEEE);
			test.add(testEE);
			test.add(testRR);
			test.add(testE);
			test.add(testR);
			assertEquals(license + 5, testR.getLicense());

		} catch (BadVehicleInformationException e) {
			fail();
			e.printStackTrace();
		}

	}

	/**
	 * Test the get and add method
	 */
	@Test
	public void testGetAndAdd() {
		String owner = "Fumi";
		String license = "NC-584";
		VehicleList test = new VehicleList();
		try {
			HybridElectricCar testEEE = new HybridElectricCar(license + 1, "Seo" + owner, 1); // fourth
			HybridElectricCar testEE = new HybridElectricCar(license + 2, "Seo" + owner, 0); // fifth
			RegularCar testRR = new RegularCar(license + 3, owner, 3); // first
			HybridElectricCar testE = new HybridElectricCar(license + 4, owner, 2); // second
			RegularCar testR = new RegularCar(license + 5, owner, 2); // third
			test.add(testEEE);
			test.add(testEE);
			test.add(testRR);
			test.add(testE);
			test.add(testR);
			assertEquals(test.get("", 0), testRR);
			assertEquals(test.get("", 1), testE);
			assertEquals(test.get("", 2), testR);
			assertEquals(test.get("", 3), testEEE);
			assertEquals(test.get("", 4), testEE);
		} catch (BadVehicleInformationException e) {
			fail();
			e.printStackTrace();
		}

	}

	/**
	 * Test the remove method
	 */
	@Test
	public void testRemove() {
		String owner = "Fumi";
		String license = "NC-584";
		VehicleList test = new VehicleList();
		try {
			HybridElectricCar testEEE = new HybridElectricCar(license + 1, "Seo" + owner, 1); // fourth
			HybridElectricCar testEE = new HybridElectricCar(license + 2, "Seo" + owner, 0); // fifth
			RegularCar testRR = new RegularCar(license + 3, owner, 3); // first
			HybridElectricCar testE = new HybridElectricCar(license + 4, owner, 2); // second
			RegularCar testR = new RegularCar(license + 5, owner, 2); // third
			test.add(testEEE);
			test.add(testEE);
			test.add(testRR);
			test.add(testE);
			test.add(testR);
			assertEquals(test.get("", 0), testRR);
			assertEquals(test.get("", 1), testE);
			assertEquals(test.get("", 2), testR);
			assertEquals(test.get("", 3), testEEE);
			assertEquals(test.get("", 4), testEE);
			// Remove from beginning
			test.remove("", 0);
			assertEquals(test.get("", 0), testE);
			assertEquals(test.get("", 1), testR);
			assertEquals(test.get("", 2), testEEE);
			assertEquals(test.get("", 3), testEE);
			// Remove from end
			test.remove("", 3);
			assertEquals(test.get("", 0), testE);
			assertEquals(test.get("", 1), testR);
			assertEquals(test.get("", 2), testEEE);
			// Remove from middle
			test.remove("", 1);
			assertEquals(test.get("", 0), testE);
			assertEquals(test.get("", 1), testEEE);
		} catch (BadVehicleInformationException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Test the remove method
	 */
	@Test
	public void testFilteredList() {
		String owner = "Fumi";
		String license = "NC-5843";
		VehicleList test = new VehicleList();
		// Test valid
		try {

			RegularCar testR = new RegularCar(license, owner, 2);
			assertEquals("R Gold      NC-5843   Fumi", testR.toString());
			HybridElectricCar testE = new HybridElectricCar(license, owner, 2);
			assertEquals("E Gold      NC-5843   Fumi", testE.toString());
			RegularCar testRR = new RegularCar(license + "5", owner, 3);
			assertEquals("R Platinum  NC-58435  Fumi", testRR.toString());
			test.add(testR);
			test.add(testRR);
			test.add(testE);
			assertEquals("R Platinum  NC-58435  Fumi\nR Gold      NC-5843   Fumi\nE Gold      NC-5843   Fumi",
					test.filteredList(""));
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Test the Scanner Constructor
	 */
	@Test
	public void testScanner() {
		try {
			Scanner listOfCars = new Scanner(new FileInputStream("test-files/cars.txt"));
			VehicleList test = new VehicleList(listOfCars);
			assertEquals("R Platinum  HI-01345  Rhyne, Lauren", test.get("", 0).toString());
		} catch (FileNotFoundException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Fixing errors from the staff test
	 */
	@Test
	public void testTSErrors() {
		VehicleList test = new VehicleList();
		try {
			RegularCar test1 = new RegularCar("license", "Doe, Elec", 3);
			RegularCar test2 = new RegularCar("license", "Doe, John", 2);
			RegularCar test3 = new RegularCar("license", "Dollar, Hank", 2);
			RegularCar test4 = new RegularCar("license", "Adams, Bob", 1);
			
			test.add(test1);
			test.add(test2);
			test.add(test3);
			test.add(test4);
			assertEquals("R Platinum  license   Doe, Elec\n" + 
					"R Gold      license   Doe, John\n" + 
					"R Gold      license   Dollar, Hank\n" + 
					"R Silver    license   Adams, Bob", test.filteredList(""));
			
			test.remove("dol", 0);
			assertEquals("R Platinum  license   Doe, Elec\n" + 
					"R Gold      license   Doe, John\n" + 
					"R Silver    license   Adams, Bob", test.filteredList(""));
		} catch (BadVehicleInformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
