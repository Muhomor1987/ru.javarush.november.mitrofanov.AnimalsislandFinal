package util;

import IslandStructure.Island;
import IslandStructure.Location;
import entities.Animal;


import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Mover implements Runnable {
    Island island;

    public Mover(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        move();
    }

    synchronized private void move() {
        ConcurrentLinkedQueue<Animal> queueMoveTMP = null;

        int xMax = island.getXMax();
        int yMax = island.getYMax();
        int xTMP = 0;
        int yTMP = 0;
        while (true) {
            for (int i = 0; i < xMax; i++) {
                for (int j = 0; j < yMax; j++) {
                    Location location = island.getLocations()[i][j];
                    location.getLock().lock();
                    if (!location.getAnimalsForMoving().isEmpty()) {
                        queueMoveTMP.addAll(location.getAnimalsForMoving());
                        location.getAnimalsForMoving().clear();
                        location.getLock().unlock();
                        for (i = 0; i < location.getAnimalsForMoving().size(); i++) {
                            Animal animal = queueMoveTMP.poll();
                            if (animal != null) {
                                int direction = ThreadLocalRandom.current().nextInt(5);
                                int move = ThreadLocalRandom.current().nextInt(animal.getSPEED());
                                switch (direction) {
                                    case 0://NotMOVE

                                        island.getLocations()[i][j].getAnimalsIn().add(animal);
                                        break;
                                    case 1://LEFT
                                        if ((i - move) > 0) {
                                            xTMP = i - move;
                                        } else {
                                            xTMP = xMax - (move - i);
                                        }
                                        island.getLocations()[xTMP][j].getAnimalsIn().add(animal);
                                        break;
                                    case 2://RIGHT
                                        if ((i + move) < xMax) {
                                            xTMP = i + move;
                                        } else {
                                            xTMP = move - i;
                                        }
                                        island.getLocations()[xTMP][j].getAnimalsIn().add(animal);
                                        break;
                                    case 3://UP
                                        if ((j - move) > 0) {
                                            yTMP = j - move;
                                        } else {
                                            yTMP = yMax - (move - j);
                                        }
                                        island.getLocations()[i][yTMP].getAnimalsIn().add(animal);
                                        break;
                                    case 4://DOWN
                                        if ((j + move) < yMax) {
                                            yTMP = j + move;
                                        } else {
                                            yTMP = move - j;
                                        }
                                        island.getLocations()[i][yTMP].getAnimalsIn().add(animal);
                                        break;
                                }
                            }
                        }
                    } else {
                        location.getLock().unlock();
                    }
                }
            }
        }
    }
}
