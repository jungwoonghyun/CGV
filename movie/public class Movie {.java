package movie;

public class Movie {
    String[] film= {"탑건 메버릭","헤어질 결심","범죄도시2"};
	int row =6;
	int column =7;
    int costs[] = {11000,12500,15000};
	float price=0;
    float dc=0;
	String buyway[]= {"현장","인터넷","쿠폰"};

    public void moviename() {
        System.out.println("Choose number of movie");
        System.out.println("-----------------");
            for(int i=0;i<film.length;i++) {
                System.out.print(i+1);
                System.out.println(". "+film[i]);
            }
    }

    
}
