package recommendation;

import static ojdbc.MysqlJdbc.DRIVER;
import static ojdbc.MysqlJdbc.PASSWORD;
import static ojdbc.MysqlJdbc.URL;
import static ojdbc.MysqlJdbc.USER;
//import static ojdbc.OracleJdbc.PASSWORD;
//import static ojdbc.OracleJdbc.URL;
//import static ojdbc.OracleJdbc.USER;
import static recommendation.DateSql.*;
import static recommendation.Recommendation.Entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;



public class RecommendationDaoImpl implements RecommendationDao {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
 // Singleton 적용
    private static RecommendationDaoImpl instance = null;
    private RecommendationDaoImpl() {}
    public static RecommendationDaoImpl getInstance() {
        if(instance == null) {
            instance = new RecommendationDaoImpl();
        }
        return instance;
    }
    private void connDB() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // connection for window
//    private void connDB() {
//        try {
//            DriverManager.registerDriver(new OracleDriver());
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    
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
    
    
    
    @Override
    public List<Recommendation> search(String loc, String category) {
        List<Recommendation>list = new ArrayList<>();
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_SEARCH);
            stmt.setString(1, loc);
            stmt.setString(2, category);
            
            rs = stmt.executeQuery();
            while(rs.next()) {
                String name = rs.getString(COL_NAME);
                String location = rs.getString(COL_LOC);
                String cate = rs.getString(COL_CATEGORY);
                String enjoy = rs.getString(COL_ENJOY);
                
                Recommendation rec = new Recommendation(name, location, enjoy, cate);
                list.add(rec);
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
        return list;
    }
    @Override
    public String searchUrl(String name) {
        String url = null;
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_SEARCH_URL);
            stmt.setString(1, name);
            
            rs=stmt.executeQuery();
            rs.next();
            url = rs.getString(COL_URL);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return url;
    }
    @Override
    public String searchImageUrl(String name) {
        String url = null;
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_SEARCH_IMAGEURL);
            stmt.setString(1, name);
            
            rs = stmt.executeQuery();
            rs.next();
            url = rs.getString(COL_IMAGEURL);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return url;
    }
    @Override
    public String searchImageUrl2(String name) {
        String url = null;
        try {
            connDB();
            stmt = conn.prepareStatement(SQL_SEARCH_IMAGEURL2);
            stmt.setString(1, name);
            
            rs = stmt.executeQuery();
            rs.next();
            url = rs.getString(COL_IMAGEURL2);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return url;
    }
}
