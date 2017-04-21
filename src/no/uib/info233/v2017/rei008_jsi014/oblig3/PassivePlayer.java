package no.uib.info233.v2017.rei008_jsi014.oblig3;

/**
 * Describes an Passive type player: strategy is to spend a moderate amount of energy in neutral positions,
 * uses least amount in offence, and the most amount in defence to win games both in offence.
 * Introduces the special attack swordPoke(), a low energy attack.
 *
 * @author rei008
 * @author jsi014
 * @version 0,1
 */
public class PassivePlayer extends Player {

    public PassivePlayer(String name) {
        super(name);
    }

    @Override
    public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
        int randMove = rand.nextInt(4);
        int useEnergy;
        switch (currentPosition){
            case 0: case 1: case 2: // 50% overheadSwing() 50% stab() for position 0, 1 and 2
                if (randMove == 0 || randMove == 1) {
                    useEnergy = overheadSwing(yourEnergy);
                }else
                    useEnergy = stab(yourEnergy);
                break;
            case 3: // 25% stab() 75% slash in position 3
                if (randMove == 0 ){
                    useEnergy = stab(yourEnergy);
                }else{
                    useEnergy= slash(yourEnergy);
                }
                break;
            case 4: case 5: case 6: // 25% slash 75% swordPoke in position 4, 5 and 6
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

    /**
     * Special attack for the PassivePlayer
     * Low output, with small amount of randomness
     * @param yourEnergy - the energy the player has available
     * @return int energy to spend
     */
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
