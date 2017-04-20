import no.uib.info233.v2017.rei008_jsi014.oblig3.AggressivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig3.GameMaster;
import no.uib.info233.v2017.rei008_jsi014.oblig3.PassivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig3.Player;

/**
 * Main calls are made here
 * @author tny034
 *
 */
public class Application {
	
	public static void main(String[] args) throws Exception {
		GameMaster gameMaster = GameMaster.getGameMaster();
		GameMaster game2Master = GameMaster.getGameMaster();

		
		Player robot1 = new AggressivePlayer("Cato");
		Player robot2 = new AggressivePlayer("Svein");
		Player robot3 = new AggressivePlayer("Nora");
		Player robot4 = new AggressivePlayer("Karl");

		robot1.registerGameMaster(gameMaster);
		robot2.registerGameMaster(gameMaster);
		robot3.registerGameMaster(game2Master);
		robot4.registerGameMaster(game2Master);

		gameMaster.setPlayers(robot1, robot2);
		gameMaster.startGame();

		game2Master.setPlayers(robot3, robot4);
		game2Master.startGame();
	}

}
