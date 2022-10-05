package member;

public interface MemberDao {
	
	
	/**
	 * 회원가입시 아이디 중복확인을 위한 메서드.
	 * @param id 회원가입시 원하는 아이디 입력.
	 * @return 아이디가 존재하면 true, 아이디가 존재하지 않아서 사용할수 있으면 false.
	 */
	public boolean findMember(String id);
	
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
	public void updateMember(Member member);
	
	
}
