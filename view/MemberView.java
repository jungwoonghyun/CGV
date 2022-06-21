package view;

import java.util.Scanner;


import manager.MemberManager;
import vo.member.Member;

public class MemberView {
    
    private Member loginMember; // 로그인하면 매니저가 회원 정보 가져와서 담아놓을 변수
	private MemberManager mManager = new MemberManager();
	 // 선언만 해두고 로그인 시 멤버 객체 할당
	private Scanner sc = new Scanner(System.in);

    /**
	 * 프로그램 실행 시 나타나는 첫 화면
	 * 메인메뉴 선택바(1. 로그인 | 2. 회원가입 | 0. 프로그램 종료)
	 */
	public void mainMenu() {
		String CGV     = "  ____             _____          __     __\n" 
				+ " |  __|          |  ___           \\ \\   / / \n"
				+ " | |__           | |_  |           \\ \\ / /  \n"
				+ " |___ |          |_____|            \\___/\n";
		
		while(true) {
			System.out.println("= = = = = = = = = = = = = = = = = = = =");
			System.out.print(CGV);
			System.out.print("\n= = = = = = = = = = = = = = = = = = = =\n");
			System.out.print("\n\t🎬영화 그 이상의 감동, CGV📺\n");
			String choice;
			System.out.print("\n= = = = = = = = CGV = = = = = = = =\n"
						   + "\t1. 로그인\n"
						   + "\t2. 회원가입\n"
						   + "\t0. 프로그램 종료\n\n"
						   + "\t> 원하시는 번호를 입력해 주세요: ");
			choice = sc.nextLine();
			
			switch (choice) {
			case "2": 
				boolean signInResult = signIn(); // 회원가입 시 따로 logIn호출하지 않고 바로 로그인으로 fall through
				if(!signInResult){ // signIn에서 가입하지 않았다면 fall through가 일어나지 않고 스위치문을 탈출
					break;          //signIn() 함수는 여기서 뒤에 생성 예정
				}
			case "1": login(); break; //login() 함수는 여기서 뒤에 생성 예정
			
			case "0":
				System.out.println("\n= = = = = = = = = = = = = = = = = = = =");
				System.out.print("\t> 종료하시겠습니까? (y,n)? ");
				if (sc.nextLine().toUpperCase().charAt(0) == 'Y') {
					System.out.println("\n\t[프로그램을 종료합니다.]");
					return;
				}
				else {
					System.out.println("\n\t[메인 메뉴로 돌아갑니다.]\n");
					break;
				}
			default: System.out.println("\n  [잘못 입력하셨습니다. 다시 입력해 주세요.]\n"); break;
			}
		}
	}
	
    /**
	 * 로그인 성공 시 보여지는 서브메뉴 선택바
	 * (1. 영화 예매하기 | 2. 마이 페이지 | 0. 로그아웃)
	 */
	public void subMenu() {
		String choice;
		while (true) {
			
			System.out.println("\n= = = = = = = = = = = = = = = = = = = =\n");
			System.out.printf("\t🙋‍♀️반갑습니다 [%s]님!🙋‍♀️\n\n", loginMember.getUserName());
			System.out.println(" 1. 영화 예매하기 | 2. 마이 페이지 | 0. 로그아웃");
			System.out.print("\n\t> 원하시는 번호를 입력해 주세요 : ");
			choice = sc.nextLine();
		
			switch(choice) {
			case "1" : mv = new MovieMain(); 	// 로그인한 멤버 객체를 매개인자로 받는 생성자 사용
					  mv1 = new Movie();    //MovieMain() & Movie()은 main branch와 합병 후 가져올 예정
					  mv.main(null);
					
			break;
			case "2" : 
				if(myPageMenu()) { //myPageMenu() 아래에 만들 예정
					return;
				}
				break;
			case "0" : System.out.println(); return;
			default : System.out.println("\n\t[잘못 입력하셨습니다.]"); break;
			}
		}
	}

    /**
	 * 서브메뉴에서 마이페이지 선택 시 보여지는 메뉴 
	 * (1. 콘텐츠 메뉴 | 2. 마이 페이지 | 0. 로그아웃) -> 마이 페이지 메뉴 선택바
	 */
	public boolean myPageMenu() {
		boolean isMemDeleted = false;
		String choice;
		String myPageMenu = "\n= = = = = = = 마이 페이지 = = = = = = = =\n"
						  + "\t1. 회원 정보 확인\n"
						  + "\t2. 회원 정보 수정\n"
						  + "\t3. 회원 탈퇴\n"
						  + "\t0. 뒤로가기\n"
						  + "\n\t> 원하시는 번호를 입력해 주세요 : ";
		exit: 
		while (true) {
			System.out.print(myPageMenu);
			choice = sc.nextLine();

			switch (choice) {
			case "1": viewUser(); break;
			case "2": updateUser(); break;
			case "3": isMemDeleted = deleteUser(); break exit;
			case "0": break exit;	
			default: System.out.println("\n [잘못 입력하셨습니다. 이전 메뉴로 돌아갑니다.]");
			}
		}
		return isMemDeleted;
	}

    /**
	 * 로그인 메소드
	 */
	public void login() {
		System.out.println("\n= = = = = = = = 로그인 = = = = = = = = =");
		System.out.print("\t> ID : ");
		String id = sc.nextLine();
		System.out.print("\t> PassWord : ");
		String pw = sc.nextLine();
		
		loginMember = mManager.loginInfo(id);
		
		if(loginMember != null) { // 로그인한 회원정보가 파일에 존재한다면 실행
			if(pw.equals(loginMember.getUserPW())){ // 로그인한 회원정보와 입력 pw가 같은지 확인하고 다음메뉴로 넘어감
				subMenu();
			}else {
				System.out.println("\n  [비밀번호가 틀렸습니다. 다시 입력해주세요.]\n");
			}
		} else { // 로그인한 회원정보가 파일에 존재하지 않을 경우
			System.out.println("\n  [계정 정보가 없습니다. 메인메뉴로 돌아갑니다.]\n");
			return;
		}
	}

    /**
	 * 회원가입 메소드
	 */
	public boolean signIn() {
		// 회원가입 취소시 mainMenu에서 fall-through가 일어나지 않도록 signIn메소드에 리턴타입을 설정
		boolean signInCheck = false; 
		Member mem = new Member();
		System.out.print("\n= = = = = = = = 계정 생성 = = = = = = = =\n");
		System.out.print("\t> ID : ");
		mem.setUserID(sc.nextLine());
		System.out.print("\t> PassWord : ");
		mem.setUserPW(sc.nextLine());
		System.out.print("\t> 이름 : ");
		mem.setUserName(sc.nextLine());
		System.out.print("\t> 생년월일(6자리) ex.990909 : ");
		mem.setUserBirth(sc.nextLine());
		System.out.print("\t> 핸드폰번호(-제외) : ");
		mem.setUserPhone(sc.nextLine());
		
		System.out.print("\n= = = = = = = = = = = = = = = = = = = =\n");
		System.out.print("\t1. 완료 | 2. 취소\n\n"
					   + "\t> 번호 입력 : ");
		String choice = sc.nextLine();
		
		switch(choice) {
		case "1" : boolean isExist = mManager.insertMember(mem);// 회원정보 추가 + 입력받은 멤버객체 id값이 이미 존재하는 경우 false 리턴
					if(!isExist) { // 이미 존재하는 id일 경우 아래 출력문 없이 탈출
						return signInCheck; // false 리턴
					}
				   System.out.println("\n\t[✨가입을 축하드립니다!✨]");
				   signInCheck = true;
				   break;
				   
		case "2" : System.out.println();
				   break;
		}
		return signInCheck; // boolean값 리턴
	}
    
    /**
	 * 1. 회원 정보 확인 메소드
	 */
	public void viewUser() { 
        //		System.out.println("*****외부파일에 잘 저장되고 있는지 확인용*****");
        //		mManager.viewAllUser();
                
                System.out.printf("\n= = = = = = %s님의 회원 정보 = = = = = = =\n", loginMember.getUserName());
                System.out.println("\t> 아이디 : " + loginMember.getUserID());
                System.out.println("\t> 이름 : " + loginMember.getUserName());
                System.out.println("\t> 생년월일 : " + loginMember.getUserBirth());
                System.out.println("\t> 핸드폰번호 : " + loginMember.getUserPhone());
                
    }

    /**
	 * 2. 회원 정보 수정 메소드
	 */
	public void updateUser() {
		while(true) {
			System.out.print("\n= = = = = = = 회원 정보 수정 = = = = = = =\n");
			System.out.println(" → 수정을 원하시면, 기존 비밀번호를 입력해주세요.");
			System.out.print("\n\t> PassWord : ");
			String inputPw = sc.nextLine();
			
			// 로그인된 회원의 비번과 다시 입력한 비번이 같으면 정보 수정
			if((loginMember.getUserPW()).equals(inputPw)) {
				System.out.print("\n= = = = = = = = = = = = = = = = = = =\n");
				Member oldUser = mManager.viewUser(loginMember.getUserID());
				Member newUser = new Member(loginMember.getUserID(), loginMember.getUserPW(), loginMember.getUserName(),
			              loginMember.getUserBirth(), loginMember.getUserPhone());
				
				System.out.printf("\t    <기존 회원 정보>\n\n"
								+ "  아이디[%s]   비밀번호[%s]   이름[%s]"
								+ "\n  생년월일[%s]   폰번호[%s]   "
					
								,oldUser.getUserID(), oldUser.getUserPW(), oldUser.getUserName(), oldUser.getUserBirth(),
								oldUser.getUserPhone());
				
				System.out.println("\t<변경할 회원 정보 입력>");
				System.out.print("\n\t> 새로운 비밀번호 입력: ");
				newUser.setUserPW(sc.nextLine());
				System.out.print("\t> 새로운 핸드폰번호 입력: ");
				newUser.setUserPhone(sc.nextLine());
				
				mManager.updateUser(oldUser, newUser);
				System.out.println();
				System.out.print("\n  [다음과 같이 정보 수정이 완료되었습니다.]\n");
				System.out.print("\n= = = = = = = = = = = = = = = = = = =\n");
				System.out.printf("\n  아이디[%s]   비밀번호[%s]   이름[%s]"
								+ "\n  생년월일[%s]   폰번호[%s]   "
								,oldUser.getUserID(), newUser.getUserPW(), oldUser.getUserName(), oldUser.getUserBirth(),
								newUser.getUserPhone());
			
				loginMember = newUser;
				System.out.println("\t[마이 페이지로 돌아갑니다.]");
				return;
			}
			else {
				System.out.println("\n  [비밀번호가 틀렸습니다. 다시 입력해주세요.]");
			}	
		}
	}

    /**
	 * 3. 회원 탈퇴 메소드
	 */
	public boolean deleteUser() {
		boolean isDeleted = false;
		while(true) {		
			System.out.print("\n= = = = = = = = 회원 탈퇴 = = = = = = = =\n");
			System.out.print(" → 탈퇴를 원하시면, 비밀번호를 입력해주세요.\n");
			System.out.print("\n\t> PassWord : ");
			String inputPw = sc.nextLine();
			
			if((loginMember.getUserPW()).equals(inputPw)) {
				System.out.print("\n❗❗탈퇴 시 모든 데이터가 삭제되며, 복구 불가능합니다.❗❗\n\n\t> 정말 탈퇴하시겠습니까? (y/n)? ");
				if(sc.nextLine().toLowerCase().charAt(0) == 'y') {
					mManager.deleteUser(loginMember.getUserID());
					System.out.println();
					System.out.println("\t[정상적으로 탈퇴되었습니다.]\n"
									  +" [그동안 CGV와 함께 해주셔서 감사합니다.]\n");
					return !isDeleted; // true 리턴
				}
				else {
					System.out.println("\n\t[로그인한 화면으로 돌아갑니다.]"
							+ "\n     [CGV와 함께 즐거운 영화 관람 되세요.]");
					break;
				}
			}
			else
				System.out.println("\n  [비밀번호가 틀렸습니다. 다시 입력해주세요.]");
		}
		return isDeleted; // false 리턴
	}
}
