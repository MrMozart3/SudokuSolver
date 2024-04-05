import java.awt.*;

public class buttonObject {
    int y, x;
    boolean changeColor;
    boolean changeValue;
    Color newColor;
    int newValue;
    buttonObject(int y, int x, Color newColor, boolean changeColor, int newValue, boolean changeValue){
        this.y = y;
        this.x = x;
        this.newColor = newColor;
        this.changeColor = changeColor;
        this.newValue = newValue;
        this.changeValue = changeValue;
    }
}
