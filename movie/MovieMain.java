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
		
		//-------------------좌석 선택 및 가격----------------------
		
		movie.showseat();//영화 좌석을 2차원 형태로 표시
		
		System.out.println("insert row(Capital letter only)");
		
		char row_out = scanner.next().charAt(0);
		System.out.println("insert column");
		
		int column_out = scanner.nextInt();
		
		movie.show_choosed_seat(row_out, column_out);
		
		
		//price = movie.cost(row_out); //행의 위치에 따라 가격 차등 적용
		

    }


}
