package services;

import data.Animal;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    /**
     * поиск животного по имени
     *
     * @param animals список в котором будет производиться поиск
     * @param name    - искомое имя
     * @return - список подходящих животных
     */
    public List<Animal> searchByName(List<Animal> animals, String name) {
        List<Animal> resulList = new ArrayList<>();
        for (Animal animal :
                animals) {
            if (animal.getName().equals(name.trim())) {
                resulList.add(animal);
            }
        }
        return resulList.size() == 0 ? null : resulList;
    }

    /**
     * поиск животного по классу
     *
     * @param animals     список в котором будет производиться поиск
     * @param classAnimal - название класса
     * @return - список подходящих животных
     */

    public List<Animal> searchByClass(List<Animal> animals, String classAnimal) {
        List<Animal> resulList = new ArrayList<>();
        for (Animal animal :
                animals) {
            if (animal.getAnimalClass().equals(classAnimal.trim())) {
                resulList.add(animal);
            }
        }
        return resulList.size() == 0 ? null : resulList;
    }


}
