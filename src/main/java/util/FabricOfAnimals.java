package util;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.Animal;
import entities.ConstantsAnimals;
import entities.Organisms;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FabricOfAnimals implements Runnable {
    ConstantsAnimals constantsAnimals;
    Island island;
    Statistics statistics;


    private HashMap<Organisms, ArrayList<Animal>> poolAnimals = new HashMap<>();

    HashMap<Organisms, Animal> mapOfFounders = new HashMap<>();
    public FabricOfAnimals(ConstantsAnimals constantsAnimals, Island island, Statistics statistics) {
        this.constantsAnimals = constantsAnimals;
        this.island = island;
        this.statistics = statistics;
        for (Organisms TYPE : Organisms.values()
        ) {
            mapOfFounders.put(TYPE, new Animal(
                            constantsAnimals.getMaxWeight().get(TYPE),
                            constantsAnimals.getSPEED().get(TYPE),
                    constantsAnimals.getSATURATION().get(TYPE),
                    TYPE,
                    constantsAnimals.getICON().get(TYPE),
                    constantsAnimals.getMapOfFood().get(TYPE)));
        }
    }




    public void createNewAnimals(Location location, Organisms TYPE, Statistics statistics) {
        if (location.getCountAnimalsMapOnLocation().get(TYPE) < constantsAnimals.getMaxAnimalForKindOfLocations().get(TYPE)) {
            if (poolAnimals.get(TYPE).isEmpty()) {
                Animal animal = mapOfFounders.get(TYPE).clone();
                animal.name = animal.getTYPE().name() + statistics.getStatistics().get(TYPE).getAndIncrement();
                animal.weight = ThreadLocalRandom.current().nextDouble(constantsAnimals.getMaxWeight().get(TYPE));
                location.getAnimalsOnLocation().add(animal);
            } else {
                location.getAnimalsOnLocation().add(poolAnimals.get(TYPE).remove(0));
            }
        }
    }
//Запуск создания животных на всех локациях(нужно сделать одновременный запуск для всех локаций)
    @Override
    public void run() {
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j < island.getYMax(); j++) {
                HashMap<Organisms, Integer> countAnimalsMapOnLocationTMP = new HashMap<>();
                for (Organisms k : Organisms.values()
                ) {
                    countAnimalsMapOnLocationTMP.put(k, ThreadLocalRandom.current().nextInt(constantsAnimals.getMaxAnimalForKindOfLocations().get(k)));
                }
                island.getLocations()[i][j].setCountAnimalsMapOnLocation(countAnimalsMapOnLocationTMP);
                for (Organisms k : Organisms.values()
                ) {
                    for (int l = 0; l < countAnimalsMapOnLocationTMP.get(k); l++) {
                        island.getLocations()[i][j].getAnimalsOnLocation().add(mapOfFounders.get(k));
                        statistics.getStatistics().get(k).incrementAndGet();
                    }
                }
            }
        }
    }
}
