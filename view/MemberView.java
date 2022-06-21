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
}
