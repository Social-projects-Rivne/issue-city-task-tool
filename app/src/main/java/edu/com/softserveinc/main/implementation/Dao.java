package edu.com.softserveinc.main.implementation;

public interface Dao {
	
	/**
	 * Write new object in database
	 * 
	 * @param obj
	 */
	public void addInDB(Object obj);
	
	/**
	 * Edit object in database
	 * 
	 * @param obj
	 */
	public void editInDB(Object obj);

	/**
	 * Remove 
	 * 
	 * @param obj
	 */
	public void deleteFromDB(Object obj);

}
