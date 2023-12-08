package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.LoginView;

public class Scoreboardview extends JFrame {

    public Scoreboardview() {
        setTitle("Account Info Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Welcome to Account Info Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Info text
        //JTextArea infoTextArea = new JTextArea(LoginView.getUsername());
        //infoTextArea.setEditable(false);
        //JScrollPane scrollPane = new JScrollPane(infoTextArea);
        //add(scrollPane, BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Scoreboardview();
            }
        });
    }
}