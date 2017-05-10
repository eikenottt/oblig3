package no.uib.info233.v2017.rei008_jsi014.oblig4.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by runeeikemo on 09.05.2017.
 */
public class GUI extends JFrame {

    private final Color bgColor;

    public static void main(String[] args) {

        new GUI();

    }

    public GUI() {

        Font avenir = new Font("Avenir", Font.BOLD, 20);

        this.setFont(avenir);

        bgColor = new Color(70, 70, 70);

        this.setSize(400,200);

        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension dim = tk.getScreenSize();

        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);

        this.setLocation(xPos, yPos);

        this.setResizable(false);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setTitle("Sword Fight");

        JPanel thePanel = new JPanel();

        JLabel label1 = new JLabel("Playername:", JLabel.CENTER);

        label1.setToolTipText("Please enter your playername");

        label1.setForeground(new Color(255,255,255));
        label1.setFont(avenir);

        label1.setSize(400,30);

        thePanel.add(label1);

        JButton button1 = new JButton("Continue");

        button1.setToolTipText("Continue to the main screen");

        button1.setFont(avenir);

        thePanel.add(button1);

        JTextField textField = new JTextField("", 15);

        textField.setColumns(10);

        textField.setFont(avenir);

        thePanel.add(textField);

        thePanel.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();

        thePanel.setBackground(bgColor);

        panel2.setBackground(bgColor);

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel2.add(label1);
        panel2.add(textField);
        panel2.add(button1);

        thePanel.add(panel2);

        this.add(thePanel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
