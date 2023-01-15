package entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.util.HashMap;
@JsonSerialize
@Getter
public class ConstantsAnimals {
    private final int maxAnimalOnLocation_Wolf=30;
    private final int maxAnimalOnLocation_Boa=30;
    private final int maxAnimalOnLocation_Fox=30;
    private final int maxAnimalOnLocation_Bear=5;
    private final int maxAnimalOnLocation_Eagle=20;
    private final int maxAnimalOnLocation_Horse=20;
    private final int maxAnimalOnLocation_Deer=20;
    private final int maxAnimalOnLocation_Rabbit=150;
    private final int maxAnimalOnLocation_Mouse=500;
    private final int maxAnimalOnLocation_Goat=140;
    private final int maxAnimalOnLocation_Sheep=140;
    private final int maxAnimalOnLocation_Boar=50;
    private final int maxAnimalOnLocation_Duck=10;
    private final int maxAnimalOnLocation_Buffalo=200;
    private final int maxAnimalOnLocation_Caterpillar=1000;
    private final int maxAnimalOnLocation_Plant=200;

    private final Animal WOLF =
            new Animal(50, 3, 8, Organisms.WOLF, "\uD83D\uDC3A",
                    new HashMap<>() {
                        {
                            put(Organisms.HORSE, 10);
                            put(Organisms.DEER, 15);
                            put(Organisms.RABBIT, 60);
                            put(Organisms.MOUSE, 80);
                            put(Organisms.GOAT, 60);
                            put(Organisms.BOAR, 15);
                            put(Organisms.BUFFALO, 10);
                            put(Organisms.DUCK, 40);
                        }
                    });
    private final Animal BOA =
            new Animal(15, 1, 3, Organisms.BOA, "\ud83d\udc0d",
                    new HashMap<>() {
                        {
                            put(Organisms.FOX, 15);
                            put(Organisms.RABBIT, 20);
                            put(Organisms.MOUSE, 40);
                            put(Organisms.DUCK, 10);
                        }
                    });
    private final Animal FOX =
            new Animal(8, 2, 2, Organisms.FOX, "\ud83e\udd8a",
                    new HashMap<>() {
                        {
                            put(Organisms.RABBIT, 70);
                            put(Organisms.MOUSE, 90);
                            put(Organisms.DUCK, 60);
                            put(Organisms.CATERPILLAR, 40);
                        }
                    });
    private final Animal BEAR =
            new Animal(500, 2, 80, Organisms.BEAR, "\ud83d\udc3b",
                    new HashMap<>() {
                        {
                            put(Organisms.BOA, 80);
                            put(Organisms.HORSE, 40);
                            put(Organisms.DEER, 80);
                            put(Organisms.RABBIT, 80);
                            put(Organisms.MOUSE, 90);
                            put(Organisms.GOAT, 70);
                            put(Organisms.BOAR, 50);
                            put(Organisms.BUFFALO, 20);
                            put(Organisms.DUCK, 10);
                        }
                    });
    private final Animal EAGLE =
            new Animal(6, 3, 1, Organisms.EAGLE, "\ud83e\udd85",
                    new HashMap<>() {
                        {
                            put(Organisms.FOX, 10);
                            put(Organisms.RABBIT, 90);
                            put(Organisms.MOUSE, 90);
                            put(Organisms.DUCK, 80);
                        }
                    });
    private final Animal HORSE =
            new Animal(400, 4, 60, Organisms.HORSE, "\ud83d\udc0e",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Animal DEER =
            new Animal(300, 4, 50, Organisms.DEER, "\ud83e\udd8c",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Animal RABBIT =
            new Animal(2, 2, 0.45, Organisms.RABBIT, "\ud83d\udc07",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Animal MOUSE =
            new Animal(0.05, 1, 0.1, Organisms.MOUSE, "\ud83d\udc01",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                            put(Organisms.CATERPILLAR, 90);
                        }
                    });
    private final Animal GOAT =
            new Animal(60, 3, 10, Organisms.GOAT, "\ud83d\udc10",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Animal SHEEP =
            new Animal(0.05, 1, 0.1, Organisms.SHEEP, "\ud83d\udc11",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Animal BOAR =
            new Animal(400, 2, 50, Organisms.BOAR, "\ud83d\udc17",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                            put(Organisms.MOUSE, 50);
                            put(Organisms.CATERPILLAR, 90);
                        }
                    });
    private final Animal BUFFALO =
            new Animal(700, 3, 100, Organisms.BUFFALO, "\ud83d\udc02",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Animal DUCK =
            new Animal(1, 4, 15, Organisms.DUCK, "\ud83e\udd86",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Animal CATERPILLAR =
            new Animal(1, 4, 15, Organisms.CATERPILLAR, "\ud83d\udc1b",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Animal PLANT =
            new Animal(1, 0, 0, Organisms.PLANT, "\ud83c\udf40",
                    new HashMap<>() {
                        {

                        }
                    });
}
