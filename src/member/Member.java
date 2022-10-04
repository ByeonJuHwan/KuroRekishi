package member;

public class Member {
	// field
	private String id;
	private String pw;
	private String name;
	private String location;
	private String history;
	
	// 기본 생성자 (default constructor)
	public Member() {
		super();
	}
	
	// argument를 갖는 생성자
	public Member(String id, String pw, String name, String location, String history) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.location = location;
		this.history = history;
	}

	// getters / setters
	
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
	
	
	
	
	
}
