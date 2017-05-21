package no.uib.info233.v2017.rei008_jsi014.oblig4.GUI;

import no.uib.info233.v2017.rei008_jsi014.oblig4.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Callable;


public class TestGUI {

    // Constraint to
    private GridBagConstraints gbc = new GridBagConstraints();

    // Default playername
    private String playerName = "Player 1";
    private String player2Name;

    // Get score from database
    private Float score = 0f;

    private GameMaster gameMaster;

    private Player player1;
    private Player player2;

    private JProgressBar player1EnergyBar,
            player2EnergyBar;

    private JLabel something = new JLabel();

    private boolean isMultiplayerGame = false;

    private JFrame gamePlayFrame, loadingFrame, multiplayerFrame, mainFrame;

    public TestGUI() {
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
            if (!playerNameTextField.getText().equals(""))
                playerName = playerNameTextField.getText();
            player1 = new HumanPlayer(playerName);
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
                    if(!playerNameTextField.getText().equals(""))
                        playerName = playerNameTextField.getText();
                    player1 = new HumanPlayer(playerName);
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

        playerNameFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int sureYouWantToQuit = JOptionPane.showConfirmDialog(playerNameFrame, "Are you sure you want to quit the game?", "Quit Game", JOptionPane.YES_NO_OPTION);
                if (sureYouWantToQuit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

    }

    private void createMainFrame() {

        // Import images
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("img/icon.png"));
        ImageIcon singleplayerImage = new ImageIcon(getClass().getResource("img/Singleplayer.png"));
        ImageIcon multiplayerImage = new ImageIcon(getClass().getResource("img/Multiplayer.png"));

        // Image in a JLabel
        JLabel iconLabel = new JLabel(imageIcon);

        // Make new Frame
        JFrame mainFrame = new NewWindow(1000, 500);

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

        singleplayerButton.addActionListener(e -> {
            isMultiplayerGame = false;
            createSingleplayerFrame();
            mainFrame.dispose();
        });

        multiplayerButton.addActionListener(e -> {
            isMultiplayerGame = true;
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
        JFrame multiplayerFrame = new NewWindow(1000, 500);
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
            SwingUtilities.invokeLater(this::createGamePlayFrame);
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

        player2Name = n.getPlayerName();

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
        JFrame loadingFrame = new NewWindow(400, 200);
        ImageIcon loading = new ImageIcon(getClass().getResource("img/Loading.gif"));

        JPanel framePanel = new JPanel();
        framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.Y_AXIS));

        JLabel iconHolder = new JLabel(loading);

        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> loadingFrame.dispose());

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);

        framePanel.add(iconHolder);
        framePanel.add(cancelButton);

        loadingFrame.add(framePanel);

        loadingFrame.setVisible(true);

    }

    private void createGamePlayFrame() {

        ImageIcon battleZoneImage = new ImageIcon(getClass().getResource("img/battlezone.png"));
        ImageIcon playerIconsImage = new ImageIcon(getClass().getResource("img/Player-icons.png"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = Component.TOP_ALIGNMENT;

        JFrame gamePlayFrame = new NewWindow(1000, 500);
        gamePlayFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                leaveGame(); // TODO FIX method
            }
        });

        JPanel headPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)),
                contentPanel = new JPanel(),
                framePanel = new JPanel(),
                battleButtonPanel = new JPanel(new GridBagLayout()),
                attack1Panel = new JPanel(),
                attack2Panel = new JPanel(),
                attack3Panel = new JPanel();

        JLayeredPane layeredPane = new JLayeredPane();

        layeredPane.setPreferredSize(new Dimension(860, 100));

        attack1Panel.setLayout(new BoxLayout(attack1Panel, BoxLayout.Y_AXIS));
        attack2Panel.setLayout(new BoxLayout(attack2Panel, BoxLayout.Y_AXIS));
        attack3Panel.setLayout(new BoxLayout(attack3Panel, BoxLayout.Y_AXIS));

        framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.Y_AXIS));

        battleButtonPanel.setPreferredSize(new Dimension(1000, 200));
        battleButtonPanel.setMaximumSize(new Dimension(1000, 300));
        battleButtonPanel.setBackground(new Color(38, 38, 38));

        JLabel player1NameLabel = new JLabel(player1.getName()),
                player2NameLabel = new JLabel(player2.getName()),
                energyLabel = new JLabel("Energy"),
                battlezoneImageLabel = new JLabel(battleZoneImage, JLabel.CENTER),
                playerIconLabel = new JLabel(playerIconsImage),
                attack1Label = new JLabel("0 - 50 Energy"),
                attack2Label = new JLabel("5 - 20 Energy"),
                attack3Label = new JLabel("15 - 40 Energy");

        layeredPane.add(battlezoneImageLabel, new Integer(1));
        layeredPane.add(playerIconLabel, new Integer(2));

        JButton stabAttackButton = new JButton("Stab"),
                slashAttackButton = new JButton("Slash"),
                overHSwingAttackButton = new JButton("Overhead Swing");

        stabAttackButton.addActionListener(e -> useAttack("stab"));

        slashAttackButton.addActionListener(e -> useAttack("slash"));

        overHSwingAttackButton.addActionListener(e -> useAttack("overhead swing"));

        player1EnergyBar = new JProgressBar(0,100);
        player2EnergyBar = new JProgressBar(0,100);
        player1EnergyBar.setForeground(Color.RED);
        player2EnergyBar.setBackground(Color.BLUE);
        player2EnergyBar.setForeground(new Color(70,70,70));
        player2EnergyBar.setStringPainted(true);
        player1EnergyBar.setStringPainted(true);
        player1EnergyBar.setValue(player1.getCurrentEnergy());
        player2EnergyBar.setValue(100-player2.getCurrentEnergy());
        player1EnergyBar.setString(player1.getCurrentEnergy()+"");
        player2EnergyBar.setString(player2.getCurrentEnergy()+"");


        if (isMultiplayerGame) {
            JLabel waitingForPlayer = new JLabel("Waiting For "+ player2.getName() +" to move");
            contentPanel.add(waitingForPlayer);
        }

        headPanel.add(player1NameLabel);
        headPanel.add(player1EnergyBar);
        headPanel.add(energyLabel);
        headPanel.add(player2EnergyBar);
        headPanel.add(player2NameLabel);
        contentPanel.add(battlezoneImageLabel);
        attack1Panel.add(stabAttackButton);
        attack1Panel.add(attack1Label);
        attack2Panel.add(slashAttackButton);
        attack2Panel.add(attack2Label);
        attack3Panel.add(overHSwingAttackButton);
        attack3Panel.add(attack3Label);
        battleButtonPanel.add(attack1Panel, gbc);
        battleButtonPanel.add(attack2Panel, gbc);
        battleButtonPanel.add(attack3Panel, gbc);

        framePanel.add(headPanel);
        framePanel.add(contentPanel);
        framePanel.add(battleButtonPanel);

        gamePlayFrame.add(framePanel);

        gamePlayFrame.setVisible(true);
    }


    private void createSingleplayerFrame() {
        // Import images
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("img/icon.png"));

        // Image in a JLabel
        JLabel iconLabel = new JLabel(imageIcon);

        // Make new Frame
        NewWindow singleplayerFrame = new NewWindow(1000, 500);
        singleplayerFrame.setDefaultClose(JFrame.DO_NOTHING_ON_CLOSE);

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

        JButton newGameButton = new JButton("New Game"),
                loadGameButton = new JButton("Load Game"),
                quitGameButton = new JButton("Back to Menu");


        // Listen for button press

        newGameButton.addActionListener(e -> {
            player1.setCurrentEnergy(100);
            player2 = new PassivePlayer("CPU");
            gameMaster = new GameMaster();
            gameMaster.setPlayers(player1, player2);
            createGamePlayFrame();
            singleplayerFrame.dispose();
        });

        loadGameButton.addActionListener(e -> {
            createMultiplayerFrame();
            singleplayerFrame.dispose();
        });

        quitGameButton.addActionListener(e -> {
            createMainFrame();
            singleplayerFrame.dispose();
        });

        singleplayerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int sureYouWantToQuit = JOptionPane.showConfirmDialog(singleplayerFrame,"Are you sure you want to quit the game?", "Quit Game", JOptionPane.YES_NO_OPTION);
                if(sureYouWantToQuit == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

        // Add lables to own panel
        head_nameAndScorePanel.add(playerLabel, BorderLayout.NORTH);
        head_nameAndScorePanel.add(scoreLabel, BorderLayout.EAST);

        // Add buttons to own panel
        head_buttonPanel.add(newGameButton);
        head_buttonPanel.add(loadGameButton);
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
        singleplayerFrame.add(framePanel);

        // Set frame visible
        singleplayerFrame.setVisible(true);

    }

    public void joinMultiplayerGame() {
        try {
            createLoadingFrame();
            player1 = new HumanPlayer(playerName);
            player2 = new HumanPlayer(player2Name);
            gameMaster = new GameMaster();
            gameMaster.setPlayers(player1, player2);
            createGamePlayFrame();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(gamePlayFrame,"There were a problem connecting to the other player, please try again");
        }
    }

    private void useAttack(String attack) {
        int energyUsed = 0;
        int energyLeft = 0;
        switch (attack) {
            case "stab":
                energyUsed = player1.stab(player1.getCurrentEnergy());
                energyLeft = player1.getCurrentEnergy() - energyUsed;
                break;
            case "slash":
                energyUsed = player1.slash(player1.getCurrentEnergy());
                energyLeft = player1.getCurrentEnergy() - energyUsed;
                break;
            case "overhead swing":
                energyUsed = player1.overheadSwing(player1.getCurrentEnergy());
                energyLeft = player1.getCurrentEnergy() - energyUsed;
                break;
        }
        player1EnergyBar.setValue(energyLeft);
        player1.updateEnergy(-energyUsed);
        player2.makeNextMove(player2.getCurrentPosition(), player2.getCurrentEnergy(), player1.getCurrentEnergy());
        player2EnergyBar.setValue(100 - player2.getCurrentEnergy());

        player1EnergyBar.setString(""+player1.getCurrentEnergy());
        player2EnergyBar.setString(player2.getCurrentEnergy()+"");
    }

    //TODO Fikse at den lukker seg om en trykker nei
    private void leaveGame() {
        JOptionPane.showConfirmDialog(gamePlayFrame, "Are you sure you want to resign?", "Resign Game", JOptionPane.YES_NO_OPTION);
    }



}
