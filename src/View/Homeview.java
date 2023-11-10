package View;

import View.SignUpLogIn.LoginView;
import View.SignUpLogIn.SignupView;
import View.SignUpLogIn.ViewManager;
import app.SignUp.SignupUseCaseFactory;
import interface_adapter.Setup.SetupController;
import interface_adapter.SignUp.LoginViewModel;
import interface_adapter.SignUp.SignupViewModel;
import interface_adapter.SignUp.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homeview extends JFrame {
    private JButton playButton;
    private JButton setGameModeButton;
    private JButton scoreboardButton;
    private JButton SignUpButton;
    private JButton LoginButton;
    private JButton quitButton;

    private SetupController setupController;

    public Homeview() {
        // Window title
        setTitle("Solitaire Organizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

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
        setGameModeButton = new JButton("Select Game Mode (Default Game Mode: Single Player)");
        setGameModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Select your Game Mode");
                //setVisible(false);
                dispose();

                //Create and display the playsession's Gameview GUI
                //SelectGameModeView selectGameModeView = new SelectGameModeView();
                SelectGameModeView.displayGameModeSelector();
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

        SignUpButton = new JButton("SignUp");
        SignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // The main application window.
                JFrame application = new JFrame("SignUp");
                application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
                CardLayout cardLayout = new CardLayout();
//
                // The various View objects. Only one view is visible at a time.
                JPanel views = new JPanel(cardLayout);
                application.add(views);
//
                // This keeps track of and manages which view is currently showing.
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                new ViewManager(views, cardLayout, viewManagerModel);
//
                // The data for the views, such as username and password, are in the ViewModels.
                // This information will be changed by a presenter object that is reporting the
                // results from the use case. The ViewModels are observable, and will
                // be observed by the Views.
                LoginViewModel loginViewModel = new LoginViewModel();
                SignupViewModel signupViewModel = new SignupViewModel();
//
                SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel);
                views.add(signupView, signupView.viewName);
//
                LoginView loginView = new LoginView(loginViewModel);
                views.add(loginView, loginView.viewName);
//
                viewManagerModel.setActiveView(signupView.viewName);
                viewManagerModel.firePropertyChanged();
//
                application.pack();
                application.setVisible(true);
            }
        });

        LoginButton = new JButton("Login");
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // The main application window.
                JFrame application = new JFrame("Login");
                application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
                CardLayout cardLayout = new CardLayout();
//
                // The various View objects. Only one view is visible at a time.
                JPanel views = new JPanel(cardLayout);
                application.add(views);
//
                // This keeps track of and manages which view is currently showing.
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                new ViewManager(views, cardLayout, viewManagerModel);
//
                // The data for the views, such as username and password, are in the ViewModels.
                // This information will be changed by a presenter object that is reporting the
                // results from the use case. The ViewModels are observable, and will
                // be observed by the Views.
                LoginViewModel loginViewModel = new LoginViewModel();
                LoginView loginView = new LoginView(loginViewModel);
                views.add(loginView, loginView.viewName);

                viewManagerModel.setActiveView(loginView.viewName);
                viewManagerModel.firePropertyChanged();
//
                application.pack();
                application.setVisible(true);
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
        buttonPanel.setLayout(new GridLayout(6, 1));
        buttonPanel.add(playButton);
        buttonPanel.add(setGameModeButton);
        buttonPanel.add(scoreboardButton);
        buttonPanel.add(SignUpButton);
        buttonPanel.add(LoginButton);
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

