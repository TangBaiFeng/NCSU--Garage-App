package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;

/**
 * Test the HybridElectricBay class
 * @author Troy Boone
 *
 */
public class HybridElectricBayTest {

	/**
	 * Test the occupy function
	 */
	@Test
	public void testOccupy() {
		HybridElectricBay bay101 = new HybridElectricBay();
		HybridElectricBay bay102 = new HybridElectricBay();
		String license = "NC-584";
		//Valid
		try {			
			HybridElectricCar testE = new HybridElectricCar(license + 2, "Hunter", 2);
			bay101.occupy(testE);
		} catch (Exception e) {
			fail();
		}
		//Invalid
		try {
			RegularCar testR = new RegularCar(license + 1, "Tang", 2);
			bay102.occupy(testR);
			fail();
		} catch (Exception e) {
			assertEquals("A regular car cannot be serviced at a electric bay.", e.getMessage());
		}
	}

}
