package edu.ncsu.csc216.garage.model.vehicle;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.garage.model.util.SimpleIterator;

/**
 * Concrete class for maintaining the list of vehicles
 * 
 * @author troy1
 *
 */
public class VehicleList {
	/** First node in the list */
	private Node head;

	/**
	 * Creates a list of vehicles from a Scanner
	 * 
	 * @param s the input list of vehicles from the file
	 */
	public VehicleList(Scanner s) {
		head = null;
		String[] carInfo = new String[3];
		int status = 0;
//		int loops = 0; For debugging while looping through scanner
		while (s.hasNext()) {

			try {
				carInfo[0] = s.next();
				status = s.nextInt();
				carInfo[1] = s.next();
				carInfo[2] = s.nextLine();
				if (carInfo[0].equalsIgnoreCase("E")) {
					add(new HybridElectricCar(carInfo[1], carInfo[2], status));
				} else if (carInfo[0].equalsIgnoreCase("R")) {
					add(new RegularCar(carInfo[1], carInfo[2], status));
				}
			} catch (BadVehicleInformationException e) {
				continue;
			} catch (InputMismatchException e) {
				continue;
			}
//				loops++;
		}

	}

	/**
	 * Creates an empty list of vehicles
	 */
	public VehicleList() {
		head = null;
	}

	/**
	 * Returns a SimpleIterator initialized to point to the first element in the
	 * list
	 * 
	 * @return a pointer to the first element
	 */
	public SimpleIterator<Vehicle> iterator() {
		SimpleIterator<Vehicle> cursor = new Cursor();
		return cursor;
	}

	/**
	 * Removes the vehicle that appears in the filtered list in the given position
	 * 
	 * @param filter   the prefix for the owners name
	 * @param position the index of the vehicle on the list
	 * @return the removed vehicle
	 */
	public Vehicle remove(String filter, int position) {
		Node traveler = head;
		if (traveler == null) {
			return null;
		}
		int positionInFilter = -1; //TODO full debug to figure out why -1 works
		// First element being removed
		if (traveler.v.meetsFilter(filter)) {
			positionInFilter++;
			if (position == 0) {
				Vehicle v = traveler.v;
				head = traveler.next;
				return v;
			}
		}
		// All other cases
		while (traveler.next != null) {
			if (traveler.next.v.meetsFilter(filter)) {
				positionInFilter++;
			}
			if (positionInFilter == position) {
				Vehicle v = traveler.next.v;
				traveler.next = traveler.next.next;
				return v;
			} else {
				traveler = traveler.next;
			}
		}

		return null;
	}

	/**
	 * Simple getter for vehicle on waiting list
	 * 
	 * @param filter   the prefix for the owners name
	 * @param position the index of the vehicle on the list
	 * @return the matching vehicle
	 */
	public Vehicle get(String filter, int position) {
		SimpleIterator<Vehicle> iterator = iterator();
		while (iterator.hasNext()) {
			Vehicle travelerCar = iterator.next();
			if (travelerCar.meetsFilter(filter)) {
				position--;
			}
			if (position == -1) {
				return travelerCar;
			}

		}
		return null;

	}

	/**
	 * Adds the given vehicle to the list of those awaiting service
	 * 
	 * @param car the new vehicle being put on the waiting list
	 */
	public void add(Vehicle car) { // TODO method confirmed working, add get to test
		Node newNode = new Node(car, null);
		// First element being added
		if (head == null) {
			head = newNode;
			return;
		}
		// First element in list is of lower tier
		if (head.v.getTier() < car.getTier()) {
			newNode.next = head;
			head = newNode;
			return;
		}
		// All other cases
		Node traveler = head;
		while (traveler.next != null) {
			if (traveler.next.v.getTier() < car.getTier()) {
				newNode.next = traveler.next;
				traveler.next = newNode;
				return;
			} else {
				traveler = traveler.next;
			}

		}
		// Last element
		if (traveler.next == null) {
			traveler.next = newNode;
		}

	}

	/**
	 * TODO ask on piazza about what this method does exactly
	 * 
	 * @param filter   the prefix for the owners name
	 * @param position the index of the vehicle on the list
	 */
	@SuppressWarnings("unused")
	private Node findTrailingNode(String filter, int position) {
		return null;

	}

	/**
	 * String representation of all vehicles that meet the filter
	 * 
	 * @param filter the prefix for the owners name
	 * @return the vehicle.toString that matches the filter
	 */
	public String filteredList(String filter) {
		SimpleIterator<Vehicle> iterator = iterator();
		String returnedList = "";

		while (iterator.hasNext()) {
			Vehicle currentVehicle = iterator.next();
			if (currentVehicle.meetsFilter(filter)) {
				returnedList += currentVehicle.toString() + "\n";

			}
		}
		return returnedList.strip();

	}

	/**
	 * Iterator for the linked list. Will hold position of element and will allow
	 * user to see value of next or to see if there is a value
	 * 
	 * @author troy1
	 *
	 */
	private class Cursor implements SimpleIterator<Vehicle> {
		/** The node the iterator is currently on */
		private Node cursor;

		/**
		 * The SimpleIterator will always be initialized to the beginning element as of
		 * the requirements
		 */
		private Cursor() {
			this.cursor = head;
		}

		/**
		 * Simple boolean to confirm if there is another node loaded into next
		 * 
		 * @return true if next node exist, false otherwise
		 */
		public boolean hasNext() {
			return cursor != null;
		}

		/**
		 * Move the cursor to the next node and return data held in the moved from node
		 * 
		 * @return Vehicle in node
		 */
		public Vehicle next() {
			if (hasNext()) {
				Vehicle returnedVehicle = cursor.v;
				cursor = cursor.next;
				return returnedVehicle;
			} else {
				throw new NoSuchElementException();
			}

		}
	}

	/**
	 * Inner class dedicated to generating the custom linked list
	 * 
	 * @author Troy Boone
	 *
	 */
	private class Node {

		/** The vehicle the node represents. */
		public Vehicle v;
		/** The next node in the list */
		public Node next;

		/**
		 * Construct a node with a connection to the next one
		 * 
		 * @param v    the Vehicle being stored in this node
		 * @param next the next node in the list
		 */
		public Node(Vehicle v, Node next) {
			this.v = v;
			this.next = next;
		}
	}
}
