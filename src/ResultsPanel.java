import javax.swing.*;
import java.awt.*;

public class ResultsPanel extends JPanel {
    JPanel topPanel;
    JLabel topText;
    JPanel botPanel;
    ResultsPanel(){
        this.setLayout(new GridLayout(2, 1));
        this.setOpaque(false);

        //
        topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new GridBagLayout());

        topText = new JLabel();
        topText.setFont(new Font("Arial", Font.BOLD, 24));

        botPanel = new JPanel();
        botPanel.setBackground(Color.RED);

        EnableAnswers(0);

        topPanel.add(topText);
        this.add(topPanel);
        this.add(botPanel);
    }
    public void EnableAnswers(int answersCount){
        if(answersCount > 0) {
            topText.setText("Found " + answersCount + " Answers");
            topText.setForeground(Color.BLUE);
        }
        else{
            topText.setText("No Answers Found");
            topText.setForeground(Color.RED);
        }
        topPanel.setVisible(true);
    }
    public void DisableAnswers(){
        topPanel.setVisible(false);
    }

}
