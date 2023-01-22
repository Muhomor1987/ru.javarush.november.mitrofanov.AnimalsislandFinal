package util;

import IslandStructure.Island;

public class Counter implements Runnable{
        Island island;

    public Counter(Island island) {
        this.island = island;
    }

    @Override
    public void run() {

                System.out.print(island.getLocations()[1][1].getAnimalsOnLocation().size());
            }
        }


    }
}
