package no.uib.info233.v2017.rei008_jsi014.oblig3;


public class AggressivePlayer extends Player {

    public AggressivePlayer(String name) {
        super(name);
    }

    @Override
    public void makeNextMove(int CurrentPosition, int yourEnergy, int opponentEnergy) {

        int useEnergy;
        if (yourEnergy > 50){
            useEnergy = 45;
        }else{
            useEnergy = yourEnergy;
        }

        this.updateEnergy(-useEnergy);
        this.getGameMaster().listenToPlayerMove(this, useEnergy);
    }
}
