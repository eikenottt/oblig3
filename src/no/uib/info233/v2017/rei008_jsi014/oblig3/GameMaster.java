package no.uib.info233.v2017.rei008_jsi014.oblig3;


import java.util.ArrayList;

public final class GameMaster {

    public static final GameMaster GAMEMASTER = new GameMaster();
    private Player player1;
    private Player player2;
    private int player1EnergyUse;
    private int player2EnergyUse;

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
        //Boolean start = true;
            //while(start){
              //  player1.makeNextMove(this.getPosition(), player1.getEnergy(), player2.getEnergy()) <-- burde returnere int?
                // this.listenToPlayerMove(player1, int move);

            }

    }

    public void listenToPlayerMove(Player player, int energyUse) {
        if(!gameOver) {

            System.out.println(player.getName() + " is using " + energyUse + " energy to swing his sword!");

            if(player.equals(player1)) {
                this.player1EnergyUse = energyUse;
            } else {
                this.player2EnergyUse = energyUse;
            }
        } else {
            evaluateTurn();
        }

        if(this.player1EnergyUse > -1 && this.player2EnergyUse > -1) {
            evaluateTurn();
        }
    }

    public void evaluateTurn() {

    }

    public void updateRanking() {

    }

    public int getPosition() {
        return position;
    }

}
