package no.uib.info233.v2017.rei008_jsi014.oblig3;


public abstract class Player {

    private String name;
    private int energy;
    private GameMaster gameMaster;

    public Player(String name) {
        this.name = name;
    }

    public void registerGameMaster(GameMaster gameMaster) {
    }

    public abstract void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy);

    public void gameOver(float earnedPoints) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy() {
        this.energy = (int)Math.floor(Math.random() * 100);
    }
}
