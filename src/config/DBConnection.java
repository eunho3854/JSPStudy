package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Connection getInstance() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
		// thin : 순수하게 자바 패키지(클래스들)만으로 바로 DB와 연결, 
		// 범용성이 높다, 상대적으로 OCI보다 속도가 느리다.
		// oci :  .DLL과 .SO 파일과 같이 특정 운영체제 내에서만 돌아가는 Native Module을 통해 DB와 연결한다.
		
		String username = "SCOTT";
		String password = "TIGER";
		
		// OracleDriver 클래스를 메모리에 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("DB 연결 성공");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
		}
		
		return null;
	}
}
