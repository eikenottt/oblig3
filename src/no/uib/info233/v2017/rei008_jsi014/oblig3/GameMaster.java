package no.uib.info233.v2017.rei008_jsi014.oblig3;


public class GameMaster {
    private Player player1;
    private Player player2;
    private int position;
    private final Integer[] GOAL;

    public GameMaster() {
        position = 3;
        GOAL = new Integer[]{0,7};
    }

    public GameMaster getGameMaster() {

        return new GameMaster();

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
