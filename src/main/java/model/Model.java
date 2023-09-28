package model;

import data.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Model implements iModel {
    /**
     * счетчик id
     */
    private int animalID = 0;

    /**
     * список животных
     */
    private List<Animal> animals = new ArrayList<>();


    @Override
    public List<Animal> getAllAnimal() {
        return animals;
    }

    @Override
    public void addNewAnimal(Animal animal) {
        animal.setId(++animalID);
        animals.add(animal);
    }
}
