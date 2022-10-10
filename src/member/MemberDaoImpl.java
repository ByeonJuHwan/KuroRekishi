package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;
import static member.Member.Entity.*;
import static ojdbc.OracleJdbc.*;
import static member.JdbcSql.*;


public class MemberDaoImpl implements MemberDao{
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	// 데이터베이스 접속 메서드
	private void connDB() {
		try {
			DriverManager.registerDriver(new OracleDriver());
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// singleton
    // 1. 자기자신 타입의 private static인 변수 선언
    private static MemberDaoImpl instance = null;
    
    // 2. private 생성자
    private MemberDaoImpl () {}
    // 3. 자기 자신을 리턴하는 public static 메서드 정의
    public static MemberDaoImpl getInstance() {
        if(instance == null) {
            instance  = new MemberDaoImpl();
        }
        return instance;
    }
    
    
    /**
     * 중복되는 아이디 검열
     * 
     */
    @Override
	public boolean findIdExist(String id) {
		boolean result = false;
		try {
			connDB();
			
			stmt = conn.prepareStatement(SQL_ISEXIST);
			
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			rs.next();
			
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result : " + result);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

    /**
     * 회원 가입
     */
	@Override
	public int addmember(Member member) {
		int result = 0;
		try {
			connDB();
			
			stmt = conn.prepareStatement(SQL_INSERT_MEMBER);
			
			stmt.setString(1, member.getId());
			stmt.setString(2, member.getPw());
			stmt.setString(3, member.getName());
			stmt.setString(4, member.getSex());
			stmt.setString(5, member.getLocation());
			stmt.setString(6, member.getHistory());
			
			result  = stmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 회원수정
	 */
	@Override
	public void updateMember(Member member) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 채팅창 이름 설정
	 */
	@Override
	public String fineName(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 로그인
	 */
    @Override
    public void Login(String id, String pw) {
        // TODO Auto-generated method stub
        boolean result = false;
        try {
            connDB();
            
            stmt = conn.prepareStatement(SQL_LOGIN);
            
            stmt.setString(1, id);
            stmt.setString(2, pw);
            
            rs = stmt.executeQuery();
            rs.next();
            
            result = Boolean.parseBoolean(rs.getString("result"));
            System.out.println("result : " + result);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
        
    }

}
