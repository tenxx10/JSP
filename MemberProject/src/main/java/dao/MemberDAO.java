package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import static db.JdbcUtil.*;
import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;

import vo.MemberBean;

public class MemberDAO {
	
	public static MemberDAO instance;   // 왜 인스턴스 따로 선언?
	Connection con; 
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	private MemberDAO() {
		//기본 생성자 방지
	}
	
	public static MemberDAO getInstance() {   // 싱글톤 패턴 객체 생성
		
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
		
	}
	
	public void setConnection(Connection con) {
		this.con = con;    //dao에 cp연결해서 db 연결 시켜줌
	}
	
	// 1.  아이디,비번 가지고 회원아이디 검색 !!
	public String selectLoginId(MemberBean member) {
		String loginId = null;   //변수 만들어줌
		String sql = "SELECT MEMBER_ID FROM MEMBER1 WHERE MEMBER_ID=? AND MEMBER_PW=?";
		
		try {
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			pstmt.setString(2, member.getMEMBER_PW());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginId = rs.getString("MEMBER_ID");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("로그인 실패 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return loginId;
		
	}
	
	// 2. 회원정보를 데이터에 추가 !!
	
	public int insertMember(MemberBean member) {
		String sql = "INSERT INTO MEMBER1 VALUES (?,?,?,?,?,?)";
		int insertCount = 0;
		
		try {
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			pstmt.setString(2, member.getMEMBER_PW());
			pstmt.setString(3, member.getMEMBER_NAME());
			pstmt.setInt(4, member.getMEMBER_AGE());
			pstmt.setString(5, member.getMEMBER_GENDER());
			pstmt.setString(6, member.getMEMBER_EMAIL());
			
			insertCount = pstmt.executeUpdate();   // 추가한 정보 저장
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("joinMember 어쩌고 : " + e);
		}finally {
			close(pstmt);
		}
		
		
		return insertCount;
	} 
	
	
	// 3. 모든 회원정보 검색해서 목록으로 반환 메소드 !!!
	
	public ArrayList<MemberBean> selectMemberList(){
		
		String sql = "SELECT * FROM MEMBER1";
		ArrayList<MemberBean> memberList = new ArrayList<>();
		MemberBean mb = null;
		
		try {
			
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mb = new MemberBean();
					mb.setMEMBER_AGE(rs.getInt("MEMBER_AGE"));
					mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
					mb.setMEMBER_GENDER(rs.getString("MEMBER_GENDER"));
					mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
					mb.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
					mb.setMEMBER_PW(rs.getString("MEMBER_PW"));
					memberList.add(mb);
					
				} while (rs.next());
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getDetailMember : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return memberList;
	}
	
	
	// 4. 특정 아이디 해당하는 회원정보 검색 !!!!
	public MemberBean selectMember (String id) {
		String sql = "SELECT * FROM MEMBER1 WHERE MEMBER_ID=?";
		MemberBean mb = null;
		
		try {
			
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setMEMBER_AGE(rs.getInt("MEMBER_AGE"));
				mb.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
				mb.setMEMBER_GENDER(rs.getString("MEMBER_GENDER"));
				mb.setMEMBER_ID(rs.getString("MEMBER_ID"));
				mb.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
				mb.setMEMBER_PW(rs.getString("MEMBER_PW"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("getDetailMember : " +  e);
		} finally {
			close(rs);
			close(pstmt);
		}
	
		return mb;
		
	}
	
	
	// 5. 회원삭제하는 메소드
	public int deleteMember(String id) {
		String sql = "DELETE MEMBER1 WHERE MEMBER_ID=?";
		int deleteCount = 0;
		
		try {
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("deleteMember : " +e);
		}finally {
			close(pstmt);
		}
		
		
		return deleteCount;
	}
	


}
