import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Solver{
    public static ActionListener listener;
    private KeyListener keyListener;
    private int currentY, currentX;
    Solver() {
        currentX = -1;
        currentY = -1;
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() instanceof InputButton button){
                    if(button.y == currentY && button.x == currentX) {
                        changeColor(currentY, currentX, SudokuPanel.COLOR_DEFAULT);
                        currentY = -1;
                        currentX = -1;
                    }
                    else{
                        if (currentY != -1 && currentX != -1) {
                            changeColor(currentY, currentX, SudokuPanel.COLOR_DEFAULT);
                        }
                        currentY = button.y;
                        currentX = button.x;
                        changeColor(currentY, currentX, SudokuPanel.COLOR_SELECT);
                    }
                }
                else if(e.getSource() instanceof keyObject key){
                    if(currentY != -1 && currentX != -1){
                        changeValue(currentY, currentX, key.key);
                    }
                }
                else{
                    System.out.println("Solver listener bad data ERROR");
                }
            }
        };
    }
    private void changeColor(int y, int x, Color color){
        SudokuPanel.listener.actionPerformed(new ActionEvent(new buttonObject(y, x, color, true, "", false), ActionEvent.ACTION_PERFORMED, "change_button"));
    }
    private void changeValue(int y, int x, String newValue){
        SudokuPanel.listener.actionPerformed(new ActionEvent(new buttonObject(y, x, Color.BLACK, false, newValue, true), ActionEvent.ACTION_PERFORMED, "change_button"));
    }
}
