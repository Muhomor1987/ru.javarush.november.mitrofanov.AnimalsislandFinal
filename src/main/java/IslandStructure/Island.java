package IslandStructure;

import lombok.Getter;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Island {
    private String name;

    private int xMax;

    private int yMax;

    private HashSet<Location> locationHashSet;

    private int totalLocations;

    private Location[][] locations  ;

    public Island(String name, int xMax, int yMax) {
        this.name = name;
        this.xMax = xMax;
        this.yMax = yMax;
        totalLocations = xMax*yMax;
    }
}
