package no.uib.info233.v2017.rei008_jsi014.oblig3;

public class AggressivePlayer extends Player {

    public AggressivePlayer(String name) {
        super(name);
    }

    @Override
    public void makeNextMove(int currentPosition, int yourEnergy, int opponentEnergy) {
        if(yourEnergy < 0) {
            yourEnergy = 0;
            setCurrentEnergy(yourEnergy);
        }
        int randMove = rand.nextInt(4);
        int useEnergy = 0;
        if (yourEnergy > 0) {

            // If yourEnergy is bigger than the actual energy set yourEnergy to actual energy
            if (yourEnergy > this.getCurrentEnergy()) {
                yourEnergy = getCurrentEnergy();
            }

            // Detects currentPosition and assigns the energy to use
            switch (currentPosition) {
                case 0:
                case 1: //25% whirlwind(), 75% slash() for position 0 and 1
                    if (randMove == 0) {
                        useEnergy = whirlwind(yourEnergy);
                    } else
                        useEnergy = slash(yourEnergy);
                    break;
                case 2:
                    useEnergy = overheadSwing(yourEnergy);
                    break;
                case 3:
                case 4: // 50% chance of using stab(),50% of using overheadSwing in position 3 and 4
                    if (randMove == 0 || randMove == 1) {
                        useEnergy = stab(yourEnergy);
                    } else {
                        useEnergy = overheadSwing(yourEnergy);
                    }
                    break;
                case 5:
                case 6: // 25% of using slash(), 25% stab(), 50% whirilwind()
                    if (randMove == 0) {
                        useEnergy = slash(yourEnergy);
                    } else if (randMove == 1) {
                        useEnergy = stab(yourEnergy);
                    } else
                        useEnergy = whirlwind(yourEnergy);
                    break;
                default:
                    useEnergy = 0;
                    break;

            }
            this.updateEnergy(-useEnergy);
            this.getGameMaster().listenToPlayerMove(this, useEnergy);
        }
    }

    /**
     * Special attack for the AggressivePlayer
     * High output, with relative high amount of randomness
     * @param yourEnergy - the energy the player has available
     * @return int energy to spend
     */
    private int whirlwind(int yourEnergy){
        int randNumber = this.rand.nextInt(25);
        if (yourEnergy >=30){
            return 5 + randNumber;
        } else if (yourEnergy > 25){
            return randNumber;
        }else
            return yourEnergy;
    }


}

