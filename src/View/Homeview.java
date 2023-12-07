package View;

import interface_adapter.Setup.SetupController;
import interface_adapter.Setup.SetupPresenter;
import interface_adapter.Setup.SetupState;

import use_case.setup_game.SetupInputData;
import use_case.setup_game.SetupInteractor;
import use_case.setup_game.SetupOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Homeview extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Home";
    private JButton playButton;
    private JButton setGameModeButton;
    private JButton scoreboardButton;
    private JButton quitButton;
    private final HomeViewModel homeViewModel;
    private final SetupController setupController;

    public Homeview(HomeViewModel homeViewModel, SetupController controller) {
        this.setupController = controller;
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        // Window title
//        setTitle("Solitaire Organizer");

        // Create buttons
        // Play button
        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Initializing the game");
                //Hide Homeview
                setVisible(false);
                // SetupState currentState = setupViewModel.getState();
                System.out.println("1");
                setupController.execute("SinglePlayerGame");
            }
        });

        // Set Game Mode button
        setGameModeButton = new JButton("Select Game Mode");
        setGameModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Select your Game Mode");
                //Hide Homeview
                setVisible(false);

                //Create and display the Select Game Mode GUI
                SelectGameModeView selectGameModeView = new SelectGameModeView();
                selectGameModeView.setVisible(true);
            }
        });

        // Scoreboard button
        scoreboardButton = new JButton("Scoreboard");
        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Check the Scoreboard");
                setVisible(false);

                //Create and display the Select Game Mode GUI
                Scoreboardview scoreboardview = new Scoreboardview();
                scoreboardview.setVisible(true);
                System.out.println("hi");
            }
        });
//        quitButton = new JButton("Quit");
//        quitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int result = JOptionPane.showConfirmDialog(Homeview.this,
//                        "Are you sure to quit the game?", "Confirm Quit", JOptionPane.YES_NO_OPTION);
//                if (result == JOptionPane.YES_OPTION) {
//                    // Terminate the Homeview UI
//                    dispose();
//                }
//            }
//        });

        // Create panels to hold buttons
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(4, 1));
//        buttonPanel.add(playButton);
//        buttonPanel.add(setGameModeButton);
//        buttonPanel.add(scoreboardButton);
//        buttonPanel.add(quitButton);

        //JPanel buttonPanel = new JPanel();
        this.setLayout(new GridLayout(3, 1));
        this.add(playButton);
        this.add(setGameModeButton);
        this.add(scoreboardButton);
        //this.add(quitButton);

        // Add the button panel to the main content pane
        //setContentPane(buttonPanel);


        // Set window size and behavior
        setSize(500, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SetupState state = (SetupState) evt.getNewValue();
        // setFields(state);
    }
}

