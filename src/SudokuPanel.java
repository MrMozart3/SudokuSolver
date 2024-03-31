import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {
    private int[] CalculateWalls(int y, int x, int width){
        int[] walls = new int[4];
        if(y % 3 == 0){
            walls[0] = width;
        }
        if(y % 3 == 2){
            walls[2] = width;
        }
        if(x % 3 == 0){
            walls[1] = width;
        }
        if(x % 3 == 2){
            walls[3] = width;
        }
        return walls;
    }
    SudokuPanel(int borderWidth){
        //opaqueness
        this.setOpaque(false);
        //border
        this.setBorder(BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth));

        this.setLayout(new GridLayout(9, 9, 3, 3));
        ButtonPanel[][] buttons = new ButtonPanel[9][9];
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                int[] walls = CalculateWalls(y, x, 2);
                buttons[y][x] = new ButtonPanel(walls[0], walls[1], walls[2], walls[3]);
                this.add(buttons[y][x]);
            }
        }

    }
}
