package vo.member;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    /**
	 * 멤버 전체 정보 외부파일로 출력하는 메소드
	 */
	public void writeAllMembers() {
		try (
				ObjectOutputStream oos = 
				new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(memberSrc)));
		){
			oos.writeObject(memberMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}


}
