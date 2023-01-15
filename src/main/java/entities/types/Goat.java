package entities.types;

import entities.Animal;
import entities.Organisms;

import java.util.HashMap;

public class Goat extends Animal {
    public Goat(double WEIGHT_MAX, int SPEED, double SATURATION, Organisms TYPE, String icon, HashMap<Organisms, Integer> MAP_OF_FOOD) {
        super(WEIGHT_MAX, SPEED, SATURATION, TYPE, icon, MAP_OF_FOOD);
    }
}
