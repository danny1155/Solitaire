package View;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGameModeView extends JFrame {

    public SelectGameModeView() {
        setTitle("Game Mode Selector");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set to DISPOSE_ON_CLOSE
        setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1)); // Four components: three buttons and a "Back" button

        JButton easyModeButton = new JButton("SinglePlayer Mode");
        JButton mediumModeButton = new JButton("MultiPlayer Mode");
        JButton hardModeButton = new JButton("Some Mode");

        easyModeButton.addActionListener(new ButtonClickListener(1));
        mediumModeButton.addActionListener(new ButtonClickListener(2));
        hardModeButton.addActionListener(new ButtonClickListener(3));

        panel.add(easyModeButton);
        panel.add(mediumModeButton);
        panel.add(hardModeButton);

        setSize(500, 500);
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
                    JOptionPane.showMessageDialog(null, "Starting SinglePlayer Mode...");
                    // Add your logic for the easy mode here
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Starting Multiplayer Mode...");
                    // Add your logic for the medium mode here
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Starting Some Mode...");
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

    public static void main(String[] args) {
        displayGameModeSelector();
    }
}
