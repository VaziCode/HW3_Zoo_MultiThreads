package animals;

import diet.Herbivore;
import graphics.ZooPanel;
import mobility.Point;

/**
 * Elephant Class--> Elephant is a chewing animal type
 * @version 1.0
 * @author Tal vazana
 * @see ChewingAnimals
 */
public class Elephant extends ChewingAnimals {
    private static final String PATH_PREFIX = "elf";
    private static final String IMG1 = "/elf_b_1.png";
    private static final double DEFAULT_SIZE = 10; //changed from STARTING_WEIGHT
    private static final Point STARTING_POSITION = new Point(50,90);
    private static final double MIN_TRUNK_LENGTH = 0.5;
    private static final double MAX_TRUNK_LENGTH = 3;
    private static final double DEFAULT_TRUNK_LENGTH = 1;

    private double trunkLength;
    //constructors


    public Elephant(String name, int size, String color, int horSpeed, int verSpeed, ZooPanel panel){
        super(name,STARTING_POSITION,size,size* DEFAULT_SIZE,color,horSpeed,verSpeed,IMG1,panel);
        this.setDiet(new Herbivore());

    }

    /**
     * Elephant constructor.
     * setting default weight, default trunk length and diet
     * passing name and location to super.
     * @param name A String that represents the  elephant name.
     * @param location Point object for the elephant location.
     */
//    public Elephant(String name, Point location){
//        super(name,location);
//        MessageUtility.logConstractor("Elephant", this.getName());
//        this.setWeight(DEFULT_SIZE);
//        this.settrunkLength(DEFAULT_TRUNK_LENGTH);
//        this.setDiet(new Herbivore());
//    }
    /**
     * Elephant constructor.
     * setting starting position, default weight, and diet
     * passing name and location to super.
     * initialized with a double that represents the trunkLength.
     * @param name A String that represents the  elephant name.
     * @param trunkLength double value to set for the trunkLength.
     */


    //API
    /**
     * The chew sound of an elephant.
     */
    @Override
    public void chew(){

    }

    //setters
    /**
     * Setter for the Elephant trunk length,
     * if the trunkLength parameter is valid, assigns the value to the trunkLength field.
     * @param trunkLength double value to set.
     * @return boolean value if set was successful or not.
     */

    public boolean settrunkLength(double trunkLength){
        boolean isSuccess = false;
        if(trunkLength >= MIN_TRUNK_LENGTH && trunkLength <= MAX_TRUNK_LENGTH){
            this.trunkLength = trunkLength;
            isSuccess = true;
        }
        return isSuccess;
    }


    public String getType(){
        return "Elephant";
    }

}