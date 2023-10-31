package View;

import interface_adapter.Setup.SetupController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homeview extends JFrame {
    private JButton playButton;
    private JButton setGameModeButton;
    private JButton scoreboardButton;
    private JButton quitButton;

    private SetupController setupController;

    public Homeview() {
        // Window title
        setTitle("Solitaire Organizer");

        // Create buttons
        // Play button
        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Initializing the game");
                //Hide Homeview
                setVisible(false);

                //Create and display the playsession's Gameview GUI
                Gameview gameview = new Gameview();
                gameview.setVisible(true);
            }
        });

        // Set Game Mode button
        setGameModeButton = new JButton("Select Game Mode");
        setGameModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Select your Game Mode");
            }
        });

        // Scoreboard button
        scoreboardButton = new JButton("Scoreboard");
        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Check the Scoreboard");
            }
        });
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(Homeview.this,
                        "Are you sure to quit the game?", "Confirm Quit", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Terminate the Homeview UI
                    dispose();
                }
            }
        });

        // Create panels to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(playButton);
        buttonPanel.add(setGameModeButton);
        buttonPanel.add(scoreboardButton);
        buttonPanel.add(quitButton);

        // Add the button panel to the main content pane
        setContentPane(buttonPanel);


        // Set window size and behavior
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Homeview homeview = new Homeview();
                homeview.setVisible(true);
            }
        });
    }
}

