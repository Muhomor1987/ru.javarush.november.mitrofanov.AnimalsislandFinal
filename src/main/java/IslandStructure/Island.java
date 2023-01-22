package IslandStructure;

import entities.Animal;
import entities.ConstantsAnimals;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

@Getter
public class Island{
    Lock islandLock;
    ConstantsAnimals constantsAnimals;
    private String name;

    private int xMax;

    private int yMax;

    private HashSet<Location> locationHashSet;

    private final int totalLocations;

    private final Location[][] locations;

    public Island(String name, ConstantsAnimals constantsAnimals) {

        this.name = name;
        this.xMax = constantsAnimals.getXCoordinate();
        this.yMax = constantsAnimals.getYCoordinate();
        totalLocations = xMax * yMax;
        locations = new Location[xMax][yMax];
    }




    }
