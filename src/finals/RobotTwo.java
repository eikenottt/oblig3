package finals;

/**
 * Subclass RobotTwo, extends Player2
 * A different kind of strategy based robot
 * @author tny034
 *
 */
public class RobotTwo extends Player2 {

	public RobotTwo(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) throws Exception {

		super.makeNextMove(currentPosition, yourEnergy, opponentEnergy);

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
		getGameMaster().listenToPlayerMove(this, energyUsage);
	}
}
