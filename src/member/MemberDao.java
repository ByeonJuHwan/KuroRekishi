package member;

import java.util.List;

public interface MemberDao {
	
	
	/**
	 * 회원가입시 아이디 중복확인을 위한 메서드.
	 * @param id 회원가입시 원하는 아이디 입력.
	 * @return 아이디가 존재하면 true, 아이디가 존재하지 않아서 사용할수 있으면 false.
	 */
	public boolean findIdExist(String id);
	
	/**
	 * 새로운 멤버 객체를 받아서 저장
	 * @param member
	 * @return 성공하면 1을 리턴, 실패시 0리턴
	 */
	public int addmember(Member member);
	
	
	/**
	 * 멤버 객체를 받아서 멤버 정보 변경
	 * @param member 업데이트할 정보
	 * @return 업데이트 성공하면 1, 그렇지 않으면 0.
	 */
	public int updateMember(Member member);
	
	/**
	 * 아이디를 받아서 이름을 알아내야할때 사용 (로그인, 채팅방 이름설정)
	 * @param id 로그인할때 입력한 아이디로 이름을 검색
	 * @return id에 따른 이름을 리턴해준다.
	 */
	public String fineName(String id);
	
	/**
	 * id,pw를 받아서 로그인동작을 수
	 * @param id
	 * @param pw
	 */
	public boolean Login(String id,String pw);

    /**
     *  회원 정보를 id로 검색해서 리턴
     */
    public List<Member> selectById(String id);
    
    
    /**
     * 메인창에 다른성별의 사진을 띄우기 위해 랜덤하게 한명을 뽑음
     * @return 뽑은 사람의 이름을 리턴
     */
    public String pickUserRamdom(String sex);
    
    /**
     * 로그안한 사람의 성별이 여자,남자인지를 구한다
     * 성별에따라 메인페이지 사진이 바뀜.
     * @return 성별
     */
    public String loginedUserSex(String id);
    
    /**
     * 좋아요 버튼을 눌렀을시 상대방에게 좋아요를 보낼수 있따.
     * @param giveName
     * @param gavedname
     * @return 성공하면 1, 실패하면 0
     */
    public int giveThumb(String giveName,String gavedname);
    
    /**
     * 로그인 후에 채팅요청받은 사람을 알려준다.
     * @param id
     * @return 이름, 좋아요받은이름, 좋아요를 준사람 이름
     */
    public Member checkThumb(String id);
    
    /**
     * 메인화면에서 상대방 이름, 사는 곳을 띄울때 사는곳을 찾기위해 사용
     * @param id
     * @return 사는 곳
     */
    public String findLocById(String id);
    
    /**
     * 검색 상세 옵션에서 키만 입력한 경우
     * @param hight
     * @return 검색한 키의 범위내의 이름을 리턴해준다.
     */
    public List<String> findHightOption(String lowHight, String maxHight,String sex);
    
    
    /**
     * 회원이 세부검색을 했으면 false를 true 로 바꿔준다.
     * @param id
     */
    public void setSearch(String id);
		
    
    /**
     * 검색 상세 옵션에서 키만 입력한 경우
     * @param hight
     * @return 검색한 나이의 범위내의 이름을 리턴해준다.
     */
    public List<String> findAgeOption(String lowAge, String maxAge,String sex);
    
    /**
     * mbti만 검색 상세 옵션에서 입력한 경우
     * @param mbti
     * @return 검색한 mbti인 사람들의 이름들을 리턴한다.
     */
    public List<String> findMbtiOption(String mbti,String sex);
    
    /**
     * mbti와 나이 2가지를 상세옵션에서 입력한 경우
     * @param lowAge
     * @param maxAge
     * @param mbti
     * @return 범위에 맞는 이름을 리턴해준다.
     */
    public List<String> findAgeMbtiOption(String lowAge, String maxAge, String mbti,String sex); 
    
    /**
     * 키, mbti 2가지를 상세옵션에서 입력한 경우.
     * @param lowHight
     * @param maxHight
     * @param mbti
     * @return 범위에 맞는 이름을 리턴해준다.
     */
    public List<String> findHightMbtiOption(String lowHight, String maxHight, String mbti,String sex); 
    
    /**
     * 키,나이 2가지를 상세옵션에서 입력한 경우.
     * @param lowHight
     * @param maxHight
     * @param lowAge
     * @param maxAge
     * @return 범위에 맞는 이름을 리턴해준다.
     */
    public List<String> findHightAgeOption(String lowHight, String maxHight, String lowAge, String maxAgem,String sex); 
    
    /**
     * 키, 나이, mbti 모두를 상세옵션에서 입력한 경우.
     * @param lowHight
     * @param maxHight
     * @param lowAge
     * @param maxAge
     * @return 범위에 맞는 이름을 리턴해준다.
     */
    public List<String> findHightAgeMBTIOption(String lowHight, String maxHight, String lowAge, String maxAge, String mbti,String sex); 
    
    /**
     * 상대방의 흑역사를 가져와서 보여줄때 사용.
     * @param id
     * @return 회원가입때 입력한 흑역사를 보여준다.
     */
    public String findHistory(String name);
   
    
    /**
     * 이름을 가지고 id를 검색해준다.
     * @param name
     * @return id
     */
    public String findIdByName(String name);
    
    /**
     * 아이디로 회원 정보를 객체로 받는다.
     * @param id
     * @return 회원 정보를 객체로 리턴.
     */
    public Member findMemberById(String id);
    
    /**
     *  채팅을 원치 않아서 no를 클릭시 db에 저장된이름을 초기화되게한다.
     * @param giveName 좋아요를 누른사람 이름
     * @param gaveName 좋아요를 받은사람 이름
     */
    public void updateNullThumbs(String giveName, String gaveName);
    
    /**
     * 검색 기록을 초기화 하기위해서 setSearch컬럼을 false로 만든다.
     * @param id
     * @return 성공하면 1, 실패하면 0
     */
    public int updateSearchReset(String id);
}
