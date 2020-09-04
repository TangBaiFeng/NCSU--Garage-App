package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.BayCarMismatchException;
import edu.ncsu.csc216.garage.model.service_garage.BayOccupiedException;
import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Child class of Vehicle. Extends the Vehicle object by allowing it to be put
 * inside of HybridElectricBays, and have a different return pattern to string
 * 
 * @author Troy Boone
 *
 */
public class HybridElectricCar extends Vehicle {

	/**
	 * Calls parent classes constructor to construct the Hybrid object
	 * 
	 * @param license the cars license
	 * @param name    the owners name
	 * @param status  the owners tier of service
	 * @throws BadVehicleInformationException if any of the 3 fields in the method
	 *                                        are considered invalid
	 */
	public HybridElectricCar(String license, String name, int status) throws BadVehicleInformationException {
		super(license, name, status);
	}

	/**
	 * Calls parent classes constructor to construct the Hybrid object
	 * 
	 * @param info   the cars license and owners name
	 * @param status the owners tier of service
	 * @throws BadVehicleInformationException if any of the 3 fields in the method
	 *                                        are considered invalid
	 */
	public HybridElectricCar(String info, int status) throws BadVehicleInformationException {
		super(info, status);
	}

	/**
	 * Formats a string representation of the Vehicle Object. Adds the char 'E' to
	 * the front to indicate it is a electric car
	 * 
	 * @return a string that displays the vehicle in the format of type, tier,
	 *         license, and owners name.
	 */
	public String toString() {
		return "E " + super.toString();

	}

	/**
	 * Puts the car in the correct service bay. It will go into the first HVEC bay,
	 * and if there are no bays available then it will be put into standard bay
	 * 
	 * @throws NoAvailableBayException if no bays are available
	 * @param g the main garage holding bays
	 */
	public void pickServiceBay(Garage g) throws NoAvailableBayException {
		try {
			for (int i = g.getSize() - 1; i > -1; i--) {
				if (!g.getBayAt(i).isOccupied()) {

					g.getBayAt(i).occupy(this);
					g.decreaseNumEmptyBays();
					return;
				}
			}
		} catch (BayOccupiedException | BayCarMismatchException e) {
			e.printStackTrace();
		}

		throw new NoAvailableBayException();
	}
}
