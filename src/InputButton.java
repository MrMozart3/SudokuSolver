import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;

public class InputButton extends JButton {
    public final int y, x;

    InputButton(int y, int x) {
        this.y = y;
        this.x = x;
        this.setFocusPainted(false);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEmptyBorder());
        changeText("0");
        changeBackground(SudokuPanel.COLOR_DEFAULT);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setForeground(Color.BLACK);
        //disable pressed button
        this.setUI(new BasicButtonUI(){
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b){

            }
        });
        //listeners
        this.addActionListener(Solver.listener);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                if(retrieveBackground() != SudokuPanel.COLOR_SELECT) {
                    setBackground(SudokuPanel.COLOR_HOVER);
                }
            }
            @Override
            public void mouseExited(MouseEvent e){
                if(retrieveBackground() != SudokuPanel.COLOR_SELECT){
                    setBackground(SudokuPanel.COLOR_DEFAULT);
                }
            }
        });
    }
    public void changeText(String newText){
        this.setText(newText);
    }
    public void changeBackground(Color color){
        this.setBackground(color);
    }
    public Color retrieveBackground(){
        return this.getBackground();
    }
}
