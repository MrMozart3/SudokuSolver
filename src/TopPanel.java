import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    TopPanel(){
        this.setBackground(Color.GRAY);
        this.setLayout(new BorderLayout());

        //
        JButton solveButton = new JButton();
        solveButton.setPreferredSize(new Dimension(400, 200));
        solveButton.setSize(new Dimension(400, 200));




        this.add(solveButton, BorderLayout.CENTER);
    }
}
