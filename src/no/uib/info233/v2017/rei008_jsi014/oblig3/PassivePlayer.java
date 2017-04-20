package no.uib.info233.v2017.rei008_jsi014.oblig3;


public class PassivePlayer extends Player {

    public PassivePlayer(String name) {
        super(name);
    }

    @Override
    public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {

        int useEnergy;
        if(currentPosition >= 3) {
            if(opponentEnergy <= 0) {
                useEnergy = minusFive(yourEnergy);
            }
            else {
                useEnergy = minusFive(yourEnergy);
            }
        } else {
            useEnergy = minusFive(yourEnergy);
        }

        if(useEnergy > yourEnergy) {
            useEnergy = yourEnergy;
        }

        this.updateEnergy(-useEnergy);
        this.getGameMaster().listenToPlayerMove(this, useEnergy);

    }
}
