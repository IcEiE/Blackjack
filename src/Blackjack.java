/*
 * Class used to hold the logic and game of the real game Blackjack.
 */
public class Blackjack {
	private Hand playerHand;
	private Hand dealerHand;
	private Deck deck;
	private String winnerString;
	private boolean gameIsOver = false;

	/*
	 * Construtor used to implement incoming componentes to the class
	 */
	public Blackjack(Deck deck, Hand player, Hand dealer) {
		this.playerHand = player;
		this.dealerHand = dealer;
		this.deck = deck;
	}

	/*
	 * Starts a game of blackjack by giving each player two cards and then check
	 * if either of them has gained a blackjack which would set them to winner.
	 */
	public void startGame() {
		gameIsOver = false;
		hit(playerHand);
		hit(dealerHand);
		hit(playerHand);
		hit(dealerHand);
		winnerString = "No one have won!";

		if (checkIfBlackJack(playerHand) && checkIfBlackJack(dealerHand)) {
			gameIsOver = true;
		} else if (checkIfBlackJack(playerHand)) {
			setWinner(playerHand);
		} else if (checkIfBlackJack(dealerHand)) {
			setWinner(dealerHand);
		}
	}

	/*
	 * Used when game ends to clear both hands.
	 */
	public void clearHands() {
		playerHand.clearHand(deck);
		dealerHand.clearHand(deck);
	}

	/*
	 * hit is used to give a player a card.
	 */
	public void hit(Hand askingHand) {
		askingHand.addCard(deck);
	}

	/*
	 * When player stand it's time for the dealer to play and after he is
	 * finsihed the program need to check who won the game.
	 */
	public void stand() {
		dealerAI();
		checkWhoWon();
	}

	/*
	 * This method is used when both players have played their turn. First check
	 * if the player has won by checking if the player has higher totalvalue
	 * then the dealer(player can't have more then 21) or the dealer has been
	 * busted. Then if the dealer has won by having higher totalvalue then
	 * player.
	 */
	private void checkWhoWon() {
		if (playerHand.totalValueHand() > dealerHand.totalValueHand() || checkIfBusted(dealerHand)) {
			setWinner(playerHand);
		} else if (dealerHand.totalValueHand() > playerHand.totalValueHand()) {
			setWinner(dealerHand);
		}
		gameIsOver = true;
	}

	/*
	 * check if a hand is busted which means that the played has exceeded the
	 * totalvalue of 21 which is the max value.
	 */
	public boolean checkIfBusted(Hand askingHand) {
		if (askingHand.totalValueHand() > 21) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Check if a hand has recived a blackjack by having only two cards and have
	 * the totalvalue of 21.
	 */
	public boolean checkIfBlackJack(Hand askingHand) {
		if (askingHand.getHandSize() == 2 && askingHand.totalValueHand() == 21) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * dealerAI represent the rules of the dealer. Take as many cards until he
	 * reach a value higher then 16.
	 */
	private void dealerAI() {

		while (dealerHand.totalValueHand() < 16) {
			hit(dealerHand);

		}
	}

	/*
	 * Gives the winner +1 on amounts of wins. The boolean gameIsOver is set to
	 * true and the string is changed with the winners name and
	 * " won this game!" string.
	 */
	public void setWinner(Hand winner) {
		winner.aWin();
		gameIsOver = true;
		winnerString = winner.getName() + " won this game!";
	}

	/*
	 * return boolean value true of game is over and false if not.
	 */
	public boolean gameIsOver() {
		return gameIsOver;
	}

	/*
	 * returns the string about who is the winner.
	 */
	public String getWinner() {
		return winnerString;
	}

}
