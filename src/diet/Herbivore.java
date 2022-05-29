package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 * Herbivore class--> represent the VEGETABLE eating animals
 * @version 2.0
 * @author Tal vazana
 * @see animals.ChewingAnimals
 */
public class Herbivore implements IDiet{

    /**
     * checks if the animal can eat the food
     * @param food , EFoodType Represents the type of food that the animal constitutes.
     * @return boolean value - true if the food is vegetable.
     */
    public boolean canEat(EFoodType food){
        boolean isSuccess =  food == EFoodType.VEGETABLE;
        return isSuccess;
    }

    /**

     * The eat method  use the IEdible interface, checks if the food type can be eaten and calculate the
     * weight gaining of the animal by eating
     * @param animal The animal we want to feed
     * @param food  IEdible Represents an animal that can be eaten.
     * @return A double value that represents the weight gained after eating.
     */
    public double eat(Animal animal, IEdible food){
        if(this.canEat(food.getFoodtype()) && !(animal == food)){
            return animal.getWeight() * 0.07;
        }
        return 0;
    }

    /**
     * toString method for Herbivore
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "[Herbivore]";
    }

}
