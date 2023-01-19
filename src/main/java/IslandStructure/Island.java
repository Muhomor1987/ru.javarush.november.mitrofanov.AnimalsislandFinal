package IslandStructure;

import entities.Animal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Island{
    private String name;

    private int xMax;

    private int yMax;

    private HashSet<Location> locationHashSet;

    private int totalLocations;

    private Location[][] locations;

    public Island(String name, int xMax, int yMax) {
        this.name = name;
        this.xMax = xMax;
        this.yMax = yMax;
        totalLocations = xMax * yMax;
    }




    }
