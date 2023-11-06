package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;


public class Gameview extends JFrame {
    private JLabel timerLabel;
    private long startTime; // Track the start time in milliseconds

    public Gameview() {
        // Set the frame to full-screen mode
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false); // Set to true for full-screen

        // Initialize the start time to zero
        startTime = 0;

        // Create and configure the timer label
        timerLabel = new JLabel("00:00:00");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create a Timer to update the timer label in real-time
        Timer gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime += 1000; // Increase the start time by one second
                long seconds = startTime / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                String timeString = String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
                timerLabel.setText(timeString);
            }
        });
        gameTimer.start();

        // Add the timer label to the content pane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(timerLabel, BorderLayout.NORTH);

        // Set the default close operation to exit on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a mini-menu panel
        JPanel miniMenuPanel = new JPanel(new FlowLayout());

        // Add the quit button
        JButton quitButton = new JButton("Exit to Desktop");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(Gameview.this,
                        "Are you certain to leave the game?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Close the Gameview
                    dispose();
                    // You can also perform any additional cleanup or actions here
                    // For example, returning to the Homeview, etc.
                }
            }
        });
        miniMenuPanel.add(quitButton);

        // Add a "Close" button to the mini-menu
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miniMenuPanel.setVisible(false); // Hide the mini-menu
            }
        });
        miniMenuPanel.add(closeButton);

        // Add a "Return to Home Menu" button to the mini-menu
        JButton returnButton = new JButton("Return to Home Menu");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(Gameview.this,
                        "Are you sure to return to Home Menu?", "Confirm Return", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Terminate Gameview and return to Homeview
                    dispose(); // Close Gameview
                    Homeview homeview = new Homeview();
                    homeview.setVisible(true); // Show Homeview
                }
            }
        });
        miniMenuPanel.add(returnButton);

        // Add the mini-menu panel to the content pane
        getContentPane().add(miniMenuPanel, BorderLayout.SOUTH);
        miniMenuPanel.setVisible(false); // Initially hide the mini-menu

        // Register a KeyAdapter to listen for the Escape key
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    miniMenuPanel.setVisible(true); // Show the mini-menu
                }
            }
        });

        // Enable focus on the frame for key events
        setFocusable(true);
        requestFocus();

        // Make the frame visible
        setVisible(true);
    }

}