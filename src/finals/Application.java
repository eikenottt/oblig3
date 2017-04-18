package finals;

/**
 * Main calls are made here
 * @author tny034
 *
 */
public class Application {
	
	public static void main(String[] args) {
		GameMaster gameMaster = GameMaster.getGameMaster();
		
		RobotOne robot1 = new RobotOne("Burt", 3, 10);
		RobotTwo robot2 = new RobotTwo("Larry", 3, 100);
		
		robot1.registerGameMaster(gameMaster);
		robot2.registerGameMaster(gameMaster);
		
		gameMaster.setPlayers(robot1, robot2);
		gameMaster.startGame();
	}
}
