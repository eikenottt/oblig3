package no.uib.info233.v2017.rei008_jsi014.oblig4.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TestGUI {

    // Constraint to
    private GridBagConstraints gbc = new GridBagConstraints();

    // Default playername
    private String playerName = "Player 1";

    public static void main(String[] args) {
        new TestGUI();
    }

    private TestGUI() {
        createPlayerNameFrame();
    }


    private void createPlayerNameFrame() {
        // Make a new JFrame
        NewWindow playerNameFrame = new NewWindow(400, 200);

        // Initialize the objects
        JPanel framePanel = new JPanel(), contentPanel = new JPanel();
        JButton continueButton = new JButton("Continue");
        JLabel playerLabel = new JLabel("Playername:");
        JTextField playerNameTextField = new JTextField("", 15);

        // Set Layouts for panels
        framePanel.setLayout(new GridBagLayout());
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // center the button and label
        continueButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        playerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Add everything to panels
        contentPanel.add(playerLabel, gbc);
        contentPanel.add(playerNameTextField, gbc);
        contentPanel.add(continueButton, gbc);
        framePanel.add(contentPanel);
        playerNameFrame.add(framePanel);

        // Listen for buttonpress
        continueButton.addActionListener(e -> {
            if(!playerNameTextField.getText().equals(""))
                playerName = playerNameTextField.getText();
            createMainFrame();
            playerNameFrame.dispose();
        });

        // Listen for "Enter"-key press
        playerNameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    playerName = playerNameTextField.getText();
                    createMainFrame();
                    playerNameFrame.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Show the frame
        playerNameFrame.setVisible(true);
    }

    private void createMainFrame() {
        // Get score from database
        Float score = 0f;

        // Import images
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("icon.png"));
        ImageIcon singleplayerImage = new ImageIcon(getClass().getResource("Singleplayer.png"));
        ImageIcon multiplayerImage = new ImageIcon(getClass().getResource("Multiplayer.png"));

        // Image in a JLabel
        JLabel iconLabel = new JLabel(imageIcon);

        // Make new Frame
        NewWindow mainFrame = new NewWindow(1000, 500);
        mainFrame.setDefaultClose(JFrame.EXIT_ON_CLOSE);

        // Make and initialize the JPanels
        JPanel headPanel = new JPanel(),
                head_nameAndScorePanel = new JPanel(),
                head_buttonPanel = new JPanel(),
                contentPanel = new JPanel(),
                framePanel = new JPanel();

        // Set panel layout
        head_nameAndScorePanel.setLayout(new BorderLayout());
        head_buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.Y_AXIS));
        contentPanel.setLayout(new BorderLayout());
        framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.Y_AXIS));

        head_nameAndScorePanel.setSize(500, 40);

        // Make and initialize JLabels and JButtons
        JLabel playerLabel = new JLabel(playerName, JLabel.CENTER),
                scoreLabel = new JLabel("Score: "+score.toString(), JLabel.NORTH_EAST);
        JButton singleplayerButton = new JButton("Singleplayer", singleplayerImage),
                multiplayerButton = new JButton("Multiplayer", multiplayerImage);

        // Add lables to own panel
        head_nameAndScorePanel.add(playerLabel, BorderLayout.NORTH);
        head_nameAndScorePanel.add(scoreLabel, BorderLayout.EAST);

        // Add buttons to own panel
        head_buttonPanel.add(singleplayerButton);
        head_buttonPanel.add(multiplayerButton);

        // Add picture to content panel
        contentPanel.add(iconLabel);

        // Add labels and buttons in head panel
        headPanel.add(head_nameAndScorePanel);
        headPanel.add(head_buttonPanel);

        // Add head and content panels in frame panel
        framePanel.add(headPanel);
        framePanel.add(contentPanel);

        // Add the frame panel to the frame
        mainFrame.add(framePanel);

        // Set frame visible
        mainFrame.setVisible(true);
    }
}
