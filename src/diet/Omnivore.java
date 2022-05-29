package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;


public class Omnivore extends Carnivore{

    private IDiet herbivore;
    private IDiet Carnivore;
    public Omnivore(){
        this.herbivore = new Herbivore();
        this.Carnivore=new Carnivore();

    }

    public double eat(Animal animal, IEdible food) {
        EFoodType foodType = food.getFoodtype();
        if (!(animal == food)) {
            if (foodType == EFoodType.MEAT) {
                return super.eat(animal,food);
            } else if(foodType == EFoodType.VEGETABLE){
                return herbivore.eat(animal, food);
            }
        }
        return 0;
    }
    /**
     * toString method for Omnivore
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "[Omnivore]";
    }

    @Override
    public boolean canEat(EFoodType food) {
        return (food==EFoodType.MEAT||food==EFoodType.VEGETABLE);

    }
}
