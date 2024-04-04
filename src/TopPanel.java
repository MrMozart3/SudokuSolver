import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class BackPanel extends JPanel{
    static Color COLOR_DEFAULT = new Color(201, 201, 201);
    static Color COLOR_HOVER = new Color(220, 220, 220);

    BackPanel(){
        //conf
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //button
        JButton button = new JButton();
        button.setText("BACK");
        button.setBackground(COLOR_DEFAULT);
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setUI(new BasicButtonUI(){
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b){

            }
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(COLOR_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(COLOR_DEFAULT);
            }
        });

        this.add(button, BorderLayout.CENTER);

    }
}
class ClearPanel extends JPanel{
    static Color COLOR_DEFAULT = new Color(219, 0, 11);
    static Color COLOR_HOVER = new Color(207, 2, 12);

    ClearPanel(){
        //conf
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //button
        JButton button = new JButton();
        button.setText("CLEAR");
        button.setBackground(COLOR_DEFAULT);
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setUI(new BasicButtonUI(){
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b){

            }
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(COLOR_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(COLOR_DEFAULT);
            }
        });

        this.add(button, BorderLayout.CENTER);

    }
}
class SolvePanel extends JPanel{
    static Color COLOR_DEFAULT = new Color(30, 255, 0);
    static Color COLOR_HOVER = new Color(23, 196, 0);

    SolvePanel(){
        //conf
        this.setLayout(new BorderLayout());
        //button
        JButton button = new JButton();
        button.setText("SOLVE");
        button.setBackground(COLOR_DEFAULT);
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setUI(new BasicButtonUI(){
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b){

            }
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(COLOR_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(COLOR_DEFAULT);
            }
        });

        this.add(button, BorderLayout.CENTER);

    }
}


public class TopPanel extends JPanel {
    TopPanel(){
        //this.setBackground(Color.GRAY);
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //clearPanel
        ClearPanel clearPanel = new ClearPanel();
        clearPanel.setPreferredSize(new Dimension(100, 50));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        this.add(clearPanel, c);

        SolvePanel solvePanel = new SolvePanel();
        solvePanel.setPreferredSize(new Dimension(210, 50));
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.5;
        this.add(solvePanel, c);

        BackPanel backPanel = new BackPanel();
        backPanel.setPreferredSize(new Dimension(100, 50));
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        backPanel.setBackground(Color.RED);
        this.add(backPanel, c);

        JPanel resultsReturnPanel = new JPanel();
        resultsReturnPanel.setPreferredSize(new Dimension(500, 125));
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0.5;
        c.gridwidth = 3;
        resultsReturnPanel.setBackground(Color.CYAN);
        this.add(resultsReturnPanel, c);
    }
}
