/*
 *
 */
public class BlackjackDemo {
	public static void main(String[] args){
		
		
		
		Deck deck = new Deck(true);
		Hand player = new Hand("Player");
		Hand dealer = new Hand("Dealer");
		new GUI(deck, player, dealer);
	}
}
