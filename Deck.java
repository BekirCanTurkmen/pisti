import java.util.Scanner;

public class Deck {
	 public static Card[] cards;
	    Scanner scanner = new Scanner(System.in);

	    public Deck() {
	        String[] suit = {"♠", "♣", "♥", "♦"};
	        String[] face = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	      
	        cards = new Card[52];

	        
	        
	        for (int count = 0; count< 52; count++) {
	            cards[count] = new Card(suit[count / 13], face[count % 13]);
	        }
	    }

	    public void shuffle() {

	        for (int i = cards.length - 1; i >= 1; i--) {
	            int j = (int) (Math.random() * (i + 1));
	            Card temporary = cards[i];
	            cards[i] = cards[j];
	            cards[j] = temporary;
	        }
	    }

	    
	    public void cut() {

	        System.out.println("Where do you want to cut(1-52)? "); // The spot you will cut
	        
	        int cutNum = scanner.nextInt();
	        
	        Card newArray[] = new Card[cards.length];

	        for (int i = 0; i < cards.length; i++) {
	        	newArray[i] = cards[i];
	        }for (int i = 0; i < cards.length; i++) {
	        	cards[i] = newArray[(cutNum + i) % 52];
	        }
	    }

	    public int topCard = 51;
	    public void deal(Game_plan player, Game_plan computer ){
	    	
	       int numberOfCards = 0;

	            for (int i = topCard; i >= topCard - 6; i=i-2) {
	                
	                player.cardsInHand[numberOfCards] = cards[i];
	                computer.cardsInHand[numberOfCards] = cards[i-1];
	                numberOfCards++;
	        }
	            player.numberOfCards= 4;
	            computer.numberOfCards= 4;
	            topCard = topCard - 8;
	    }
}
