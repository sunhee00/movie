package member;

import java.sql.Date;

public class MemberVO {

	private String id; //아이디
	private String name; //이름
	private String pw; //비밀번호
	private String email; //이메일
	private String hp; //전화번호
	private Date birth; //생년월일
	private int mno; //회원번호
	private String grade; //회원등급
	
	public MemberVO() {
		super();
	}

	public MemberVO(String id, String pw, String email, String hp) {
		super();
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.hp = hp;
	}

	public MemberVO(String id, String name, String pw, String email, String hp, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.email = email;
		this.hp = hp;
		this.birth = birth;
	}
	
	public MemberVO(String id, String name, String pw, String email, String hp, Date birth, int mno) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.email = email;
		this.hp = hp;
		this.birth = birth;
		this.mno = mno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	
	

}
