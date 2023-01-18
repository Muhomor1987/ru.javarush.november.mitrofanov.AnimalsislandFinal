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
                lock.lock();
                eat(animalFirst, animalSecond, animalFirstTYPE, animalSecondTYPE);
                lock.unlock();

                //поесть травы

                //move(animalFirst, animalSecond)

                //AnimalForAdd in Location лочим этот лист
                //sleep(100);


                wait(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void eat(Animal animalFirst, Animal animalSecond, Organisms animalFirstTYPE, Organisms animalSecondTYPE) {
        if (animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE) || animalSecond.getMAP_OF_FOOD().containsKey(animalFirstTYPE)) {
            if (animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE)) {
                whoEating(animalSecond, animalFirst, animalSecondTYPE);
            }
            if (animalSecond.getMAP_OF_FOOD().containsKey(animalFirstTYPE)) {
                whoEating(animalFirst, animalSecond, animalFirstTYPE);
            }
        } else {
            eatPlant(animalFirst);
            eatPlant(animalSecond);
        }
    }

    private  void eatPlant(Animal animal){
        if (animal.getMAP_OF_FOOD().containsKey(Organisms.PLANT)) {
            if (animal.getWeight() <= 1) {
                animal.setWeight(animal.getWEIGHT_MAX());
                Animal plant = null;
                if (!plantSet.isEmpty()) {
                    plant = plantSet.poll();
                    animal.setWeight(animal.getWEIGHT_MAX());
                }
                fabricOfAnimals.getPoolAnimals().get(Organisms.PLANT).add(plant);
            } else {
                if (animal.getSATURATION() <= plantSet.size()) {
                    animal.setWeight(animal.getWEIGHT_MAX());
                    for (int i = 0; i < animal.getSATURATION(); i++) {
                        plantSet.poll();
                    }
                }
                else {
                    animal.setWeight(animal.weight+plantSet.size());
                    fabricOfAnimals.getPoolAnimals().get(Organisms.PLANT).addAll(plantSet);
                    plantSet.clear();
                }
            }
        }
    }
    private void whoEating(Animal animalFirst, Animal animalSecond, Organisms animalFirstTYPE) {
        if (ThreadLocalRandom.current().nextInt(100) >= animalSecond.getMAP_OF_FOOD().get(animalFirstTYPE)) {
            if (animalFirst.getWeight() >= animalSecond.getSATURATION()) {
                animalSecond.setWeight(animalSecond.getWEIGHT_MAX());
            } else {
                animalSecond.setWeight(animalSecond.getWeight() + animalFirst.getWeight());
            }
            countAnimalsMapOnLocation.put(animalFirstTYPE, countAnimalsMapOnLocation.get(animalFirstTYPE) - 1);
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
