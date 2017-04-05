package no.uib.info233.v2017.rei008_jsi014.oblig3;


public class GameMaster {
    private Player player1;
    private Player player2;
    private int position;

    public GameMaster(){

    }

    public GameMaster getGameMaster(){

        return new GameMaster();

    }

    public void setPlayers(Player player1, Player player2){

    }

    public void startGame(){

    }

    public void listenToPlayerMove(Player player, int move){

    }

    public void evaluateTurn(){

    }

    public void updateRanking(){

    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
