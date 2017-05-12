package no.uib.info233.v2017.rei008_jsi014.oblig4.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by runeeikemo on 12.05.2017.
 */
public class ListTest extends JComponent {

    private JPanel m;
    private ArrayList<ArrayList<JComponent>> array = new ArrayList<>();

    JPanel ytterst = new JPanel(new BorderLayout());

    public ListTest() {

    }

    public JPanel getPanel() {
        getFromStuff(100);


        m = new JPanel();
        m.setLayout(new BoxLayout(m, BoxLayout.Y_AXIS));

        amount(m, array);

        JScrollPane scrollPane = new JScrollPane(m);
        scrollPane.setForeground(new Color(70, 70, 70));
        ytterst.add(scrollPane, BorderLayout.CENTER);
        return ytterst;
    }

    private void amount(JPanel pan , ArrayList<ArrayList<JComponent>> num) {

        for (int i = 0; i < num.size(); i++) {
            JPanel m = new JPanel(new BorderLayout());

            m.setSize(new Dimension(1000, 30));
            m.setMinimumSize(new Dimension(1000, 30));
            m.setMaximumSize(new Dimension(1000, 30));

            if(i%2 == 0) {
                m.setBackground(new Color(54,54,54));
            }
            else {
                m.setBackground(new Color(37,37,37));
            }

            m.add(num.get(i).get(0), BorderLayout.WEST);
            m.add(num.get(i).get(1), BorderLayout.CENTER);
            m.add(num.get(i).get(2), BorderLayout.EAST);

            pan.add(m);

        }
    }

    private void getFromStuff(int num) {

        for (int i=0; i < num; i++) {
            ArrayList<JComponent> array2 = new ArrayList<>();
            array2.add(new JLabel("GameName"));
            array2.add(new JLabel("Highscore", JLabel.CENTER));
            array2.add(new JButton("Join"));
            array.add(array2);
        }
    }


}
