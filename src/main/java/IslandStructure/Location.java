package IslandStructure;

import entities.Animal;
import entities.Organisms;
import lombok.Getter;
import lombok.Setter;
import util.FabricOfAnimals;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
public class Location implements Runnable {
    FabricOfAnimals fabricOfAnimals;
    Statistics statistics;
    final Lock lock = new ReentrantLock();
    Boolean created = false;
    boolean isLiveLocation = false;
    ConcurrentLinkedQueue<Animal> animalsOnLocation = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Animal> animalsForMoving = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Animal> plantSet = new ConcurrentLinkedQueue<>();

    HashMap<Organisms, Integer> countAnimalsMapOnLocation;

    //засыпает на 2 секунды через константа жизненый цикл
    public void liveLocation() {
        while (true) {//заменить на wait
            try {
                lock.lock();
                Animal animalFirst = animalsOnLocation.poll();
                if (animalFirst == null) {
                    wait();
                }
                Animal animalSecond = animalsOnLocation.poll();
                if (animalSecond == null) {
                    wait();
                }
                assert animalFirst != null;
                Organisms animalFirstTYPE = animalFirst.getTYPE();
                assert animalSecond != null;
                Organisms animalSecondTYPE = animalSecond.getTYPE();
                //является ли животные одного вида
                lock.unlock();
                reproduction(animalFirst, animalSecond);

                eat(animalFirst, animalSecond, animalFirstTYPE, animalSecondTYPE);


                //поесть травы

                //move(animalFirst, animalSecond)

                //AnimalForAdd in Location лочим этот лист
                //sleep(100);
                lock.unlock();

                wait(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void eatPlant(Animal animalFirst, Animal animalSecond) {


    }

     private void eat(Animal animalFirst, Animal animalSecond, Organisms animalFirstTYPE, Organisms animalSecondTYPE) {
        if (animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE)) {
            whoEating(animalSecond, animalFirst, animalSecondTYPE);
        } else if (animalSecond.getMAP_OF_FOOD().containsKey(animalFirstTYPE)) {
            whoEating(animalFirst, animalSecond, animalFirstTYPE);
        }
    }

    private void whoEating(Animal animalFirst, Animal animalSecond, Organisms animalFirstTYPE) {
        if (ThreadLocalRandom.current().nextInt(100) >= animalSecond.getMAP_OF_FOOD().get(animalFirstTYPE)) {
            if (animalFirst.getWeight() >= animalSecond.getSATURATION()) {
                animalSecond.setWeight(animalSecond.getWEIGHT_MAX());
            } else {
                animalSecond.setWeight(animalSecond.getWeight() + animalFirst.getWeight());
            }
            animalFirst.setLife(false);
            fabricOfAnimals.getPoolAnimals().get(animalFirstTYPE).add(animalFirst);
        }
    }

    private void reproduction(Animal animalFirst, Animal animalSecond) {
        if (animalFirst.getTYPE() == animalSecond.getTYPE()) {
            fabricOfAnimals.createNewAnimals(this, animalFirst.getTYPE(), statistics);
        }
    }


    @Override
    public void run() {

        liveLocation();

    }
}
