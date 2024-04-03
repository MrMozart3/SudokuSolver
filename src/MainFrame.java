import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame{
    MainFrame(){
        this.setMinimumSize(new Dimension(600, 850));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(0x323233));
        this.setLocationRelativeTo(null);



        //TopPanel
        TopPanel topPanel = new TopPanel();
        topPanel.setOpaque(true);
        this.add(topPanel, BorderLayout.CENTER);
        //SudokuPanel
        SudokuPanel sudokuPanel = new SudokuPanel(3);
        sudokuPanel.setPreferredSize(new Dimension(600, 600));
        this.add(sudokuPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
