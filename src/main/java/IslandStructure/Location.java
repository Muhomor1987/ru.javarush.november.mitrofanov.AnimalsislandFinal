package IslandStructure;

import entities.Animal;
import entities.Organisms;
import lombok.Getter;
import lombok.Setter;
import util.FabricOfAnimals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class Location implements Runnable {
    FabricOfAnimals fabricOfAnimals;
    Statistics statistics;
    private  Lock lock = new ReentrantLock();

    private boolean isCreated = false;

    public Location(FabricOfAnimals fabricOfAnimals, Statistics statistics) {
        this.fabricOfAnimals = fabricOfAnimals;
        this.statistics = statistics;
    }

    private ArrayList<Animal> animalsOnLocation = new ArrayList<>();
    private ConcurrentLinkedQueue<Animal> animalsForMoving = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Animal> animalsIn = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Animal> plantSet = new ConcurrentLinkedQueue<>();

    ConcurrentHashMap<Organisms, Integer> countAnimalsMapOnLocation;


    //засыпает на 2 секунды через константа жизненый цикл
    public void liveLocation() {
        while (true) {

                Animal animalFirst = null;
                Animal animalSecond = null;

 //               this.lock.lock();
//                if (animalsOnLocation.isEmpty()) {
//                    while (animalsOnLocation.isEmpty()) {
 //                       this.lock.unlock();
//                        Thread.sleep(1000);

//                } else {
// //                   this.lock.lock();
//                    animalFirst = animalsOnLocation.remove(ThreadLocalRandom.current().nextInt(animalsOnLocation.size()-1));
//                }
//                if (animalsOnLocation.isEmpty()) {
//                    while (animalsOnLocation.isEmpty()) {
// //                       this.lock.unlock();
////                        Thread.sleep(1000);
//                    }
//                } else {
  //                  this.lock.lock();
            if(!animalsOnLocation.isEmpty()) {
                animalFirst = animalsOnLocation.remove(ThreadLocalRandom.current().nextInt(animalsOnLocation.size() - 1));
            }
            if(!animalsOnLocation.isEmpty()) {
                animalSecond = animalsOnLocation.remove(ThreadLocalRandom.current().nextInt(animalsOnLocation.size() - 1));
            }
  //              this.lock.unlock();
                assert animalFirst != null;
                Organisms animalFirstTYPE = animalFirst.getTYPE();
                assert animalSecond != null;
                Organisms animalSecondTYPE = animalSecond.getTYPE();
                //является ли животные одного вида
                reproduction(animalFirst, animalSecond);
 //               this.lock.lock();
                eat(animalFirst, animalSecond);
//                Thread.sleep(100);
  //             this.lock.lock();
                animalsOnLocation.addAll(animalsIn);
                animalsIn.clear();
//                Thread.sleep(10);
                //move(animalFirst, animalSecond)

        }
    }

    synchronized private void eat(Animal animalFirst, Animal animalSecond) {
        Organisms animalFirstTYPE = animalFirst.getTYPE();
        Organisms animalSecondTYPE = animalSecond.getTYPE();
        if (animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE) || animalSecond.getMAP_OF_FOOD().containsKey(animalFirstTYPE)) {
            if (animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE)) {
                whoEating(animalSecond, animalFirst);
            }
            if (animalSecond.getMAP_OF_FOOD().containsKey(animalFirstTYPE)) {
                whoEating(animalFirst, animalSecond);
            }

        }
    }


    synchronized private void whoEating(Animal animalDead, Animal animalWhoEat) {
        Organisms animalDeadTYPE = animalDead.getTYPE();
        if (ThreadLocalRandom.current().nextInt(100) >= animalWhoEat.getMAP_OF_FOOD().get(animalDeadTYPE)) {
            if (animalDead.getWeight() >= animalWhoEat.getSATURATION()) {
                animalWhoEat.setWeight(animalWhoEat.getWEIGHT_MAX());
            } else {
                animalWhoEat.setWeight(animalWhoEat.getWeight() + animalDead.getWeight());
            }
            animalsForMoving.add(animalWhoEat);
            countAnimalsMapOnLocation.put(animalDeadTYPE, countAnimalsMapOnLocation.get(animalDeadTYPE) - 1);
            statistics.getStatistics().get(animalDeadTYPE).set(statistics.getStatistics().get(animalDeadTYPE).decrementAndGet());
            fabricOfAnimals.getPoolAnimals().get(animalDeadTYPE).add(animalDead);
        }
    }

    private void reproduction(Animal animalFirst, Animal animalSecond) {
        if (animalFirst.getTYPE() == animalSecond.getTYPE()) {
            fabricOfAnimals.createNewAnimals(this, animalFirst.getTYPE(), statistics);
            animalsIn.add(animalFirst);
            animalsIn.add(animalSecond);
        }
    }


    @Override
    public void run() {

        liveLocation();

    }
}
