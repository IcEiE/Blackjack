import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> currentDeck = new ArrayList<Card>();

	/*
	 * Constructor which takes in an boolean argument, creates a deck with the
	 * help of createDeck function and shuffles the deck if the boolean value
	 * shuffle is true.
	 */
	public Deck(boolean shuffle) {
		createDeck();
		if (shuffle) {
			shuffleDeck();
		}
	}

	// Creates a deck of cards by looping though 1-13 and adds each kind of suit
	// for the value to the arrayList.
	private void createDeck() {
		for (int cardValue = 2; cardValue < 15; cardValue++) {
			currentDeck.add(createCard(cardValue, Suit.Hearts));
			currentDeck.add(createCard(cardValue, Suit.Spades));
			currentDeck.add(createCard(cardValue, Suit.Clubs));
			currentDeck.add(createCard(cardValue, Suit.Diamonds));
		}
	}

	// Takes cardValue and type of suit and creates a card then
	// returns it.
	private Card createCard(int cardValue, Suit cardSuit) {
		Card newCard = new Card(cardValue, cardSuit);
		return newCard;
	}

	// Shuffles the deck. If the Deck has less then two objects it can't be
	// shuffled and thereafter return feedback on this matter.
	public void shuffleDeck() {
		if (currentDeck.size() > 1) {
			Collections.shuffle(currentDeck);
		} else {
			System.out.println("The deck is to small to shuffle.");
		}
	}

	// Removes the first Card from the deck and gives it to the user asking for
	// it. If the deck doesn't have any cards it returns feedback on this
	// matter.
	public Card takeACard() {
		if (currentDeck.size() > 0) {
			return currentDeck.remove(0);
		} else {
			System.out.println("Sorry, you have no cards inside the Deck!");
			return null;
		}
	}

	// takes a card and adds it to the end of a given deck. if amount of used
	// cards exceeds 20 the deck will be shuffled again!
	public void returnCardToDeck(Card usedCard) {
		currentDeck.add(usedCard);
	}
}