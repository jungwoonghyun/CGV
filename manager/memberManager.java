package manager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;

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

    /**
	 * mainMenu에서 로그인한 멤버가 누구인지 받아서 subMenu에서 사용하기 위한 메소드
	 */
	public Member loginInfo(String Id) {
        readAllMembers();
    
    return memberMap.get(Id); // 맵에 없는 키로 호출시 null리턴
    }

    /**
	 * 멤버 정보 추가 메소드
	 */
	public boolean insertMember(Member mem) {
		boolean isIdExist = true; // 입력받은 멤버객체 id값이 이미 존재하는 경우 false
		if(!memberMap.containsKey(mem.getUserID())) {
			memberMap.put(mem.getUserID(), mem);
			writeAllMembers();
		}else {
			System.out.println("\n\t[이미 존재하는 Id입니다.]\n\t[메인 메뉴로 돌아갑니다.]\n");
			isIdExist = false;
			return isIdExist;
		}
		return isIdExist;
	}

    /**
	 * 모든 멤버 정보 확인 메소드
	 */
	public void viewAllUser() {
		Set<String> keySet = memberMap.keySet();
		
		// memberMap에 멤버 객체가 잘 저장되고 있는지 확인용
		for(String id : keySet) {
			Member value = memberMap.get(id);
			System.out.println("***" + id + " = " + value); 
		}
	}

	/**
	 * 멤버 정보 확인 메소드
	 */
	public Member viewUser(String id) {
		return memberMap.get(id);
		
	}



	
}
