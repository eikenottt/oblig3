package no.uib.info233.v2017.rei008_jsi014.oblig4.GUI;

import no.uib.info233.v2017.rei008_jsi014.oblig4.Connector;
import no.uib.info233.v2017.rei008_jsi014.oblig4.GameMaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Created by runeeikemo on 12.05.2017.
 */
class ListTest extends JComponent {

    private JPanel m;
    private ArrayList<ArrayList<JComponent>> array = new ArrayList<>();
    private HashMap<String, Float> players = new HashMap<>();
    private HashMap<String, HashMap<String, Float>> playerMap = new HashMap<>();
    private final String[] playerName = new String[0];

    JPanel ytterst = new JPanel(new BorderLayout());

    public ListTest() {
        populateMiltiplayerTable();
    }

    public JPanel getPanel() {
        ArrayList<JComponent> arr = new ArrayList<>();
        arr.add(new JLabel("Gamename"));
        arr.add(new JLabel("Highscore", JLabel.CENTER));
        arr.add(new JLabel("Join Game"));
        array.add(arr);
        getFromStuff();


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

    private String getFromStuff() {
        for (String player_id : playerMap.keySet()) {
            for(String playername : playerMap.get(player_id).keySet()) {
                ArrayList<JComponent> array2 = new ArrayList<>();
                array2.add(new JLabel(playername));
                array2.add(new JLabel(players.get(playername).toString(), JLabel.CENTER));
                array2.add(new JButton(new AbstractAction("Join") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        playerName[0] = playername;
                    }
                }));
                array.add(array2);
            }
        }
        return playerName[0];
    }

    private void populateMiltiplayerTable () {

        Connector conn = new Connector();
        playerMap = conn.getMultiplayerMap(players);
    }

    public String getPlayerName() {
        return playerName[0];
    }


}