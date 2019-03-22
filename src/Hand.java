import java.util.ArrayList;

/*
 * Class which represent an hand/player in Blackjack.
 */
public class Hand {
	private int totalValue = 0; // The sum of all cards currently held by this
								// hand.

	private ArrayList<Card> currentHand = new ArrayList<Card>(); // The Deck
																	// which
																	// this hand
																	// is
																	// playing
																	// with
	private int amoutOfWins = 0; // Count the amount of wins won by this hand.
	private String name;

	/*
	 * Constructor which imp. the name of this hand.
	 */
	public Hand(String name) {
		this.name = name;
	}

	/*
	 * Add a card to this hand from the deck which is being played with.
	 */
	public void addCard(Deck deck) {
		currentHand.add(deck.takeACard());
	}

	/*
	 * Removes all cards from the hand with the help of a while-loop which is
	 * true as long as a card is being held by the hand.
	 */
	public void clearHand(Deck deck) {
		while (!currentHand.isEmpty()) {
			deck.returnCardToDeck(currentHand.remove(0));
		}
	}

	/*
	 * Method which calculate the totalvalue of the hand. This is done with a
	 * for-loop which iterate though the array carrieng it's cards.This method
	 * returns an int of the value.
	 */
	public int totalValueHand() {
		totalValue = 0;
		for (int i = 0; i < currentHand.size(); i++) {
			totalValue += currentHand.get(i).getValue();
		}

		return totalValue;
	}

	/*
	 * returns a card of a specifike card in it's hand.
	 */
	public Card getCard(int index) {
		return currentHand.get(index);
	}

	public String getName() {
		return name;
	}

	/*
	 * returns the amount of wins this hand has won.
	 */
	public int getWins() {
		return amoutOfWins;
	}

	/*
	 * method used to add a win to it's hand.
	 */
	public void aWin() {
		amoutOfWins++;
	}

	/*
	 * returns an int with the amount of cards in it's hand.
	 */
	public int getHandSize() {
		return currentHand.size();
	}

}
