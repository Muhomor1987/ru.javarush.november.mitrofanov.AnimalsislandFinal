package IslandStructure;

import entities.Animal;
import entities.Organisms;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import util.FabricOfAnimals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class Location implements  Runnable{
    FabricOfAnimals fabricOfAnimals;
    Statistics statistics;
    final Lock lock = new ReentrantLock();
    Boolean created = false;
        boolean isLiveLocation = false;
     ArrayList<Animal> animalsOnLocation = new ArrayList<>();
    ArrayList<Animal> animalsForMoving = new ArrayList<>();

    HashMap<Organisms,Integer> countAnimalsMapOnLocation;

//засыпает на 2 секунды через константа жизненый цикл
    synchronized public void liveLocation(){
        while (!isLiveLocation) {
            while (animalsOnLocation.size()>1) {
                    Animal animalFirst = animalsOnLocation.remove(0);
                    Animal animalSecond = animalsOnLocation.remove(0);
                    Organisms animalFirstTYPE = animalFirst.getTYPE();
                    Organisms animalSecondTYPE = animalSecond.getTYPE();

                    if (animalFirst.getTYPE()==animalSecond.getTYPE()){
                        fabricOfAnimals.createNewAnimals(this,animalFirst.getTYPE(),statistics);
                    }
                    else {
                        if(animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE)){
                            if (ThreadLocalRandom.current().nextInt(100)>animalFirst.getMAP_OF_FOOD()po00000000000000000000)
                        }
                    }

            }
        }
    }
    @Override
    public void run() {
        liveLocation();
    }
}
