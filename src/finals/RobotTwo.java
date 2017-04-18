package finals;

/**
 * Subclass RobotTwo, extends Player
 * A different kind of strategy based robot
 * @author tny034
 *
 */
public class RobotTwo extends Player{

	public RobotTwo(String name, int currentPosition, int currentEnergy) {
		super(name, currentPosition, currentEnergy);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
		
		if(currentPosition <= 0) {
			gameOver(currentPosition);
		}
		
		int energyUsage = 0;
		
		if(yourEnergy > 0) {
			if(currentPosition <= 5) {
				if(opponentEnergy <= yourEnergy) {
					energyUsage = 25;
				} else {
					energyUsage = 10;
				}	
			}
		}
		
		if(energyUsage >= yourEnergy) {
			energyUsage = yourEnergy;
		}	
		
		adjustEnergy(-energyUsage);
		getGameMaster().listenToPlayerMover(this, energyUsage);
	}
}
