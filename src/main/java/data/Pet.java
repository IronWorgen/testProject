package data;

import lombok.Data;

@Data
public abstract class Pet extends Animal {

    public Pet() {
        super.setAnimalClass("Домашнее животное");

    }

}
