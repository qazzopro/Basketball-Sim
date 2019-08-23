package javaFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;

public abstract class GrabDataFromDatabase {	
	private ResultSet rs;
	
	public abstract List<?> getDataList();
	
	public abstract <E> void addData(E e);
	
	public abstract <E> void deleteData(E e); 
	
	public abstract String getSQLCommand();
	
	public abstract Connection getConn();
	
	public abstract Object FromSQL();
	
	public ResultSet getCurrRS() {
		return rs;
	}
	
	public void setInitialResultSet() {
		try {
			rs = getConn().createStatement().executeQuery(getSQLCommand());
		} 
		
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void grabFromDatabase() {
		setInitialResultSet();
		try {
			while (rs.next())
				addData(FromSQL());
		}
		
		catch (SQLException e) {
            System.out.println(e.getMessage());          
        }
	}
	
}