package view;

import data.Animal;

import java.util.List;
import java.util.Scanner;

public class View implements iView {
    /**
     * отобразить главное меню
     *
     * @return выбор пользователя, в какое окно перейти
     */
    @Override
    public int showMenu() throws Exception {
        System.out.println("Главное меню");
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Добавить животное");
        System.out.println("2. Поиск");
        System.out.println("3. Завершить работу");
        System.out.println("---------------------------------------------------------");
        System.out.print("Введите номер операции:  ");

        int userInput = -1;

        try {
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextInt();
        } catch (Exception e) {
            throw new Exception();
        }

        return userInput;
    }

    /**
     * добавить животное
     *
     * @return
     */
    @Override
    public Animal addAnimal() {
        return null;
    }

    /**
     * отобразить список животных
     *
     * @param animalList - список животных
     */
    @Override
    public void showAnimalList(List<Animal> animalList) {
        for (int i = 0; i < animalList.size(); i++) {
            Animal currentAnimal = animalList.get(i);
            System.out.printf("%d. %s - %s(%s)\n", i + 1, currentAnimal.getName(), currentAnimal.getAnimalType(), currentAnimal.getAnimalClass());
        }
    }

    /**
     * отобразить всю информацию по животному
     *
     * @param animal
     */
    @Override
    public void printAnimal(Animal animal) {
        System.out.println("\nid: " + animal.getId());
        System.out.println("--------------------------------");
        System.out.println("Имя - " + animal.getName());
        System.out.println("Вид - " + animal.getAnimalType());
        System.out.println("Класс - " + animal.getAnimalClass());
        System.out.println("Дата рождения - " + animal.getDateOfBirth());
        System.out.println("Список команд - " + animal.getCommands().getCommands().toString());
        System.out.println("--------------------------------");
    }

    /**
     * отобразить панель выбора
     *
     * @param message - текст панели
     * @return - выбор пользователя
     * @throws Exception
     */
    @Override
    public int showSelectBar(String message) throws Exception {
        System.out.println(message);
        System.out.print(": ");
        int userInput = 0;
        try {
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextInt();
        } catch (Exception e) {
            throw new Exception();
        }

        return userInput;
    }

    /**
     * показать сообщение об ошибке
     *
     * @param message - текст сообщения
     */
    @Override
    public void showError(String message) {

        int SystemMessageLength = "\nОшибка!!!".length();
        for (int i = 0; i < message.length() + SystemMessageLength; i++) {
            System.out.print("=");
        }
        System.out.println("\nОшибка!!!" + message);
        for (int i = 0; i < message.length() + SystemMessageLength; i++) {
            System.out.print("=");
        }
        System.out.println("\n");

    }

}
