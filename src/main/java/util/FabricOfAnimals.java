package util;

import IslandStructure.Island;
import IslandStructure.Location;
import IslandStructure.Statistics;
import entities.Animal;
import entities.ConstantsAnimals;
import entities.Organisms;
import lombok.Getter;
import lombok.Synchronized;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;

@Getter
public class FabricOfAnimals {
    ConstantsAnimals constantsAnimals;
    Island island;
    Statistics statistics;
    Lock lockFabric;

    private final ConcurrentHashMap<Organisms, ArrayList<Animal>> poolAnimals= new ConcurrentHashMap<>();

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
            if (!poolAnimals.isEmpty()) {
                if (poolAnimals.get(TYPE).isEmpty()) {
                    Animal animal = mapOfFounders.get(TYPE).clone();
                    animal.name = animal.getTYPE().name() + statistics.getStatistics().get(TYPE).getAndIncrement();
                    animal.weight = ThreadLocalRandom.current().nextDouble(constantsAnimals.getMaxWeight().get(TYPE));
                    location.getAnimalsIn().add(animal);
                } else {
                    location.getAnimalsIn().add(poolAnimals.get(TYPE).remove(0));
                    statistics.getStatistics().get(TYPE).incrementAndGet();
                }
            }
        }
    }

    //Запуск создания животных на всех локациях(нужно сделать одновременный запуск для всех локаций)

}
