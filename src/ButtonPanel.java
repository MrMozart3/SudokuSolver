import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    ButtonPanel(int top, int left, int bottom, int right){
        this.setOpaque(false);
        this.setLayout(new GridLayout(1, 1));
        this.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        InputLabel inputLabel = new InputLabel();
        this.add(inputLabel);
    }
}
