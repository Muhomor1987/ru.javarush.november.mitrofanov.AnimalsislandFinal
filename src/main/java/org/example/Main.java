package org.example;

import IslandStructure.Island;
import IslandStructure.Statistics;
import entities.ConstantsAnimals;
import util.*;

import java.io.IOException;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

//        Mapper mapper = new Mapper();
//        mapper.writeSettingsFile(constantsAnimals);
        ConstantsAnimals constantsAnimals = new ConstantsAnimals();
        Island island = new Island("Terra", constantsAnimals);
        Statistics statistics = new Statistics(constantsAnimals);
        FabricOfAnimals fabricOfAnimals = new FabricOfAnimals(constantsAnimals, island, statistics);

        CreatorLocations creatorLocations = new CreatorLocations(fabricOfAnimals, island, constantsAnimals, statistics);

        ExecutorService executorService = Executors.newWorkStealingPool();
        executorService.execute(creatorLocations);
        executorService.execute(creatorLocations);
        executorService.execute(creatorLocations);
        System.out.println(executorService.awaitTermination(2,TimeUnit.SECONDS));
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(statistics, 2, 3, TimeUnit.SECONDS);
        Mover mover = new Mover(island);
        LifeCycle lifeCycle = new LifeCycle(island,fabricOfAnimals,statistics);
        scheduledExecutorService.schedule(mover,6,TimeUnit.SECONDS);
        scheduledExecutorService.schedule(lifeCycle,8,TimeUnit.SECONDS);

        Thread.sleep(5000);
        for (int i = 0; i < island.getXMax(); i++) {
            for (int j = 0; j < island.getYMax(); j++) {
                executorService.execute(island.getLocations()[i][j]);
            }
        }

        System.out.println(island.getLocations()[5][5].getAnimalsOnLocation().get(2));



        Counter counter = new Counter(island);
        //scheduledExecutorService.scheduleAtFixedRate(counter,2,2,TimeUnit.SECONDS);


//        scheduledExecutorService.schedule(creatorLocations, 10, TimeUnit.MILLISECONDS);



//        for (int i = 0; i < island.getXMax(); i++) {
//            for (int j = 0; j < island.getYMax(); j++) {
//                System.out.print("X =" + i + " " + "Y=" + j + "____");
//                System.out.print("___" + Thread.currentThread().getName() + "___");
//                System.out.println(island.getLocations()[i][j].getAnimalsOnLocation().size());
//
//            }
//        }
    }
}