import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    public InputButton inputButton;
    ButtonPanel(int y, int x, int top, int left, int bottom, int right){
        this.setOpaque(false);
        this.setLayout(new GridLayout(1, 1));
        this.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        inputButton = new InputButton(y, x, "");
        this.add(inputButton);
    }
    public void changeText(String newText){
        inputButton.changeText(newText);
    }
    public void changeBackground(Color color){
        inputButton.changeBackground(color);
    }
}
