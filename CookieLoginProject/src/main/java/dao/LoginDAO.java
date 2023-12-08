package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import static db.JdbcUtil.*;
import com.mysql.jdbc.PreparedStatement;

import vo.Member;

public class LoginDAO {
	
	// 자바빈
	
	private static LoginDAO loginDAO;
	private Connection con;
	
	private LoginDAO() {
		// 기본생성자 방지용
	}
	
	public static LoginDAO getInstance() {   //싱글톤패턴으로 생성자 만들기
		if(loginDAO == null) {
			loginDAO = new LoginDAO();
		}
		return loginDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public Member selectLoginMember(String id, String passwd) {

		Member loginMember = null;   //정보저장 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			pstmt = (PreparedStatement) con.prepareStatement("SELECT * FROM users WHERE id = ? AND passwd=?");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setAddr(rs.getString("addr"));
				loginMember.setAge(rs.getInt("age"));
				loginMember.setEmail(rs.getString("email"));
				loginMember.setGender(rs.getString("gender"));
				loginMember.setId(rs.getString("id"));
				loginMember.setName(rs.getString("name"));
				loginMember.setNation(rs.getString("nation"));
				loginMember.setPasswd(rs.getString("passwd"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				close(rs);
				close(pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		return loginMember;
		
	}
	
}
