package chat;

import static ojdbc.MysqlJdbc.*;
import static chat.ChatSql.*;
import static chat.Chat.Entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
            connDB();
            stmt = conn.prepareStatement(SQL_CHAT_INSERT_ID);
            stmt.setString(1, id);
            stmt.setString(2, id2);
            result = stmt.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public List<String> idList(String id) {
        List<String>idList = new ArrayList<>();
        String userId = null;
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_FIND_ID_LIST);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
                userId = rs.getString(COL_GIVEID);
                idList.add(userId);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idList;
    }

    @Override
    public boolean checkDuplicateThumb(String id) {
        boolean result = false;
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_CHECK_GAVEDID);
            stmt.setString(1, id);
            rs = stmt.executeQuery(); 
            rs.next(); 
            result = Boolean.parseBoolean(rs.getString("result"));
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
