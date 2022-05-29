package diet;

import food.EFoodType;
import food.IEdible;
import animals.*;

/**
 * Carnivore class --> Represents animals that eat meat
 * @version 3.0
 * @author Tal vazana
 * @see RoaringAnimal
 */
public class Carnivore implements IDiet{

    /**
     * checks if the animal can eat the food
     * @param food , EFoodType Represents the type of food that the animal constitutes.
     * @return boolean value - true if the food is meat.
     */
    public boolean canEat(EFoodType food){
        return   food == EFoodType.MEAT;
    }
    /**
     * if the animal can eat the food, and the food and the animal aren't pointing to the same animal
     * returns the weight the eating animal has gained in the process of eating, else returns 0
     * @param animal The animal we want to feed
     * @param food  IEdible Represents an animal that can be eaten.
     * @return A double value that represents the weight gained after eating.
     */
    @Override
    public double eat(Animal animal, IEdible food){
        if(this.canEat(food.getFoodtype()) && !(animal == food)){
            return animal.getWeight() * 0.10;
        }
        return 0;
    }
    /**
     * toString method for Carnivore
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "[Carnivore]";
    }

}
