package kr.ac.sunmoon.utill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {
	private static DataSource datasource;
	
	// static initializer. 해당 클래스가  JVM 메모리에 올라갈 때 자동으로 딱 한번만 실행되는 코드
	static {
		Context context;
		try {
			context = (Context) new InitialContext().lookup("java:comp/env/"); // connection pool 찾아오는 코드
			datasource = (DataSource) context.lookup("jdbc/mysql"); 
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws Exception {
		return datasource.getConnection();
	}

	public static void close(ResultSet rs, Statement stmt, Connection con) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt, Connection con) {
		
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close(); // connection pool로 반납하는 코드
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
