package vo.member;

import java.io.Serializable;

public class Member implements Serializable {

    private String userID;
	private String userPW;
	private String userName;
	private String userBirth;
	private String userPhone;


	public Member() {
		super();
	}

    public Member(String userID, String userPW, String userName, String userBirth, String userPhone) {
		super();
		this.userID = userID;
		this.userPW = userPW;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userPhone = userPhone;
		
	}


	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	
	@Override
	public String toString() {
		return "Member [userID=" + userID + ", userPW=" + userPW + ", userName=" + userName + ", userBirth=" + userBirth
				+ ", userPhone=" + userPhone + "]";
	}

}
