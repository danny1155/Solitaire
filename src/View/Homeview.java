package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homeview extends JFrame {
    private JButton playButton;
    private JButton setGameModeButton;
    private JButton scoreboardButton;

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

        // Create panels to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.add(playButton);
        buttonPanel.add(setGameModeButton);
        buttonPanel.add(scoreboardButton);

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

