package finals;

import java.util.Random;

/**
 * Subclass RobotOne, extends player
 * A different kind of strategy based robot
 * @author tny034
 *
 */
public class RobotOne extends Player {

	/**
	 * Constructor for subclass RobotOne
	 * @param name name
	 * @param currentPosition currentPosition
	 * @param currentEnergy currentEnergy
	 */
	public RobotOne(String name, int currentPosition, int currentEnergy) {
		super(name, currentPosition, currentEnergy);
	}
	
	@Override
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		
		if (currentPosition <= 0) {
			gameOver(currentPosition);
		}
		
		int energyUsage = 0;
		
		if(yourEnergy > 0) {
			if (currentPosition <= 3) {
				energyUsage = 30;
			} else if (currentPosition > 3) {
				energyUsage = chaos();
			}
		}
		
		if(energyUsage >= yourEnergy) {
			energyUsage = yourEnergy;
		}
			
		adjustEnergy(-energyUsage);
		getGameMaster().listenToPlayerMover(this, energyUsage);
	}
	
	/**
	 * Generates a random number between 0 and 100
	 * @return i
	 */
	private int chaos() {
		Random generator = new Random();
		int i = generator.nextInt(100);
		
		return i;
	}
}
