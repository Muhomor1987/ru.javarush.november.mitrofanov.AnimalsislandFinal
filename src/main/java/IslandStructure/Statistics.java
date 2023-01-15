package IslandStructure;

import entities.Organisms;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
@Getter
@Setter
public class Statistics implements Serializable {

    private HashMap<Organisms,AtomicInteger> statistics = new HashMap<Organisms,AtomicInteger>(){{
        put(Organisms.WOLF, new AtomicInteger());
        put(Organisms.BOA,new AtomicInteger());
        put(Organisms.FOX,new AtomicInteger());
        put(Organisms.BEAR,new AtomicInteger());
        put(Organisms.EAGLE,new AtomicInteger());
        put(Organisms.HORSE,new AtomicInteger());
        put(Organisms.DEER,new AtomicInteger());
        put(Organisms.RABBIT,new AtomicInteger());
        put(Organisms.MOUSE,new AtomicInteger());
        put(Organisms.GOAT,new AtomicInteger());
        put(Organisms.SHEEP,new AtomicInteger());
        put(Organisms.BOAR,new AtomicInteger());
        put(Organisms.DUCK,new AtomicInteger());
        put(Organisms.BUFFALO,new AtomicInteger());
        put(Organisms.CATERPILLAR,new AtomicInteger());
        put(Organisms.PLANT,new AtomicInteger());
    }};
/*    private AtomicInteger totalCounterWolf;
    private AtomicInteger totalCounterBoa;
    private AtomicInteger totalCounterFox;
    private AtomicInteger totalCounterBear;
    private AtomicInteger totalCounterEagle;
    private AtomicInteger totalCounterHorse;
    private AtomicInteger totalCounterDeer;
    private AtomicInteger totalCounterRabbit;
    private AtomicInteger totalCounterMouse;
    private AtomicInteger totalCounterGoat;
    private AtomicInteger totalCounterSheep;
    private AtomicInteger totalCounterBoar;
    private AtomicInteger totalCounterDuck;
    private AtomicInteger totalCounterBuffalo;
    private AtomicInteger totalCounterCaterpillar;
    private AtomicInteger totalCounterPlant;*/
}
