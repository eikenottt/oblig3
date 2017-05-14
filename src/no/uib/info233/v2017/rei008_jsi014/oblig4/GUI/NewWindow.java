package no.uib.info233.v2017.rei008_jsi014.oblig4.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.util.Enumeration;

/**
 * Created by Rune on 11.05.2017.
 */
public class NewWindow extends JFrame {
    public NewWindow(int width, int height) {
        setUI();
        setSize(width,height);
        setResizable(false);

        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension dim = tk.getScreenSize();

        int xPos = (dim.width / 2) - (getWidth() / 2);
        int yPos = (dim.height / 2) - (getHeight() / 2);

        setLocation(xPos, yPos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setDefaultClose(int CLOSE) {
        setDefaultCloseOperation(CLOSE);
    }

    private void setUI() {
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
                    if(key.toString().contains("Button.foreground"))
                        UIManager.put(key, fg);
                    if(key.toString().contains("Button.select"))
                        UIManager.put(key, bg);
                    if(key.toString().contains("Button.focus"))
                        UIManager.put(key, buttonBG);
                    if(key.toString().contains("TextField.background"))
                        UIManager.put(key, fg);
                    if(key.toString().contains("TextField.foreground"))
                        UIManager.put(key, bg);
                    if(key.toString().contains("List.background"))
                        UIManager.put(key, buttonBG);
                    if(key.toString().contains("OptionPane.messageForeground")) {
                        UIManager.put(key, fg);
                    }
                }
            }
        }
    }
}
