package animals;

import diet.IDiet;
import food.EFoodType;
import food.Food;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.Utilities;
import graphics.ZooPanel;
import mobility.Mobile;
import mobility.Point;
import food.IEdible;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Class Animal is an abstract class, represent the base class for all animals types
 * @version 2.0
 * @author Tal Vazana
 * @see Mobile
 */
public abstract class Animal extends Mobile implements IEdible, IDrawable,IAnimalBehavior,Runnable {
    private static final int MAX_SPEED = 10;
    private static final int MIN_SPEED = 1;
    private final int EAT_DISTANCE = 10;
    private int size;
    private int horSpeed;
    private int verSpeed;
    private int x_dir=1;
    private int y_dir=1;
    private int eatCount;
    private String name;
    private String col;
    private double weight;
    private boolean coordChanged;
    private ZooPanel pan;
    private BufferedImage img1; //1-->right
    private BufferedImage img2; // 2-->left
    private Point location;
    private IDiet diet;
    private boolean isAlive;
    protected Thread thread;
    protected boolean threadSuspended;

    public Animal(String name,Point location,int size,double weight,String color,int horSpeed, int verSpeed,String imgPath,ZooPanel panel){
        super(location);
        this.setAnimalName(name);
        this.setSize(size);
        this.setWeight(weight);
        this.setColor(color);
        this.setHorSpeed(horSpeed);
        this.setVerSpeed(verSpeed);
        this.loadImages(this.getType());
        this.setPan(panel);
        this.eatCount = 0;
        this.coordChanged = true;
        thread= new Thread(this,getName()+"_Thread");
        this.isAlive=true;
        this.threadSuspended=false;
    }

    //abstract method
    public abstract void makeSound();
    public abstract EFoodType getFoodtype();

    //API
    public boolean eat(IEdible food){
        boolean isSuccess = false;
        double weightGain = diet.eat( this, food);
        if(weightGain > 0){
            setWeight(this.getWeight()+weightGain);
            this.makeSound();
            isSuccess = true;
        }
        System.out.println();
        return isSuccess;
    }


    //setters
    public boolean setName(String name){
        boolean isSuccess = false;
        if(name.length() > 0) {
            this.name = name;
            isSuccess = true;
        }
        else {
            this.name = " Empty name ";
        }
        return isSuccess;
    }
    public boolean setWeight(double weight){
        boolean isSuccess = false;
        if(weight > 0) {
            this.weight = weight;
            isSuccess = true;
        }
        return isSuccess;
    }
    public boolean setDiet(IDiet diet) {
        boolean isSuccess = true;
        this.diet = diet;
        return isSuccess;
    }

    public void setEatCount(int eatCount) {
        this.eatCount = eatCount;
    }

    public boolean setVerSpeed(int verSpeed){
        boolean isSuccess = false;
        if (MIN_SPEED <= verSpeed && verSpeed <= MAX_SPEED){
            this.verSpeed = verSpeed;
            isSuccess = true;
        } else this.verSpeed = 1;
        return isSuccess;
    }
    public boolean setColor(String color){
        boolean isSuccess = true;
        String upperCaseColor  = color.toUpperCase();
        switch (upperCaseColor) {
            case "RED" -> this.col = "RED";
            case "BLUE" -> this.col = "BLUE";
            case "NATURAL" -> this.col = "NATURAL";
            default -> {
                this.col = "NATURAL";
                isSuccess = false;
            }
        }
        return isSuccess;
    }
    public boolean setHorSpeed(int horSpeed){
        boolean isSuccess = false;
        if (MIN_SPEED <= horSpeed && horSpeed <= MAX_SPEED){
            this.horSpeed = horSpeed;
            isSuccess = true;
        } else this.horSpeed = 1;
        return isSuccess;
    }
    @Override
    public void setChanges (boolean state){
        this.coordChanged = state;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public void setX_dir(int x_dir) {
        this.x_dir = x_dir;
    }
    public void setY_dir(int y_dir) {
        this.y_dir = y_dir;
    }
    public void setPan(ZooPanel panel){
        this.pan = panel;
    }
    public void setCol(String col) {
        this.col = col;
    }
    public void setAnimalName(String name) {
        this.setName(name);
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    //getters
    public String getName(){
        return this.name;
    }
    public double getWeight(){
        return this.weight;
    }
    public int getHorSpeed() {
        return this.horSpeed;
    }
    public int getVerSpeed() {
        return this.verSpeed;
    }
    public BufferedImage getImage1(){return this.img1;}
    public BufferedImage getImage2(){return this.img2;}
    public int getX_dir(){
        return x_dir;
    }
    public int getY_dir() {
        return y_dir;
    }
    public IDiet getDiet(){
        return this.diet;
    }
    public String toString(){
        return this.getClass().getSimpleName();
    }
    public BufferedImage getImg1() {
        return this.img1;
    }
    public BufferedImage getImg2() {
        return this.img2;
    }
    public abstract String getType();

    // Implementation of the IAnimalBehavior methods
    @Override
    public String getAnimalName(){
        return this.name;
    }
    @Override
    public int getSize(){
        return this.size;
    }
    @Override
    public void eatInc(){
        this.eatCount++;
    }
    @Override
    public int getEatCount(){
        return this.eatCount;
    }
    @Override
    public boolean getChanges (){
        return this.coordChanged;
    }
    @Override
    public String getColor(){
        return this.col;
    }

    @Override
    public synchronized void loadImages(String animalType) {
        String path1 = Utilities.findAnimalImagePath(animalType,col, 1);
        String path2 = Utilities.findAnimalImagePath(animalType,col, -1);
        try {
            this.img1 = ImageIO.read(new File(path1));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.img2=ImageIO.read(new File(path2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public synchronized void drawObject (Graphics g)
    {
        int size = this.getSize();
        int xCoordinate = this.getLocation().getX();
        int yCoordinate = this.getLocation().getY();
        if (x_dir == 1) //  right side
            g.drawImage(img1, xCoordinate, yCoordinate , size/2 , (int)(size/1.2), this.pan);
        else //  left side
            g.drawImage(img2, xCoordinate, yCoordinate, size/2 , (int)(size/1.2), this.pan);

    }
    public boolean tryStart(){
        if(this.thread.isAlive()){
            return false;
        }
        else{
            thread.start();
            this.threadSuspended=false;
        }
        return true;
    }
    @Override
    public synchronized void setResumed() {
        if(this.threadSuspended)
            this.threadSuspended=false;
    }
    @Override
    public synchronized void setSuspended() {
        if(!this.threadSuspended)
            this.threadSuspended=true;
    }
    public void killThread() {
        if(isAlive) {
            synchronized (this){
                isAlive=false;
            }
        }
    }

    /**
     * The moveToFood method is changing the animal's location directly to the food location
     */
    public synchronized void moveToFood(){
    Food food=this.pan.getFood();

    double distance;
    if(food!=null){
        Point foodLocation=food.getLocation();
        if (this.getDiet().canEat(food.getFoodtype())) {
            distance = this.calcDistance(foodLocation);
            if (this.getLocation().getX() > foodLocation.getX() && this.getX_dir() != -1)
                this.setX_dir(-1);
            if (this.getLocation().getX() < foodLocation.getX() && this.getX_dir() != 1)
                this.setX_dir(1);
            if (this.getLocation().getY() < foodLocation.getY() && this.getY_dir() != 1)
                this.setY_dir(1);
            if (this.getLocation().getY() > foodLocation.getY() && this.getY_dir() != -1)
                this.setY_dir(-1);
            if (distance - (int) (this.getSize() / 2) <= 10) {
                pan.setThereIsFood(false);
                this.eatInc();
                String message = "The animal " + this.getName() + "Eat the food";
                JOptionPane.showMessageDialog(this.pan, message, "Feeding completed", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }

}
    @Override
    public synchronized double  move(Point nextLocation) {
        double distance = super.move(nextLocation);
        double weight = this.getWeight();
        if (distance != 0) {
            this.setWeight(weight - (distance * weight * 0.00025));
        }
        this.coordChanged = true;
        return distance;
    }
    @Override
    public void run() {
        while (isAlive)
        {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            while (threadSuspended)
            {
                synchronized (this){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            int new_x= this.getLocation().getX()+this.getHorSpeed()*this.getX_dir();
            int new_y= this.getLocation().getY()+this.getVerSpeed()*this.getY_dir();
            if(new_x+this.getSize()/2>=pan.getWidth()||new_x<=0)
                this.setX_dir(this.getX_dir()*-1);
            if(new_y+this.getSize()>=pan.getHeight()||new_y<=0)
                this.setY_dir(this.getY_dir()*-1);
            this.move(new Point(new_x,new_y));
        }
        System.out.println("Animal run Thread: " +Thread.currentThread().getName()+ ", is Dead! ");
    }
}


