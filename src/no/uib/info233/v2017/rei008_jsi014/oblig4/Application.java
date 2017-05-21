package no.uib.info233.v2017.rei008_jsi014.oblig4;

import no.uib.info233.v2017.rei008_jsi014.oblig4.GUI.TestGUI;
import org.junit.Test;

import javax.swing.*;
import java.util.HashMap;

/**
 * Runs two separate games with four different players
 * @author rei008
 * @author jsi014
 */
public class Application {
	
	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(() -> new TestGUI());



		/*Player robot1 = new AggressivePlayer("Cato");
		Player robot2 = new PassivePlayer("Svein");
		Player robot3 = new AggressivePlayer("Nora");
		Player robot4 = new PassivePlayer("Karl");
		String gameid = "ostekuler";*/

//		conn.updateSavedGame(gameid, robot1, robot2, 2);

//		gameMaster.setPlayers(robot1, robot2);

//		gameMaster.setPlayers(robot3, robot4);
//		gameMaster.startGame();

		//test if gamemaster loads a save

//		GameMaster savedGameMaster = new GameMaster();
//		savedGameMaster.loadGame("");
//		System.out.println(savedGameMaster.getGameID()+ "Game Loaded " );
//		System.out.println("Player 1: " + savedGameMaster.getPlayerBlueName() + " Player 2: " + savedGameMaster.getPlayerRedName());
	}



}
