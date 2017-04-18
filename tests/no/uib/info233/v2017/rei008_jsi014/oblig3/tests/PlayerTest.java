package no.uib.info233.v2017.rei008_jsi014.oblig3.tests;

import no.uib.info233.v2017.rei008_jsi014.oblig3.AggressivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig3.GameMaster;
import no.uib.info233.v2017.rei008_jsi014.oblig3.PassivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig3.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PlayerTest {

    private Player player1;
    private Player player2;
    private GameMaster gameMaster;

    @BeforeEach
    void setUp() {
        gameMaster = GameMaster.GAMEMASTER;
        player1 = new AggressivePlayer("Android");
        player2 = new PassivePlayer("iOS");
        gameMaster.setPlayers(player1, player2);
    }

    @Test
    void registerGameMaster() {
        player1.registerGameMaster(gameMaster);
        player2.registerGameMaster(gameMaster);
    }

    @Test
    void makeNextMove() {
        player1.makeNextMove(gameMaster.getPosition(), player1.getEnergy(), player2.getEnergy());
    }

    @Test
    void gameOver() {
    }

}