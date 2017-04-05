package no.uib.info233.v2017.rei008_jsi014.oblig3.tests;

import no.uib.info233.v2017.rei008_jsi014.oblig3.AgressivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig3.GameMaster;
import no.uib.info233.v2017.rei008_jsi014.oblig3.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {

    private Player player1;
    private GameMaster gameMaster;

    @BeforeEach
    void setUp() {
        player1 = new AgressivePlayer("Android");
        gameMaster = new GameMaster();
    }

    @Test
    void registerGameMaster() {
        player1.registerGameMaster(gameMaster);
    }

    @Test
    void makeNextMove() {
    }

    @Test
    void gameOver() {
    }

}