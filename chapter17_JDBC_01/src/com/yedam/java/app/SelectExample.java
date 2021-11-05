package com.yedam.java.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 1. jdbc driver 로딩하기.
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2. db 서버 접속하기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hr";
		String password = "hr";

		Connection conn = DriverManager.getConnection(url, id, password);

		// 3. statement or preparedstatement 객체 생성하기 -> 운반 역할
		Statement stmt = conn.createStatement();

		// 4. sql문 실행.
		ResultSet rs = stmt.executeQuery("SELECT*FROM employees");

		// 5. 결과값 받아서 출력.
		while (rs.next()) {
			String name = "이름 : " + rs.getString("First_Name");
			System.out.println(name);
		}

		// 6. 자원 해제... 트랜잭션이 계속 실행되어 대기상태로 유지될 수 있으므로 연결을 끊어줘야 함. 실행 역순으로.
		if(rs != null)rs.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	}

}
