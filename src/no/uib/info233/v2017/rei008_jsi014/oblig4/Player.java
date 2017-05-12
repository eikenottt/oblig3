package no.uib.info233.v2017.rei008_jsi014.oblig4;


import java.util.Random;

/**
 * Describes the super class of Player Subclasses
 * The class introduces commonalities between all playes
 * and introduces some basic attacks.
 * @author rei008
 * @author js014
 * @version 0.1
 *
 */
public abstract class Player {
	
	private String name;
	private int currentPosition;
	private int currentEnergy;
	protected Random rand = new Random();
	
	private GameMaster gameMaster;
	
	/**
	 * Constructor for player
	 * sets currentPosition as 3 by default
	 * sets currentEnergy as 100 by default
	 * @param name name
	 */
	public Player(String name) {
		this.name = name;
		this.currentPosition = 3;
		this.currentEnergy = 100;
	}
	
	/**
	 * Sets the gameMaster
	 * @param gameMaster
	 */
	public void registerGameMaster(GameMaster gameMaster) {
		this.gameMaster = gameMaster;
	}
	
	/**
	 * Takes in parameters of currentPosistion, currentEnergy and opponentEnergy
	 * and uses the information to strategize the next move the player wants to make.
	 * When the player has decided, it informs the gameMaster of how much energy it wants to spend,
	 * @param currentPosition player position
	 * @param yourEnergy player energy
	 * @param opponentEnergy opponent energy
	 */
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
	}
	
	/**
	 * Print method, to write out the points earned by the player after a game.
	 * @param earnedPoints
	 */
	public void gameOver(float earnedPoints) {

		System.out.println(getGameMaster().getPlayerName(this) + " earned " + earnedPoints + " points!");
	}
	
	/**
	 * Updates the energy value for player
	 * @param value - adds value to the currentEnergy
	 */
	public void updateEnergy(int value) {
		this.currentEnergy += value;
		if (currentEnergy < 0) {
			currentEnergy = 0;
		}
	}
	
	/**
	 * Update the position for player
	 * @param value - adds value to the currentPosition
	 */
	public void updatePosition(int value) {
		currentPosition += value;

		if (currentPosition > 6){
			currentPosition = 6;
		}
		if(currentPosition < 0 ){
			currentPosition = 0;
		}
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
	 *	Sets the currentEnergy
	 * @param currentEnergy - new value of currentEnergy
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
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * A high energy usage basic attack all players can perform.
	 * implements some randomness
	 * @param yourEnergy - the energy the player has available
	 * @return int energy to spend
	 */
    protected int overheadSwing(int yourEnergy){
        int randNumber;
        randNumber = rand.nextInt(15);

        if(yourEnergy > 35){
            return 20 + randNumber;
        }else {
            return getCurrentEnergy();
        }
    }

	/**
	 * Low predictability attack, implements a lot of randomness
	 * @param yourEnergy - the energy the player has available
	 * @return int energy to spend
	 */
	protected int stab(int yourEnergy){

        int randNumber;
        randNumber = this.rand.nextInt(50);

        if (yourEnergy > 50){
            return randNumber;
        }else if(yourEnergy>11){
            return randNumber/10 + 1;
        }else{
            return 0;
        }
    }

	/**
	 * The most basic attack midrange energy usage
	 * Implements some randomness in return
	 * @param yourEnergy - the energy the player has available
	 * @return int energy to spend
	 */
    protected int slash(int yourEnergy){

        int randNumber = this.rand.nextInt(15);
        if (yourEnergy > 20) {
            return 5 + randNumber;
        }else if( yourEnergy >= 5){
            return 5;
        }else return 0;

    }
}