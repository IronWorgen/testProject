package model;

import data.Animal;

import java.util.List;

public interface iModel {


    /**
     * получить список всех животных в модель
     * @return - список животных
     */
    List<Animal> getAllAnimal();

    /**
     * добавить новое животное в модель
     * @param animal
     */
    void addNewAnimal(Animal animal);

}
