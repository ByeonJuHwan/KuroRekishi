package member;

public class MemberDaoImpl implements MemberDao{

	// singleton
    // 1. 자기자신 타입의 private static인 변수 선언
    private static MemberDaoImpl instance = null;
    // 2. private 생성자
    private MemberDaoImpl () {
        //TODO 연락처 프로그램 fileutil 패키지 참고해서 이미지 넣기랑 파일 만들기 참고.
        
    }
    // 3. 자기 자신을 리턴하는 public static 메서드 정의
    public static MemberDaoImpl getInstance() {
        if(instance == null) {
            instance  = new MemberDaoImpl();
        }
        return instance;
    }
    
    
    
    
    
    @Override
	public boolean findMember(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addmember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateMember(Member member) {
		// TODO Auto-generated method stub
		
	}

}
