package com.ensharp.kimyejin.voicerecognitiontest;

import java.sql.*;

public class DBManager {
	
	private static Connection conn;
	private static Statement st;
	private static String query;
	
	public static void connectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/whisperer", "root", "ensharp");
			System.out.println("DB연결완료");
			
			st = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 연결실패");
		} catch (SQLException e) {
            System.out.println("DB연결실패");
        }
	}

	public static void selectAll() {
		query = "SELECT * FROM noun";
		ResultSet rs;
		
		try {
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				String name = rs.getString("name");
				String original = rs.getString("original");
				String part = rs.getString("part");
				
				System.out.format("%s, %s, %s\n", name, original, part);
			}
		} catch (SQLException e) {
			System.out.println("DB연결실패");
		}
				
	}
	
	public static String select(String table, String column, String condition) {
		query = "SELECT " + column + " FROM " + table + " WHERE " + condition;
		ResultSet rs;
		String result;
		
		try {
			rs = st.executeQuery(query);
			rs.next();
			result = rs.getString(column);
			
			return result;
		} catch (SQLException e){
			System.out.println("DB연결실패");
		}
		
		return "";
	}
	
	public static int count(String table, String condition) {
		query = "SELECT COUNT(*) FROM " + table + " WHERE " + condition;
		ResultSet rs;
		String result;
		
		try {
			rs = st.executeQuery(query);
			rs.next();
			result = rs.getString("count(*)");
			
			return Integer.parseInt(result);
		} catch (SQLException e) {
			System.out.println("DB연결실패");
		}
		
		return -1;
	}
	
	public static String getOriginal(String table, String condition) {
		query = "SELECT * FROM " + table + " WHERE " + condition;
		ResultSet rs;
		String result;
		
		try {
			rs = st.executeQuery(query);
			rs.next();
			result = rs.getString("part");
			
			if (rs.wasNull()) {
				result = select(table, "original", condition);
			} else {
				return rs.getString("name");
			}
			
			return result;
		} catch (SQLException e) {
			System.out.println("DB연결실패");
		}
		
		return "없음";
	}
	
	public static void closeDB() {
		try {
			st.close();
			System.out.println("DB연결종료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
