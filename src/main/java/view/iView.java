package view;

import data.Animal;

import java.util.List;

public interface iView {
    /**
     * отобразить главное меню
     */
    int showMenu() throws Exception;

    /**
     * добавить животное
     *
     * @return
     */
    Animal addAnimal();

    /**
     * отобразить список животных
     */

    void showAnimalList(List<Animal> animalList);

    /**
     * напечатать животное
     *
     * @param animal
     */
    void printAnimal(Animal animal);

    /**
     * отобразить панель с выбором для пользователя
     *
     * @param message - текст панели
     * @return - выбор пользователя
     */
    int showSelectBar(String message) throws Exception;

    /**
     * отобразить окно ошибки
     *
     * @param message - сообщение пользователю
     */
    void showError(String message);


}
