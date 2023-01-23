package util;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.Animal;

import java.util.Iterator;

public class LifeCycle implements Runnable {
    Island island;
    FabricOfAnimals fabricOfAnimals;
    Statistics statistics;

    public LifeCycle(Island island, FabricOfAnimals fabricOfAnimals, Statistics statistics) {
        this.island = island;
        this.fabricOfAnimals = fabricOfAnimals;
        this.statistics = statistics;
    }

    private void lifeCycle(Location location, Iterator<Animal> iterator) {
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            animal.setWeight(animal.getWeight() - animal.getWEIGHT_MAX() * 0.1);
            if (animal.getWeight() < animal.getWEIGHT_MAX() / 2) {
                iterator.remove();
                fabricOfAnimals.getPoolAnimals().get(animal.getTYPE()).add(animal);
                statistics.getStatistics().get(animal.getTYPE()).decrementAndGet();  //lll?
                location.getCountAnimalsMapOnLocation().put(animal.getTYPE(), location.getCountAnimalsMapOnLocation().get(animal.getTYPE()) - 1);
            }
        }
    }
    @Override
    public void run() {
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j < island.getYMax(); j++) {
                Location location = island.getLocations()[i][j];
                location.getLock().lock();
                Iterator<Animal> iterator = location.getAnimalsIn().iterator();
                lifeCycle(location, iterator);
                Iterator<Animal> iterator1 = location.getAnimalsOnLocation().iterator();
                lifeCycle(location, iterator1);
                Iterator<Animal> iterator2 = location.getAnimalsForMoving().iterator();
                lifeCycle(location, iterator2);
            }
        }
    }
}

