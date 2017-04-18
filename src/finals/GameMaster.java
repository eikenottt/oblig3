package finals;

import java.util.ArrayList;

/**
 * Class GameMaster facilitates game.
 * @author tny034
 *
 */
public class GameMaster {
	
	private Player player1;
	private Player player2;
	private final ArrayList<Integer> GOAL;
	
	private int p1_energyUse;
	private int p2_energyUse;

	private static GameMaster instance = new GameMaster();
	
	private boolean gameOver;

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
	 * @param player1 player1
	 * @param player2 player2
	 */
	public void setPlayers(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	/**
	 * Sends a message to each other players to come up with
	 * their next move. This is done by running player for
	 * for each player.
	 */
	public void startGame() {
		System.out.println(player1.getName() + " vs " + player2.getName() + "\n");
		player1.makeNextMove(player1.getCurrentPosition(), player1.getCurrentEnergy(), player2.getCurrentEnergy());
		player2.makeNextMove(player2.getCurrentPosition(), player2.getCurrentEnergy(), player1.getCurrentEnergy());
	}
	
	/**
	 * If game is not over, sets the amount of energy to use
	 * for each player. When both are set, then call evaluate method.
	 * @param player player
	 * @param energyUse energyUse
	 */
	public void listenToPlayerMover(Player player, int energyUse) {	
		//TODO: each player uses this method to communicate how
		//much energy he wants to use in the current turn. Treat
		//all invalid inputs (values other than the energy currently
		//available to the player) as equal to 0. If both players
		// made a call to this method during the current round, run
		// evaluateTurn()
				
		if(!gameOver) {
			
			System.out.println(player.getName() + " is using " + energyUse + " power.");
			
			if(player.equals(player1)) {
				this.p1_energyUse = energyUse;
			} else {
				this.p2_energyUse = energyUse;
			}
		} else {
			evaluateTurn();
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
		
		if(isGameOver()) {
			gameOver = true;
		}
		
		if(!gameOver) {
			if(getP1_energyUse() > getP2_energyUse()) {
				this.player1.adjustPosition(1);
				this.player2.adjustPosition(-1);
				
				System.out.println(player1.getName() + " won!" + "\n");
												
			} else {
				this.player1.adjustPosition(-1);
				this.player2.adjustPosition(1);
				
				System.out.println(player2.getName() + " won!" + "\n");
			}
			
			System.out.println(player1.getName() + " at position: " + player1.getCurrentPosition());
			System.out.println(player2.getName() + " at position: " + player2.getCurrentPosition() + "\n");
			
			this.p1_energyUse = -1;
			this.p2_energyUse = -1;
			
			player1.makeNextMove(player1.getCurrentPosition(), player1.getCurrentEnergy(), player2.getCurrentEnergy());
			player2.makeNextMove(player2.getCurrentPosition(), player2.getCurrentEnergy(), player1.getCurrentEnergy());
						
		} else {		
			updateRanking();
		}	
	}
	
	/**
	 * SQL stuff
	 */
	public void updateRanking() {
		
		//TODO: update the player rankings in the ranking table. This
		//table is to be stored in a remove (mySQL) database. Use the
		//table named "ranking", with the columns "player" (VARCHAR128)
		// and "score" (FLOAT). You will be given the credentials required
		//connect to your group's database from your seminar leader.
		
		System.out.println("");
		System.out.println("Game is over!" + "\n");
		System.out.println("Score:");
		System.out.println(player1.getName() + " earned: " + getPointsFromPosition(player1) + " points.");
		System.out.println(player2.getName() + " earned: " + getPointsFromPosition(player2) + " points.");
	}
	
	/**
	 * @return the gameOver
	 */
	private boolean isGameOver() {
		System.out.println(GOAL.contains(player1.getCurrentPosition()));
		return false;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	/**
	 * @return the player1
	 */
	public Player getPlayer1() {
		return player1;
	}
	
	/**
	 * @return the player2
	 */
	public Player getPlayer2() {
		return player2;
	}

	/**
	 * @return the p1_energyUse
	 */
	public int getP1_energyUse() {
		return p1_energyUse;
	}

	/**
	 * @param p1_energyUse the p1_energyUse to set
	 */
	public void setP1_energyUse(int p1_energyUse) {
		this.p1_energyUse = p1_energyUse;
	}

	/**
	 * @return the p2_energyUse
	 */
	public int getP2_energyUse() {
		return p2_energyUse;
	}

	/**
	 * @param p2_energyUse the p2_energyUse to set
	 */
	public void setP2_energyUse(int p2_energyUse) {
		this.p2_energyUse = p2_energyUse;
	}

	private float getPointsFromPosition(Player player) {
		float points;
		switch (player.getCurrentPosition()) {
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
}

