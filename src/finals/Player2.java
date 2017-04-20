package finals;

/**
 * Class Player2, manages player moves
 * @author tny034
 *
 */
public class Player2 {
	
	private String name;
	private int currentPosition;
	private int currentEnergy;
	
	private GameMaster gameMaster;
	
	/**
	 * Constructor for player
	 * @param name name
	 */
	public Player2(String name) {
		this.name = name;
		this.currentPosition = 3;
		this.currentEnergy = 100;
	}
	
	/**
	 * Sets the gameMaster for the player to
	 * communicate with.
	 * @param gameMaster
	 */
	public void registerGameMaster(GameMaster gameMaster) {
		this.gameMaster = gameMaster;
	}
	
	/**
	 * Figures out how much energy the player wants to spend
	 * based on the current state of the game. Call gameMaster.listenToPlayerMove
	 * to inform the gameMaster about the player choice.
	 * @param currentPosition player position
	 * @param yourEnergy player energy
	 * @param opponentEnergy opponent energy
	 */
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) throws Exception {
	}
	
	/**
	 * Sets the game to end
	 * @param earnedPoints
	 */
	public void gameOver(float earnedPoints) {
		//TODO: informs the player that the game has come to an
		// end and how many points he earned in the game
		System.out.println(getGameMaster().getPlayerName(this) + " earned " + earnedPoints + " points!");
		gameMaster.setGameOver(true);
	}
	
	/**
	 * Adjusts the energy value for player
	 * @param value
	 */
	public void adjustEnergy(int value) {
		this.currentEnergy += value;
	}
	
	/**
	 * Adjusts the position for player
	 * @param value
	 */
	public void adjustPosition(int value) {
		this.currentPosition += value;
	}

	/**
	 * @return the player name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the current position 
	 */
	public int getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @return the current energy
	 */
	public int getCurrentEnergy() {
		return currentEnergy;
	}

	/**
	 * @return the gameMaster
	 */
	public GameMaster getGameMaster() {
		return gameMaster;
	}

	/**
	 *
	 * @param currentEnergy
	 */
	public void setCurrentEnergy(int currentEnergy) {
		this.currentEnergy = currentEnergy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player2 other = (Player2) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}