package point;

import static ojdbc.MysqlJdbc.DRIVER;
import static ojdbc.MysqlJdbc.PASSWORD;
import static ojdbc.MysqlJdbc.URL;
import static ojdbc.MysqlJdbc.USER;
import static point.PointSql.*;
import static point.Point.Entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PointDaoImpl implements PointDao {

    
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
    private static PointDaoImpl instance = null;
    
    // 2. private 생성자
    private PointDaoImpl () {}
    // 3. 자기 자신을 리턴하는 public static 메서드 정의
    public static PointDaoImpl getInstance() {
        if(instance == null) {
            instance  = new PointDaoImpl();
        }
        return instance;
    }
    
    @Override
    public List<Integer> getStarPoint(String id) {
        List<Integer> pointList = new ArrayList<>();
        int point = 0;
        int memberNum=0;
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_GET_STARPOINT);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
                point = rs.getInt(1);
                memberNum = rs.getInt(2);
                pointList.add(point);
                pointList.add(memberNum);
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
        return pointList;
    }

    @Override
    public int insertPoint(Point point) {
        int result = 0;
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_INSERT_POINT);
            stmt.setInt(1,point.getPoint());
            stmt.setString(2, point.getGiveStarId());
            stmt.setString(3, point.getGavedStarId());
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
    public List<String> getGiveId(String id) {
        List<String>giveIdList = new ArrayList<>();
        String userId = null;
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_GET_GIVEID);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            while(rs.next()) {
                userId = rs.getString(COL_GIVESTARID);
                giveIdList.add(userId);
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
        return giveIdList;
    }

}
