package View;

import interface_adapter.*;
import interface_adapter.Setup.SetupController;
import interface_adapter.Setup.SetupViewModel;
import use_case.setup_game.SetupInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;
    private final logInController loginController;

    /**
     * The username chosen by the user
     */
    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    /**
     * The password
     */
    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton SignUp;

    /**
     * A window with a title and a JButton.
     */
    public LoginView(logInController controller, LoginViewModel loginViewModel) {
        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        SignUp = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(SignUp);

        logIn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currState = loginViewModel.getState();
                            loginController.execute(currState.getUsername(),
                                    currState.getPassword());
                            System.out.println("hi");
                        }
                    }
                }
        );
        SignUp.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(SignUp)) {
                            CardLayout cardLayout = new CardLayout();
//
                            JPanel views = new JPanel(cardLayout);
                            //application.add(views);

                            ViewManagerModel viewManagerModel = new ViewManagerModel();
                            new ViewManager(views, cardLayout, viewManagerModel);

                            final SetupInputBoundary setupInteractor = null;

                            HomeViewModel homeViewModel = new HomeViewModel();
                            SetupViewModel setupViewModel = new SetupViewModel();
                            SignupViewModel signupViewModel = new SignupViewModel();
                            LoginViewModel loginViewModel = new LoginViewModel();
                            SetupController setupController = new SetupController(setupInteractor);

                            Homeview homeView = new Homeview(homeViewModel,setupController);
                            views.add(homeView, homeView.viewName);

                            viewManagerModel.setActiveView(homeView.viewName);
                            viewManagerModel.firePropertyChanged();

                            homeView.setVisible(true);

                            setVisible(false);
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

}