package no.uib.info233.v2017.rei008_jsi014.oblig4.GUI;

import no.uib.info233.v2017.rei008_jsi014.oblig4.AggressivePlayer;
import no.uib.info233.v2017.rei008_jsi014.oblig4.Player;

import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;
import java.awt.*;
import java.awt.event.*;


public class TestGUI {

    // Constraint to
    private GridBagConstraints gbc = new GridBagConstraints();

    // Default playername
    private String playerName = "Player 1";

    // Get score from database
    private Float score = 0f;

    Player player1 = new AggressivePlayer("Kalle");

    public static void main(String[] args) {
        new TestGUI();
    }

    private TestGUI() {
        createPlayerNameFrame();
        //createMultiplayerFrame();
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

        // Import images
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("img/icon.png"));
        ImageIcon singleplayerImage = new ImageIcon(getClass().getResource("img/Singleplayer.png"));
        ImageIcon multiplayerImage = new ImageIcon(getClass().getResource("img/Multiplayer.png"));

        // Image in a JLabel
        JLabel iconLabel = new JLabel(imageIcon);

        // Make new Frame
        NewWindow mainFrame = new NewWindow(1000, 500);
        mainFrame.setDefaultClose(JFrame.DO_NOTHING_ON_CLOSE);

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
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.Y_AXIS));

        head_nameAndScorePanel.setSize(500, 40);

        // Make and initialize JLabels and JButtons
        JLabel playerLabel = new JLabel(playerName, JLabel.CENTER),
                scoreLabel = new JLabel("Score: "+score.toString(), JLabel.RIGHT);

        JButton singleplayerButton = new JButton("Singleplayer", singleplayerImage),
                multiplayerButton = new JButton("Multiplayer", multiplayerImage),
                quitGameButton = new JButton("Quit Game");


        // Listen for button press
        multiplayerButton.addActionListener(e -> {
            createMultiplayerFrame();
            mainFrame.dispose();
        });

        quitGameButton.addActionListener(e -> {
            int sureYouWantToQuit = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to quit the game?", "Quit Game", JOptionPane.YES_NO_OPTION);
            if(sureYouWantToQuit == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int sureYouWantToQuit = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to quit the game?", "Quit Game", JOptionPane.YES_NO_OPTION);
                if(sureYouWantToQuit == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

        // Add lables to own panel
        head_nameAndScorePanel.add(playerLabel, BorderLayout.NORTH);
        head_nameAndScorePanel.add(scoreLabel, BorderLayout.EAST);

        // Add buttons to own panel
        head_buttonPanel.add(singleplayerButton);
        head_buttonPanel.add(multiplayerButton);
        head_buttonPanel.add(quitGameButton);

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

    private void createMultiplayerFrame() {
        NewWindow multiplayerFrame = new NewWindow(1000, 500);
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
                scoreLabel = new JLabel("Score: "+score.toString(), JLabel.RIGHT);

        JButton createGameButton = new JButton("Create Game"),
                backToMenuButton = new JButton("Back to Menu");


        // Listen for buttonpress
        createGameButton.addActionListener(e -> {
            createLoadingFrame();
            multiplayerFrame.dispose();
        });


        backToMenuButton.addActionListener(e -> {
            createMainFrame();
            multiplayerFrame.dispose();
        });

        multiplayerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                createMainFrame();
                multiplayerFrame.dispose();
            }
        });

        // Add lables to own panel
        head_nameAndScorePanel.add(playerLabel, BorderLayout.NORTH);
        head_nameAndScorePanel.add(scoreLabel, BorderLayout.EAST);

        // Add buttons to own panel
        head_buttonPanel.add(createGameButton, BorderLayout.WEST);
        head_buttonPanel.add(backToMenuButton, BorderLayout.EAST);

        // Add List to content panel
        ListTest n = new ListTest();
        contentPanel.add(n.getPanel());

        // Add labels and buttons in head panel
        headPanel.add(head_nameAndScorePanel);
        headPanel.add(head_buttonPanel);

        // Add head and content panels in frame panel
        framePanel.add(headPanel);
        framePanel.add(contentPanel);

        // Add the frame panel to the frame
        multiplayerFrame.add(framePanel);

        // Set frame visible
        multiplayerFrame.setVisible(true);
    }

    private void createLoadingFrame() {
        NewWindow loadingFrame = new NewWindow(400, 200);
        ImageIcon loading = new ImageIcon(getClass().getResource("img/Loading.gif"));

        JPanel framePanel = new JPanel();
        framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.Y_AXIS));

        JLabel iconHolder = new JLabel(loading);

        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> {
            createMultiplayerFrame();
            loadingFrame.dispose();
        });

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);

        framePanel.add(iconHolder);
        framePanel.add(progressBar);
        framePanel.add(cancelButton);

        loadingFrame.add(framePanel);

        loadingFrame.setVisible(true);

    }

}
