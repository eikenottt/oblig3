package no.uib.info233.v2017.rei008_jsi014.oblig3;


public class PassivePlayer extends Player {

    public PassivePlayer(String name) {
        super(name);
    }

    @Override
    public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
        int randMove = rand.nextInt(4);
        int useEnergy;
        switch (currentPosition){
            case 0: case 1: case 2:
                if (randMove == 0 || randMove == 1) {
                    useEnergy = overheadSwing(yourEnergy);
                }else
                    useEnergy = stab(yourEnergy);
                break;
            case 3:
                if (randMove == 0 ){
                    useEnergy = stab(yourEnergy);
                }else{
                    useEnergy= slash(yourEnergy);
                }
                break;
            case 4: case 5: case 6:
                if(randMove == 3){
                    useEnergy = slash(yourEnergy);
                }else{
                    useEnergy = swordPoke(yourEnergy);
                }
                break;
            default:
                useEnergy = 0;
                break;

        }
        this.updateEnergy(-useEnergy);
        this.getGameMaster().listenToPlayerMove(this, useEnergy);
    }

    private int swordPoke(int yourEnergy){
        int randNumber = this.rand.nextInt(10);
        if (yourEnergy >=15){
            return 5 + randNumber;
        } else if (yourEnergy > 10){
            return randNumber;
        }else
            return yourEnergy;
    }
}
