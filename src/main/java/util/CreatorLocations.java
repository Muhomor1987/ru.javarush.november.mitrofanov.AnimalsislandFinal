package util;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.ConstantsAnimals;
import entities.Organisms;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class CreatorLocations implements Runnable {
    FabricOfAnimals fabricOfAnimals;
    Island island;
    ConstantsAnimals constantsAnimals;
    Statistics statistics;

    public CreatorLocations(FabricOfAnimals fabricOfAnimals, Island island, ConstantsAnimals constantsAnimals, Statistics statistics) {
        this.fabricOfAnimals = fabricOfAnimals;
        this.island = island;
        this.constantsAnimals = constantsAnimals;
        this.statistics = statistics;
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j <island.getYMax(); j++) {
                island.getLocations()[i][j]=new Location(fabricOfAnimals,statistics);
            }
        }

    }

    @Override
    public void run() {
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j < island.getYMax(); j++) {
                if (island.getLocations()[i][j].getLock().tryLock()) {
                    if (!island.getLocations()[i][j].isCreated()) {
                        island.getLocations()[i][j].setCreated(true);
                        ConcurrentHashMap<Organisms, Integer> countAnimalsMapOnLocationTMP = new ConcurrentHashMap<>();
                        for (Organisms k : Organisms.values()
                        ) {
                            countAnimalsMapOnLocationTMP.put(k, ThreadLocalRandom.current().nextInt(constantsAnimals.getMaxAnimalForKindOfLocations().get(k)));
                        }
                        island.getLocations()[i][j].setCountAnimalsMapOnLocation(countAnimalsMapOnLocationTMP);
                        for (Organisms k : Organisms.values()
                        ) {
                            for (int l = 0; l < countAnimalsMapOnLocationTMP.get(k); l++) {
                                fabricOfAnimals.createNewAnimals(island.getLocations()[i][j],k,statistics);
                            }
                        }
                        System.out.println(Thread.currentThread().getName() +"___"+ island.getLocations()[i][j].getAnimalsOnLocation().size() +"___"+i+"___" + j);
                    }
                }
            }
        }
    }
}