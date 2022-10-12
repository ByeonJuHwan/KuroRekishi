package member;

public class Member {
	
    public interface Entity{
    	String TBL_MEMBER = "MEMBER"; // 테이블 이름
    	String COL_MEM_ID = "ID"; // 회원 아이디
    	String COL_MEM_PW = "PW"; // 회원 비밀번호
    	String COL_MEM_NAME = "NAME"; // 회원 이름
    	String COL_MEM_SEX = "SEX"; // 회원 성별
    	String COL_MEM_LOC = "LOC"; // 회원 지역
    	String COL_MEM_HISTORY = "HISTORY"; // 회원 흑역사
    	String COL_MEM_GAVEDTHUMBNAME = "GAVEDTHUMB";
    	String COL_MEM_GIVETHUMBNAME = "GIVETHUMB";
    }
    
    
    
    // field
	private String id;
	private String pw;
	private String name;
	private String sex;
	private String location;
	private String history;
	private String gavedThumbName;
	private String givedThumbName;
	
	// 기본 생성자 (default constructor)
	public Member() {}
	
	public Member(String name, String gavedThumbName, String givedThumbName) {
		super();
		this.name = name;
		this.gavedThumbName = gavedThumbName;
		this.givedThumbName = givedThumbName;
	}

	// argument를 갖는 생성자
	public Member(String id, String pw, String name, String sex, String location, String history) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.sex = sex;
		this.location = location;
		this.history = history;
	}
	
	
	

	// getters / setters
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getGavedThumbName() {
		return gavedThumbName;
	}

	public void setGavedThumbName(String gavedThumbName) {
		this.gavedThumbName = gavedThumbName;
	}

	public String getGivedThumbName() {
		return givedThumbName;
	}

	public void setGivedThumbName(String givedThumbName) {
		this.givedThumbName = givedThumbName;
	}
	
	
	
	
	
}
