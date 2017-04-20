package no.uib.info233.v2017.rei008_jsi014.oblig3.tests;

import no.uib.info233.v2017.rei008_jsi014.oblig3.AggressivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig3.GameMaster;
import no.uib.info233.v2017.rei008_jsi014.oblig3.PassivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig3.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {

    private Player player1;
    private Player player2;
    private GameMaster gameMaster;

    @BeforeEach
    void setUp() {
        gameMaster = GameMaster.getGameMaster();
        player1 = new AggressivePlayer("Android");
        player2 = new PassivePlayer("iOS");
        gameMaster.setPlayers(player1, player2);
        try {
            gameMaster.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetFromPositionToPoints() throws Exception {
        float expected = 2f;
        assertEquals(expected, gameMaster.getPointsFromPosition(player1.getCurrentPosition()));
    }

    @Test
    void gameOver() {
        boolean expected = true;
        assertEquals(expected, gameMaster.isGameOver());
    }

}