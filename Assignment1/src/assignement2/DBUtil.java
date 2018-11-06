package assignement2;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private Connection conn;

	public Connection getConnection() {
		try {
			if(conn==null) {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection("jdbc:postgresql:icon", "postgres", "root");
				return conn;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
