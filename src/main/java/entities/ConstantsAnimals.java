package entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import entities.types.*;
import lombok.Getter;
import java.io.Serializable;
import java.util.HashMap;

@JsonSerialize
@Getter
public class ConstantsAnimals implements Serializable {

    private final HashMap<Organisms,Integer> eatPlantMuch = new HashMap<>(){{
        put(Organisms.HORSE, 20);
        put(Organisms.DEER, 20);
        put(Organisms.RABBIT,1);
        put(Organisms.MOUSE, 1);
        put(Organisms.GOAT, 10);
        put(Organisms.SHEEP, 10);
        put(Organisms.BOAR, 10);
        put(Organisms.BUFFALO, 30);
        put(Organisms.DUCK, 3);
        put(Organisms.CATERPILLAR, 1);


    }};
    private final HashMap<Organisms, Integer> maxAnimalForKindOfLocations = new HashMap<Organisms, Integer>() {
        {
            put(Organisms.WOLF, 30);
            put(Organisms.BOA, 30);
            put(Organisms.FOX, 30);
            put(Organisms.BEAR, 5);
            put(Organisms.EAGLE, 20);
            put(Organisms.HORSE, 20);
            put(Organisms.DEER, 20);
            put(Organisms.RABBIT, 150);
            put(Organisms.MOUSE, 500);
            put(Organisms.GOAT, 140);
            put(Organisms.SHEEP, 140);
            put(Organisms.BOAR, 50);
            put(Organisms.BUFFALO, 200);
            put(Organisms.DUCK, 10);
            put(Organisms.CATERPILLAR, 1000);
            put(Organisms.PLANT, 200);
        }
    };

    private final HashMap<Organisms, Double> maxWeight = new HashMap<>() {
        {
            put(Organisms.WOLF, 50.);
            put(Organisms.BOA, 15.);
            put(Organisms.FOX, 8.);
            put(Organisms.BEAR, 500.);
            put(Organisms.EAGLE, 6.);
            put(Organisms.HORSE, 400.);
            put(Organisms.DEER, 300.);
            put(Organisms.RABBIT, 2.);
            put(Organisms.MOUSE, 0.05);
            put(Organisms.GOAT, 60.);
            put(Organisms.SHEEP, 70.);
            put(Organisms.BOAR, 400.);
            put(Organisms.BUFFALO, 700.);
            put(Organisms.DUCK, 1.);
            put(Organisms.CATERPILLAR, 0.1);
            put(Organisms.PLANT, 1.);
        }
    };

    private final HashMap<Organisms, Integer> SPEED = new HashMap<>() {
        {
            put(Organisms.WOLF, 3);
            put(Organisms.BOA, 1);
            put(Organisms.FOX, 2);
            put(Organisms.BEAR, 2);
            put(Organisms.EAGLE, 3);
            put(Organisms.HORSE, 4);
            put(Organisms.DEER, 4);
            put(Organisms.RABBIT, 2);
            put(Organisms.MOUSE, 1);
            put(Organisms.GOAT, 3);
            put(Organisms.SHEEP, 3);
            put(Organisms.BOAR, 4);
            put(Organisms.BUFFALO, 3);
            put(Organisms.DUCK, 4);
            put(Organisms.CATERPILLAR, 0);
            put(Organisms.PLANT, 0);
        }
    };

    private final HashMap<Organisms, String> ICON = new HashMap<>() {
        {
            put(Organisms.WOLF, "\uD83D\uDC3A");
            put(Organisms.BOA, "\ud83d\udc0d");
            put(Organisms.FOX, "\ud83e\udd8a");
            put(Organisms.BEAR, "\ud83d\udc3b");
            put(Organisms.EAGLE, "\ud83e\udd85");
            put(Organisms.HORSE, "\ud83d\udc0e");
            put(Organisms.DEER, "\ud83e\udd8c");
            put(Organisms.RABBIT, "\ud83d\udc07");
            put(Organisms.MOUSE, "\ud83d\udc01");
            put(Organisms.GOAT, "\ud83d\udc10");
            put(Organisms.SHEEP, "\ud83d\udc11");
            put(Organisms.BOAR, "\ud83d\udc17");
            put(Organisms.BUFFALO, "\ud83d\udc02");
            put(Organisms.DUCK, "\ud83e\udd86");
            put(Organisms.CATERPILLAR, "\ud83d\udc1b");
            put(Organisms.PLANT, "\ud83c\udf31");
        }
    };
    private final HashMap<Organisms, Double> SATURATION = new HashMap<>() {
        {
            put(Organisms.WOLF, 8.);
            put(Organisms.BOA, 3.);
            put(Organisms.FOX, 2.);
            put(Organisms.BEAR, 80.);
            put(Organisms.EAGLE, 1.);
            put(Organisms.HORSE, 60.);
            put(Organisms.DEER, 50.);
            put(Organisms.RABBIT, 0.45);
            put(Organisms.MOUSE, 0.1);
            put(Organisms.GOAT, 10.);
            put(Organisms.SHEEP, 15.);
            put(Organisms.BOAR, 50.);
            put(Organisms.BUFFALO, 100.);
            put(Organisms.DUCK, 0.15);
            put(Organisms.CATERPILLAR, null);
            put(Organisms.PLANT, null);
        }
    };
    private final HashMap<Organisms, HashMap<Organisms, Integer>> MapOfFood = new HashMap<>() {
        {
            put(Organisms.WOLF, new HashMap<>() {
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
            put(Organisms.BOA, new HashMap<>() {
                {
                    put(Organisms.FOX, 15);
                    put(Organisms.RABBIT, 20);
                    put(Organisms.MOUSE, 40);
                    put(Organisms.DUCK, 10);
                }
            });
            put(Organisms.FOX, new HashMap<>() {
                {
                    put(Organisms.RABBIT, 70);
                    put(Organisms.MOUSE, 90);
                    put(Organisms.DUCK, 60);
                    put(Organisms.CATERPILLAR, 40);
                }
            });
            put(Organisms.BEAR, new HashMap<>() {
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
            put(Organisms.EAGLE, new HashMap<>() {
                {
                    put(Organisms.FOX, 10);
                    put(Organisms.RABBIT, 90);
                    put(Organisms.MOUSE, 90);
                    put(Organisms.DUCK, 80);
                }
            });
            put(Organisms.HORSE, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.DEER, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.RABBIT, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.MOUSE, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                    put(Organisms.CATERPILLAR, 90);
                }
            });
            put(Organisms.GOAT, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.SHEEP, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.BOAR, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                    put(Organisms.MOUSE, 50);
                    put(Organisms.CATERPILLAR, 90);
                }
            });
            put(Organisms.BUFFALO, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.DUCK, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.CATERPILLAR, new HashMap<>() {
                {
                    put(Organisms.PLANT, 100);
                }
            });
            put(Organisms.PLANT, new HashMap<>());
        }
    };

/*    private final int wolf_maxAnimalOnLocation_Wolf = 30;
    private final int maxAnimalOnLocation_Boa = 30;
    private final int maxAnimalOnLocation_Wolf = 30;
    private final int maxAnimalOnLocation_Fox = 30;
    private final int maxAnimalOnLocation_Bear = 5;
    private final int maxAnimalOnLocation_Eagle = 20;
    private final int maxAnimalOnLocation_Horse = 20;
    private final int maxAnimalOnLocation_Deer = 20;
    private final int maxAnimalOnLocation_Rabbit = 150;
    private final int maxAnimalOnLocation_Mouse = 500;
    private final int maxAnimalOnLocation_Goat = 140;
    private final int maxAnimalOnLocation_Sheep = 140;
    private final int maxAnimalOnLocation_Boar = 50;
    private final int maxAnimalOnLocation_Duck = 10;
    private final int maxAnimalOnLocation_Buffalo = 200;
    private final int maxAnimalOnLocation_Caterpillar = 1000;
    private final int maxAnimalOnLocation_Plant = 200;*/


/*    private final Wolf WOLF =
            new Wolf(50, 3, 8, Organisms.WOLF, "\uD83D\uDC3A",
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
    private final Boa BOA =
            new Boa(15, 1, 3, Organisms.BOA, "\ud83d\udc0d",
                    new HashMap<>() {
                        {
                            put(Organisms.FOX, 15);
                            put(Organisms.RABBIT, 20);
                            put(Organisms.MOUSE, 40);
                            put(Organisms.DUCK, 10);
                        }
                    });
    private final Fox FOX =
            new Fox(8, 2, 2, Organisms.FOX, "\ud83e\udd8a",
                    new HashMap<>() {
                        {
                            put(Organisms.RABBIT, 70);
                            put(Organisms.MOUSE, 90);
                            put(Organisms.DUCK, 60);
                            put(Organisms.CATERPILLAR, 40);
                        }
                    });
    private final Bear BEAR =
            new Bear(500, 2, 80, Organisms.BEAR, "\ud83d\udc3b",
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
    private final Eagle EAGLE =
            new Eagle(6, 3, 1, Organisms.EAGLE, "\ud83e\udd85",
                    new HashMap<>() {
                        {
                            put(Organisms.FOX, 10);
                            put(Organisms.RABBIT, 90);
                            put(Organisms.MOUSE, 90);
                            put(Organisms.DUCK, 80);
                        }
                    });
    private final Horse HORSE =
            new Horse(400, 4, 60, Organisms.HORSE, "\ud83d\udc0e",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Deer DEER =
            new Deer(300, 4, 50, Organisms.DEER, "\ud83e\udd8c",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Rabbit RABBIT =
            new Rabbit(2, 2, 0.45, Organisms.RABBIT, "\ud83d\udc07",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Mouse MOUSE =
            new Mouse(0.05, 1, 0.1, Organisms.MOUSE, "\ud83d\udc01",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                            put(Organisms.CATERPILLAR, 90);
                        }
                    });
    private final Goat GOAT =
            new Goat(60, 3, 10, Organisms.GOAT, "\ud83d\udc10",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Sheep SHEEP =
            new Sheep(0.05, 1, 0.1, Organisms.SHEEP, "\ud83d\udc11",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Boar BOAR =
            new Boar(400, 2, 50, Organisms.BOAR, "\ud83d\udc17",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                            put(Organisms.MOUSE, 50);
                            put(Organisms.CATERPILLAR, 90);
                        }
                    });
    private final Buffalo BUFFALO =
            new Buffalo(700, 3, 100, Organisms.BUFFALO, "\ud83d\udc02",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Duck DUCK =
            new Duck(1, 4, 15, Organisms.DUCK, "\ud83e\udd86",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Catterpillar CATERPILLAR =
            new Catterpillar(1, 4, 15, Organisms.CATERPILLAR, "\ud83d\udc1b",
                    new HashMap<>() {
                        {
                            put(Organisms.PLANT, 100);
                        }
                    });
    private final Plant PLANT =
            new Plant(1, 0, 0, Organisms.PLANT, "\ud83c\udf31",
                    new HashMap<>());*/

}
