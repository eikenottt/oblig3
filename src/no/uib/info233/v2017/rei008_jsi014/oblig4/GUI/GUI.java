package no.uib.info233.v2017.rei008_jsi014.oblig4.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by runeeikemo on 09.05.2017.
 */
public class GUI extends JFrame {

    public static void main(String[] args) {

        new GUI();

    }

    public GUI() {
        this.setSize(400,400);

        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension dim = tk.getScreenSize();

        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);

        this.setLocation(xPos, yPos);

        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("My First Frame");

        JPanel thePanel = new JPanel();

        JLabel label1 = new JLabel("Tell Me Something");

        label1.setText("New Text");

        label1.setToolTipText("Doesn't do anything");

        thePanel.add(label1);

        JButton button1 = new JButton("Send");

        button1.setText("New Button");

        button1.setToolTipText("It's a button");

        thePanel.add(button1);

        JTextField textField = new JTextField("Type here", 15);

        textField.setColumns(10);

        thePanel.add(textField);

        this.add(thePanel);

        this.setVisible(true);
    }
}
