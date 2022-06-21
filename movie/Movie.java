package movie;

public class Movie {
    String[] film= {"탑건 메버릭","헤어질 결심","범죄도시2"};
	int row =6;
	int column =7;
    int costs[] = {11000,12500,15000};
	float price=0;
    float dc=0;
	String buyway[]= {"현장","인터넷","쿠폰"};

    //------------movie choose---------------

    public void moviename() {
        System.out.println("Choose number of movie");
        System.out.println("-----------------");
            for(int i=0;i<film.length;i++) {
                System.out.print(i+1);
                System.out.println(". "+film[i]);
            }
    }

    public String movie_choice(int movie_num) {
        boolean i =true;
        while(i){
        if(movie_num==1) {
            return film[0]; 
        }
        else if(movie_num==2) {
            return film[1];
        }
        else if(movie_num==3) {
            return film[2];
        }
        else {
        return "wrong movie number choose again";
        }
        }
        return "\n";
    }

    //---------------movie seat------------------

    public void showseat() {
		System.out.println("choose your seat");
		for(int i=0;i<this.row;i++) {
			char row = (char)('A'+i);
			System.out.print(row+" ");
			for(int j=0;j<this.column;j++) {
				System.out.print("o");//⬜ special square
			}
			System.out.println(" ");
		}
	}

    public void show_choosed_seat(char row_out,int column_out) {
		String[][] seat;
		seat = new String[this.row][this.column];
		
		seat[(int) row_out-65][column_out-1] = "ON";
		System.out.println("This is your seat");
		for(int i=0;i<this.row;i++) {
			char row = (char)('A'+i);
			System.out.print(row+" ");
			for(int j=0;j<this.column;j++) {
				if(seat[i][j] == "ON") {
					System.out.print("*");//⬛
				}else {
					System.out.print("o");//⬜️
				}
			}
			System.out.println(" ");
		}
	}

    public float cost(char row_out) {
	
		int choice_seat = (int) row_out; 
	if(choice_seat-65<=1) {
		price = costs[0];
	}
	else if(choice_seat-65<=3) {
		price = costs[1];
	}
	else if(choice_seat-65<=5) {
		price = costs[2];
	}
	else System.out.println("fail");
	return price;
	}
    
    public void show_buyway() {
		System.out.println("Choose your buyway");
		for(int i=0;i<buyway.length;i++) {
			System.out.print(i+1);
			System.out.println(". "+buyway[i]);
		}
		System.out.println("-----------------------------");
	}

    public String choose_buyway(int choice_way) {
        if(choice_way == 1) {
            return buyway[0]; 
        }
        else if(choice_way == 2) {
            return buyway[1]; 
        }
        else if(choice_way == 3) {
            return buyway[2]; 
        }
        else return "You choose wrong number";
        }
}
