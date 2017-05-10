package no.uib.info233.v2017.rei008_jsi014.oblig4;


import java.util.ArrayList;

/**
 * GameMaster sets up and keeps track of a game between two players
 *
 * @author rei008
 * @author jsi014
 * @version 0.1
 */
public final class GameMaster {

	// Sets the different colors used in String
	private static final String RESET_COLOR = "\u001B[0m";
	private static final String CYAN = "\u001B[36m";
	private static final String RED = "\u001B[31m";
	private static final String BLUE = "\u001B[34m";
	private static final String GREEN = "\u001B[32m";

	private static final GameMaster GAMEMASTER = new GameMaster();

	// The Players
	private static Player playerBlue;
	private static Player playerRed;

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
	private GameMaster() {
		gameOver = false;
		p1_energyUse = -1;
		p2_energyUse = -1;
		gamesPlayed = 0;
		GOAL.add(0);
		GOAL.add(6);
	}

	/**
	 * @return GameMaster
	 */
	public static GameMaster getGameMaster() {
		return GAMEMASTER;
	}
	
	/**
	 * Assigns the players that are going to play against each other
	 * @param playerBlue playerBlue
	 * @param playerRed playerRed
	 */
	public static void setPlayers(Player playerBlue, Player playerRed) {
		GameMaster.playerBlue = playerBlue;
		GameMaster.playerRed = playerRed;
		playerBlueName = getPlayerName(playerBlue);
		playerRedName = getPlayerName(playerRed);
		playerBlue.registerGameMaster(GAMEMASTER);
		playerRed.registerGameMaster(GAMEMASTER);
	}
	
	/**
	 * Tells the players to make their first move
	 */
	public static void startGame() throws Exception {
		setGameOver(false);
		System.out.println(playerBlueName + " vs " + playerRedName + "\n");
		playerBlue.makeNextMove(playerBlue.getCurrentPosition(), playerBlue.getCurrentEnergy(), playerRed.getCurrentEnergy());
		playerRed.makeNextMove(playerRed.getCurrentPosition(), playerRed.getCurrentEnergy(), playerBlue.getCurrentEnergy());
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
			
			if(player.equals(playerBlue)) {
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
				this.playerBlue.updatePosition(1);
				this.playerRed.updatePosition(-1);
				
				System.out.println(playerBlueName + " won!" + "\n");
												
			}else if(getP1_energyUse() == getP2_energyUse()) {
				System.out.println("DRAW!!!!" + "\n");
			}
			else {
				this.playerBlue.updatePosition(-1);
				this.playerRed.updatePosition(1);
				
				System.out.println(playerRedName + " won!" + "\n");
			}
			
			System.out.println(playerBlueName + " at position: " + playerBlue.getCurrentPosition());
			System.out.println(playerRedName + " at position: " + playerRed.getCurrentPosition() + "\n");
			
			this.p1_energyUse = -1;
			this.p2_energyUse = -1;

			if(isGameOver()) {
				gameOver = true;
			}

			try {
				playerBlue.makeNextMove(playerBlue.getCurrentPosition(), playerBlue.getCurrentEnergy(), playerRed.getCurrentEnergy());
				playerRed.makeNextMove(playerRed.getCurrentPosition(), playerRed.getCurrentEnergy(), playerBlue.getCurrentEnergy());
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

		float pointsPlayerBlue = getPointsFromPosition(playerBlue.getCurrentPosition());
		float pointsPlayerRed = getPointsFromPosition(playerRed.getCurrentPosition());

		System.out.println();
		System.out.println("--------- "+CYAN+"Game Over!"+RESET_COLOR+" --------- \n");
		System.out.println("The game ended at X \n" + showPosition(playerBlue.getCurrentPosition()) + RESET_COLOR);
		System.out.println();
		playerBlue.gameOver(pointsPlayerBlue);
		playerRed.gameOver(pointsPlayerRed);

		System.out.println("There have been played " + gamesPlayed + " games!");

		try {
			connector.updateRanking(playerBlue, pointsPlayerBlue);
			connector.updateRanking(playerRed, pointsPlayerRed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return (GOAL.contains(playerBlue.getCurrentPosition()) || (playerBlue.getCurrentEnergy() == 0 && playerRed.getCurrentEnergy() == 0));
	}


	private static void setGameOver(boolean gameOver) {
		GAMEMASTER.gameOver = gameOver;
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
	public static float getPointsFromPosition(int currentPosition) {
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
	 * Visualizes the gameboard at the of the game
	 *
	 * @param position position the game ended
	 * @return String visualisation
	 */
	private String showPosition(int position) {
		String x;
		if(position != 3) {
			x = GREEN+"X"+RED;
		}else {
			x = RESET_COLOR+"X"+RED;
		}

		String[] gameBoard = {BLUE+"0","O","O","O","O","O","0"};
		gameBoard[position] = x;
		return String.join("|",gameBoard);
	}

	/**
	 * Colorizes the player names for the console
	 * @param player the player
	 * @return String with colorized names
	 */
	public static String getPlayerName(Player player) {

		if(player.getName().equals(playerBlue.getName())) {
			return BLUE + playerBlue.getName() + RESET_COLOR;
		}
		else {
			return RED + playerRed.getName() + RESET_COLOR;
		}
	}

}

