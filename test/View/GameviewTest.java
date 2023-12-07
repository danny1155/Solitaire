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

public class GameviewTest {

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



    //Test if there are 7 moveable JLabel have been initiated when game gets set up
    @org.junit.Test
    public void testNumMoveableCards(){
        Gameview gv = getGameView();
        HashMap<Integer, ArrayList<JLabel>> moveableCards = gv.getMoveableCards();
        int num = 0;
        for (int i = 0; i < 12; i++){
            if (moveableCards.get(i) != null) {
                num += moveableCards.get(i).size();
            }
        }
        assert(num == 7);
    }

    //Test if there are 21 immoveable JLabel at the tableau have been initiated when game gets set up
    @org.junit.Test
    public void testNumImmoveableCards(){
        Gameview gv = getGameView();
        HashMap<Integer, ArrayList<JLabel>> immoveableCards = gv.getImmoveableCards();
        int num = 0;
        for (int i = 0; i < 8; i++){
            if (immoveableCards.get(i) != null) {
                num += immoveableCards.get(i).size();
            }
        }
        assert(num == 45);
    }

    //Test if 4 foundation tableau has been properly initiated
    @org.junit.Test
    public void testNumFoundation(){
        Gameview gv = getGameView();
        JLayeredPane cardsPanel = (JLayeredPane) gv.getComponent(1);
        int numTableauLabels = 0;
        Component[] components = cardsPanel.getComponents();

        // Loop through the components and check properties
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;

                // Check the x-coordinate, width, and height of each label
                int x = label.getX();
                int y = label.getY();
                int width = label.getWidth();
                int height = label.getHeight();

                // Check if the label properties match the expected values
                if (width == 100 && height == 140) {
                    if ((x >= 880 && (y == 0 || y == 150 || y == 300 || y == 450))) {
                        numTableauLabels++;
                    }
                }
            }
        }
        assert (numTableauLabels == 4);
    }

    //Test if a viable card can be selected
    @org.junit.Test
    public void testSelectCard(){
        Gameview gv = getGameView();
        HashMap<Integer, ArrayList<JLabel>> moveableCards = gv.getMoveableCards();
        JLabel club2 = moveableCards.get(6).get(0);
        int club2X = club2.getX();
        int club2Y = club2.getY();
        MouseEvent pressEvent = new MouseEvent(gv, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, club2X + 50, club2Y + 50, 1, false);
        gv.dispatchEvent(pressEvent);
        System.out.println(club2);
        System.out.println(gv.getSelectedCard());
        assert (gv.getSelectedCard().equals(club2));
    }

    //Test if a viable card can be dragged to a location
    @org.junit.Test
    public void testDragToLocation(){
        Gameview gv = getGameView();
        HashMap<Integer, ArrayList<JLabel>> moveableCards = gv.getMoveableCards();
        JLabel club2 = moveableCards.get(6).get(0);
        int club2X = club2.getX();
        int club2Y = club2.getY();
        MouseEvent pressEvent = new MouseEvent(gv, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, club2X + 50, club2Y + 50, 1, false);
        gv.dispatchEvent(pressEvent);
        MouseEvent dragEvent = new MouseEvent(gv, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 1020, 0, 1, false);
        gv.dispatchEvent(dragEvent);
        assert (gv.getIsDragged());
    }

    //Test if a viable card can be dragged and drop at a viable foundation location
    @org.junit.Test
    public void testReleaseAtFoundation(){
        Gameview gv = getGameView();
        HashMap<Integer, ArrayList<JLabel>> moveableCards = gv.getMoveableCards();
        JLabel club2 = moveableCards.get(6).get(0);
        int club2X = club2.getX();
        int club2Y = club2.getY();
        MouseEvent pressEvent = new MouseEvent(gv, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, club2X + 50, club2Y + 50, 1, false);
        gv.dispatchEvent(pressEvent);
        MouseEvent dragEvent = new MouseEvent(gv, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 930, 50, 1, false);
        gv.dispatchEvent(dragEvent);
        MouseEvent releaseEvent = new MouseEvent(gv, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 930 , 50, 1, false);
        gv.dispatchEvent(releaseEvent);
//        MouseEvent pressEvent2 = new MouseEvent(gv, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 880, 0, 1, false);
//        gv.dispatchEvent(pressEvent2);
        assert (gv.getMoveableCards().get(8).get(0).equals(club2) && !gv.getIsDragged());
    }

    //Test if a card is sent back to its original location if it is dropped at an illegal location on tableau
    @org.junit.Test
    public void testReleaseAtUnDroppableTableau(){
        Gameview gv = getGameView();
        HashMap<Integer, ArrayList<JLabel>> moveableCards = gv.getMoveableCards();
        JLabel club2 = moveableCards.get(6).get(0);
        int club2X = club2.getX();
        int club2Y = club2.getY();
        MouseEvent pressEvent = new MouseEvent(gv, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, club2X + 50, club2Y + 50, 1, false);
        gv.dispatchEvent(pressEvent);
        MouseEvent dragEvent = new MouseEvent(gv, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, club2X + 110, club2Y + 500, 1, false);
        gv.dispatchEvent(dragEvent);
        MouseEvent releaseEvent = new MouseEvent(gv, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, club2X + 110 , club2Y + 500, 1, false);
        gv.dispatchEvent(releaseEvent);
        assert(!gv.getIsDragged() && gv.getMoveableCards().get(6).get(0).equals(club2));
    }

    //Test if a card is sent back to its original location if it is dropped at an illegal location on Foundation
    @org.junit.Test
    public void testReleaseAtUnDroppableFoundation(){
        Gameview gv = getGameView();
        HashMap<Integer, ArrayList<JLabel>> moveableCards = gv.getMoveableCards();
        JLabel club2 = moveableCards.get(6).get(0);
        int numFail = 0;
        int club2X = club2.getX();
        int club2Y = club2.getY();
        for (int i = 1; i <4; i++){
            MouseEvent pressEvent = new MouseEvent(gv, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, club2X + 50, club2Y + 50, 1, false);
            gv.dispatchEvent(pressEvent);
            MouseEvent dragEvent = new MouseEvent(gv, MouseEvent.MOUSE_DRAGGED, System.currentTimeMillis(), 0, 1020, i * 150, 1, false);
            gv.dispatchEvent(dragEvent);
            MouseEvent releaseEvent = new MouseEvent(gv, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 1020 , i * 150, 1, false);
            gv.dispatchEvent(releaseEvent);
            if ((gv.getMoveableCards().get(6).get(0).equals(club2) && !gv.getIsDragged())){
                numFail += 1;
            }
        }
        assert (numFail == 3);
    }

    //Test if the mini Panel is present when gameview is launched
    @org.junit.Test
    public void testMiniPanelPresent(){
        Gameview gv = getGameView();
        assert (gv.getComponent(2).isVisible());
    }

    //Test if the mini Panel has the correct buttons
    @org.junit.Test
    public void testMiniPanelButton(){
        Gameview gv = getGameView();
        JPanel miniPanel = (JPanel) gv.getComponent(2);
        ArrayList<String> buttonList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            JButton button = (JButton) miniPanel.getComponent(i);
            buttonList.add(button.getText());
        }
        ArrayList<String> testButtons = new ArrayList<>();
        testButtons.add("Close");
        testButtons.add("Return to Home Menu");
        assert (buttonList.equals(testButtons));
    }

    @org.junit.Test
    public void testDrawCard(){
        Gameview gv = getGameView();
        MouseEvent pressEvent = new MouseEvent(gv, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 20, 20, 1, false);
        gv.dispatchEvent(pressEvent);
        assert (!gv.getListCardsDrawn().isEmpty());
    }

    @org.junit.Test
    public void testDrawCardSpam(){
        Gameview gv = getGameView();
        MouseEvent pressEvent = new MouseEvent(gv, MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, 20, 20, 1, false);
        for (int i = 0; i < 25; i++){
            gv.dispatchEvent(pressEvent);
        }
        assert (gv.getListCardsDrawn().isEmpty());
    }

}
