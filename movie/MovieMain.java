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
		
    }
}
