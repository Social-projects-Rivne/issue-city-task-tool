package edu.com.softserveinc.main.dao;

import java.util.List;

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
	
	/**
	 * It finds class's object by id
	 * 
	 * @param id 
	 * @param null object of that class which you want to get
	 * @return object
	 */
	public Object getById(int id, Object obj);

	/**
	 * It loads all objects of class
	 * 
	 * @param null object of that class which you want to get
	 * @return List of objects
	 */
	@SuppressWarnings("rawtypes")
	public List getAll(Object obj);

	
}
