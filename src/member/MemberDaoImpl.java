package member;

public class MemberDaoImpl implements MemberDao{

	// singleton
    // 1. 자기자신 타입의 private static인 변수 선언
    private static MemberDaoImpl instance = null;
    
    // 2. private 생성자
    private MemberDaoImpl () {}
    // 3. 자기 자신을 리턴하는 public static 메서드 정의
    public static MemberDaoImpl getInstance() {
        if(instance == null) {
            instance  = new MemberDaoImpl();
        }
        return instance;
    }
    
    
    
    
    /**
     * 중복되는 아이디 검열
     * 
     */
    @Override
	public boolean findMember(String id) {
		// TODO Auto-generated method stub
		return false;
	}

    /**
     * 회원 가입
     */
	@Override
	public int addmember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 회원수정
	 */
	@Override
	public void updateMember(Member member) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 채팅창 이름 설정
	 */
	@Override
	public String fineName(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
