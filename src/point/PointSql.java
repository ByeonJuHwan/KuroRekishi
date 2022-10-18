package point;


import static point.Point.Entity.*;

public interface PointSql {
    
    // 얼평을 받은 사람의 점수의 총점과, 몇명이 점수를 줬는지 알려준다.
    String SQL_GET_STARPOINT = String.format("select count(%s),sum(%s) from %s where %s = ?", COL_GIVESTARID,COL_POINT,TBL_POINT,COL_GAVEDSTARID );

    // 입력받은 정보들을 데이터베이스에 저장한다.
    String SQL_INSERT_POINT = String.format("insert into %s (%s,%s,%s) values (?,?,?)",TBL_POINT,COL_POINT,COL_GIVESTARID,COL_GAVEDSTARID);
    
    // 중복회원 방지를 위한 아이디 가져오기
    String SQL_GET_GIVEID = String.format("select %s from %s where %s=?", COL_GIVESTARID,TBL_POINT,COL_GAVEDSTARID);
}
