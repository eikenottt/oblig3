package no.uib.info233.v2017.rei008_jsi014.oblig3;


import java.util.Random;

public class AggressivePlayer extends Player {

    public AggressivePlayer(String name) {
        super(name);
    }

    @Override
    public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
        System.out.println(yourEnergy);
        int randMove = rand.nextInt(4);
        int useEnergy;
        switch (currentPosition){
            case 0: case 1: case 2:
                useEnergy=overheadSwing(yourEnergy);
                break;
            case 3: case 4:
                if (randMove == 0 || randMove == 1){
                    useEnergy=stab(yourEnergy);
                }else{
                    useEnergy=overheadSwing(yourEnergy);
                }
                break;
            case 5: case 6:
                if(randMove == 3){
                    useEnergy=slash(yourEnergy);
                }else{
                    useEnergy=stab(yourEnergy);
                }
                break;
            default:
                useEnergy = 0;
                break;

        }
        this.updateEnergy(-useEnergy);
        this.getGameMaster().listenToPlayerMove(this, useEnergy);

/*        int useEnergy;
        if (yourEnergy > 50){
            useEnergy = 45;
        }else{
            useEnergy = yourEnergy;
        }

        this.updateEnergy(-useEnergy);
        this.getGameMaster().listenToPlayerMove(this, useEnergy);*/
    }


}

