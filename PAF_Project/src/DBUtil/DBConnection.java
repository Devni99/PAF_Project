package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db","root","Devni_@1999?");

			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
}
