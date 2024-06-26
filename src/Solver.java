import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Solver{
    public static ActionListener listener;
    private KeyListener keyListener;
    public static boolean DEBUG_MODE = false;
    private int currentY, currentX;
    private sudokuObject data;
    //history
    private sudokuObject[] history;
    private final int historySize = 50;
    int currentlyInHistory;
    //answers
    private final int maxAns = 100;
    private boolean isAnswerMode;
    private int[] answers;
    private sudokuObject[] answersBoards;
    private int currentAnswerShowing;
    Solver() {
        //history init
        history = new sudokuObject[historySize];
        for(int i = 0; i < historySize; i++) history[i] = new sudokuObject();
        currentlyInHistory = 1;
        //answer init
        answersBoards = new sudokuObject[maxAns];
        isAnswerMode = false;
        answers = new int[1];
        currentAnswerShowing = -1;
        //

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
                        TopPanel.listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "DON"));
                        if(key.key == 10){
                            data.set(currentY, currentX, 0);
                            changeValue(currentY, currentX, 0);
                            changeColor(currentY, currentX, SudokuPanel.COLOR_DEFAULT);
                            currentY = -1;
                            currentX = -1;
                            AnswerMode(false, -1);
                            ReloadListAnswers();
                            SaveToHistory(data);
                        }
                        else {
                            data.set(currentY, currentX, key.key);
                            changeValue(currentY, currentX, key.key);
                            changeColor(currentY, currentX, SudokuPanel.COLOR_DEFAULT);
                            currentY = -1;
                            currentX = -1;
                            AnswerMode(false, -1);
                            ReloadListAnswers();
                            SaveToHistory(data);
                        }
                    }
                }
                else if(e.getActionCommand().equals("SOLVE")){
                    answers[0] = 0;
                    if(currentX != -1 && currentY != -1) {
                        changeColor(currentY, currentX, SudokuPanel.COLOR_DEFAULT);
                        currentX = -1;
                        currentY = -1;
                    }
                    SolveSudoku(data, answersBoards, answers);
                    TopPanel.listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "SET_" + answers[0]));
                    if(DEBUG_MODE) System.out.println("answers found: " + answers[0]);
                    if(answers[0] > 0){
                        AnswerMode(true, answers[0]);
                        ShowResult(data, answersBoards[0]);
                        SaveToHistory(data);
                        currentAnswerShowing = 0;
                        ReloadListAnswers();
                    }
                    else{
                        if(DEBUG_MODE)  System.out.println("NO RESULTS");
                    }
                }
                else if(e.getActionCommand().equals("CLEAR")){
                    TopPanel.listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "DON"));
                    sudokuObject empty = new sudokuObject();
                    ShowResult(data, empty);
                    AnswerMode(false, 0);
                    ReloadListAnswers();
                    SaveToHistory(data);
                }
                else if(e.getActionCommand().equals("BACK")){
                    if(currentlyInHistory >= 2){
                        TopPanel.listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "DON"));
                        data.copy(GetFromHistory());
                        AnswerMode(false, 0);
                        ReloadListAnswers();
                        for(int y = 0; y < 9; y++){
                            for(int x = 0; x < 9; x++){
                                changeValue(y, x, data.get(y, x));
                            }
                        }
                        if(currentlyInHistory <= 1){
                            TopPanel.listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "DISABLE_BACK"));
                        }
                    }
                }
                else if(e.getActionCommand().equals("NEXT-ANS")){
                    if(currentX != -1 && currentY != -1) {
                        changeColor(currentY, currentX, SudokuPanel.COLOR_DEFAULT);
                        currentX = -1;
                        currentY = -1;

                    }
                    if(isAnswerMode && answers[0] > 0 && currentAnswerShowing < maxAns - 1){
                        currentAnswerShowing++;
                        ReloadListAnswers();

                        ShowResult(data, answersBoards[currentAnswerShowing]);
                        SaveToHistory(data);
                    }
                }
                else if(e.getActionCommand().equals("PREV-ANS")){
                    if(currentX != -1 && currentY != -1) {
                        changeColor(currentY, currentX, SudokuPanel.COLOR_DEFAULT);
                        currentX = -1;
                        currentY = -1;
                    }
                    if(isAnswerMode && answers[0] > 0 && currentAnswerShowing > 0){
                        currentAnswerShowing--;
                        ReloadListAnswers();

                        ShowResult(data, answersBoards[currentAnswerShowing]);
                        SaveToHistory(data);
                    }
                }
                else {
                    if(DEBUG_MODE) System.out.println(e.getSource());
                }
            }
        };
    }
    private void ReloadListAnswers(){
        String send = "LIST_";
        if(isAnswerMode){
            send+="EN_";
        }
        else{
            send+="DIS_";
        }
        send+=String.valueOf(currentAnswerShowing) + "_" + String.valueOf(answers[0]);
        if(DEBUG_MODE) System.out.println(send);
        TopPanel.listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, send));
    }
    private void SaveToHistory(sudokuObject s){
        for(int i = historySize - 1; i > 0; i--){
            history[i].copy(history[i - 1]);
        }
        history[0].copy(s);
        if(currentlyInHistory < historySize) {
            currentlyInHistory++;
            if(currentlyInHistory >= 2){
                TopPanel.listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, "ENABLE_BACK"));
            }
        }
    }
    private sudokuObject GetFromHistory(){
        sudokuObject temp = new sudokuObject();
        if(currentlyInHistory > 1){

            temp.copy(history[1]);
            for(int i = 1; i < historySize; i++){
                history[i - 1].copy(history[i]);
            }
            currentlyInHistory--;
        }

        return temp;
    }
    private void AnswerMode(boolean enabled, int answers){
        isAnswerMode = enabled;
        this.answers[0] = answers;
        if(DEBUG_MODE) System.out.println("ANSWER MODE" + enabled + "   answers:" + answers);
    }
    private void changeColor(int y, int x, Color color){
        SudokuPanel.listener.actionPerformed(new ActionEvent(new buttonObject(y, x, color, true, 0, false), ActionEvent.ACTION_PERFORMED, "change_button"));
    }
    private void changeValue(int y, int x, int newValue){
        SudokuPanel.listener.actionPerformed(new ActionEvent(new buttonObject(y, x, Color.BLACK, false, newValue, true), ActionEvent.ACTION_PERFORMED, "change_button"));
    }
    private void SolveSudoku(sudokuObject data, sudokuObject[] ans, int[] curAns){
        if(!data.isValid()){
            curAns[0] = 0;
            return;
        }
        SolveIteration(0, 0, data, ans, curAns);
    }
    private void ShowResult(sudokuObject data, sudokuObject ans){
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                data.set(y, x, ans.get(y, x));
                changeValue(y, x, ans.get(y, x));
            }
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
