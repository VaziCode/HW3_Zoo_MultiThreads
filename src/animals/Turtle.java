package animals;

import diet.Herbivore;
import graphics.ZooPanel;
import mobility.Point;

/**
 * Turtle class --> Turtle is a chewing animal type
 * @version 1.0
 * @author Tal vazana
 * @see ChewingAnimals
 */
public class Turtle extends ChewingAnimals {
    private static final String PATH_PREFIX = "trt";
    private static final String IMG1 = "/trt_b_1.png";
    private static final double DEFAULT_SIZE = 0.5;
    private static final Point STARTING_POSITION = new Point(80,0);
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 500;
    private static final int DEFAULT_AGE = 1;



    private int age;

    //Constructors

    public Turtle(String name, int size, String color, int horSpeed, int verSpeed, ZooPanel panel){
        super(name,STARTING_POSITION,size,size* DEFAULT_SIZE,color,horSpeed,verSpeed,IMG1,panel);
        this.setDiet(new Herbivore());
    }
    /**
     * Turtle constructor.
     * setting ,starting weight, default age and diet
     * passing name and location to super (ChewingAnimals).
     * @see ChewingAnimals
     * @param name String that represents the turtle name.
     * @param location Point object for the turtle location.
     */
//    public Turtle(String name,Point location){
//        super(name,location);
//        MessageUtility.logConstractor("Turtle", this.getName());
//        this.setWeight(DEFULT_SIZE);
//        this.setAge(DEFAULT_AGE);
//        this.setDiet(new Herbivore());
//    }
    /**
     * Turtle constructor.
     * setting starting position, starting weight, and diet
     * passing name and location to super (ChewingAnimals).
     * @see ChewingAnimals
     * @param name A String that represents the turtle name.
     * @param age An integer value for the turtle age.
     */
//    public Turtle(String name,int age){
//        super(name, STARTING_POSITION);
//        MessageUtility.logConstractor("Turtle", this.getName());
//        this.setWeight(DEFULT_SIZE);
//        this.setAge(age);
//        this.setDiet(new Herbivore());
//
//    }
    //API
    /**
     * the chew method describes the chewing animals sound
     */
    public void chew(){

    }

    public void setWeight(int size){
        super.setWeight(DEFAULT_SIZE *size);
    }

    //setters

    /**
     * Setter for the age attribute
     * if the age is valid, assigns the parameter value to the age field.
     * @param age A integer value for the turtle age.
     * @return boolean value if the age was set successfully or not.
     */
    public boolean setAge(int age){
        boolean isSuccess = false;
        if(age >= MIN_AGE && age <= MAX_AGE){
            this.age = age;
            isSuccess = true;
        }
        return isSuccess;

    }
    public static double getDefaultSize() {return DEFAULT_SIZE;}
    public String getType(){
        return "Turtle";
    }
}