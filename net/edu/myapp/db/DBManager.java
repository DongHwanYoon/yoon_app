package net.edu.myapp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Context context = (Context) new InitialContext().lookup("java:comp/env/");
			DataSource datasource = (DataSource) context.lookup("jdbc/mysql");
			con = datasource.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void releaseConnection(ResultSet rs, PreparedStatement pstmt, Connection con) {

		try {
			if(rs != null ) { rs.close(); }
			if(rs != null ) { pstmt.close(); }
			if(rs != null ) { con.close(); }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
