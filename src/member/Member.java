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
    	String COL_MEM_HIGHT = "HIGHT";
    	String COL_MEM_AGE = "AGE";
    	String COL_MEM_MBTI= "MBTI";
    	String COL_MEM_SETSEARCH = "SETSEARCH";
    	String COL_MEM_POINT = "POINT";
    	String COL_MEM_GIVESTARNUM = "GIVESTARNUM";
    	String COL_MEM_GIVESTARID = "GIVESTARID";
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
	private String hight;
	private String age;
	private String mbti;
	private String setSearch;
	private int point;
	private int giveStarNum;
	private String giveStarId;
	
	

    public String getGiveStarId() {
        return giveStarId;
    }

    // 기본 생성자 (default constructor)
	public Member() {}
	
	// 좋아요 누를때 저장하는 객
	public Member(String name, String gavedThumbName, String givedThumbName) {
		super();
		this.name = name;
		this.gavedThumbName = gavedThumbName;
		this.givedThumbName = givedThumbName;
	}

    // 회원 가입시 저장하는 객체
	public Member(String id, String pw, String name, String location,String sex, String history, String hight,
            String age, String mbti) {
        super();
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.sex = sex;
        this.location = location;
        this.history = history;
        this.hight = hight;
        this.age = age;
        this.mbti = mbti;
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

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public String getSetSearch() {
        return setSearch;
    }

    public int getPoint() {
        return point;
    }

    public int getGiveStarNum() {
        return giveStarNum;
    }

    
	
	
	
	
	
}
