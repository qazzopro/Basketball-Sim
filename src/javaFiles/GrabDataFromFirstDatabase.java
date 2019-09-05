package javaFiles;

import java.sql.Connection;
import java.util.List;

public abstract class GrabDataFromFirstDatabase extends GrabDataFromDatabase {
	
	/**
	 * Constructor for creating a GrabDataFromFirstDatabase object.
	 * @param conn Connection to database
	 */
	public GrabDataFromFirstDatabase(Connection conn) {
		super(conn);
	}

	private Connection conn;
	
	@Override
	public abstract List<?> getDataList();

	@Override
	public abstract <E> void addData(E e);

	@Override
	public abstract <E> void deleteData(E e);

	@Override
	public String getSQLCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object FromSQL() {
		// TODO Auto-generated method stub
		return null;
	}
	
}