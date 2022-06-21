package movie;
import java.util.Scanner;

public class MovieMain {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String title;
		float price;
		int age;
		float dc;
		
		Movie movie = new Movie();

        //----------------영화 이름 표시 및 선택---------------------------
		
		movie.moviename();// 예매가능 영화 표시
		System.out.print("\n\t> 예매하실 영화를 선택해 주세요 : ");
		int choice_movie = scanner.nextInt();

        title = movie.movie_choice(choice_movie); //고른 영화 제목 String 저장
		
		//-------------------좌석 선택 ----------------------
		
		movie.showseat();//영화 좌석을 2차원 형태로 표시
		
		System.out.println("insert row(Capital letter only)");
		
		char row_out = scanner.next().charAt(0);
		System.out.println("insert column");
		
		int column_out = scanner.nextInt();
		
		movie.show_choosed_seat(row_out, column_out);

		// 나이에 따른 할인률 계산을 위해 임시로 나이를 외부에서 입력받게함
		// 목표는 회원가입 정보의 나이를 기반으로 나이 설정

		System.out.println("Input your age");
		System.out.println("-------------------");
		
		age = scanner.nextInt();//나이
		

		//-------------할인 방법에 따른 할인률---------------------

		movie.show_buyway();// 할인방법 표시
		
		int choice_way = scanner.nextInt();

		dc = movie.buyDC(choice_way)+movie.AgeDC(age);
		
		price = movie.cost(row_out); //행의 위치에 따라 가격 차등 적용

		int totalprice =  (int) (price*(1-dc));
		
		
		System.out.print("\n= = = = = = = = CGV = = = = = = = =\n"
				   + "영화명\t 금액\t  나이\t 할인된 금액\n\n"  
				   +  title+"\t"+price+"\t   "+age+"\t   " +totalprice+"\n" 
		           +"\n예약이 완료되었습니다. 즐거운 영화관람 되세요.");
		
		}
    }



