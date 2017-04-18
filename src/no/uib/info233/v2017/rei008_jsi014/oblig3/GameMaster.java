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
        Boolean start = true;
            while(start){
              //  player1.makeNextMove(this.getPosition(), player1.getEnergy(), player2.getEnergy()) <-- burde returnere int?
                // this.listenToPlayerMove(player1, int move);

            }

    }

    public int listenToPlayerMove(Player player, int move) {
        int energyAvailable = player.getEnergy();
        if ((move > energyAvailable) || (move < 0)) {
            move = 0;
        }
        return move;
    }

    public void evaluateTurn() {

    }

    public void updateRanking() {

    }

    public int getPosition() {
        return position;
    }

}
