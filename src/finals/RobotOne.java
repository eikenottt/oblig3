package finals;

import java.util.Random;

/**
 * Subclass RobotOne, extends player
 * A different kind of strategy based robot
 * @author tny034
 *
 */
public class RobotOne extends Player2 {

	/**
	 * Constructor for subclass RobotOne
	 * @param name name
	 */
	public RobotOne(String name) {
		super(name);
	}
	
	@Override
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) throws Exception {

		super.makeNextMove(currentPosition, yourEnergy, opponentEnergy);

		int energyUsage = 0;

		if(yourEnergy > 0) {
			if (currentPosition <= 3) {
				energyUsage = chaos()/3;
			} else if (currentPosition > 3) {
				energyUsage = chaos()/2;
			}
		}
		
		if(energyUsage >= yourEnergy) {
			energyUsage = yourEnergy;
		}
			
		adjustEnergy(-energyUsage);
		getGameMaster().listenToPlayerMove(this, energyUsage);
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
