import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.DBConnection;
import model.Dept;

public class MainApp {

	public static void 추가(int id) {
		String sql = "INSERT INTO test1(id) VALUES(?)";
		Connection conn = DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); // 인젝션 공격을 막아줌
			int result = pstmt.executeUpdate(); // 변경된 row count를 리턴, 0 : 변경 없음, -1 : 오류
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void 삭제(int id) {
		String sql = "DELETE FROM test1 WHERE id = ?";
		Connection conn = DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); // 인젝션 공격을 막아줌
			int result = pstmt.executeUpdate(); // 변경된 row count를 리턴, 0 : 변경 없음, -1 : 오류
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void 수정(int id) {
		String sql = "UPDATE test1 SET id = 3 WHERE id = ?";
		Connection conn = DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); // 인젝션 공격을 막아줌
			int result = pstmt.executeUpdate(); // 변경된 row count를 리턴, 0 : 변경 없음, -1 : 오류
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Dept 찾기(int deptno) {
		String sql = "SELECT deptno, dname, loc FROM dept WHERE deptno = ?";
		Connection conn = DBConnection.getInstance();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno); // 인젝션 공격을 막아줌
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Dept dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname"))
						.loc(rs.getString("loc")).build();
				System.out.println(dept);
				return dept;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Dept> 전체찾기() {
		String sql = "SELECT deptno, dname, loc FROM dept";
		Connection conn = DBConnection.getInstance();

			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				ArrayList<Dept> list = new ArrayList<>() ;
				
				while (rs.next()) {
					Dept dept = Dept.builder()
							.deptno(rs.getInt("deptno"))
							.dname(rs.getString("dname"))
							.loc(rs.getString("loc"))
							.build();
					
					list.add(dept);
				}
				System.out.println(list);

				return list;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		
	}

	public static void main(String[] args) {
//		추가(9);
//		삭제(10);
//		수정(9);
//		찾기(10);
		전체찾기();
	}
}
