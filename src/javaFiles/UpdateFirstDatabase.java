package javaFiles;

import java.sql.Connection;

public abstract class UpdateFirstDatabase implements UpdateDatabase {
	
	
	public UpdateFirstDatabase(Connection conn) {
	}
	
	@Override
	public abstract <E> void modifyDatabase(E e);
}