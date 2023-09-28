package data;

import lombok.Data;

@Data
public abstract class PackAnimal extends Animal{


    public PackAnimal(){
        super.setAnimalClass("Вьючное животное");

    }

}
