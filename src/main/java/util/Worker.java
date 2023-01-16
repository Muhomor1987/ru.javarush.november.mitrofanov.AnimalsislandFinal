package util;

import IslandStructure.Island;

public class Worker implements Runnable {
    Island island;
    FabricOfAnimals fabricOfAnimals;
    @Override
    public void run() {
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j < island.getYMax(); j++) {
                if(island.getLocations()[i][j].getLock().tryLock()){
                    island.getLocations()[i][j].getLock();
                }
            }
        }
    }
}
