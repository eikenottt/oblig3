package no.uib.info233.v2017.rei008_jsi014.oblig3;

import java.util.ArrayList;

/**
 * Class GameMaster facilitates game.
 * @author tny034
 *
 */
public class GameMaster {

	// Sets the different colors used in String
	private static final String RESET_COLOR = "\u001B[0m";
	private static final String CYAN = "\u001B[36m";
	private static final String RED = "\u001B[31m";
	private static final String BLUE = "\u001B[34m";
	private static final String GREEN = "\u001B[32m";
	private static final String PURPLE = "\u001B[35m";

	public static final GameMaster GAMEMASTER = new GameMaster();

	// The Players
	private Player playerBlue;
	private Player playerYellow;

	// ArrayList containing the two positions where the game ends
	private final ArrayList<Integer> GOAL = new ArrayList<>(2);

	// Players energy use
	private int p1_energyUse;
	private int p2_energyUse;

	// Color representation of the player name. Set by the getPlayerName method
	private String playerBlueName;
	private String playerYellowName;

	// Game Master
	private static GameMaster instance = new GameMaster();

	// Determines the state of the game
	private boolean gameOver;

	// Is made to see if both players did run the listenToPlayerMove method
	private int playerHasRun = 0;

	/**
	 * Constructor for GameMaster
	 * Sets the energyUse for both players
	 * and the positions where the games ends
	 */
	private GameMaster() {
		gameOver = false;
		p1_energyUse = -1;
		p2_energyUse = -1;
		GOAL.add(0);
		GOAL.add(6);
	}

	/**
	 * Returns the GameMaster instance
	 * @return GameMaster
	 */
	public static GameMaster getGameMaster() {
		return instance;
	}
	
	/**
	 * Assigns the players that are going to play against each other
	 * @param playerBlue playerBlue
	 * @param playerYellow playerYellow
	 */
	public void setPlayers(Player playerBlue, Player playerYellow) {
		this.playerBlue = playerBlue;
		this.playerYellow = playerYellow;
		playerBlueName = getPlayerName(playerBlue);
		playerYellowName = getPlayerName(playerYellow);
	}
	
	/**
	 * Sends a message to each other players to come up with
	 * their next move. This is done by running the Player's method
	 * makeNextMove for each player.
	 */
	public void startGame() throws Exception {
		setGameOver(false);
		System.out.println(playerBlueName + " vs " + playerYellowName + "\n");
		playerBlue.makeNextMove(playerBlue.getCurrentPosition(), playerBlue.getCurrentEnergy(), playerYellow.getCurrentEnergy());
		playerYellow.makeNextMove(playerYellow.getCurrentPosition(), playerYellow.getCurrentEnergy(), playerBlue.getCurrentEnergy());
	}
	
	/**
	 * If game is not over, sets the amount of energy to use
	 * for each player. When both are set, then call evaluate method.
	 * @param player player
	 * @param energyUse energyUse
	 */
	public void listenToPlayerMove(Player player, int energyUse) {
		//TODO: each player uses this method to communicate how
		//much energy he wants to use in the current turn. Treat
		//all invalid inputs (values other than the energy currently
		//available to the player) as equal to 0. If both players
		// made a call to this method during the current round, run
		// evaluateTurn()

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
	 * use the information submitted via listenToPlayerMove
	 * to identify who won and update the players on the state of
	 * the game by either running player.makeNextMove(if the game
	 * has not yet ended). or player.gameOver(in case the game has
	 * come to an end). If the game come to an end, also run
	 * .updateRanking()
	 */
	public void evaluateTurn() {



		if(!gameOver) {

			if(isGameOver()) {
				gameOver = true;
			}

			if(getP1_energyUse() > getP2_energyUse()) {
				this.playerBlue.updatePosition(1);
				this.playerYellow.updatePosition(-1);
				
				System.out.println(playerBlueName + " won!" + "\n");
												
			}else if(getP1_energyUse() == getP2_energyUse()) {
				System.out.println("DRAW!!!!" + "\n");
			}
			else {
				this.playerBlue.updatePosition(-1);
				this.playerYellow.updatePosition(1);
				
				System.out.println(playerYellowName + " won!" + "\n");
			}
			
			System.out.println(playerBlueName + " at position: " + playerBlue.getCurrentPosition());
			System.out.println(playerYellowName + " at position: " + playerYellow.getCurrentPosition() + "\n");
			
			this.p1_energyUse = -1;
			this.p2_energyUse = -1;


			try {
				playerBlue.makeNextMove(playerBlue.getCurrentPosition(), playerBlue.getCurrentEnergy(), playerYellow.getCurrentEnergy());
				playerYellow.makeNextMove(playerYellow.getCurrentPosition(), playerYellow.getCurrentEnergy(), playerBlue.getCurrentEnergy());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else {
			updateRanking();
		}
	}

	private void updateRanking() {
		// connection to the SQL database
		Connector connector = new Connector();

		float pointsPlayerBlue = getPointsFromPosition(playerBlue.getCurrentPosition());
		float pointsPlayerRed = getPointsFromPosition(playerYellow.getCurrentPosition());

		System.out.println();
		System.out.println("--------- "+CYAN+"Game Over!"+RESET_COLOR+" --------- \n");
		System.out.println("The game ended at X \n" + showPosition(playerBlue.getCurrentPosition()) + RESET_COLOR);
		System.out.println();
		playerBlue.gameOver(pointsPlayerBlue);
		playerYellow.gameOver(pointsPlayerRed);

		try {
			connector.updateRanking(playerBlue, pointsPlayerBlue);
			connector.updateRanking(playerYellow, pointsPlayerRed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return (GOAL.contains(playerBlue.getCurrentPosition()) || (playerBlue.getCurrentEnergy() == 0 && playerYellow.getCurrentEnergy() == 0));
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	/**
	 * @return the playerBlue
	 */
	public Player getplayerBlue() {
		return playerBlue;
	}
	
	/**
	 * @return the playerYellow
	 */
	public Player getPlayer2() {
		return playerYellow;
	}

	/**
	 * @return the p1_energyUse
	 */
	public int getP1_energyUse() {
		return p1_energyUse;
	}

	/**
	 * @return the p2_energyUse
	 */
	public int getP2_energyUse() {
		return p2_energyUse;
	}

	/**
	 *
	 * @param currentPosition
	 * @return
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

	private String showPosition(int position) {
		String x;
		if(position != 3) {
			x = GREEN+"X"+RED;
		}else if (position == 6) {
			x = GREEN+"X"+RESET_COLOR;
		}
		else {
			x = RESET_COLOR+"X"+RED;
		}

		String[] gameBoard = {BLUE+"0","O","O","O","O","O","0"};
		gameBoard[position] = x;
		return String.join("|",gameBoard);
	}

	public String getPlayerName(Player player) {

		if(player.getName().equals(playerBlue.getName())) {
			return BLUE + playerBlue.getName() + RESET_COLOR;
		}
		else {
			return RED + playerYellow.getName() + RESET_COLOR;
		}
	}

}

