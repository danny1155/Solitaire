package View;
import App.Main;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;
public class SignupViewTest {
    public JButton getPlayButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        Homeview sv = (Homeview) jp2.getComponent(0);
        return (JButton) sv.getComponent(0);
    }

    public JButton getGuestButtons(){
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows){
            if (window instanceof JFrame){
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SignupView sv = (SignupView) jp2.getComponent(2);
        return sv.getGuest();
    }

    public Gameview getGameView(){
        Main.main(null);
        JButton guest = getGuestButtons();
        guest.doClick();
        JButton playbutton = getPlayButton();
        playbutton.doClick();
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        Gameview gv = (Gameview) jp2.getComponent(1);
        return gv;
    }

    @org.junit.Test
    public void testSignupPresent(){
        Main.main(null);
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows){
            if (window instanceof JFrame){
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SignupView sv = (SignupView) jp2.getComponent(2);
        JPanel panel = (JPanel) sv.getComponent(4);
        JButton button = (JButton) panel.getComponent(0);
        assert (button.getText().equals("Sign up"));
    }

    @org.junit.Test
    public void testLoginPresent(){
        Main.main(null);
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows){
            if (window instanceof JFrame){
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SignupView sv = (SignupView) jp2.getComponent(2);
        JPanel panel = (JPanel) sv.getComponent(4);
        JButton button = (JButton) panel.getComponent(1);
        assert (button.getText().equals("Log in"));
    }

    @org.junit.Test
    public void testQuitPresent(){
        Main.main(null);
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows){
            if (window instanceof JFrame){
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SignupView sv = (SignupView) jp2.getComponent(2);
        JPanel panel = (JPanel) sv.getComponent(4);
        JButton button = (JButton) panel.getComponent(2);
        assert (button.getText().equals("Quit"));
    }

    @org.junit.Test
    public void testPlayAsGuestPresent(){
        Main.main(null);
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows){
            if (window instanceof JFrame){
                app = (JFrame) window;
            }
        }
        assertNotNull(app); // found the window?
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        SignupView sv = (SignupView) jp2.getComponent(2);
        JPanel panel = (JPanel) sv.getComponent(4);
        JButton button = (JButton) panel.getComponent(3);
        assert (button.getText().equals("Play As Guest"));
    }
}
