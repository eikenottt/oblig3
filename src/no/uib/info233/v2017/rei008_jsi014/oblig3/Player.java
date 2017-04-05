package no.uib.info233.v2017.rei008_jsi014.oblig3;


public abstract class Player {

    private String name;
    private float earnedPoints;
    private int energy;

    public Player(String name) {
        this.name = name;
    }

    public void registerGameMaster(GameMaster gameMaster) {
    }

    public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {

    }

    public void gameOver(float earnedPoints) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getEarnedPoints() {
        return earnedPoints;
    }

    public void setEarnedPoints(float earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
