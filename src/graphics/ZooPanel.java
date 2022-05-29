package graphics;

import animals.Animal;
import animals.RoaringAnimal;
import diet.Carnivore;
import food.Food;
import food.Meat;
import mobility.Point;
import plants.Cabbage;
import plants.Lettuce;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * ZooPanel Class, The main panel which controls the system and navigate between all the program's actions
 * @version 3.0
 * @author Tal vazana 313454472
 * @see ZooFrame
 */
public class ZooPanel extends JPanel implements ActionListener,Runnable{

    final int PANEL_WIDTH=800;
    final  int PANEL_HEIGHT=600;
    private final String IMG_PATH = "src/pictures/savanna.png";
    private Image backgroundImg;
    private AddAnimalDialog addAnimalDialog;
//    private MoveAnimalDialog moveAnimalDialog;
    private JPanel toolBar;
    private InfoTable infoTable;
    private Animals animalsArr;
    private Food food;
    private Thread controller;
    private boolean isAlive;
    private boolean thereIsFood=false;
    private static boolean allAnimalsSleep=false;
    public ZooPanel() {
        this.animalsArr =Animals.getInstance();
//        this.setPreferredSize(new Dimension(Point.getMaxXVal(),Point.getMaxYVal()));
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setFocusable(true);
        this.setLayout(new BorderLayout());
        JPanel toolBar = createToolBar();
        toolBar.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        toolBar.setBackground(Color.LIGHT_GRAY);
        toolBar.setBounds(0,Point.getMaxYVal(),Point.getMaxXVal(),50);
        this.add(toolBar,BorderLayout.SOUTH);
        this.setVisible(true);
        this.controller = new Thread(this,"ZooPanelThread");
        this.isAlive=true;
        this.controller.start();
    }

    private JPanel createToolBar() {
        toolBar = new JPanel();
        JButton addAnimalBtn = new JButton("Add Animal");
        JButton sleepBtn = new JButton("Sleep");
        JButton wakUpBtn = new JButton("Wake Up");
        JButton clearBtn = new JButton("Clear");
        JButton foodBtn = new JButton("Food");
        JButton infoBtn = new JButton("Info");
        JButton exitBtn = new JButton("Exit");

        // adding buttons to the toolbar.
        toolBar.add(addAnimalBtn);
        toolBar.add(sleepBtn);
        toolBar.add(wakUpBtn);
        toolBar.add(clearBtn);
        toolBar.add(foodBtn);
        toolBar.add(infoBtn);
        toolBar.add(exitBtn);

        addAnimalBtn.addActionListener(this);
        sleepBtn.addActionListener(this);
        wakUpBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        foodBtn.addActionListener(this);
        infoBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        return toolBar;

    }
    public Animals getAnimalsArr(){
        return this.animalsArr;
    }

    public Food getFood() {
        return food;
    }
    public void setFood(Food food) {
        this.food = food;
    }

    //    public int getPANEL_WIDTH() {
//        return PANEL_WIDTH;
//    }
//
//    public int getPANEL_HEIGHT() {
//        return PANEL_HEIGHT;
//    }

    /**
     * Background panel method, changing the Background according to the user's choice.
     * @param background
     */
    public void setPanelBackground(String background){

        switch (background) {
            case "Green" -> {
                backgroundImg = null;
                this.setBackground(Color.green);
                repaint();
            }
            case "Image" -> {
                try {
                    backgroundImg = ImageIO.read(new File(IMG_PATH));
                    repaint();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "None" -> {
                backgroundImg = null;
                this.setBackground(null);
                repaint();
            }
        }
    }

    public void setThereIsFood(boolean thereIsFood) {
        this.thereIsFood = thereIsFood;
    }

    public synchronized void tryToFeedAnimal(Animal animal) {

        if(this.food != null) {
            double distance;
            Point location = food.getLocation();
            if (animal.getDiet().canEat(food.getFoodtype())) {
                distance =  animal.calcDistance(location);
                if(distance - animal.getSize()/2  <= 10 ){
                    animal.eatInc();
                    this.food = null;
                    String message = "The animal "+animal.getName()+ "Eat the food";
                    JOptionPane.showMessageDialog(null,message,"Feeding completed",JOptionPane.INFORMATION_MESSAGE);
                    this.manageZoo(); //ToDo
                }
            }
        }
    }
    private synchronized void huntingAnimal(){
        for (int i = 0; i < animalsArr.getAnimalsSize(); i++) {
            Animal animal1 = animalsArr.getAnimalByIndex(i);
            for (int j = i + 1; j < animalsArr.getAnimalsSize(); j++) {
                Animal animal2 = animalsArr.getAnimalByIndex(j);
                if ((animal1.getDiet().canEat(animal2.getFoodtype()))&&(animal1.getWeight() >= (animal2.getWeight() * 2)) &&
                        animal1.calcDistance(animal2.getLocation()) < animal2.getSize()){
                    animalsArr.setTotalEatCount(getAnimalsArr().getTotalEatCount() - animal2.getEatCount());
                    animalsArr.removeAnimal(j);
                    j--;
                    animal1.eatInc();
                    String message = "The "+ animal2.getName()+ "was preyed by the " +animal1.getName();
                    JOptionPane.showMessageDialog(this,message,"Feeding completed",JOptionPane.INFORMATION_MESSAGE);
                    animal2.killThread();
                    repaint();
                }
                else if (animal2.getDiet().canEat(animal1.getFoodtype())&& animal2.getWeight() >= animal1.getWeight() * 2 && animal2.calcDistance(animal1.getLocation()) < animal1.getSize()){
                    animalsArr.setTotalEatCount(getAnimalsArr().getTotalEatCount() - animal1.getEatCount());
                    animalsArr.removeAnimal(i);
                    i--;
                    animal2.eatInc();
                    String message = "The "+ animal1.getName()+ "was preyed by the " +animal2.getName();
                    JOptionPane.showMessageDialog(this,message,"Feeding completed",JOptionPane.INFORMATION_MESSAGE);
                    animal1.killThread();
                    repaint();
                }
            }
        }
    }

    private void createFoodDialog() {
        String[] foodOptions = {"Lettuce", "Cabbage", "Meat"};
        int choice = JOptionPane.showOptionDialog(this,
                " Please Choose food ", "Food for animals",
                0, 3, (Icon) null, foodOptions, null);
        switch (choice) {
            case 0: {
                this.food = new Lettuce(this);
                break;
            }
            case 1: {
                this.food = new Cabbage(this);
                break;
            }
            case 2: {
                this.food = new Meat(this);
                break;
            }
        }
        this.thereIsFood=true;
//        manageZoo();

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        switch (command) {
            case "Add Animal": {
                if (this.animalsArr.getAnimalsSize() == 10) {
                    JOptionPane.showMessageDialog(this, "Only 10 animals can be at the Zoo at the same time!");

                } else {
                    this.addAnimalDialog = new AddAnimalDialog(this);
                }
                break;
            }
            case "Sleep": {
                this.sleepAll();
                //ToDo
                break;
            }
            case "Wake Up": {
                this.wakeUpAll();
                //ToDo
                break;
            }
            case "Clear": {
                this.clearAll();
                MessageDialog.errorMessage("The zoo is empty");
                repaint();
                break;
            }
            case "Food": {
                createFoodDialog();
                //ToDo: CALL repaint();
                break;
            }
            case "Info": {
                if (animalsArr.getAnimalsSize() > 0) {
                    this.infoTable = new InfoTable(this.animalsArr);
                } else {
                    MessageDialog.errorMessage("The zoo is empty");
                }
                break;
            }
            case "Exit":
                for (Animal animal : animalsArr.getAnimalsArr()) {
                    animal.setAlive(false);
                }
                System.exit(1);
        }

        manageZoo();

    }
    public void sleepAll(){
        synchronized (this.animalsArr.getAnimalsArr())
        {
            for(Animal animal:animalsArr.getAnimalsArr()){
                animal.setSuspended();
            }
        }
    }

    public void wakeUpAll(){
        synchronized (this.animalsArr.getAnimalsArr())
        {
            for(Animal animal:animalsArr.getAnimalsArr()){
                animal.setResumed();
                synchronized (animal){
                    animal.notify();
                }
            }
        }
    }

    public void clearAll(){
        synchronized (animalsArr.getAnimalsArr()){
            for(Animal animal:animalsArr.getAnimalsArr()){
                if(animal!=null)
                    animal.killThread();
            }
            this.animalsArr.removeAllAnimals();
            if(food!=null){
                food=null;
                this.thereIsFood=false;
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImg,0 , 0,this);
        if (this.food != null){
            this.food.drawObject(g);
        }
        for (Animal animal : animalsArr.getAnimalsArr()) {
            synchronized (animal){
                animal.drawObject(g);
            }
        }
    }

    public boolean isChange(){
        for (Animal animal : animalsArr.getAnimalsArr()){
            synchronized (animal){
                if (animal.getChanges()) {
                    //checkEatAnimal();
                    animal.setChanges(false);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * manageZoo method, the program's controller.
     * running as long as the program is running.
     */
    public synchronized void manageZoo() {


        if(isChange()) {
            repaint();
        }
        huntingAnimal();
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */

    @Override
    public void run() {

        while (isAlive){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Animal animal : animalsArr.getAnimalsArr()) {
                if (animal.tryStart()) {
                    manageZoo();
                }
            }
            if(this.food!=null) {
                for (Animal animal : animalsArr.getAnimalsArr()) {
                    animal.moveToFood();
                    if(!thereIsFood)
                        food=null;
                }
            }
            manageZoo();
    }

}
}



