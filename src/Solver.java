import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Solver{
    public static ActionListener listener;
    private KeyListener keyListener;
    private int currentY, currentX;
    private sudokuObject data;
    private final int maxAns = 100;
    Solver() {
        currentX = -1;
        currentY = -1;
        data = new sudokuObject();
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
                        data.set(currentY, currentX, Integer.parseInt(key.key));
                        changeValue(currentY, currentX, key.key);
                    }
                }
                else if(e.getActionCommand().equals("SOLVE")){
                    sudokuObject[] ans = new sudokuObject[maxAns];
                    int[] curAns = {0};
                    if(currentX != -1 && currentY != -1) {
                        changeColor(currentY, currentX, SudokuPanel.COLOR_DEFAULT);
                        currentX = -1;
                        currentY = -1;
                    }
                    SolveSudoku(data, ans, curAns);
                    System.out.println("answers found: " + curAns[0]);

                    for(int y = 0; y < 9; y++) {
                        for(int x = 0; x < 9; x++) {
                            changeValue(y, x, String.valueOf(ans[0].get(y, x)));
                        }
                    }
                }
                else if(e.getActionCommand().equals("CLEAR")){
                    System.out.println("CLEAR");
                }
                else if(e.getActionCommand().equals("BACK")){
                    System.out.println("BACK");
                }
                else {
                    System.out.println(e.getSource());
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
    private void SolveSudoku(sudokuObject data, sudokuObject[] ans, int[] curAns){
        SolveIteration(0, 0, data, ans, curAns);
        for(int x = 0; x < maxAns && x < curAns[0]; x++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(ans[x].get(i, j) + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    private void SolveIteration(int curY, int curX, sudokuObject data, sudokuObject[] ans, int[] curAns){
        //upper limit of answers
        if(curAns[0] == maxAns){
            return;
        }
        //check if answer
        if(curY == 9){
            ans[curAns[0]] = new sudokuObject();
            ans[curAns[0]].copy(data);
            curAns[0]++;
            return;
        }
        //going through possible boards
        if(data.get(curY, curX) == 0){
            for(int i = 1; i <= 9 && curAns[0] < maxAns; i++){
                data.set(curY, curX, i);
                boolean valid = false;
                //if valid go next
                if(!data.isValid()){
                    data.set(curY, curX, 0);
                    continue;
                }
                //
                int nextX = curX + 1;
                int nextY = curY;
                if(nextX > 8){
                    nextX = 0;
                    nextY++;
                }
                SolveIteration(nextY, nextX, data, ans, curAns);
                //after coming back remove
                data.set(curY, curX, 0);
            }
        }
        else if(curAns[0] < maxAns){
            int nextX = curX + 1;
            int nextY = curY;
            if(nextX > 8){
                nextX = 0;
                nextY++;
            }
            SolveIteration(nextY, nextX, data, ans, curAns);
        }
    }
}
