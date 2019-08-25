package javaFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Retrieves data from an SQLite database.
 * @author Dean Jariv
 * @since 26 Aug 2019
 */
public abstract class GrabDataFromDatabase {	
	private ResultSet rs;
	
	/**
	 * @return A List of of data that the object stores.
	 */
	public abstract List<?> getDataList();
	
	/**
	 * Adds data to the object's data list.
	 * @param <E> Generic element type that the list of data will have.
	 * @param e The element added to the object's data list.
	 */
	public abstract <E extends Object> void addData(E e);
	
	/**
	 * Deletes data to the object's data list.
	 * @param <E> Generic element type that the list of data will have.
	 * @param e The element added to the object's data list.
	 */
	public abstract <E extends Object> void deleteData(E e); 
	
	/**
	 * @return SQLite command used for retreiving data.
	 */
	public abstract String getSQLCommand();
	
	/**
	 * @return Connection object created from the database.
	 */
	public abstract Connection getConn();
	
	/**
	 * @return Object created from the data in the used SQLite command.
	 */
	public abstract Object FromSQL();
	
	/**
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
			this.setRs(getConn().createStatement().executeQuery(getSQLCommand()));
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A setter for current position of rs.
	 * @param rs to set.
	 */
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	/**
	 * Adds data to the object's data list from database.
	 * May catch an SQLException, imported from java.sql
	 */
	public void grabFromDatabase() {
		setInitialResultSet();
		try {
			while (getCurrRS().next())
				addData(FromSQL());
		}
		
		catch (SQLException e) {
            e.printStackTrace();       
        }
	}
	
}