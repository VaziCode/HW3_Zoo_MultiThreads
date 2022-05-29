package graphics;

import java.awt.*;

/**
 * IDrawable Interface, Drawing and presenting on the screen auctions
 * @version 1.0
 *
 */
public interface IDrawable {
    public final static String PICTURE_PATH = "src/pictures";
    public void loadImages(String nm);
    public void drawObject (Graphics g);
    public String getColor();
}
