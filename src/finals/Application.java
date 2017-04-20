package finals;

/**
 * Main calls are made here
 * @author tny034
 *
 */
public class Application {
	
	public static void main(String[] args) throws Exception {
		GameMaster gameMaster = GameMaster.getGameMaster();
		
		RobotOne robot1 = new RobotOne("Cato");
		RobotTwo robot2 = new RobotTwo("Nora");
		
		robot1.registerGameMaster(gameMaster);
		robot2.registerGameMaster(gameMaster);
		
		gameMaster.setPlayers(robot1, robot2);
		gameMaster.startGame();
	}

}
