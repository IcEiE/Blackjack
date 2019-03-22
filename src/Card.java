import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * A class representing a Card type.
 */
public class Card {
	/*
	 * Two values used to represent the card.
	 */
	private Suit mSuit;
	private int mValue;
	private JLabel cardImg;
	private JLabel cardBackside;

	/*
	 * Constructor used to declare the two values of the card and a picture
	 * which represents the card and also a backside of a card. Depending on the
	 * value of the card the card gets it value. Ace(nr 14) have the value 11
	 * and all other cards with value ten and over has value 10. Rest of the
	 * cards has their cardValue as value.
	 */
	public Card(int incValue, Suit incSuit) {
		this.mSuit = incSuit;
		if (incValue > 1 && incValue < 15) {
			if (incValue == 14) {
				this.mValue = 11;
			} else if (incValue >= 10) {
				this.mValue = 10;
			} else {
				this.mValue = incValue;
			}

			this.cardImg = new JLabel(
					new ImageIcon("./deck/" + Integer.toString(incValue) + mSuit.toString() + ".png"));
			this.cardBackside = new JLabel(new ImageIcon("./deck/backside.png"));
		}
	}

	/*
	 * returns this cards value.
	 */
	public int getValue() {
		return this.mValue;
	}

	/*
	 * returns this cards suit.
	 */
	public Suit getSuit() {
		return this.mSuit;
	}


	/*
	 * returns the image for the specifike card.
	 */
	public JLabel getCardImage() {
		return cardImg;
	}

	/*
	 * returns the backside of the card.
	 */
	public JLabel getCardBackside() {
		return cardBackside;
	}
}