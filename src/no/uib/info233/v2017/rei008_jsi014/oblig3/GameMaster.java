package no.uib.info233.v2017.rei008_jsi014.oblig3;


import java.util.ArrayList;

public final class GameMaster {
    private static final GameMaster GAMEMASTER = new GameMaster();
    private Player player1;
    private Player player2;
    private int position;
    private final Integer[] GOAL;
    private ArrayList<Player> ranking;

    private GameMaster() {
        position = 3;
        GOAL = new Integer[]{0,7};
        ranking = new ArrayList<>();
    }

    public static GameMaster getGameMaster() {
        return GAMEMASTER;
    }

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startGame() {

    }

    public void listenToPlayerMove(Player player, int move) {

    }

    public void evaluateTurn() {

    }

    public void updateRanking() {

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getPosition() {
        return position;
    }

}
