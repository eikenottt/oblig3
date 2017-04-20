package no.uib.info233.v2017.rei008_jsi014.oblig3;


import java.util.Random;

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

    private int overheadSwing(int yourEnergy){
        int randNumber;
        randNumber = rand.nextInt(15);

        if(yourEnergy < 35){
            return 20 + randNumber;
        }else {
            return getCurrentEnergy();
        }
    }

    private int stab(int yourEnergy){

        int randNumber;
        randNumber = this.rand.nextInt(50);

        if (yourEnergy > 50){
            return randNumber;
        }else if(yourEnergy>11){
            return randNumber/10 + 1;
        }else{
            return 0;
        }
    }
    private int slash(int yourEnergy){

        int randNumber = this.rand.nextInt(15);
            if (yourEnergy > 20) {
                return 5 + randNumber;
            }else if( yourEnergy >= 5){
                return 5;
            }else return 0;

    }
}

