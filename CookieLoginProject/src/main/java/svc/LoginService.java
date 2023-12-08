package svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.LoginDAO;
import vo.Member;

public class LoginService {
	
	
	public Member getLoginMember (String id,String passwd) {
		
		LoginDAO loginDAO = LoginDAO.getInstance();  //객체 생성
		Connection con = getConnection();
		loginDAO.setConnection(con);   //cp 연결까지해서 db연결 완
		
		Member loginMember = loginDAO.selectLoginMember(id, passwd);
		close(con);  //재정의해준 메소드로 반환
		
		return loginMember;
	}

}
