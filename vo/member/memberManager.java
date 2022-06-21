package vo.member;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import vo.member.Member;

public class memberManager {
    private HashMap<String, Member> memberMap = new HashMap<>();
	private File memberSrc = new File("data/member/userList.ser");
	

	/**
	 * 지정된 외부파일에서 멤버 전체 정보 읽어오는 메소드 
	 */
	public void readAllMembers() {
		try (
				ObjectInputStream ois = 
				new ObjectInputStream(new BufferedInputStream(new FileInputStream(memberSrc)));
		){
			memberMap = (HashMap<String, Member>) ois.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

    
}
