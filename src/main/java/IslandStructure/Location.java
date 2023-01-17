package IslandStructure;

import entities.Animal;
import entities.Organisms;
import lombok.Getter;
import lombok.Setter;
import util.FabricOfAnimals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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
    ArrayList<Animal> animalsOnLocation = new ArrayList<>();
    ArrayBlockingQueue<Animal> animalsForMoving = new ArrayBlockingQueue<>();
    ArrayList<Animal> plantSet = new ArrayList<>();

    HashMap<Organisms, Integer> countAnimalsMapOnLocation;

    //засыпает на 2 секунды через константа жизненый цикл
    synchronized public void liveLocation() {
        while (true) {//заменить на wait
            while (!isLiveLocation) {
                while (animalsOnLocation.size() > 1) {
                    Animal animalFirst = animalsOnLocation.remove(0);
                    Animal animalSecond = animalsOnLocation.remove(0);
                    Organisms animalFirstTYPE = animalFirst.getTYPE();
                    Organisms animalSecondTYPE = animalSecond.getTYPE();
                    //является ли животные одного вида
                    reproduction(animalFirst, animalSecond);
                    eat(animalFirst, animalSecond, animalFirstTYPE, animalSecondTYPE);


                    //поесть травы
                    //sleep(100);
                    //move(animalFirst, animalSecond)
                    //AnimalForAdd in Location лочим этот лист
                }
            }
        }
    }
        synchronized private void eatPlant(Animal animal1, Animal animal2) {

        }
    synchronized private void eat(Animal animalFirst, Animal animalSecond, Organisms animalFirstTYPE, Organisms animalSecondTYPE) {
        if (animalFirst.getMAP_OF_FOOD().containsKey(animalSecondTYPE)) {
            if (ThreadLocalRandom.current().nextInt(100) >= animalFirst.getMAP_OF_FOOD().get(animalSecondTYPE)) {
                if(animalSecond.getWeight()>=animalFirst.getSATURATION()){
                    animalFirst.setWeight(animalFirst.getWEIGHT_MAX());
                }
                else {
                    animalFirst.setWeight(animalFirst.getWeight()+animalSecond.getWeight());
                }
                animalFirst.setWeight(animalFirst.getWeight() + animalSecond.getWeight());
                animalSecond.setLife(false);
                fabricOfAnimals.getPoolAnimals().get(animalSecondTYPE).add(animalSecond);//Добавляем животное в пулл фабрики

            }
        }
        if (animalSecond.getMAP_OF_FOOD().containsKey(animalFirstTYPE)) {
            if (ThreadLocalRandom.current().nextInt(100) >= animalSecond.getMAP_OF_FOOD().get(animalFirstTYPE)) {

                fabricOfAnimals.getPoolAnimals().get(animalFirstTYPE).add(animalFirst);
                animalsForMoving.add(animalFirst);
                
            }
        }
    }

    synchronized private void reproduction(Animal animalFirst, Animal animalSecond) {
        if (animalFirst.getTYPE() == animalSecond.getTYPE()) {
            fabricOfAnimals.createNewAnimals(this, animalFirst.getTYPE(), statistics);
        }
    }


    @Override
    public void run() {

        liveLocation();

    }
}
