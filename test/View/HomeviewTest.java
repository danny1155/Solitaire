package View;
import App.Main;
import org.junit.After;
import org.junit.Assert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

import static org.junit.Assert.*;

public class HomeviewTest {

    static String message = "";

    static boolean popUpDiscovered = false;

    //return a list of string that represent each buttons in the GUI, used to test if intended buttons present in Homeview
    public ArrayList<String> getButtons(){
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
        Homeview sv = (Homeview) jp2.getComponent(0);
        ArrayList<String> buttonList = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            JButton buttons = (JButton) sv.getComponent(i);
            buttonList.add(buttons.getText());
        }
        return buttonList;
    }

    //return the play button in the Homeview GUI
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

    public JButton getSelectGameModeButton(){
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
        return (JButton) sv.getComponent(1);
    }

    public Gameview getGameView(){
        Main.main(null);
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



    //Test that the Homeview GUI consists of correct buttons
    @org.junit.Test
    public void testButtonPresent(){
        Main.main(null);
        ArrayList<String> buttonList = getButtons();
        ArrayList<String> testButtons = new ArrayList<>();
        testButtons.add("Play");
        testButtons.add("Select Game Mode");
        testButtons.add("Scoreboard");
        assert(buttonList.equals(testButtons));
    }

    //Test if Gameview is displayed after play button is clicked
    @org.junit.Test
    public void testGameViewLaunched(){
        Gameview gv = getGameView();
        assert(gv.isVisible());
    }

    //Test if SelectGameModeView is displayed after select game mode button is clicked
    @org.junit.Test
    public void testSelectGameModeViewLaunched(){
        Main.main(null);
        JButton selectButton = getSelectGameModeButton();
        selectButton.doClick();
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
        SelectGameModeView sv = (SelectGameModeView) jp2.getComponent(2);
        assert(sv.isVisible());
    }

}