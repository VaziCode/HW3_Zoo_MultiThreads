package food;

import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public abstract class Food implements IEdible, ILocatable, IDrawable {
    private int height;
    private int width ;
    private Point location;
    private double weight;
    private BufferedImage foodImg;
    private ZooPanel pan;


    public Food(ZooPanel panel,String imgPath){
        this.pan = panel;
        int x = this.pan.getWidth()/2;
        int y = this.pan.getHeight()/2 -50;
        this.setLocation(new Point(x, y));
        Random rand = new Random();
        this.height = rand.nextInt(30);
        this.weight = rand.nextInt(12);
        this.height = 100 ;
        this.width = height/2;
        this.loadImages(imgPath);
    }
    //IEdible methods
    @Override
    public abstract EFoodType getFoodtype();

    //ILocatable method
    @Override
    public boolean setLocation(Point newLocation) {
        boolean isSuccess = Point.checkBoundaries(newLocation);
        if (isSuccess) {
            this.location = newLocation;
        }
        return isSuccess;
    }
    @Override
    public Point getLocation() {
        return this.location;
    }

    //IDrawable methods
    @Override
    public void loadImages(String nm){
        StringBuilder path =new StringBuilder();
        path.append(PICTURE_PATH);
        path.append(nm);
        try {
            this.foodImg = ImageIO.read(new File(path.toString()));
        } catch (IOException e) {
            System.out.println("Cannot load image");
        }
    }
    public void drawObject (Graphics g){

        int xCoordinate = this.getLocation().getX();
        int yCoordinate = this.getLocation().getY() ;
        g.drawImage(foodImg, xCoordinate  , yCoordinate , this.width,this.height, this.pan);
    }
    public abstract String getColor();
    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] ";
    }
}
