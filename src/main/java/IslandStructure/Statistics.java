package IslandStructure;

import entities.ConstantsAnimals;
import entities.Organisms;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
@Getter
@Setter
public class Statistics implements Runnable {
    ConstantsAnimals constantsAnimals;

    public Statistics(ConstantsAnimals constantsAnimals) {
        this.constantsAnimals = constantsAnimals;
    }

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


    @Override
    public void run() {
                for (Organisms value:Organisms.values()
                     ) {
                    System.out.print(constantsAnimals.getICON().get(value)+": "+statistics.get(value).get()+"  ");
                }
        System.out.println();



    }
}
