package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.BayCarMismatchException;
import edu.ncsu.csc216.garage.model.service_garage.BayOccupiedException;
import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Child class of Vehicle. Extends the Vehicle object by allowing it to be put
 * inside of only standard ServiceBays, and have a different return pattern to
 * string
 * 
 * @author Troy Boone
 *
 */
public class RegularCar extends Vehicle {
	/**
	 * Calls parent classes constructor to construct the Regular object
	 * 
	 * @param license the cars license
	 * @param name    the owners name
	 * @param status  the owners tier of service
	 * @throws BadVehicleInformationException if any of the 3 fields in the method
	 *                                        are considered invalid
	 */
	public RegularCar(String license, String name, int status) throws BadVehicleInformationException {
		super(license, name, status);
	}

	/**
	 * Calls parent classes constructor to construct the Regular object
	 * 
	 * @param info   the cars license and owners name
	 * @param status the owners tier of service
	 * @throws BadVehicleInformationException if any of the 3 fields in the method
	 *                                        are considered invalid
	 */
	public RegularCar(String info, int status) throws BadVehicleInformationException {
		super(info, status);
	}

	/**
	 * Formats a string representation of the Vehicle Object. Adds the char 'R' to
	 * the front to indicate it is a regular car
	 * 
	 * @return a string that displays the vehicle in the format of type, tier,
	 *         license, and owners name.
	 */
	public String toString() {
		return "R " + super.toString();

	}

	/**
	 * Puts the car in the correct service bay. It will go into the first standard
	 * bay available
	 * 
	 * @throws NoAvailableBayException if no bays are available
	 * @param g the garage the service bay is in
	 */
	public void pickServiceBay(Garage g) throws NoAvailableBayException {
		for (int i = 0; i < g.getSize(); i++) {
			if (!g.getBayAt(i).isOccupied() && g.getBayAt(i).getClass().toString()
					.equals("class edu.ncsu.csc216.garage.model.service_garage.ServiceBay")) {
				try {
					g.getBayAt(i).occupy(this);
					g.decreaseNumEmptyBays();
					return;
				} catch (BayCarMismatchException | BayOccupiedException e) {
					e.getMessage();
				}
			}
		}
		throw new NoAvailableBayException();
	}
}
