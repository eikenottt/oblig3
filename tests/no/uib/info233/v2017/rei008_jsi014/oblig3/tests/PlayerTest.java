package no.uib.info233.v2017.rei008_jsi014.oblig3.tests;

import org.junit.Before;
import org.junit.Test;

import no.uib.info233.v2017.rei008_jsi014.oblig3.AggressivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig3.GameMaster;
import no.uib.info233.v2017.rei008_jsi014.oblig3.PassivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig3.Player;

import static org.junit.Assert.assertEquals;


public class PlayerTest {

    private Player player1;
    private Player player2;
    private GameMaster gameMaster;

    @Before
    public void setUp() {
        gameMaster = GameMaster.getGameMaster();
        player1 = new AggressivePlayer("Android");
        player2 = new PassivePlayer("iOS");
        gameMaster.setPlayers(player1, player2);
    }

    @Test
    public void testGetFromPositionToPoints() throws Exception {
        float expected = 0.5f;
        assertEquals(expected, gameMaster.getPointsFromPosition(player1.getCurrentPosition()), 0);
    }

    @Test
    public void testGameOver() {
        try {
            gameMaster.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean expected = true;
        assertEquals(expected, gameMaster.isGameOver());
    }

    @Test
    public void testMakeNextMove() {
        player1.setCurrentEnergy(-1);
        int expected1 = 0;


        try {
            player1.makeNextMove(player1.getCurrentPosition(), player1.getCurrentEnergy(), player2.getCurrentEnergy());
            assertEquals(expected1, player1.getCurrentEnergy());
            //player1.setCurrentEnergy(100);
            player1.makeNextMove(player1.getCurrentPosition(), 2000, player2.getCurrentEnergy());
            boolean expected2 = (player1.getCurrentEnergy() < 100);
            System.out.println(player1.getCurrentEnergy());
            assertEquals(expected2, true);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

