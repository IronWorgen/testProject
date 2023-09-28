package data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Animal {

    private int id;
    private String dateOfBirth;
    private String name;
    private String animalType;
    private String animalClass;
    private PetCommands commands = new PetCommands();

    public Animal(String dateOfBirth, String name, PetCommands commands) {

        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.commands = commands;
    }

    /**
     * добавить команду
     */
    public void addCommand() {

    }

}
