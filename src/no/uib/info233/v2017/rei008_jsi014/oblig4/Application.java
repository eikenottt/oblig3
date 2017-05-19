package no.uib.info233.v2017.rei008_jsi014.oblig4;
/**
 * Runs two separate games with four different players
 * @author rei008
 * @author jsi014
 */
public class Application {
	
	public static void main(String[] args) throws Exception {

		GameMaster gameMaster = new GameMaster();
		Connector conn = new Connector();

		Player robot1 = new AggressivePlayer("Cato");
		Player robot2 = new PassivePlayer("Svein");
		Player robot3 = new AggressivePlayer("Nora");
		Player robot4 = new PassivePlayer("Karl");
		String gameid = "ostekuler";

//		conn.updateSavedGame(gameid, robot1, robot2, 2);

		gameMaster.setPlayers(robot1, robot2);
//		gameMaster.startGame();

		gameMaster.setPlayers(robot3, robot4);
//		gameMaster.startGame();

		//test if gamemaster loads a save


		GameMaster savedGameMaster = new GameMaster();
		savedGameMaster.loadGame("ostekuler");
		System.out.println(savedGameMaster.getGameID()+ "Game Loaded " );
		System.out.println("Player 1: " + savedGameMaster.getPlayerBlueName() + " Player 2: " + savedGameMaster.getPlayerRedName());
	}



}
