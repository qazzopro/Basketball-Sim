package javaFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;

/**
 * Parent class for classes that retrieve data from an SQLite database.
 * @author Dean Jariv
 * @since 24 Aug 2019
 */
public abstract class GrabDataFromDatabase {	
	private ResultSet rs;
	
	/**
	 * Returns the list of data from the object.
	 */
	public abstract List<?> getDataList();
	
	/**
	 * Adds data to the object's data list.
	 * @param <E> Generic element type that the list of data will have.
	 * @param e The element added to the object's data list.
	 */
	public abstract <E> void addData(E e);
	
	/**
	 * Deletes data to the object's data list.
	 * @param <E> Generic element type that the list of data will have.
	 * @param e The element added to the object's data list.
	 */
	public abstract <E> void deleteData(E e); 
	
	/**
	 * Returns the SQLite Command used for retrieving data.
	 */
	public abstract String getSQLCommand();
	
	/**
	 * Returns a Connection object created from the database.
	 */
	public abstract Connection getConn();
	
	/**
	 * Returns an Object created from SQLite command.
	 */
	public abstract Object FromSQL();
	
	/**
	 * Returns the current row of database.
	 * @return rs ResultSet variable for storing current row of database. 
	 */
	public ResultSet getCurrRS() {
		return rs;
	}
	
	/**
	 * Sets a ResultSet object to the first row of the database.
	 * May catch an SQLException, imported from java.sql
	 */
	public void setInitialResultSet() {
		try {
			rs = getConn().createStatement().executeQuery(getSQLCommand());
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds data to the object's data list from database.
	 * May catch an SQLException, imported from java.sql
	 */
	public void grabFromDatabase() {
		setInitialResultSet();
		try {
			while (rs.next())
				addData(FromSQL());
		}
		
		catch (SQLException e) {
            e.printStackTrace();       
        }
	}
	
}