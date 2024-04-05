import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SudokuPanel extends JPanel {
    public static Color COLOR_DEFAULT = Color.WHITE;
    public static Color COLOR_HOVER = Color.LIGHT_GRAY;
    public static Color COLOR_SELECT = Color.GREEN;
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
    public static ActionListener listener;
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
                buttons[y][x] = new ButtonPanel(y, x, walls[0], walls[1], walls[2], walls[3]);
                this.add(buttons[y][x]);
            }
        }
        //listener
        listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() instanceof buttonObject b){
                    if(b.changeColor){
                        buttons[b.y][b.x].changeBackground(b.newColor);
                    }
                    if(b.changeValue){
                        if(b.newValue == 0){
                            buttons[b.y][b.x].changeText("");
                        }
                        else{
                            buttons[b.y][b.x].changeText(String.valueOf(b.newValue));
                        }
                    }
                }
            }
        };


        //keyBindings
        //1
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "1_PRESSED");
        this.getActionMap().put("1_PRESSED", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.listener.actionPerformed(new ActionEvent(new keyObject(1), ActionEvent.ACTION_PERFORMED, "BUTTON PRESSED"));
            }
        });
        //2
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "2_PRESSED");
        this.getActionMap().put("2_PRESSED", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.listener.actionPerformed(new ActionEvent(new keyObject(2), ActionEvent.ACTION_PERFORMED, "BUTTON PRESSED"));
            }
        });
        //3
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "3_PRESSED");
        this.getActionMap().put("3_PRESSED", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.listener.actionPerformed(new ActionEvent(new keyObject(3), ActionEvent.ACTION_PERFORMED, "BUTTON PRESSED"));
            }
        });
        //4
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "4_PRESSED");
        this.getActionMap().put("4_PRESSED", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.listener.actionPerformed(new ActionEvent(new keyObject(4), ActionEvent.ACTION_PERFORMED, "BUTTON PRESSED"));
            }
        });
        //5
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "5_PRESSED");
        this.getActionMap().put("5_PRESSED", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.listener.actionPerformed(new ActionEvent(new keyObject(5), ActionEvent.ACTION_PERFORMED, "BUTTON PRESSED"));
            }
        });
        //6
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "6_PRESSED");
        this.getActionMap().put("6_PRESSED", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.listener.actionPerformed(new ActionEvent(new keyObject(6), ActionEvent.ACTION_PERFORMED, "BUTTON PRESSED"));
            }
        });
        //7
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "7_PRESSED");
        this.getActionMap().put("7_PRESSED", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.listener.actionPerformed(new ActionEvent(new keyObject(7), ActionEvent.ACTION_PERFORMED, "BUTTON PRESSED"));
            }
        });
        //8
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "8_PRESSED");
        this.getActionMap().put("8_PRESSED", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.listener.actionPerformed(new ActionEvent(new keyObject(8), ActionEvent.ACTION_PERFORMED, "BUTTON PRESSED"));
            }
        });
        //9
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), "9_PRESSED");
        this.getActionMap().put("9_PRESSED", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.listener.actionPerformed(new ActionEvent(new keyObject(9), ActionEvent.ACTION_PERFORMED, "BUTTON PRESSED"));
            }
        });
        //del
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "BACKSPACE_PRESSED");
        this.getActionMap().put("BACKSPACE_PRESSED", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver.listener.actionPerformed(new ActionEvent(new keyObject(10), ActionEvent.ACTION_PERFORMED, "BUTTON PRESSED"));
            }
        });
    }
}
