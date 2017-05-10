package no.uib.info233.v2017.rei008_jsi014.oblig4;
/**
 * Runs two separate games with four different players
 * @author rei008
 * @author jsi014
 */
public class Application {
	
	public static void main(String[] args) throws Exception {



		Player robot1 = new AggressivePlayer("Cato");
		Player robot2 = new PassivePlayer("Svein");
		Player robot3 = new AggressivePlayer("Nora");
		Player robot4 = new PassivePlayer("Karl");

		GameMaster.setPlayers(robot1, robot2);
		GameMaster.getGameMaster().startGame();

		GameMaster.getGameMaster().setPlayers(robot3, robot4);
		GameMaster.getGameMaster().startGame();
	}

}
