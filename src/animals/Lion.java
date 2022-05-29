package animals;

import diet.Carnivore;
import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
import mobility.Point;

import java.util.Random;

/**
 * Lion class--> Lion is a roaring animal type
 * @version 1.0
 * @author
 * @see RoaringAnimal
 */
public class Lion extends RoaringAnimal {
    private static final String PATH_PREFIX = "lio";
    private static final String IMG1 = "/elf_b_1.png";
    private static final EFoodType LION = EFoodType.NOTFOOD;
    private static final double DEFAULT_SIZE = 0.8;
    private static final Point STARTING_POSITION = new Point(20,0);
    private int scarCount ;

    //Constructors
    /**
     * Lion constructor.
     * setting starting position, starting weight, scar count and diet.
     * passing name and location to super (RoaringAnimal).
     * @param name A String that represents the lion name.
     */

    public Lion(String name, int size, String color, int horSpeed, int verSpeed, ZooPanel panel){
        super(name,STARTING_POSITION,size,size* DEFAULT_SIZE,color,horSpeed,verSpeed,IMG1,panel);
        this.setDiet(new Carnivore());
        this.scarCount = 0;
    }

    //API
    /**
     * The roar method, describe the Roaring animals sound
     */
    public void roar(){

    }

    /**
     If feeding has been successful,Trying to set a scar
     * @see Animal - eat() method.
     * @param food an edible type of animal to eat.
     * @return boolean value if feeding has been successful or not.
     */
    @Override
    public boolean eat(IEdible food) {
        boolean isSuccess = super.eat(food);
        if (isSuccess){
            this.setScar(1);
        }
        return isSuccess;
    }
    //setters
    public void setWeight(int size){
        super.setWeight(DEFAULT_SIZE *size);
    }
    /**
     * Setter for the lion scar count, use a random chance 50% for getting a scar
     * @param scar An int value fot the amount of scars.
     * @return boolean value if the scar was set successfully.
     */
    public boolean setScar(int scar){
        boolean isSuccess = false;
        Random random = new Random();
        if ( random.nextInt(2) == 1) {
            scarCount+= scar;
            isSuccess = true;
        }

        return isSuccess;
    }

    //getters

    /**
     * getter for food type
     * @return EFoodType.NOTFOOD
     */
    public EFoodType getFoodtype(){
        return LION;
    }
    public String getType(){
        return "Lion";
    }



}
