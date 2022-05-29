package plants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import food.EFoodType;
import food.Food;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;

import javax.imageio.ImageIO;


public abstract class Plant extends Food {

    //private double height;
    // private Point location;
    private double weight;
    //private BufferedImage planetImg;
    private ZooPanel pan;

    public Plant(ZooPanel panel,String imgPath) {
        super(panel,imgPath);
        this.pan = panel;
//        int x = this.pan.getWidth()/2;
//        int y = this.pan.getHeight()/2 -50;
//        this.location = new Point(x, y);
//        Random rand = new Random();
//        this.height = rand.nextInt(30);
//        this.weight = rand.nextInt(12);
//        this.loadImages(imgPath);

    }

    @Override
    public EFoodType getFoodtype() {
        return EFoodType.VEGETABLE;
    }
//
//    public double getHeight() {
//        return this.height;
//    }
//
//    @Override
//    public Point getLocation() {
//        return this.location;
//    }

    public double getWeight() {
        return weight;
    }

//    public boolean setHeight(double height) {
//
//        boolean isSuccess = (height >= 0);
//        if (isSuccess) {
//            this.height = height;
//        } else {
//            this.height = 0;
//        }
//        return isSuccess;
//    }
//
//    @Override
//    public boolean setLocation(Point newLocation) {
//        boolean isSuccess = Point.checkBoundaries(newLocation);
//        if (isSuccess) {
//            this.location = newLocation;
//        }
//        return isSuccess;
//    }

    public boolean setWeight(double weight) {
        boolean isSuccess = (weight >= 0);
        if (isSuccess) {
            this.weight = weight;
        } else {
            this.weight = 0;
        }
        return isSuccess;
    }



    //    @Override
//    public void loadImages(String nm){
//        StringBuilder path =new StringBuilder();
//        path.append(PICTURE_PATH);
//        path.append(nm);
//        try {
//            this.planetImg = ImageIO.read(new File(path.toString()));
//        } catch (IOException e) {
//            System.out.println("Cannot load image");
//        }
//    }
//    @Override
//    public void drawObject (Graphics g){
//        int size = 100;
//        int xCoordinate = this.getLocation().getX();
//        int yCoordinate = this.getLocation().getY() ;
//        g.drawImage(planetImg, xCoordinate  , yCoordinate , size/2, size, this.pan);
//    }
    @Override
    public String getColor(){
        return "Green";
    }
}
