package javaFiles;

import java.sql.*;

public interface ConnectionInterface {
	public abstract Connection getConnectionFromDatabase();
}