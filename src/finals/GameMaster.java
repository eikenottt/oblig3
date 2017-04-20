package finals;

import no.uib.info233.v2017.rei008_jsi014.oblig3.Connector;

import java.util.ArrayList;

/**
 * Class GameMaster facilitates game.
 * @author tny034
 *
 */
public class GameMaster {
	
	private Player2 playerBlue;
	private Player2 playerRed;
	private final ArrayList<Integer> GOAL;

	private int p1_energyUse;
	private int p2_energyUse;
	private String playerBlueName;
	private String playerRedName;

	private static GameMaster instance = new GameMaster();

	private boolean gameOver;

	private Connector connector = new Connector();

	int playerHasRun = 0;

	/**
	 * Constructor for GameMaster
	 * Sets a couple of values
	 */
	private GameMaster() {
		gameOver = false;
		p1_energyUse = -1;
		p2_energyUse = -1;
		GOAL = new ArrayList<>(2);
		GOAL.add(0);
		GOAL.add(6);
	}
	
	public static GameMaster getGameMaster() {
		return instance;
	}
	
	/**
	 * Assigns the players that are going to play against each other
	 * @param playerBlue playerBlue
	 * @param playerRed playerRed
	 */
	public void setPlayers(Player2 playerBlue, Player2 playerRed) {
		this.playerBlue = playerBlue;
		this.playerRed = playerRed;
		playerBlueName = getPlayerName(playerBlue);
		playerRedName = getPlayerName(playerRed);
	}
	
	/**
	 * Sends a message to each other players to come up with
	 * their next move. This is done by running player for
	 * for each player.
	 */
	public void startGame() throws Exception {
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
	public void listenToPlayerMove(Player2 player, int energyUse) {
		//TODO: each player uses this method to communicate how
		//much energy he wants to use in the current turn. Treat
		//all invalid inputs (values other than the energy currently
		//available to the player) as equal to 0. If both players
		// made a call to this method during the current round, run
		// evaluateTurn()

		if(!gameOver) {
			
			System.out.println(getPlayerName(player) + " is using " + energyUse + " power.");
			
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
				this.playerBlue.adjustPosition(1);
				this.playerRed.adjustPosition(-1);
				
				System.out.println(playerBlueName + " won!" + "\n");
												
			}else if(getP1_energyUse() == getP2_energyUse()) {
				System.out.println("DRAW!!!!" + "\n");
			}
			else {
				this.playerBlue.adjustPosition(-1);
				this.playerRed.adjustPosition(1);
				
				System.out.println(playerRedName + " won!" + "\n");
			}
			
			System.out.println(playerBlueName + " at position: " + playerBlue.getCurrentPosition());
			System.out.println(playerRedName + " at position: " + playerRed.getCurrentPosition() + "\n");
			
			this.p1_energyUse = -1;
			this.p2_energyUse = -1;

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

	private void updateRanking() {
		float pointsplayerBlue = getPointsFromPosition(playerBlue.getCurrentPosition());
		float pointsPlayer2 = getPointsFromPosition(playerRed.getCurrentPosition());

		System.out.println();
		System.out.println("--------- \u001B[36mGame Over!\u001B[0m --------- \n");
		System.out.println("The game ended at X \n" + showPosition(playerBlue.getCurrentPosition()));
		System.out.println("\u001B[0m");
		playerBlue.gameOver(pointsplayerBlue);
		playerRed.gameOver(pointsPlayer2);

		try {
			connector.updateRanking(playerBlue, pointsplayerBlue);
			connector.updateRanking(playerRed, pointsPlayer2);
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

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	/**
	 * @return the playerBlue
	 */
	public Player2 getplayerBlue() {
		return playerBlue;
	}
	
	/**
	 * @return the playerRed
	 */
	public Player2 getPlayer2() {
		return playerRed;
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
			x = "\u001B[32mX\u001B[31m";
		}else if (position == 6) {
			x = "X\u001B[0m";
		}
		else {
			x = "\u001B[0mX\u001B[31m";
		}

		String[] gameBoard = {"\u001B[34m0","O","O","O","O","O","0"};
		gameBoard[position] = x;
		return String.join("|",gameBoard);
	}

	public String getPlayerName(Player2 player) {

		if(player.getName().equals(playerBlue.getName())) {
			return "\u001B[34m" + playerBlue.getName() + "\u001B[0m";
		}
		else {
			return "\u001B[31m" + playerRed.getName() + "\u001B[0m";
		}
	}

}

