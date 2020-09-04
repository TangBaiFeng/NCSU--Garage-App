package edu.ncsu.csc216.garage.model.service_garage;

import java.util.AbstractList;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Concrete class for managing the list of service bays.
 * 
 * @author Troy Boone
 *
 */
public class Garage {
	/** The max number of service bays. */
	private final static int MAX_ROOMS = 30;
	/** The default number of starting service bays. */
	private final static int DEFAULT_SIZE = 8;
	/** The total number of open service bays */
	private int numEmptyBays;
	/** The total number of electricBays */
	private double electricBay;
	/** The list of bays */
	private ArrayList<ServiceBay> bays;

	/**
	 * Default constructor. Initialize the Garage object and calls initBays to
	 * generate bays at the start of the day
	 */
	public Garage() {
		ServiceBay.startBayNumberingAt101();
		bays = new ArrayList<ServiceBay>(MAX_ROOMS);
		electricBay = 0;
		initBays(DEFAULT_SIZE);
	}

	/**
	 * Adds a number of service bays to the garage
	 * 
	 * @param startingValue number of bays to create
	 */
	private void initBays(int startingValue) {
		for (int i = 0; i < startingValue; i++) {
			addRepairBay();
		}
	}

	/**
	 * Helper method to decrease the number of empty bays
	 */
	public void decreaseNumEmptyBays() {
		this.numEmptyBays--;
	}

	/**
	 * Adds a repair bay. Implements checker to determine how many bays are HVEX to
	 * assure at least 30% support hybrid vehicle repair
	 */
	public void addRepairBay() {
		if (getSize() == 0 || electricBay / getSize() < .34) {
			bays.add(getSize(), new HybridElectricBay());
			electricBay++;
			numEmptyBays++;
		} else {
			bays.add(0, new ServiceBay());
			numEmptyBays++;
		}
	}

	/**
	 * Simple getter for numEmptyBays
	 * 
	 * @return the number of empty bays
	 */
	public int numberOfEmptyBays() {
		return numEmptyBays;
	}

	/**
	 * Simple getter for the size of the list of bays
	 * 
	 * @return the number of bays occupied
	 */
	public int getSize() {
		return bays.size();

	}

	/**
	 * Simple getter for the bay at index
	 * 
	 * @param index the location of the service bay
	 * @return the service bay at index
	 */
	public ServiceBay getBayAt(int index) {
		return bays.get(index);

	}

	/**
	 * Calls the ServiceBay.release() method to Removes the vehicle currently in the
	 * service bay and returns it
	 * 
	 * @param index the index of the bay that is being cleared
	 * @return the vehicle that was in the bay
	 */
	public Vehicle release(int index) {
		if (getBayAt(index) == null || !getBayAt(index).isOccupied()) {
			return null;
		} else {
			this.numEmptyBays++;
			return getBayAt(index).release();
		}

	}

	/**
	 * Custom ArrayList application for the Pack Scheduler System.
	 * 
	 * @author Troy Boone
	 * @param <E> the object the array is holding
	 */
	private class ArrayList<E> extends AbstractList<E> {
		/** The array holding all the content */
		private E[] list;
		/** The number of non null elements of the array */
		private int size;

		/**
		 * Constructs the ArrayList object. Initializes the List array to a size of 10
		 * and set the size to 0
		 */
		@SuppressWarnings("unchecked")
		public ArrayList(int size) {
			list = (E[]) new Object[size];
			size = 0;
		}

		/**
		 * Simple getter for the element at index location
		 * 
		 * @param index the index being checked
		 * @return the element at index location
		 */
		@Override
		public E get(int index) {
			if (index < 0 || index >= size()) {
				return null;
			}
			return list[index];
		}

		/**
		 * Simple getter for size
		 */
		@Override
		public int size() {
			return size;
		}

		/**
		 * Override for the add method. Add the element at the specific position of the
		 * array. If the array has reached max size, will call the growArray method
		 * 
		 * @param index   the index of where the element should be added
		 * @param element the element being added
		 * @throws NullPointerException      if the element is null
		 * @throws IndexOutOfBoundsException if the index for the added element is
		 *                                   outside the current bounds
		 * @throws IllegalArgumentException  if the element is already in the array
		 */
		@Override
		public void add(int index, E element) {
//			if (element == null) {
//				throw new NullPointerException();
//			}
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}
//			for (int i = 0; i < size; i++) {
//				if (list[i].equals(element)) {
//					throw new IllegalArgumentException();
//				}
//			}

			System.arraycopy(list, index, list, index + 1, size - index);
			list[index] = element;
			size++;
		}

	}
}
