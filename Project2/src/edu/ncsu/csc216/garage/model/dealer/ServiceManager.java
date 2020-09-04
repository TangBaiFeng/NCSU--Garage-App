package edu.ncsu.csc216.garage.model.dealer;

import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;
import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Tiered;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;
import edu.ncsu.csc216.garage.model.vehicle.VehicleList;

/**
 * Concrete class for managing all service bays and vehicles awaiting service
 * 
 * @author Troy Boone
 *
 */
public class ServiceManager implements Manageable {

	/** The garage of service bays. */
	private Garage myGarage;
	/** The list of cars waiting to be serviced. */
	private VehicleList waitingCars;

	/**
	 * Default Constructor that initialized the ServiceManager.
	 */
	public ServiceManager() {
		myGarage = new Garage();
		waitingCars = new VehicleList();
	}

	/**
	 * Alternative Constructor that initialized the ServiceManager by reading the
	 * input from a scanner
	 * 
	 * @param s the scanner being read from
	 */
	public ServiceManager(Scanner s) {
		myGarage = new Garage();
		if (s == null) {
			waitingCars = new VehicleList();
		} else {
			waitingCars = new VehicleList(s);
		}
	}

	@Override
	public void putOnWaitingList(String vehicleType, String license, String name, int tier) {
		Vehicle car = null;
		vehicleType = vehicleType.strip();

		try {
			if (vehicleType.equalsIgnoreCase("E")) {
				car = new HybridElectricCar(license, name, tier);
				putOnWaitingList(car);
			} else if (vehicleType.equalsIgnoreCase("R")) {

				car = new RegularCar(license, name, tier);
				putOnWaitingList(car);
			}
		} catch (BadVehicleInformationException e) {
			return;
		}
	}

	@Override
	public void putOnWaitingList(Tiered vehicle) { // This is probably what they want
		if (vehicle != null) {
			waitingCars.add((Vehicle) vehicle);
		}

	}

	@Override
	public Tiered getWaitingItem(String filter, int position) { // This is probably what they want
		return waitingCars.get(filter, position);
	}

	@Override
	public Tiered remove(String filter, int position) {
		return waitingCars.remove(filter, position);
	}

	@Override
	public void fillServiceBays() {
		int i = 0;
		while (myGarage.numberOfEmptyBays() > 0) {
			try {
				Vehicle addedCar = waitingCars.get("", i);
				if (addedCar == null)
					return;
				addedCar.pickServiceBay(myGarage);
				waitingCars.remove("", i);
			} catch (NoAvailableBayException e) {
				i++; // If vehicle doesn't have a bay, move onto the next bay
			}
			catch (NoSuchElementException e) {
				return;
			}
		}

	}

	@Override
	public Tiered releaseFromService(int bay) {
		return myGarage.release(bay);
	}

	@Override
	public void addNewBay() {
		myGarage.addRepairBay();

	}

	@Override
	public String printWaitList(String filter) {
		return waitingCars.filteredList(filter);
	}

	@Override
	public String printServiceBays() {
		String bays = "";
		for (int i = 0; i < myGarage.getSize(); i++) {
			bays += myGarage.getBayAt(i).toString() + "\n";
		}
		return bays;
	}
}
