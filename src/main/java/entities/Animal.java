package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
public class Animal {
    public String name;

    public double weight;

    //final
    public final double WEIGHT_MAX;
    private final int SPEED;
    private final double SATURATION;
    private final int locationMax;

    private final HashMap<Organisms, Integer> MAP_OF_FOOD;

}
