package member;

import static member.Member.Entity.*;

public interface JdbcSql { 
	
	// 회원가입 -> 새로운 회원 등록
	String SQL_INSERT_MEMBER = String.format("insert into %s (%s,%s,%s,%s,%s,%s,%s,%s,%s) values(?,?,?,?,?,?,?,?,?)",
			TBL_MEMBER,COL_MEM_ID,COL_MEM_PW,COL_MEM_NAME,COL_MEM_SEX,COL_MEM_LOC,COL_MEM_HISTORY,COL_MEM_HIGHT,COL_MEM_AGE,COL_MEM_MBTI);
	
	// 회원 아이디 중복확인 -- for Window
	String SQL_ISEXIST = String.format("select decode(count(*),1,'true','false') as result from %s where %s =?",
			TBL_MEMBER,COL_MEM_ID);
	
	// 회원 아이디 중복확인 -- for MAC
	String SQL_ISEXIST_FOR_MAC = String.format("select if(count(*)>0,'true','false') as result from %s where %s=?", TBL_MEMBER,COL_MEM_ID);
	
	// 프로필 수정
	String SQL_UPDATE_MEMBER = String.format("update %s set %s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=?,%s=? where %s=?",
	        TBL_MEMBER,COL_MEM_ID,COL_MEM_PW,COL_MEM_NAME,COL_MEM_LOC,COL_MEM_SEX,COL_MEM_HISTORY,COL_MEM_HIGHT,COL_MEM_AGE,COL_MEM_MBTI,COL_MEM_ID);
	
	// 로그인
	String SQL_LOGIN = String.format("select decode(count(*),1,'true','false') as result from %s where %s =? and %s = ?",
	       TBL_MEMBER,COL_MEM_ID,COL_MEM_PW);
	
	// 맥북으로 로그인 시 사용
	String SQL_LOGIN_FORMAC = String.format("select if(count(*)>0,'true','false') as result from %s where %s=? and %s=?", TBL_MEMBER,COL_MEM_ID,COL_MEM_PW);
	
	           
	// 이름찾기
	String SQL_MEMBER_NAME_BY_ID = String.format("select %s from %s where %s = ?", COL_MEM_NAME,TBL_MEMBER,COL_MEM_ID);
	
	// ID로 회원 정보찾기
	String SQL_SELECT_MEMBER_BY_ID = String.format("select * from %s where %s=?", TBL_MEMBER,COL_MEM_ID);
	
	// ID로 회원 성별 찾기
	String SQL_SELECT_SEX_BY_ID = String.format("select %s from %s where %s=?" ,COL_MEM_SEX,TBL_MEMBER,COL_MEM_ID);
	
	// Random하게 회원이름 가져와서 사진띄우기 -- for window
	String SQL_SELECT_NAME_RANDOM = String.format("select %s,%s from (select %s,%s from %s order by dbms_random.random) where rownum<2 and %s=?",
			COL_MEM_NAME,COL_MEM_SEX,COL_MEM_NAME,COL_MEM_SEX,TBL_MEMBER,COL_MEM_SEX);
	
	// Random하게 회원이름 가져와서 사진띄우기 -- for MAC
	String SQL_SELECT_NAME_RANDOM_FOR_MAC = String.format("select %s from %s where %s=? order by rand() limit 1", COL_MEM_NAME,TBL_MEMBER,COL_MEM_SEX);
	
	// 좋아요를 눌렀을때 내 이름을 입력해준다.
	String SQL_UPDATE_GIVETHUMB = String.format("update %s set %s=?, %s=? where %s=?", TBL_MEMBER,COL_MEM_GIVETHUMBNAME,COL_MEM_GAVEDTHUMBNAME,COL_MEM_NAME);
	
	// 메인창에 띄울 주소 리턴
	String SQL_SELECT_LOC = String.format("select %s from %s where %s=?", COL_MEM_LOC,TBL_MEMBER,COL_MEM_ID);
	
	// 검색한 경우 그 사람의 정보에 세부검색을 했는지 안했는지 여부를 남긴다.
	String SQL_UPDATE_SEARCHSET = String.format("update %s set %s=true where %s=?", TBL_MEMBER,COL_MEM_SETSEARCH, COL_MEM_ID);
	
	// 검색 조건에서 키만 입력한경우
	String SQL_SELECT_HIGHT_SET = String.format("select %s from %s where %s>=? and %s<=?", COL_MEM_NAME,TBL_MEMBER,COL_MEM_HIGHT,COL_MEM_HIGHT);
	
	// 검색 조건에서 나이만 입력한 경우
	String SQL_SELECT_AGE_SET = String.format("select %s from %s where %s>=? and %s<=?", COL_MEM_NAME,TBL_MEMBER,COL_MEM_AGE,COL_MEM_AGE);
	
	// 검색 조건에서 mbti만 입력한 경우
	String SQL_SELECT_MBTI_SET = String.format("select %s from %s where %s=?", COL_MEM_NAME,TBL_MEMBER,COL_MEM_MBTI);
	
	// 검색 조건에서 나이,mbti를 입력한 경우
	String SQL_SELECT_AGE_MBTI_SET = String.format("select %s from %s where (%s>=? and %s<=?) and %s=?", COL_MEM_NAME, TBL_MEMBER,COL_MEM_AGE,COL_MEM_AGE,COL_MEM_MBTI);
	
	// 검색 조건에서 키,mbti를 입력한 경우
	String SQL_SELECT_HIGHT_MBTI_SET = String.format("select %s from %s where (%s>=? and %s<=?) and %s=?", COL_MEM_NAME, TBL_MEMBER,COL_MEM_HIGHT,COL_MEM_HIGHT,COL_MEM_MBTI);
	
	// 검색 조건에서 키, 나이를 입력한 경우
	String SQL_SELECT_HIGHT_AGE_SET = String.format("select %s from %s where (%s>=? and %s<=?) and (%s>=? and %s<=?)", COL_MEM_NAME,TBL_MEMBER,COL_MEM_HIGHT,COL_MEM_HIGHT,COL_MEM_AGE,COL_MEM_AGE);
	
	// 검색 조건에서 키,나이,mbti 전부 입력한 경우
	String SQL_SELECT_HIGHT_AGE_MBTI_SET = String.format("select %s from %s where (%s>=? and %s<=?) and (%s>=? and %s<=?) and %s=?", COL_MEM_NAME,TBL_MEMBER,COL_MEM_HIGHT,COL_MEM_HIGHT,COL_MEM_AGE,COL_MEM_AGE,COL_MEM_MBTI);
	
	// 로그인한 회원의 흑역사를 가져온다.
	String SQL_SELECT_HISTORY = String.format("select %s from %s where %s=?", COL_MEM_HISTORY,TBL_MEMBER,COL_MEM_NAME);
	
	// 이름으로 ID 찾기
	String SQL_SELECT_ID_BY_NAME = String.format("select %s from %s where %s=?", COL_MEM_ID,TBL_MEMBER,COL_MEM_NAME);
	
	// 채팅을 원치 않아서 no를 클릭시 db에 저장된이름을 초기화되게한다.
	String SQL_UPDATE_NULL_THUMBS = String.format("update %s set %s = null, %s = null where %s=? and %s=?", TBL_MEMBER,COL_MEM_GIVETHUMBNAME,COL_MEM_GAVEDTHUMBNAME,COL_MEM_GAVEDTHUMBNAME,COL_MEM_GIVETHUMBNAME);
	
	// 검색 기록을 초기화 하기위해서 setSearch컬럼을 false로 만든다.
	String SQL_UPDATE_SEARCH_RESET = String.format("update %s set %s=false where %s=?", TBL_MEMBER,COL_MEM_SETSEARCH,COL_MEM_ID);
}
