package member;

import static member.Member.Entity.*;

public interface JdbcSql {
	// TODO SQL문 작성 
	
	// 회원가입 -> 새로운 회원 등록
	String  SQL_INSERT_MEMBER = String.format("insert into %s (%s,%s,%s,%s,%s,%s) values(?,?,?,?,?,?)",
			TBL_MEMBER,COL_MEM_ID,COL_MEM_PW,COL_MEM_NAME,COL_MEM_SEX,COL_MEM_LOC,COL_MEM_HISTORY);
	
	// 회원 아이디 중복확인
	
	
	
}
