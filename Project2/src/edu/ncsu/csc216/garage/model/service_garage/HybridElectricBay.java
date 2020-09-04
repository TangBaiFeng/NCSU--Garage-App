package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Concrete class that extends ServiceBay and represents a service bay that can
 * accommodate hybrid/electric vehicles only.
 * 
 * @author Troy Boone
 *
 */
public class HybridElectricBay extends ServiceBay {
	/**
	 * Creates a new empty bay for servicing hybrid/electric vehicles according to
	 * the current bay numbering, then increments that number while attaching the
	 * prefix "E".
	 */
	public HybridElectricBay() {
		super("E");
	}

	/**
	 * Occupies the service bay with the given vehicle
	 * 
	 * @param car the vehicle going into the bay.
	 * @throws BayCarMismatchException if the method is called with Vehicle with the
	 *                                 instanceOf RegularCar
	 */
	public void occupy(Vehicle car) throws BayOccupiedException, BayCarMismatchException {
		if (car instanceof RegularCar)
			throw new BayCarMismatchException("A regular car cannot be serviced at a electric bay.");
		super.occupy(car);
	}
}
