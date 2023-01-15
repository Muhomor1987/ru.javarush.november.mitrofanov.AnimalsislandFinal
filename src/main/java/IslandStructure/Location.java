package IslandStructure;

import entities.Animal;
import entities.Organisms;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class Location {
    Lock lock = new ReentrantLock();
    Boolean created = false;

    ArrayList<Animal> setLocations = new ArrayList<>();
    HashMap<Organisms,Integer> countAnimalsMapOnLocation;



}
