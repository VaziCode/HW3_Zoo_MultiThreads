package animals;

import food.EFoodType;
import graphics.ZooPanel;
import mobility.Point;

/**
 * Roaring animals, an abstract class representing animals that make a roar sound.
 * @version 2.0
 * @author Tal vazana

 */

public abstract class RoaringAnimal  extends Animal{
    /**
     * RoaringAnimals constructor
     * @param name String representation of the animal name.
     * @param location Point object representing the current location of the animal.
     */

    public RoaringAnimal(String name, Point location, int size, double weight, String color, int horSpeed, int verSpeed, String imgPath, ZooPanel panel){
        super(name,location,size,weight,color,horSpeed,verSpeed,imgPath,panel);
    }

    /**
     * abstract method, describe the sound that the animal makes
     */
    public abstract void roar();
    /**
     * This sound uses the roar method to describe the roaring animals sound
     */
    public void makeSound() {
            roar();
    }

    //getters
    /**
     * Getter for the food type of the animal
     * @return MEAT
     */
    public EFoodType getFoodtype() {
        return EFoodType.MEAT;
    }




}
