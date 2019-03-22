import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel, dealerCardPanel, playerPanel, playerCardPanel, buttonsPanel, dealerValuesPanel,
			playerValuesPanel;
	private JButton dealButton, standButton, hitButton, playAgainButton;
	private JLabel totalValuePlayer, gamesWonPlayer, totalValueDealer, gamesWonDealer, blackjackStatus;
	private Font textForCardValue = new Font("Serif", Font.BOLD, 20);
	private Font iconFont = new Font("Serif", Font.BOLD + Font.ITALIC, 50);

	// gbc used to determine the position of all components on the frame.
	private GridBagConstraints gbc = new GridBagConstraints();

	private Deck bjDeck;
	private Hand player, dealer;
	private Blackjack bj;

	private int screenWidth = 900;
	private int screenHight = 700;

	/*
	 * Definition and Implementation of all objects inside the constructor. The
	 * end the method startGUI is activated.
	 */
	public GUI(Deck deck, Hand player, Hand dealer) {
		Color backgroundClr = new Color(0, 100, 0);
		// Implement objects needed for a game of bj.
		this.bjDeck = deck;
		this.player = player;
		this.dealer = dealer;
		this.bj = new Blackjack(bjDeck, player, dealer);

		gbc.insets = new Insets(3, 3, 3, 3);

		/*
		 * Settings for each component on the game.
		 */

		// Settings for the GUI frame.
		setSize(new Dimension(screenWidth, screenHight));
		setLocationRelativeTo(null);
		setTitle("Blackjack");

		// Construction of the panel including cards for dealer.
		dealerCardPanel = new JPanel();
		dealerCardPanel.setLayout(new GridBagLayout());
		dealerCardPanel.setBackground(backgroundClr);

		// Settings for the label which represent the total value of cards given
		// to dealer.

		totalValueDealer = new JLabel("Total value: " + Integer.toString(dealer.totalValueHand()));
		totalValueDealer.setOpaque(true);
		totalValueDealer.setBackground(Color.gray);
		totalValueDealer.setFont(textForCardValue);

		// Settings for the label which present the amount of wins done by the
		// player.
		gamesWonDealer = new JLabel("Games won: " + Integer.toString(dealer.getWins()));
		gamesWonDealer.setOpaque(true);
		gamesWonDealer.setBackground(Color.gray);
		gamesWonDealer.setFont(textForCardValue);

		// Setting up panel which holds totalvalue and GamesWon labels for
		// dealer.

		dealerValuesPanel = new JPanel();
		dealerValuesPanel.setLayout(new GridBagLayout());
		dealerValuesPanel.setBackground(backgroundClr);

		gbc.gridx = 0;
		gbc.gridy = 0;
		dealerValuesPanel.add(gamesWonDealer, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		dealerValuesPanel.add(totalValueDealer, gbc);

		// Construction of the top panel used by the dealer.
		JPanel dealerPanel = new JPanel();
		dealerPanel.setLayout(new GridBagLayout());
		dealerPanel.setBackground(backgroundClr);

		// Construction of the panel including cards for player.
		playerCardPanel = new JPanel();
		playerCardPanel.setLayout(new GridBagLayout());
		playerCardPanel.setBackground(backgroundClr);

		// Settings for the label which represent the total value of cards given
		// to player.
		totalValuePlayer = new JLabel("Total value: " + Integer.toString(player.totalValueHand()));
		totalValuePlayer.setOpaque(true);
		totalValuePlayer.setBackground(Color.gray);
		totalValuePlayer.setFont(textForCardValue);

		// Settings for the label which present the amount of wins done by the
		// player.
		gamesWonPlayer = new JLabel("Games won: " + Integer.toString(player.getWins()));
		gamesWonPlayer.setOpaque(true);
		gamesWonPlayer.setBackground(Color.gray);
		gamesWonPlayer.setFont(textForCardValue);

		// Setting up panel which holds totalvalue and GamesWon labels for
		// player.
		playerValuesPanel = new JPanel();
		playerValuesPanel.setLayout(new GridBagLayout());
		playerValuesPanel.setBackground(backgroundClr);

		gbc.gridx = 0;
		gbc.gridy = 0;
		playerValuesPanel.add(gamesWonPlayer, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		playerValuesPanel.add(totalValuePlayer, gbc);

		// Construction of the panel which contains all buttons inside panel for
		// player
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(backgroundClr);

		// Construction of the panel used by the player.
		playerPanel = new JPanel();
		playerPanel.setLayout(new GridBagLayout());
		playerPanel.setBackground(backgroundClr);

		// Implementing blackJackIcon which is an icon used in middle of the
		// frame.
		blackjackStatus = new JLabel("BlackJack by IcEiE");
		blackjackStatus.setFont(iconFont);

		// Construction of the mainpanel.
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBackground(backgroundClr);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		playAgainButton = new JButton("Play Again");
		standButton = new JButton("Stand");
		hitButton = new JButton("Hit");
		dealButton = new JButton("Deal");

		// Adding the component mainPanel to the frame.
		add(mainPanel);

		/*
		 * ActionsListerners for each button to fulfill the request from the
		 * player. Each button also activites the method updateFrame() in order
		 * to update each part of the frame with the new values and cards if
		 * changed.
		 */
		hitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bj.hit(player);
				updatePlayerPanel();
			}
		});

		standButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonsPanel.removeAll();
				bj.stand();
				updateDealerPanel(true);
				updatePlayerPanel();
			}
		});

		dealButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// starting a new game of blackjack.
				bj.startGame();

				// Setting the frame made for gamemode.
				gameOnFrame();

				// Updating the two panels.
				if (bj.gameIsOver()) {
					updateDealerPanel(true);
				} else {
					updateDealerPanel(false);
				}
				updatePlayerPanel();
			}
		});

		playAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerCardPanel.removeAll();
				player.clearHand(bjDeck);
				dealerCardPanel.removeAll();
				dealer.clearHand(bjDeck);
				blackjackStatus.setText("BlackJack by IcEiE");
				// starting a new game of blackjack.
				bj.startGame();

				// Setting the frame made for gamemode.
				gameOnFrame();

				// Updating the two panels.
				updateDealerPanel(false);
				updatePlayerPanel();
			}
		});

		startGUI();

	}

	/*
	 * Represent the first frame when the game is running.
	 */
	private void startGUI() {

		gbc.gridx = 0;
		gbc.gridy = 2;
		mainPanel.add(blackjackStatus, gbc);

		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonsPanel.add(dealButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		mainPanel.add(buttonsPanel, gbc);

		add(mainPanel);
		setVisible(true);

	}

	/*
	 * creates the frame made when a game is being played with the help of
	 * GridbagLayout.
	 */
	private void gameOnFrame() {
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(dealerValuesPanel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(dealerCardPanel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		mainPanel.add(playerCardPanel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		mainPanel.add(playerValuesPanel, gbc);

		// Updating the buttonPanel for this situation.
		buttonsPanel.removeAll();
		buttonsPanel.add(hitButton);
		buttonsPanel.add(standButton);
	}

	/*
	 * Prints out the frame when the game is over
	 */
	private void gameOver() {
		buttonsPanel.removeAll();
		buttonsPanel.add(playAgainButton);
	}

	/*
	 * Method used to update the upper part of the screen which is the part
	 * where the dealer is set. The method first removes all cards and then
	 * places all the cards the dealer is currently holding. The dealer should
	 * not show second card if he only has two cards and it's players turn.
	 * After this the card shall be shown. This method also updates the
	 * totalvalue which also is dependet on if the second card can be shown or
	 * not hence two different lines showing the total value.
	 */
	private void updateDealerPanel(boolean showSecondCard) {
		dealerCardPanel.removeAll();

		gbc.gridy = 0;
		for (int i = 0; i < dealer.getHandSize(); i++) {
			gbc.gridx = i;

			if (!showSecondCard && i == 1) {
				dealerCardPanel.add(dealer.getCard(i).getCardBackside(), gbc);
			} else {
				dealerCardPanel.add(dealer.getCard(i).getCardImage(), gbc);
			}
		}

		if (showSecondCard) {
			totalValueDealer.setText("Total value: " + Integer.toString(dealer.totalValueHand()));
		} else {
			totalValueDealer.setText("Total value: " + Integer.toString(dealer.getCard(0).getValue()));
		}
		gamesWonDealer.setText("Games won: " + Integer.toString(dealer.getWins()));

		setVisible(true);
	}

	/*
	 * Updates the panel above the buttons which belongs to the player. This
	 * method also checks if the player has been busted and if true it sets
	 * dealer to winner. If the game is over the text blackjackStatus is
	 * updated, dealers panel gets updated and shows all cards and the method
	 * gameOver is run to update the fram to correct state of the game.
	 */
	private void updatePlayerPanel() {
		gbc.gridy = 0;

		for (int i = 0; i < player.getHandSize(); i++) {
			gbc.gridx = i;

			playerCardPanel.add(player.getCard(i).getCardImage(), gbc);
		}

		if (bj.checkIfBusted(player)) {
			bj.setWinner(dealer);
		}

		if (bj.gameIsOver()) {
			blackjackStatus.setText(bj.getWinner());
			updateDealerPanel(true);
			gameOver();
		}
		totalValuePlayer.setText("Total value: " + Integer.toString(player.totalValueHand()));
		gamesWonPlayer.setText("Games won: " + Integer.toString(player.getWins()));

		setVisible(true);
	}

}
