import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputLabel extends JLabel {
    public static Color COLOR_DEFAULT = Color.WHITE;
    public static Color COLOR_HOVER = Color.LIGHT_GRAY;
    public static Color COLOR_SELECT = Color.GREEN;
    private void ChangeBackground(Color color){
        this.setBackground(color);
    }
    InputLabel() {
        this.setOpaque(true);
        ChangeBackground(COLOR_DEFAULT);
        this.setText("3");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setForeground(Color.BLACK);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                setBackground(COLOR_HOVER);
            }
            @Override
            public void mouseExited(MouseEvent e){
                setBackground(COLOR_DEFAULT);
            }
        });
    }
}
