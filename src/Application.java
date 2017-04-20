import no.uib.info233.v2017.rei008_jsi014.oblig3.AggressivePlayer;
import static no.uib.info233.v2017.rei008_jsi014.oblig3.GameMaster.*;
import no.uib.info233.v2017.rei008_jsi014.oblig3.PassivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig3.Player;

/**
 * Main calls are made here
 * @author tny034
 *
 */
public class Application {
	
	public static void main(String[] args) throws Exception {

		Player robot1 = new AggressivePlayer("Cato");
		Player robot2 = new PassivePlayer("Svein");
		Player robot3 = new AggressivePlayer("Nora");
		Player robot4 = new PassivePlayer("Karl");

		setPlayers(robot1, robot2);
		startGame();

		setPlayers(robot3, robot4);
		startGame();
	}

}
