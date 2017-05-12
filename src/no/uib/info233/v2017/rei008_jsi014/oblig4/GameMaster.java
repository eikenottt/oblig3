package no.uib.info233.v2017.rei008_jsi014.oblig4;


import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;

/**
 * GameMaster sets up and keeps track of a game between two players
 *
 * @author rei008
 * @author jsi014
 * @version 0.1
 */
public class GameMaster {

	// The Players
	private Player player1;
	private Player player2;

	// ArrayList containing the two positions where the game ends
	private final ArrayList<Integer> GOAL = new ArrayList<>(2);

	// Players energy use
	private int p1_energyUse;
	private int p2_energyUse;

	// Color representation of the player name. Set by the getPlayerName method
	private static String playerBlueName;
	private static String playerRedName;

	// Determines the state of the game
	private boolean gameOver;
	private int gamesPlayed;

	// Is made to see if both players did run the listenToPlayerMove method
	private int playerHasRun = 0;

	/**
	 * Constructor for GameMaster
	 * initializes the energyUse for both players
	 * and the positions where the games ends
	 */
	public GameMaster() {
		generateGamemasterID();
		gameOver = false;
		p1_energyUse = -1;
		p2_energyUse = -1;
		gamesPlayed = 0;
		GOAL.add(-3);
		GOAL.add(3);
	}

	/**
	 * Assigns the players that are going to play against each other
	 * @param playerBlue player1
	 * @param playerRed player2
	 */
	public void setPlayers(Player playerBlue, Player playerRed) {
		player1 = playerBlue;
		player2 = playerRed;
		playerBlueName = getPlayerName(playerBlue);
		playerRedName = getPlayerName(playerRed);
		playerBlue.registerGameMaster(this);
		playerRed.registerGameMaster(this);
	}
	
	/**
	 * Tells the players to make their first move
	 */
	public void startGame(){
		setGameOver(false);
		System.out.println(playerBlueName + " vs " + playerRedName + "\n");
		player1.makeNextMove(player1.getCurrentPosition(), player1.getCurrentEnergy(), player2.getCurrentEnergy());
		player2.makeNextMove(player2.getCurrentPosition(), player2.getCurrentEnergy(), player1.getCurrentEnergy());
	}
	
	/**
	 * If game is not over, sets the amount of energy to use
	 * for each player. When both are set, then call evaluate method.
	 * @param player player
	 * @param energyUse energyUse
	 */
	public void listenToPlayerMove(Player player, int energyUse) {

		if(!gameOver) {
			
			System.out.println(getPlayerName(player) + " is using " + energyUse + " energy to swing its sword!.");
			
			if(player.equals(player1)) {
				this.p1_energyUse = energyUse;
			} else {
				this.p2_energyUse = energyUse;
			}
			playerHasRun++;

		} else {
			if(playerHasRun > 0) {
				evaluateTurn();
				playerHasRun = 0;
			}
		}

		if(this.p1_energyUse > -1 && this.p2_energyUse > -1) {
			evaluateTurn();
		}
	}
	
	/**
	 * If the game is not over, use the information from
	 * listenToPlayerMove() to figure out who won the round.
	 * When game is over, it runs the updateRanking method
	 */
	private void evaluateTurn() {

		if(!gameOver) {

			if(getP1_energyUse() > getP2_energyUse()) {
				this.player1.updatePosition(1);
				this.player2.updatePosition(-1);
				
				System.out.println(playerBlueName + " won!" + "\n");
												
			}else if(getP1_energyUse() == getP2_energyUse()) {
				System.out.println("DRAW!!!!" + "\n");
			}
			else {
				this.player1.updatePosition(-1);
				this.player2.updatePosition(1);
				
				System.out.println(playerRedName + " won!" + "\n");
			}
			
			System.out.println(playerBlueName + " at position: " + player1.getCurrentPosition());
			System.out.println(playerRedName + " at position: " + player2.getCurrentPosition() + "\n");
			
			this.p1_energyUse = -1;
			this.p2_energyUse = -1;

			if(isGameOver()) {
				gameOver = true;
			}

			try {
				player1.makeNextMove(player1.getCurrentPosition(), player1.getCurrentEnergy(), player2.getCurrentEnergy());
				player2.makeNextMove(player2.getCurrentPosition(), player2.getCurrentEnergy(), player1.getCurrentEnergy());
			} catch (Exception e) {
				e.printStackTrace();
			}


		}
		else {
			updateRanking();
		}
	}

	/**
	 * Runs when the game is over and updates the database
	 */
	private void updateRanking() {
		gamesPlayed++;
		// connection to the SQL database
		Connector connector = new Connector();

		float pointsPlayerBlue = getPointsFromPosition(player1.getCurrentPosition());
		float pointsPlayerRed = getPointsFromPosition(player2.getCurrentPosition());

		player2.gameOver(pointsPlayerRed);

		System.out.println("There have been played " + gamesPlayed + " games!");

		try {
			connector.updateRanking(player1, pointsPlayerBlue);
			connector.updateRanking(player2, pointsPlayerRed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return (GOAL.contains(player1.getCurrentPosition()) || (player1.getCurrentEnergy() == 0 && player2.getCurrentEnergy() == 0));
	}


	private void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * @return the p1_energyUse
	 */
	private int getP1_energyUse() {
		return p1_energyUse;
	}

	/**
	 * @return the p2_energyUse
	 */
	private int getP2_energyUse() {
		return p2_energyUse;
	}

	/**
	 * Translates the position of the players into points
	 * @param currentPosition position of the player
	 * @return a float value with the player score
	 */
	public float getPointsFromPosition(int currentPosition) {
		float points;
		switch (currentPosition) {
			case 0:
				points = -1f;
				break;
			case 1:
				points = 0f;
				break;
			case 2:
				points = 0.25f;
				break;
			case 3:
				points = 0.5f;
				break;
			case 4:
				points = 0.75f;
				break;
			case 5:
				points = 1f;
				break;
			default:
				points = 2f;
				break;
		}

		return points;
	}

	/**
	 * Colorizes the player names for the console
	 * @param player the player
	 * @return String with colorized names
	 */
	public String getPlayerName(Player player) {

		if(player.getName().equals(player1.getName())) {
			return player1.getName();
		}
		else {
			return player2.getName();
		}
	}
	public void loadGame(){
		//TODO MAke this method load a saved game
	}

	public void saveGame(){
		//TODO make this method save a game ()
	}

	public void hostGame(){
		//TODO When a HumanPlayer creates a new multiplayer game, he is "hosting" the game.
	}

	public void listGames(){
		//TODO displays the available games that players can join in the multiplayer section.
	}

	/**
	 * Used to generate the gamemaster id from the object referance
	 * @return - the unique Gamemaster id
	 */
	private String generateGamemasterID(){
		String gameID = this.toString();

		return gameID.substring(gameID.indexOf("@"), gameID.length());
	}

}

