package no.uib.info233.v2017.rei008_jsi014.oblig3;

/**
 *
 */
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
        System.out.println(getCurrentEnergy());
        int randMove = rand.nextInt(4);
        int useEnergy = 0;
        if(yourEnergy > 0) {

            // If yourEnergy is bigger than the actual energy set yourEnergy to actual energy
            if(yourEnergy > this.getCurrentEnergy()) {
                yourEnergy = getCurrentEnergy();
            }

            // Detects currentPosition and assigns the energy to use
            switch (currentPosition) {
                case 0:
                case 1:
                    if (randMove == 0) {
                        useEnergy = whirlwind(yourEnergy);
                    } else
                        useEnergy = slash(yourEnergy);
                    break;
                case 2:
                    useEnergy = overheadSwing(yourEnergy);
                    break;
                case 3:
                case 4:
                    if (randMove == 0 || randMove == 1) {
                        useEnergy = stab(yourEnergy);
                    } else {
                        useEnergy = overheadSwing(yourEnergy);
                    }
                    break;
                case 5:
                case 6:
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
        }
        this.updateEnergy(-useEnergy);

        // Sends energy usage to gamemaster
        this.getGameMaster().listenToPlayerMove(this, useEnergy);

    }

    /**
     *
     * @param yourEnergy
     * @return energy to use
     */
    private int whirlwind(int yourEnergy){
        int randNumber = this.rand.nextInt(25);
        if (yourEnergy >=30){
            return 5 + randNumber;
        } else if (yourEnergy > 10){
            return randNumber;
        }else
            return yourEnergy;
    }


}

