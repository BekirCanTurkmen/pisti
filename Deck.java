import java.util.Arrays;
import java.util.Scanner;
public class Deck {
    public Card[] cards;
    Scanner scanner = new Scanner(System.in);
    public String[] suit = {"♠", "♣", "♥", "♦"};
    public String[] face = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};



    public Deck() {


        cards = new Card[52];



        for (int count = 0; count< 52; count++) {
            cards[count] = new Card(count, suit[count / 13], face[count % 13]);
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
    public void deal(Game_plan gameplan){

        int numberOfPlayedCards = 0;

        for (int i = topCard; i >= topCard - 6; i=i-2) {

            gameplan.cardsInHandPlayer[numberOfPlayedCards] = cards[i];
            gameplan.cardsInHandComputer[numberOfPlayedCards] = cards[i-1];
            numberOfPlayedCards++;
        }

        topCard = topCard - 8;
    }

    @Override
    public String toString() {
        return "Deck [scanner=" + scanner + ", suit=" + Arrays.toString(suit) + ", face=" + Arrays.toString(face)
                + ", topCard=" + topCard + "]";
    }
}