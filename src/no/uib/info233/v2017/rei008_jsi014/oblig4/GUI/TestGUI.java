package no.uib.info233.v2017.rei008_jsi014.oblig4.GUI;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * Created by Rune on 11.05.2017.
 */
public class TestGUI extends JFrame {
    public static void main(String[] args) {
        new TestGUI();
    }

    private TestGUI() {
        initializeUI();
        JPanel p = new JPanel();
        JPanel p2 = new JPanel();
        p.setLayout(new GridBagLayout());
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        GridBagConstraints gbc = new GridBagConstraints();
        JButton b = new JButton("Continue");
        b.setAlignmentX(JButton.CENTER_ALIGNMENT);
        JLabel l = new JLabel("Playername:");
        l.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JTextField tf = new JTextField("", 15);
        p2.add(l, gbc);
        p2.add(tf, gbc);
        p2.add(b, gbc);
        p.add(p2);
        add(p);
        setVisible(true);
    }

    private void setUI() {
        FontUIResource f = new FontUIResource("Avenir", Font.BOLD, 20);
        ColorUIResource bg = new ColorUIResource(70,70,70);
        ColorUIResource fg = new ColorUIResource(255,255,255);
        Color buttonBG = new Color(120,120,120);
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value != null) {
                if (value instanceof FontUIResource) {
                    UIManager.put(key, f);
                }
                if (value instanceof ColorUIResource) {
                    if(key.toString().contains("background"))
                        UIManager.put(key, bg);
                    if(key.toString().contains("foreground"))
                        UIManager.put(key, fg);
                    if(key.toString().contains("Button.background"))
                        UIManager.put(key, buttonBG);
                    if(key.toString().contains("Button.select")) {
                        UIManager.put(key, bg);
                    }
                    if(key.toString().contains("TextField.background"))
                        UIManager.put(key, fg);
                    if(key.toString().contains("TextField.foreground"))
                        UIManager.put(key, bg);
                }
            }
        }
    }

    private void initializeUI() {
        setUI();
        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
