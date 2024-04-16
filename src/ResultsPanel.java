import javax.print.event.PrintJobAttributeListener;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ArrowButton extends JButton{
    final int size = 40;
    private Icon icon;
    ArrowButton(boolean isLeftArrow){
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(size, size));

        this.setRolloverEnabled(false);
        this.setFocusable(false);
        this.setFocusPainted(false);
        this.setBorder(BorderFactory.createEmptyBorder());

        if(isLeftArrow){
            icon = new ImageIcon(new ImageIcon("assets/left-arrow.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
        }
        else{
            icon = new ImageIcon(new ImageIcon("assets/right-arrow.png").getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
        }
        this.setIcon(icon);

        this.setUI(new BasicButtonUI(){
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b){

            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
            }
            @Override
            public void mouseExited(MouseEvent e){
            }
        });
        if(isLeftArrow){
            this.addActionListener(e -> {
                Solver.listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "PREV-ANS"));
            });
        }
        else{
            this.addActionListener(e -> {
                Solver.listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "NEXT-ANS"));
            });
        }
    }
    public void ShouldBeVisible(boolean isVisible){
        if(isVisible){
            this.setIcon(icon);
        }
        else{
            this.setIcon(null);
        }
    }
}

public class ResultsPanel extends JPanel {
    JPanel topPanel;
    JLabel topText;
    JPanel botPanel;
    JLabel botText;
    ArrowButton leftButton;
    ArrowButton rightButton;
    ResultsPanel(){
        this.setLayout(new GridLayout(2, 1));
        this.setOpaque(false);

        //
        topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new GridBagLayout());

        topText = new JLabel();
        topText.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(topText);

        botPanel = new JPanel();
        botPanel.setOpaque(false);
        botPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        botPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        leftButton = new ArrowButton(true);
        botPanel.add(leftButton);

        botText = new JLabel();
        botText.setForeground(Color.RED);
        botText.setFont(new Font("Arial", Font.BOLD, 20));
        botPanel.add(botText);

        rightButton = new ArrowButton(false);
        botPanel.add(rightButton);

        changeShowingNumber(false, "0_0");

        this.add(topPanel);
        this.add(botPanel);
    }
    public void EnableAnswers(int answersCount){
        if(answersCount > 0) {
            topText.setText("Found " + answersCount + " Answers");
            topText.setForeground(Color.WHITE);
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
    public void changeShowingNumber(boolean show, String compressedValue){
        if(show){
            int current = Integer.parseInt(compressedValue.substring(0, compressedValue.indexOf('_')));
            int max = Integer.parseInt(compressedValue.substring(compressedValue.indexOf('_') + 1));
            System.out.println(show + " " + current + " " + max);
            botText.setText(String.valueOf(current + 1));

            botText.setVisible(false);
            if(current > 0){
                leftButton.ShouldBeVisible(true);
                botText.setVisible(true);
            }
            else{
                leftButton.ShouldBeVisible(false);
            }
            if(current < max - 1) {
                rightButton.ShouldBeVisible(true);
                botText.setVisible(true);
            }
            else{
                rightButton.ShouldBeVisible(false);
            }
        }
        else{
            leftButton.ShouldBeVisible(false);
            rightButton.ShouldBeVisible(false);
            botText.setVisible(false);
        }
    }
}
