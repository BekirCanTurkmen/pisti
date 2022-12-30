import java.util.Scanner;

public class Game_plan {
	public Card[] cardsInHandPlayer;
	public Card[] cardsInHandComputer;
	public int playedNumberOfCards = 0; //
	public int score = 0;
	public int pistiNum = 0; // how much pisti
	public Card[] wonCardsPlayer = new Card[1]; // cards you gain if you play "same" or "j"
	public Card[] wonCardsComputer = new Card[1];
	public int wonCardsNum = 0; // how much card do you have
	Scanner input = new Scanner(System.in);

	public void setup() {
		Deck deck = new Deck(); // do deck
		cardsInHandPlayer = new Card[4];
		cardsInHandComputer = new Card[4];

		Card[] yerdekiKartlar = new Card[4];
		deck.shuffle();
		deck.cut();

		for (int i = 0; i < 4; i++) {
			yerdekiKartlar[i] = deck.cards[i];
			System.out.println("yerdeki kartlar:  " + yerdekiKartlar[i].getFace());
			this.playedNumberOfCards++;
		}
		int elSayacıBilgisyar = 0;
		int elSayacıOyuncu = 0;
		for (int i = playedNumberOfCards; i < playedNumberOfCards + 4; i++) {
			cardsInHandPlayer[elSayacıOyuncu] = deck.cards[i];
			elSayacıOyuncu++;
		}
		playedNumberOfCards += elSayacıOyuncu;
		for (int i = playedNumberOfCards; i < playedNumberOfCards + 4; i++) {
			cardsInHandComputer[elSayacıBilgisyar] = deck.cards[i];
			elSayacıBilgisyar++;
		}
		playedNumberOfCards += elSayacıBilgisyar;
		System.out.println(playedNumberOfCards);
		System.out.println("yerdeki son kart: " + yerdekiKartlar[yerdekiKartlar.length - 1].getFace() + ", "
				+ yerdekiKartlar[yerdekiKartlar.length - 1].getSuit());

		for (int i = 0; i < cardsInHandPlayer.length; i++) {
			System.out
					.println("computer:  " + cardsInHandComputer[i].getFace() + "   " + cardsInHandComputer[i].getSuit());
			System.out.println("----------------");
		}
		boolean playerTurn = true;
	    while(playedNumberOfCards < 52) {
	    	
	    	if(playerTurn ) {
	    		if(cardsInHandPlayer.length == 0) {
	    			 cardsInHandPlayer = new Card[4];
	    			 cardsInHandComputer = new Card[4];
	    			for (int i = playedNumberOfCards; i < playedNumberOfCards + 4; i++) {
	    				cardsInHandPlayer[i - playedNumberOfCards] = deck.cards[i];
	    				
	    			}
	    			
	    			for (int i = playedNumberOfCards; i < playedNumberOfCards + 4; i++) {
	    				cardsInHandComputer[i - playedNumberOfCards] = deck.cards[i];
	    				
	    			}
	    			playedNumberOfCards += 8;
	    			
	    			
	    		}
	    		
	    		yerdekiKartlar = playerPlay(yerdekiKartlar);
	    		playerTurn = false;
	    	} else {
	    		System.out.println("bilgisyarın sırası:  ");
	    		yerdekiKartlar = computerPlay(yerdekiKartlar);
	    		playerTurn = true;
	    	}
	    }

	}

	public Card[] computerPlay(Card[] cardsOnGround) {
		boolean collect = false;
		Card playedCard = null;

		for (int i = 0; i < cardsInHandComputer.length; i++) {
			if (cardsOnGround[cardsOnGround.length - 1] == null) {
				playedCard = cardsInHandComputer[i];
				cardsInHandComputer = removeTheElement(cardsInHandComputer, i);
				cardsOnGround = addOneCardToArray(cardsOnGround, playedCard);
				break;
			} else if (cardsInHandComputer[i].getFace() == cardsOnGround[cardsOnGround.length - 1].getFace()) {
				playedCard = cardsInHandComputer[i];
				cardsInHandComputer = removeTheElement(cardsInHandComputer, i);

				for (int j = 0; j < cardsInHandComputer.length; j++) {
					System.out.println("elinde J harici kart varsa:  " + cardsInHandComputer[j].getFace() + "     "
							+ cardsInHandComputer[j].getSuit());

				}
				collect = true;
				break;

			} else if (cardsInHandComputer[i].getFace().equals("J")) {
				playedCard = cardsInHandComputer[i];
				cardsInHandComputer = removeTheElement(cardsInHandComputer, i);

				for (int j = 0; j < cardsInHandComputer.length; j++) {
					System.out.println("elinde j varsa:  " + cardsInHandComputer[j].getFace() + "     "
							+ cardsInHandComputer[j].getSuit());

				}
				collect = true;
				break;

			} else if (i == cardsInHandComputer.length - 1) {
				playedCard = cardsInHandComputer[i];
				cardsInHandComputer = removeTheElement(cardsInHandComputer, i);
				cardsOnGround = addOneCardToArray(cardsOnGround, playedCard);

				for (int j = 0; j < cardsInHandComputer.length; j++) {
					System.out.println("computerhand  :  " + cardsInHandComputer[j].getFace() + "     "
							+ cardsInHandComputer[j].getSuit());

				}
				break;

			}
		}
		if (collect) {

			handleComputerCardCollect(cardsOnGround, playedCard);
			cardsOnGround = new Card[4];

			return cardsOnGround;
		} else {
			return cardsOnGround;
		}

	}

	public Card[] playerPlay(Card[] cardsOnGround) {

		if (cardsOnGround[cardsOnGround.length - 1] != null) {
			System.out.println("En üstteki kart :  " + cardsOnGround[cardsOnGround.length - 1].getFace()
					+ cardsOnGround[cardsOnGround.length - 1].getSuit());
		}
		System.out.println("\n Lütfen oynamak için elinizden kart seçiniz:");
		for (int i = 0; i < cardsInHandPlayer.length; i++) {
			System.out.print(i + ": " + cardsInHandPlayer[i].getFace() + cardsInHandPlayer[i].getSuit() + ", ");
		}

		int selection = input.nextInt();
		boolean collect = false;
		Card playedCard = null;
		if (cardsOnGround[cardsOnGround.length - 1] == null) {
			playedCard = cardsInHandPlayer[selection];
			cardsInHandPlayer = removeTheElement(cardsInHandPlayer, selection);

		} else if (cardsInHandPlayer[selection].getFace() == cardsOnGround[cardsOnGround.length - 1].getFace()) {
			playedCard = cardsInHandPlayer[selection];
			cardsInHandPlayer = removeTheElement(cardsInHandPlayer, selection);
			collect = true;

		} else if (cardsInHandPlayer[selection].getFace().equals("J")) {
			playedCard = cardsInHandPlayer[selection];
			cardsInHandPlayer = removeTheElement(cardsInHandPlayer, selection);
			collect = true;

		} else {
			playedCard = cardsInHandPlayer[selection];
			cardsInHandPlayer = removeTheElement(cardsInHandPlayer, selection);
			cardsOnGround = addOneCardToArray(cardsOnGround, playedCard);

		}

		if (collect) {

			handlePlayerCardCollect(cardsOnGround, playedCard);
			cardsOnGround = new Card[1];

			return cardsOnGround;
		} else {
			return cardsOnGround;
		}

	}

	public void handleComputerCardCollect(Card[] cardsOnGround, Card playedCard) {
		 wonCardsComputer = addOneCardToArray(wonCardsComputer, playedCard);
		    for(int i = 0; i<cardsOnGround.length;i++) {
		    	System.out.println(cardsOnGround[i].getFace()+ cardsOnGround[i].getSuit());
		    }
			for (int i = 0; i < cardsOnGround.length; i++) {
				wonCardsComputer = addOneCardToArray(wonCardsComputer, cardsOnGround[i]);
				System.out.println("Kazanılan kartalr:    " + wonCardsComputer[i].getFace());
				if (i == cardsOnGround.length - 1) {
					System.out.println("kazanılan kartlar:     " + wonCardsComputer[i + 1].getFace());
				}

			}

	}

	public void handlePlayerCardCollect(Card[] cardsOnGround, Card playedCard) {
		 Card[] wonCardsPlayerTemp = addOneCardToArray(wonCardsPlayer, playedCard);
		 wonCardsPlayer = wonCardsPlayerTemp;
		 
		for (int i = 0; i < cardsOnGround.length; i++) {
			Card[] wonCardsPlayerTemp2 = addOneCardToArray(wonCardsPlayer, cardsOnGround[i]);
			wonCardsPlayer = wonCardsPlayerTemp2;
			
			System.out.println("Kazanılan kartalr:    " + wonCardsPlayer[i].getFace());
			if (i == cardsOnGround.length - 1) {
				System.out.println("kazanılan kartlar:     " + wonCardsPlayer[i + 1].getFace());
			}

		}

	}

	// handle played Card
	public void handleCardPlay(Card playedCard, Card[] cardsInHand) {

	}

	public Card[] removeTheElement(Card[] arr, int index) {

		// If the array is empty
		// or the index is not in array range
		// return the original array
		if (arr == null || index < 0 || index >= arr.length) {

			return arr;
		}

		// Create another array of size one less
		Card[] anotherArray = new Card[arr.length - 1];

		// Copy the elements except the index
		// from original array to the other array
		for (int i = 0, k = 0; i < arr.length; i++) {

			// if the index is
			// the removal element index
			if (i == index) {
				continue;
			}

			// if the index is not
			// the removal element index
			anotherArray[k++] = arr[i];
		}

		// return the resultant array
		return anotherArray;
	}

	public static Card[] addOneCardToArray(Card[] initialArray, Card newValue) {

		Card[] newArray = new Card[initialArray.length + 1];
		for (int index = 0; index < initialArray.length; index++) {
			newArray[index] = initialArray[index];
		}

		newArray[newArray.length - 1] = newValue;
		return newArray;
	}

	// Driver Code

	public Card[] getCardsInHandPlayer() {
		return cardsInHandPlayer;
	}

	public void setCardsInHandPlayer(Card[] cardsInHandPlayer) {
		this.cardsInHandPlayer = cardsInHandPlayer;
	}

	public Card[] getCardsInHandComputer() {
		return cardsInHandComputer;
	}

	public void setCardsInHandComputer(Card[] cardsInHandComputer) {
		this.cardsInHandComputer = cardsInHandComputer;
	}

	public int getNumberOfCards() {
		return playedNumberOfCards;
	}

	public void setNumberOfCards(int numberOfCards) {
		this.playedNumberOfCards = numberOfCards;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPistiNum() {
		return pistiNum;
	}

	public void setPistiNum(int pistiNum) {
		this.pistiNum = pistiNum;
	}

	public int getWonCardsNum() {
		return wonCardsNum;
	}

	public void setWonCardsNum(int wonCardsNum) {
		this.wonCardsNum = wonCardsNum;
	}

}