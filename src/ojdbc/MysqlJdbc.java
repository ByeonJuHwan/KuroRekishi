package ojdbc;

public interface MysqlJdbc {
    // MYSQL에 접속하는 (주소, 포트번호, SID)를 정의
    String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    String URL= "jdbc:mysql://127.0.0.1:3306/byeon_member?user=root";
    
    
    String USER = "root";
    
    
    String PASSWORD = "milk0147";
}
