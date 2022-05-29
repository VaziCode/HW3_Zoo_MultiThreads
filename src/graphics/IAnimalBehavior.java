package graphics;

public interface IAnimalBehavior {
    public String getAnimalName();
    public int getSize();
    public void eatInc();
    public int getEatCount();
    public boolean getChanges ();
    public void setChanges (boolean state);
    public void setAnimalName(String name);
    public void setResumed();
    public void setSuspended();
}
