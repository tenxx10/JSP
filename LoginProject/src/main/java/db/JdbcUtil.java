package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


	//DB 작업 할 때 반복적으로 수행해야하는 작업을 정의하는 클래스
public class JdbcUtil {
	
	public static Connection getConnection() { // 메서드 재정의임
		Connection con = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jbdc/OracleDB");
			con = ds.getConnection();
			con.setAutoCommit(false);   //자동커밋 끄고 수동 전환
			System.out.println("connect success");
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return con;
		
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void rollblack(Connection con ) {
		try {
			con.rollback();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}











