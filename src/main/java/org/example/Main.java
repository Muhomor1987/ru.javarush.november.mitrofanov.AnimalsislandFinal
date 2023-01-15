package org.example;

import entities.ConstantsAnimals;
import util.Mapper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConstantsAnimals constantsAnimals= new ConstantsAnimals();
        Mapper mapper = new Mapper();
        mapper.writeSettingsFile(constantsAnimals);


    }
}