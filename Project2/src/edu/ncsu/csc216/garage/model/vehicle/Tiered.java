package edu.ncsu.csc216.garage.model.vehicle;

/**
 * Interface for handling the behavior of objects with different tier status
 * 
 * @author Troy Boone
 *
 */
public interface Tiered {

	/**
	 * Simple getter for tier
	 * 
	 * @return the tier as a number
	 */
	int getTier();

	/**
	 * Compare the tier status of this object with another
	 * 
	 * @param t the tier as a integer
	 * @return 0 if the tiers are the same, -1 if this object is lower then the
	 *         compared, and 1 for if this object is higher then the compared
	 */
	int compareToTier(Tiered t);
}
