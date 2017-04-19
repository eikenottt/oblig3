package no.uib.info233.v2017.rei008_jsi014.oblig3;


public abstract class Player {

    private String name;
    private int energy;
    private GameMaster gameMaster;

    public Player(String name) {
        this.name = name;
        setEnergy();
    }

    public void registerGameMaster(GameMaster gameMaster) {
        this.gameMaster = gameMaster;
    }

    public abstract void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy);

    public void gameOver(float earnedPoints) {

    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    private void setEnergy() {
        this.energy = 100;
    }

    public GameMaster getGameMaster() {
        return gameMaster;
    }

    protected void updateEnergy(int usedEnergy){
        this.energy += usedEnergy;
    }
}
