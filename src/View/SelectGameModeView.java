package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGameModeView extends JFrame {

    public SelectGameModeView() {
        setTitle("Game Mode Selector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1)); // Four components: three buttons and a "Back" button

        JButton easyModeButton = new JButton("SinglePlayer Easy");
        JButton mediumModeButton = new JButton("SinglePlayer Hard");
        JButton hardModeButton = new JButton("Limitless Time");
//        JButton backButton = new JButton("Back");

        easyModeButton.addActionListener(new ButtonClickListener(1));
        mediumModeButton.addActionListener(new ButtonClickListener(2));
        hardModeButton.addActionListener(new ButtonClickListener(3));
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Code to navigate back to HomeView or any other view
//                // For demonstration, let's assume there's a HomeView class
//                dispose(); // Close Gameview
//                Homeview homeview = new Homeview();
//                homeview.setVisible(true); // Assuming HomeView has a default constructor to display the view
//            }
//        });

        panel.add(easyModeButton);
        panel.add(mediumModeButton);
        panel.add(hardModeButton);
//        panel.add(backButton);

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(panel);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int selectedMode;

        ButtonClickListener(int mode) {
            this.selectedMode = mode;
        }

        public void actionPerformed(ActionEvent e) {
            switch (selectedMode) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Starting SinglePlayer Easy Mode...");
                    // Add your logic for the easy mode here
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Starting SinglePlayer Hard Mode...");
                    // Add your logic for the medium mode here
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Starting Limitless Time Mode...");
                    // Add your logic for the hard mode here
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid game mode selected. Exiting...");
            }
        }
    }

    public static void displayGameModeSelector() {
        SwingUtilities.invokeLater(() -> {
            SelectGameModeView gameModeView = new SelectGameModeView();
            gameModeView.setVisible(true);
        });
    }

    //public static void main(String[] args) {
    //    displayGameModeSelector();
    //}


}

