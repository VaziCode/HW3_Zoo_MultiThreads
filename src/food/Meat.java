package food;

import graphics.ZooPanel;

public class Meat extends Food{
    private static final String IMG_PATH = "/meat.gif";
    //private Point location;
    //private BufferedImage meetImg;
    //private ZooPanel pan;

    public Meat(ZooPanel panel){
        super(panel,IMG_PATH);
//        this.pan = panel;
//        this.setLocation(new Point(pan.getWidth()/2,pan.getHeight()/2));
//        this.loadImages(IMG_PATH);


    }
    @Override
    public EFoodType getFoodtype() {
        return EFoodType.MEAT;
    }

//    @Override
//    public void loadImages(String nm) {
//        StringBuilder path =new StringBuilder();
//        path.append(PICTURE_PATH);
//        path.append(nm);
//        try {
//            this.meetImg = ImageIO.read(new File(path.toString()));
//        } catch (IOException e) {
//            System.out.println("Cannot load image");
//        }
//    }
//
//    @Override
//    public void drawObject(Graphics g) {
//        int size = 100;
//        int xCoordinate = this.getLocation().getX();
//        int yCoordinate = this.getLocation().getY() ;
//        g.drawImage(meetImg, xCoordinate  , yCoordinate , size/2, size, this.pan);
//    }

    @Override
    public String getColor() {
        return "Red";
    }

//    @Override
//    public Point getLocation() {
//        return location;
//    }
//
//    @Override
//    public boolean setLocation(Point p) {
//        boolean isSuccess = Point.checkBoundaries(p);
//        if (isSuccess) {
//            this.location = p;
//        }
//        return isSuccess;
//    }
}
