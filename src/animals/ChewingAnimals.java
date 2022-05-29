package animals;

import food.EFoodType;
import graphics.ZooPanel;
import mobility.Point;


/**
 * The ChewingAnimals is an abstract class representing animals That make a chew sound.
 * @version: 3.0
 * @author : Tal vazana
 */
public abstract  class ChewingAnimals extends Animal{

    public ChewingAnimals(String name, Point location, int size, double weight, String color, int horSpeed, int verSpeed, String imgPath, ZooPanel panel){
        super(name,location,size,weight,color,horSpeed,verSpeed,imgPath,panel);
    }


    /**
     * abstract method, describe the sound that the animal makes
     */
    public abstract void chew();
    /**
     * this method use the chew method to describe the Chewing animals sound
     */
    public void makeSound() {
        chew();
    }
    /**
     * Getter for food type of the current chewing animal
     * @return Meat
     */
    public EFoodType getFoodtype() {
        return EFoodType.MEAT;
    }
}
