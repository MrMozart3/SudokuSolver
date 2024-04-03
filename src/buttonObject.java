import java.awt.*;

public class buttonObject {
    int y, x;
    boolean changeColor;
    boolean changeValue;
    Color newColor;
    String newValue;
    buttonObject(int y, int x, Color newColor, boolean changeColor, String newValue, boolean changeValue){
        this.y = y;
        this.x = x;
        this.newColor = newColor;
        this.changeColor = changeColor;
        this.newValue = newValue;
        this.changeValue = changeValue;
    }
}
