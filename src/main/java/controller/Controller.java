package controller;

import data.Animal;
import data.Command;
import data.PackAnimal;
import data.Pet;
import data.packAnimal.Camel;
import data.packAnimal.Donkey;
import data.packAnimal.Horse;
import data.pets.Cat;
import data.pets.Dog;
import data.pets.Hamster;
import model.Model;
import model.iModel;
import services.SearchService;
import view.View;
import view.iView;

import java.util.List;
import java.util.Scanner;

public class Controller {
    iModel model;
    iView view;
    SearchService searchService;

    public Controller() {
        model = new Model();
        view = new View();
        searchService = new SearchService();
    }

    /**
     * запуск программы
     */
    public void run() {
        boolean flag = true;
        while (flag) {
            int userInput = 0;
            try {
                userInput = view.showMenu();
            } catch (Exception e) {
                view.showError("Введите число!");
                continue;
            }

            switch (userInput) {
                case 1:
                    System.out.println("\nДобавление нового животного \n-------------------------------------");
                    Animal newAnimal = addAnimalController();
                    if (newAnimal != null) {
                        model.addNewAnimal(newAnimal);
                    }
                    System.out.println("-------------------------------------");
                    break;

                case 2:
                    List<Animal> allAnimals = model.getAllAnimal();
                    List<Animal> filteredAnimalsList = selectSearchType(allAnimals);
                    if (filteredAnimalsList != null) {
                        view.showAnimalList(filteredAnimalsList);
                        Animal animal = takeAnimal(filteredAnimalsList);
                        if (animal != null) {
                            addCommand(animal);

                        }

                    } else {
                        view.showError("Ничего не найдено");
                    }
                    break;

                case 3:
                    flag = false;
                    break;

                default:
                    view.showError(String.format("Неверное число %d. Выберите номер из списка", userInput));
                    break;
            }
        }
    }

    /**
     * выбрать животное из списка
     * @param animalList - список животных
     */
    private Animal takeAnimal(List<Animal> animalList) {
        boolean flag = true;
        while (flag) {
            int input = userInputCheck("Чтобы узнать подробнее о животном введите его  номер. Чтобы выйти введите 0");
            if (input == 0) {
                flag = false;
            } else if (input > 0 && input <= animalList.size()) {
                return animalList.get(input - 1);
            }
        }
        return null;

    }

    /**
     * Выбор типа поиска в списке животных
     * @param allAnimals - список всех животных
     * @return - отфильтрованный список
     */
    private List<Animal> selectSearchType(List<Animal> allAnimals) {
        boolean flag = true;
        int userInput = -1;

        while (flag) {
            try {
                System.out.println("------------");
                String nextSelectBar = "1. Поиск по имени\n" +
                        "2. Поиск по классу\n" +
                        "3. Отменить создание и выйти.";
                userInput = view.showSelectBar(nextSelectBar);
                System.out.println("------------");
            } catch (Exception e) {
                view.showError("Введите число!");
                continue;
            }
            switch (userInput) {
                case 1:
                    Scanner scanner = new Scanner(System.in);
                    String nameAnimal = scanner.nextLine();
                    List<Animal> resultList = searchService.searchByName(allAnimals, nameAnimal);
                    return resultList;
                case 2:
                    int input = userInputCheck("1. домашнее животное\n" +
                            "2. Вьючное животное.\n" +
                            "3. Назад");
                    List<Animal> resultSearchByClass;

                    switch (input) {
                        case 1:
                            resultSearchByClass = searchService.searchByClass(allAnimals, "Домашнее животное");
                            return resultSearchByClass;

                        case 2:
                            resultSearchByClass = searchService.searchByClass(allAnimals, "Вьючное животное");
                            return resultSearchByClass;

                        case 3:
                            return null;

                        default:
                            view.showError(String.format("Неверное число %d. Выберите номер из списка", userInput));
                            break;
                    }
                    break;

                case 3:
                    break;
            }

        }
        return null;
    }

    /**
     * добавление нового животного
     * @return -  созданное животное
     */
    private Animal addAnimalController() {
        boolean flag = true;
        int userInput = -1;

        while (flag) {
            try {

                String nextSelectBar = "1. Домашнее животное\n" +
                        "2. Вьючное животное\n" +
                        "3. Отменить создание и выйти.";
                userInput = view.showSelectBar(nextSelectBar);

            } catch (Exception e) {
                view.showError("Введите число!");
                continue;
            }
            switch (userInput) {
                case 1:
                    Pet newPet = createPet();
                    if (newPet == null) {
                        return null;
                    } else {
                        System.out.print("Введите имя для животного:\t");
                        Scanner scanner = new Scanner(System.in);
                        newPet.setName(scanner.nextLine());
                        System.out.print("Введите дату рождения в формате гггг.мм.дд:\t");
                        String dateOfBirth = scanner.nextLine();
                        newPet.setDateOfBirth(dateOfBirth);
                    }
                    return newPet;

                case 2:
                    PackAnimal newAnimal = createPackAnimal();
                    if (newAnimal == null) {
                        return null;
                    } else {
                        System.out.print("Введите имя для животного:\t");
                        Scanner scanner = new Scanner(System.in);
                        newAnimal.setName(scanner.nextLine());
                        System.out.print("Введите дату рождения в формате гггг.мм.дд:\t");
                        String dateOfBirth = scanner.nextLine();
                        newAnimal.setDateOfBirth(dateOfBirth);
                    }

                    return newAnimal;
                case 3:
                    return null;
                default:
                    view.showError(String.format("Неверное число %d. Выберите номер из списка", userInput));
                    break;
            }
        }
        return null;
    }

    /**
     * добавить животному команду
     * @param animal
     */
    public void addCommand(Animal animal) {
        int userInput = -1;
        boolean flag = true;
        while (flag) {
            view.printAnimal(animal);
            userInput = userInputCheck("Введите:\n1. чтобы добавить команду\n2. Чтобы вернуться в главное меню");
            switch (userInput) {
                case 1:
                    int userSelect = userInputCheck("1. GO\n2. STOP\n3. RUN_AWAY\n4. COME_UP\n5. LIE\n6. JUMP\n7. TRUP\n8.STAND_UP\n9. Отмена");
                    switch (userSelect) {
                        case 1:
                            animal.getCommands().addCommand(Command.GO);
                            break;
                        case 2:
                            animal.getCommands().addCommand(Command.STOP);
                            break;
                        case 3:
                            animal.getCommands().addCommand(Command.RUN_AWAY);
                            break;
                        case 4:
                            animal.getCommands().addCommand(Command.COME_UP);
                            break;
                        case 5:
                            animal.getCommands().addCommand(Command.LIE);
                            break;
                        case 6:
                            animal.getCommands().addCommand(Command.JUMP);
                            break;
                        case 7:
                            animal.getCommands().addCommand(Command.TRUP);
                            break;
                        case 8:
                            animal.getCommands().addCommand(Command.STAND__UP);
                            break;
                        case 9:
                            break;
                    }
                    break;
                case 2:
                    flag = false;
                    break;
            }
        }

    }

    /**
     * создание домашнего животного
     *
     * @return тип животного
     */
    private Pet createPet() {
        boolean flag = true;
        int userInput = -1;


        while (flag) {
            try {
                String nextSelectBar = "\n1. Кошка\n" +
                        "2. Собака\n" +
                        "3. Хомяк\n" +
                        "4. Отменить создание и выйти.";
                userInput = view.showSelectBar(nextSelectBar);
            } catch (Exception e) {
                view.showError("Введите число!");
                continue;
            }
            switch (userInput) {
                case 1:
                    return new Cat();
                case 2:
                    return new Dog();
                case 3:
                    return new Hamster();
                case 4:
                    flag = false;
                    break;
                default:
                    view.showError(String.format("Неверное число %d. Выберите номер из списка", userInput));
                    break;
            }

        }
        return null;

    }

    /**
     * создание вьючного животного
     *
     * @return тип животного
     */
    private PackAnimal createPackAnimal() {
        boolean flag = true;
        int userInput = -1;

        while (flag) {
            try {
                String nextSelectBar = "\n1. Лошадь\n" +
                        "2. Осел\n" +
                        "3. Верблюд\n" +
                        "4. Отменить создание и выйти.";
                userInput = view.showSelectBar(nextSelectBar);
            } catch (Exception e) {
                view.showError("Введите число!");
                continue;
            }
            switch (userInput) {
                case 1:
                    return new Horse();
                case 2:
                    return new Donkey();
                case 3:
                    return new Camel();
                case 4:
                    flag = false;
                    break;
                default:
                    view.showError(String.format("Неверное число %d. Выберите номер из списка", userInput));
                    break;
            }

        }
        return null;

    }


    /**
     * проверка пользовательского ввода
     * @param message
     * @return
     */
    private int userInputCheck(String message) {
        int userInput = -1;
        boolean flag = true;
        while (flag) {
            try {
                System.out.println("------------");
                userInput = view.showSelectBar(message);
                System.out.println("------------");
            } catch (Exception e) {
                view.showError("Введите число!");
                continue;
            }
            flag = false;

        }
        return userInput;
    }


}
