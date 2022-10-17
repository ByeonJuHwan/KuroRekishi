package chat;

import static ojdbc.MysqlJdbc.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ChatDaoImpl implements ChatDao {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    // 데이터베이스 접속 메서드 -- for MAC
    private void connDB() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // 연결 close() 시 사용하는 메서드 -- executeUpdate() 인경우
    private void closeResources(Connection conn, Statement stmt) throws SQLException{
        stmt.close();
        conn.close();
    }
    
    // 연결 close() 시 사용하는 메서드 -- executeQuery() 인경우 / 위 메서드를 오버로드 해준다.
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) throws SQLException{
        rs.close();
        closeResources(conn,stmt);
    }

    // singleton
    // 1. 자기자신 타입의 private static인 변수 선언
    private static ChatDaoImpl instance = null;
    
    // 2. private 생성자
    private ChatDaoImpl () {}
    // 3. 자기 자신을 리턴하는 public static 메서드 정의
    public static ChatDaoImpl getInstance() {
        if(instance == null) {
            instance  = new ChatDaoImpl();
        }
        return instance;
    }

    @Override
    public int insertId(String id, String id2) {
        int result = 0;
        try {
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            
        }
        return result;
    }
}
