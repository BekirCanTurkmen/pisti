import java.util.Scanner;
public class Main {

	public static void main(String[] args) {

		Scanner scanner= new Scanner(System.in);

        Deck deck = new Deck();
        Card[] gameTable = new Card[52];
       
        int topCardOnTable = -1 ; // if there is card on the top of table
       
        int cardsGainer = -1; // person who took the cars last
        

        Game_plan player = new Game_plan();
        Game_plan computer = new Game_plan();
        
        deck.shuffle();
        deck.cut();
        deck.deal(computer, player);
        
        
        for ( int i= 0; i<4 ; i++) {
        	gameTable[i] = deck.cards[deck.topCard];
        	topCardOnTable++;
        	deck.topCard--;
        }

        

	}

}
