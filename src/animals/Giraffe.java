package animals;


import diet.Herbivore;
import graphics.ZooPanel;
import mobility.Point;

/**
 * Giraffe class --> Giraffe is a chewing animal type
 * @version 1.0
 * @author Tal vazana
 * @see ChewingAnimals
 */
public class Giraffe extends ChewingAnimals  {
    private static final String PATH_PREFIX = "grf";
    private static final String IMG1 = "/grf_b_1.png";
    private static final double DEFULT_SIZE = 2.2;
    private static final Point STARTING_POSITION = new Point(50,0);
    private static final double MIN_NECK_LENGTH = 1;
    private static final double MAX_NECK_LENGTH = 2.5;
    private static final double DEFAULT_NECK_LENGTH = 1.5;

    private double neckLength;

    //Constructors
    /**
     * Giraffe constructor.
     * setting starting position, starting weight, default neck length and diet.
     * passing name and location to super.
     * @param name a String that represents the giraffe name.
     */
//    public Giraffe(String name){
//        super(name,STARTING_POSITION);
//        MessageUtility.logConstractor("Giraffe", this.getName());
//        this.setWeight(DEFULT_SIZE);
//        this.setNeckLength(DEFAULT_NECK_LENGTH);
//        this.setDiet(new Herbivore());
//    }
    public Giraffe(String name, int size, String color, int horSpeed, int verSpeed, ZooPanel panel){
        super(name,STARTING_POSITION,size,size*DEFULT_SIZE,color,horSpeed,verSpeed,IMG1,panel);
        this.setDiet(new Herbivore());

    }


    //API
    /**
     * the chew method describes the chewing animals sound
     */
    public void chew(){

    }

    //setters
    /**
     * Setter for thr neck length attribute, checks if the neck length parameter is valid first
     * @param neckLength double value for the neck length.
     * @return boolean value if the neckLength was set successfully or not.
     */

    public boolean setNeckLength(double neckLength){
        boolean isSuccess = false;
        if(neckLength > MIN_NECK_LENGTH && neckLength < MAX_NECK_LENGTH){
            this.neckLength = neckLength;
            isSuccess = true;

        }
        return isSuccess;
    }

    public String getType(){
        return "Giraffe";
    }



}