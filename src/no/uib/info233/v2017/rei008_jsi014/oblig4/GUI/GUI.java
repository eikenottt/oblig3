package no.uib.info233.v2017.rei008_jsi014.oblig4.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by runeeikemo on 09.05.2017.
 */
public class GUI extends JFrame {

    private Color bgColor;
    private JPanel p = new JPanel();
    private JButton b = new JButton("Continue");
    private JPanel panel2 = new JPanel();
    private JTextField textField = new JTextField("", 15);
    private JLabel label1 = new JLabel("Playername:", JLabel.CENTER);

    public static void main(String[] args) {

        new GUI();

    }

    private GUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        bgColor = new Color(70, 70, 70);
        setPlayerNameGUI();
        this.setVisible(true);
    }

    private void setPlayerNameGUI() {
        Font avenir = new Font("Avenir", Font.BOLD, 20);

        JOptionPane optionPane = new JOptionPane("Write your playername", JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION,null, null, "player1");

        this.setFont(avenir);

        this.setSize(400,200);

        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension dim = tk.getScreenSize();

        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);

        this.setLocation(xPos, yPos);

        this.setResizable(false);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setTitle("Sword Fight");

        label1.setToolTipText("Please enter your playername");

        label1.setForeground(new Color(255,255,255));
        label1.setFont(avenir);

        label1.setSize(400,30);

        p.add(label1);

        b.setToolTipText("Continue to the main screen");

        b.setFont(avenir);

        p.add(b);

        textField.setColumns(10);

        textField.setFont(avenir);

        p.add(textField);

        p.setLayout(new FlowLayout());

        p.setBackground(bgColor);

        panel2.setBackground(bgColor);

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel2.add(label1);
        panel2.add(textField);
        panel2.add(b);

        p.add(panel2);

        this.add(p, BorderLayout.CENTER);
    }
}
