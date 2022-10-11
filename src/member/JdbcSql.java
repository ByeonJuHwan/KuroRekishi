package member;

import static member.Member.Entity.*;

public interface JdbcSql {
	// TODO SQL문 작성 
	
	// 회원가입 -> 새로운 회원 등록
	String SQL_INSERT_MEMBER = String.format("insert into %s (%s,%s,%s,%s,%s,%s) values(?,?,?,?,?,?)",
			TBL_MEMBER,COL_MEM_ID,COL_MEM_PW,COL_MEM_NAME,COL_MEM_SEX,COL_MEM_LOC,COL_MEM_HISTORY);
	
	// 회원 아이디 중복확인
	String SQL_ISEXIST = String.format("select decode(count(*),1,'true','false') as result from %s where %s =?",
			TBL_MEMBER,COL_MEM_ID);
	
	// 프로필 수정
	String SQL_UPDATE_MEMBER = String.format("update %s set %s=?,%s=?,%s=?,%s=?,%s=?,%s=? where %s=?",
	        TBL_MEMBER,COL_MEM_ID,COL_MEM_PW,COL_MEM_NAME,COL_MEM_LOC,COL_MEM_SEX,COL_MEM_HISTORY,COL_MEM_ID);
	
	// 로그인
	String SQL_LOGIN = String.format("select decode(count(*),1,'true','false') as result from %s where %s =? and %s = ?",
	       TBL_MEMBER,COL_MEM_ID,COL_MEM_PW);
	           
	// 이름찾기
	String SQL_MEMBER_NAME_BY_ID = String.format("select %s from %s where %s = ?", COL_MEM_NAME,TBL_MEMBER,COL_MEM_ID);
	
	// ID로 회원 정보찾기
	String SQL_SELECT_MEMBER_BY_ID = String.format("select * from %s where %s=?", TBL_MEMBER,COL_MEM_ID);
}
