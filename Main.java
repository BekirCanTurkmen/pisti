import java.util.Scanner;
public class Main {

	public static void main(String[] args) {

		Scanner scanner= new Scanner(System.in);

        Deck deck = new Deck();

        Game_plan player = new Game_plan();
        Game_plan computer = new Game_plan();
        
        deck.shuffle();
        deck.cut();
        deck.deal(computer, player);
        
        for ( int i= 0; i<4 ; i++) {
        	
        }

        

	}

}
