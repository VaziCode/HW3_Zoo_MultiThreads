package animals;

import diet.Carnivore;
import diet.Omnivore;
import food.EFoodType;
import graphics.ZooPanel;
import mobility.Point;

/**
 * Bear class --> Bear is a roaring animal type
 *@version 1.0
 *@author Tal vazana
 * @see RoaringAnimal
 */

public class Bear extends RoaringAnimal {
    //TODO add all images path
    private static final String PATH_PREFIX = "bea";
    private static final String IMG1 = "/bea_b_1.png";
    private static final EFoodType BEAR = EFoodType.MEAT;
    private static final double DEFULT_SIZE = 1.5;
    private static final Point STARTING_POSITION = new Point(100, 5);
    private static final String DEFAULT_FURCOLOR = "GRAY";
    private String furColor;


    /**
     * private enum class for the valid fur colors
     */
    private enum FurColors {
        WHITE("WHITE"),
        GRAY("GRAY"),
        BLACK("BLACK");

        private final String color;
        /**
         * FurColor constructor.
         initialized of the fur color
         * @param color A string that represents the fur color.
         */
        FurColors(String color) {
            this.color = color;
        }
        /**
         * Getter for fur color attribute
         * @return A string that represents the current fur color.
         */
        public String getFurcolorValue() {
            return this.color;
        }
    }

    //constructors

    public Bear(String name, int size, String color, int horSpeed, int verSpeed, ZooPanel panel){
        super(name,STARTING_POSITION,size,size*DEFULT_SIZE,color,horSpeed,verSpeed,PATH_PREFIX,panel);
        this.setDiet(new Omnivore());
        //TODO add image by color
    }

    //API

    /**
     * this method checks if the fur color parameter is valid
     * @param furColor A String that represents the fur color.
     * @return boolean value if the given string is a valid fur color or not.
     */
    public boolean validFurColor(String furColor) {
        boolean isSuccess = false;
        for (FurColors color : FurColors.values()) {
            if (color.getFurcolorValue().equals(furColor)) {
                isSuccess = true;
            }
        }
        return isSuccess;
    }
    /**
     * The roar method, describe the Roaring animals sound
     */
    public void roar() {

    }

    //setters

    /**
     * Setter for the fur color attribute
     * @param furColor A String that represents the fur color.
     * @return boolean value - true if the fur color was set successfully.
     */
    public boolean setFurColor(String furColor) {
        boolean isSuccess = false;
        if(validFurColor(furColor)){
            this.furColor = furColor;
            isSuccess = true;
        }
        else {
            this.furColor = DEFAULT_FURCOLOR;
        }
        return isSuccess;
    }
    public String getType(){
        return "Bear";
    }
}