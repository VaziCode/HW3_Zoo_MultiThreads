package graphics;

import animals.Animal;

import java.util.ArrayList;
import java.util.Vector;
/**
 * Animals class --> Represents the Animal model
 * @version 3.0
 * @author Tal vazana
 * @see
 */
//singleton
public class Animals {
    private static int NUM_OF_LIONS;
    private static int NUM_OF_BEARS;
    private static int NUM_OF_GIRAFFES;
    private static int NUM_OF_TURTLES;

    private static volatile Animals instance = null;//added volatile- denies from the JVM to change this class creation way
    private ArrayList<Animal> animalsArr;
    private static final int MAX_ANIMALS = 10;
    private  int totalCount = 0;
    private Animals(){
        animalsArr = new ArrayList<Animal>();
        NUM_OF_LIONS = 0;
        NUM_OF_BEARS = 0;
        NUM_OF_GIRAFFES = 0;
        NUM_OF_TURTLES = 0;


    }
//    public static synchronized Animals getInstance() {//c hanged to synchronized for solving the deadlock
//        if(instance == null) {
//            instance = new Animals();
//        }
//        return instance;
//    }
    public static Animals getInstance() {//c hanged to synchronized for solving the DCL
        if(instance == null) {
            synchronized (Animals.class){
                if(instance == null)
                    instance = new Animals();
            }
        }
        return instance;
    }

    public boolean addAnimal(Animal animal){
        boolean isSuccess = false;

        if (animalsArr.size() < MAX_ANIMALS){

            animalsArr.add(animal);
            increaseAnimalType(animal.getType());
            isSuccess = true;
        }
        return isSuccess;
    }
    //After adding an animal to the zoo, increase by 1 the static variable corresponding to the type of animal
    private void increaseAnimalType(String type){
        switch (type){
            case "Lion"-> NUM_OF_LIONS ++;
            case "Bear"-> NUM_OF_BEARS ++;
            case "Turtle"-> NUM_OF_TURTLES ++;
            case "Giraffe"->NUM_OF_GIRAFFES ++;
        }
    }
    public void removeAllAnimals(){
        animalsArr = new ArrayList<>();
        NUM_OF_LIONS = 0;
        NUM_OF_BEARS = 0;
        NUM_OF_TURTLES = 0;
        NUM_OF_GIRAFFES = 0;
    }
    public void removeAnimal(int index){
        String type = animalsArr.get(index).getType();
        switch (type) {
            case "Lion" -> {
                NUM_OF_LIONS--;
            }
            case "Bear" -> {
                NUM_OF_BEARS--;
            }
            case "Turtle" -> {
                NUM_OF_TURTLES--;
            }
            case "Giraffe" -> {
                NUM_OF_GIRAFFES--;
            }
        }
        setTotalEatCount(getTotalEatCount()-animalsArr.get(index).getEatCount());
        animalsArr.remove(index);

    }
    public ArrayList<Animal> getAnimalsArr(){
        return animalsArr;
    }
    public Animal getAnimalByIndex(int index){
        return animalsArr.get(index);
    }
    public int getAnimalsSize() {
        return animalsArr.size();
    }

    public Vector<String> getAnimalsName(){
        Vector<String> animalsName = new Vector<>();
        for (Animal animal: animalsArr){
            animalsName.add(animal.getName());
        }
        return animalsName;
    }
    public int getNumOfAnimalsType(String type){
        switch (type){
            case "Lion" -> {return NUM_OF_LIONS ; }
            case "Bear" -> {return NUM_OF_BEARS ; }
            case "Turtle" -> {return NUM_OF_TURTLES;}
            case "Giraffe"-> {return NUM_OF_GIRAFFES ;}
        }
        return 0;
    }

    public boolean containsName(String name) {
        for(Animal animal: animalsArr){
            if(name.equals(animal.getName())){
                return true;
            }
        }
        return false;
    }
    public int getTotalEatCount(){
        int totalEat = 0;
        for(Animal animal: animalsArr){
            totalEat += animal.getEatCount();
        }
        this.totalCount = totalEat;
        return totalEat;
    }
    public void setTotalEatCount(int totalCount){
        this.totalCount = totalCount;
    }


}
