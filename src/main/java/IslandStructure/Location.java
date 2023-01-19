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

    private ConcurrentLinkedQueue<Animal> animalsOnLocation = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Animal> animalsForMoving = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Animal> animalsIn = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Animal> plantSet = new ConcurrentLinkedQueue<>();

    HashMap<Organisms, Integer> countAnimalsMapOnLocation;


    //засыпает на 2 секунды через константа жизненый цикл
    public void liveLocation() {
        while (true) {
            try {
                Animal animalFirst = null;
                Animal animalSecond = null;

                lock.lock();
                if (animalsOnLocation.isEmpty()) {
                    while (animalsOnLocation.isEmpty()) {
                        lock.unlock();
                        wait(1000);
                    }
                } else {
                    lock.lock();
                    animalFirst = animalsOnLocation.poll();
                }
                if (animalsOnLocation.isEmpty()) {
                    while (animalsOnLocation.isEmpty()) {
                        lock.unlock();
                        wait(1000);
                    }
                } else {
                    lock.lock();
                    animalSecond = animalsOnLocation.poll();
                }
                lock.unlock();
                assert animalFirst != null;
                Organisms animalFirstTYPE = animalFirst.getTYPE();
                assert animalSecond != null;
                Organisms animalSecondTYPE = animalSecond.getTYPE();
                //является ли животные одного вида
                reproduction(animalFirst, animalSecond);
                lock.lock();
                eat(animalFirst, animalSecond, animalFirstTYPE, animalSecondTYPE);
                wait(1000);
                lock.lock();
                animalsOnLocation.addAll(animalsIn);
                wait(100);
                //move(animalFirst, animalSecond)
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                lock.unlock();
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

    private void eatPlant(Animal animal) {
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
                } else {
                    animal.setWeight(animal.weight + plantSet.size());
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
