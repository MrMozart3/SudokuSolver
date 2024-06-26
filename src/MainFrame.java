import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    MainFrame(){
        this.setMinimumSize(new Dimension(600, 850));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(0x323233));
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("assets/icon.png");
        this.setIconImage(icon.getImage());

        this.setTitle("Sudoku Solver");



        //TopPanel
        TopPanel topPanel = new TopPanel();
        this.add(topPanel, BorderLayout.CENTER);
        //SudokuPanel
        SudokuPanel sudokuPanel = new SudokuPanel(3);
        sudokuPanel.setPreferredSize(new Dimension(600, 600));
        this.add(sudokuPanel, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
