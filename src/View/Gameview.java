package View;

import entity.Card;
import entity.SinglePlayerGame;
import interface_adapter.MoveCard.MoveCardController;
import interface_adapter.Setup.SetupController;
import interface_adapter.Setup.SetupViewModel;
import interface_adapter.Setup.SetupState;
import interface_adapter.ViewManagerModel;
import interface_adapter.Drawcard.DrawcardController;
import interface_adapter.Drawcard.DrawcardPresenter;
import use_case.draw_card.DrawcardInteractor;
import interface_adapter.Drawcard.DrawcardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;
import javax.swing.JOptionPane;



public class Gameview extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "setup";
    private JLayeredPane cardsPanel;
    private JLabel timerLabel;
    private JLabel scoreLabel;
    private JLabel movesLabel;
    private long startTime;
    private int score;
    private int moves;
    private JPanel topPanel;
    private java.util.List<String> shownCardsImage;
    private int numShownCards;
    private final SetupViewModel setupViewModel;
    private final SetupController setupController;
    private final MoveCardController moveCardController;
    private final HomeViewModel homeViewModel;
    private Timer gameTimer;
    private final DrawcardController drawCardController;
    private final DrawcardPresenter drawCardPresenter;
    private final DrawcardViewModel drawCardViewModel;


    private Point previousPoint;
    private Point imageCorner;
    private Map<Integer, ArrayList<Card>> columns;
    private HashMap<Integer, ArrayList<JLabel>> moveableCards;
    private HashMap<Integer, ArrayList<JLabel>> immoveableCards;
    private JLabel selectedCard;
    private boolean canBeDropped;
    private boolean isDragged;
    private Point previousImageCorner;
    private int i;
    private int j;
    private List<JLabel> listCardsDrawn;
    private boolean newCardShown;


    public Gameview(SetupViewModel setupViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel, SetupController setupController, MoveCardController moveCardController) {
        this.setupViewModel = setupViewModel;
        this.setupController = setupController;
        this.moveCardController = moveCardController;
        this.homeViewModel = homeViewModel;
        this.setupViewModel.addPropertyChangeListener(this);
        this.shownCardsImage = new ArrayList<>();
        this.columns = new HashMap<>();
        this.moveableCards = new HashMap<>();
        this.immoveableCards = new HashMap<>();
        this.canBeDropped = false;
        this.isDragged = false;
        this.listCardsDrawn = new ArrayList();
        this.score = 0;
        this.moves = 0;
        // Create the DrawcardViewModel first
        drawCardViewModel = new DrawcardViewModel();

        // Pass the DrawcardViewModel to the DrawcardPresenter
        drawCardPresenter = new DrawcardPresenter(drawCardViewModel);

        // Now create the DrawcardController with the DrawcardPresenter
        drawCardController = new DrawcardController(new DrawcardInteractor(drawCardPresenter));

        this.setBackground(Color.getHSBColor(0.335F,0.7f,0.6f));

        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);

        // Initialize the start time to zero
        startTime = 0;

        // Create and configure the timer label
        timerLabel = new JLabel("00:00:00");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        scoreLabel = new JLabel("                   score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setHorizontalAlignment(JLabel.RIGHT);

        movesLabel = new JLabel("                   moves: " + moves);
        movesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        movesLabel.setHorizontalAlignment(JLabel.RIGHT);


        topPanel = new JPanel();
        topPanel.setBackground(Color.getHSBColor(0.335F,0.7f,0.6f));

        topPanel.add(timerLabel);
        topPanel.add(scoreLabel);
        topPanel.add(movesLabel);
        // Create a panel to hold the cards
        cardsPanel = new JLayeredPane();
        cardsPanel.setBounds(0,0, 1000, 600);
        cardsPanel.addPropertyChangeListener(String.valueOf(shownCardsImage), this);
        topPanel.addPropertyChangeListener(String.valueOf(score),this);

        // Parse the poker card string into an array
        System.out.println(shownCardsImage);

        // Create a Timer to update the timer label in real-time
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime += 1000;
                long seconds = startTime / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                String timeString = String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
                timerLabel.setText(timeString);
            }
        });


        // Set the default close operation to exit on close
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         //Create a mini-menu panel
        JPanel miniMenuPanel = new JPanel(new FlowLayout());

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
                    // Close Gameview
                    viewManagerModel.setActiveView(homeViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        miniMenuPanel.add(returnButton);

        // Add components to the content pane
        miniMenuPanel.setVisible(true);
        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(cardsPanel, BorderLayout.CENTER);
        this.add(miniMenuPanel, BorderLayout.SOUTH);

        // Register a KeyAdapter to listen for the Escape key
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    miniMenuPanel.setVisible(true); // Show the mini-menu
                }
            }
        });
//
////         Enable focus on the frame for key events
        setFocusable(true);
        requestFocus();
//
//        // Make the frame visible
//        setVisible(true);
    }

    // Helper method to add a card back image to the panel
    private void addCardBack(Container panel, int i, int j, int x, int y) {


        URL cardBackImageUrl = getCardBackImageURL();
        if (cardBackImageUrl != null) {
            try {
                BufferedImage cardBackImage = ImageIO.read(cardBackImageUrl);
                Image image = cardBackImage.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
                Icon icon = new ImageIcon(image);

                JLabel cardBackLabel = new JLabel(icon);
                cardBackLabel.setOpaque(true);
                cardBackLabel.setBounds(x, y, 100, 140);

                panel.add(cardBackLabel, 0);
                if (!immoveableCards.containsKey(i) || immoveableCards.get(i) == null) {
                    immoveableCards.put(i, new ArrayList<>());
                    immoveableCards.get(i).add(cardBackLabel);
                } else {
                    immoveableCards.get(i).add(cardBackLabel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void addDeck(Container panel, int i) {
        URL cardBackImageUrl = getCardBackImageURL();
        if (cardBackImageUrl != null) {
            try {
                BufferedImage cardBackImage = ImageIO.read(cardBackImageUrl);
                Image image = cardBackImage.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
                Icon icon = new ImageIcon(image);
                JLabel cardBackLabel = new JLabel(icon);
                cardBackLabel.setOpaque(true);
                cardBackLabel.setBounds(0,0,100,140);

                panel.add(cardBackLabel, 0);
                if (!immoveableCards.containsKey(0)) {
                    ArrayList<JLabel> cardsInDeck = new ArrayList<>();
                    cardsInDeck.add(cardBackLabel);
                    immoveableCards.put(0,cardsInDeck);
                } else {
                    immoveableCards.get(0).add(cardBackLabel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to add a card image to the panel
    private void addCard(Container panel, String cardImageLink, boolean isDrawn, int i, int x, int y) {
        URL imageUrl = getCardImageURL(cardImageLink);
        if (imageUrl != null) {
            try {
                BufferedImage cardImage = ImageIO.read(imageUrl);
                Image image = cardImage.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
                Icon icon = new ImageIcon(image);
                JLabel cardLabel = new JLabel(icon);
                cardLabel.setOpaque(true);

                cardLabel.setBounds(x, y, 100, 140);


                panel.add(cardLabel, 0);
                if (isDrawn) {
                    listCardsDrawn.add(cardLabel);

                    ArrayList<JLabel> moveableDrawnCards = new ArrayList();
                    moveableDrawnCards.add(cardLabel);
                    moveableCards.put(0, moveableDrawnCards);

                } else {
                    if (!moveableCards.containsKey(i) || moveableCards.get(i) == null) {
                        moveableCards.put(i, new ArrayList<>());
                        moveableCards.get(i).add(cardLabel);
                    } else {
                        moveableCards.get(i).add(0, cardLabel);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeFoundation(Container panel, String fileName, int y) {
        ImageIcon image = new ImageIcon(new ImageIcon(fileName).getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT));
        JLabel foundationLabel = new JLabel(image);
        foundationLabel.setOpaque(true);
        foundationLabel.setBounds(880, y * 150, 100, 140);
        moveableCards.put(y+8, new ArrayList<>());
        immoveableCards.put(y+8, new ArrayList<>());
        panel.add(foundationLabel, 0);
    }

    // Helper method to get the URL of the card back image
    private URL getCardBackImageURL() {
        try {
            return new URL("https://www.deckofcardsapi.com/static/img/back.png");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper method to get the URL of the card image based on its code
    private URL getCardImageURL(String cardImageLink) {
        try {

            return new URL(cardImageLink);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SetupState state) {
            if (state.getIsNewGame()) {
                System.out.println("start");
                state.setIsNewGame(false);
                cardsPanel.removeAll();
                setCards(state);
;
                resetTimer();
                gameTimer.start();
            }
            System.out.println("size: " + shownCardsImage.size());
            System.out.println("num: " + numShownCards);
            if (shownCardsImage.size() == 52) {
                System.out.println("You won!!");
            }
            score = state.getScore();
            moves = state.getNumMoves();
            scoreLabel.setText("                   score: " + score);
            movesLabel.setText("                   moves: " + moves);

            System.out.println(score);
        }

    }

    private void setCards(SetupState state) {
        shownCardsImage = state.getCurrentlyShownCardsImage();
        numShownCards = shownCardsImage.size();
        columns = state.getColumns();

        ImageIcon image = new ImageIcon(new ImageIcon("images/emptyDeck.png").getImage().getScaledInstance(100, 140, Image.SCALE_DEFAULT));
        JLabel emptyDeckLabel = new JLabel(image);
        emptyDeckLabel.setOpaque(true);
        emptyDeckLabel.setBounds(0, 0, 100, 140);
        cardsPanel.add(emptyDeckLabel, 0);

        for (int i = 0; i < 24; i++) {
            addDeck(cardsPanel, i);
            //System.out.println();
        }
         //Create and configure JLabels for each card and card backs
        for (int i = 0; i < 7; i++) {

            if (i > 0) {
                // Add card backs starting from the second pile
                for (int j = 0; j < columns.get(i + 1).size() - 1; j++) {
                    addCardBack(cardsPanel, i + 1, j + 1, 110 * i + 110, 20 * j);
                }
            }
            columns.get(i + 1).get(columns.get(i + 1).size() - 1).setImage_corner(110 * i + 110, i * 20);
            addCard(cardsPanel, columns.get(i + 1).get(i).getImageLink(), false, i + 1,  110 * i + 110, i * 20);
        }
        initializeFoundation(cardsPanel, "images/AC.png",0);
        initializeFoundation(cardsPanel, "images/AS.png",1);
        initializeFoundation(cardsPanel, "images/AD.png",2);
        initializeFoundation(cardsPanel, "images/AH.png",3);
        for (int i = 8; i < 12; i++){
            columns.put(i, new ArrayList<>());
        }
    }

    public HashMap<Integer, ArrayList<JLabel>> getMoveableCards() {
        return moveableCards;
    }

    public HashMap<Integer, ArrayList<JLabel>> getImmoveableCards() {
        return immoveableCards;
    }

    public JLabel getSelectedCard() {
        return selectedCard;
    }

    public boolean getIsDragged() {
        return isDragged;
    }

    public List<JLabel> getListCardsDrawn(){
        return listCardsDrawn;
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent evt) {

            previousPoint = evt.getPoint();
            if (0 <= previousPoint.getX() && previousPoint.getX() <= 100 && 0 <= previousPoint.getY() && previousPoint.getY() <= 140) {
                drawCard();
            }
            outer:
            for (i = 0; i < 12; i++) {
                if (moveableCards.containsKey(i) && !moveableCards.get(i).isEmpty()) {
                    j = 0;
                    if (moveableCards.get(i).get(0).getX() <= previousPoint.getX() && previousPoint.getX() <= moveableCards.get(i).get(0).getX() + 100.0
                            && moveableCards.get(i).get(0).getY() + 40 <= previousPoint.getY() && previousPoint.getY() <= moveableCards.get(i).get(0).getY() + 180.0) {
                        imageCorner = (Point) columns.get(i).get(columns.get(i).size() - 1).getImage_corner().clone();
                        previousImageCorner = (Point) columns.get(i).get(columns.get(i).size() - 1).getImage_corner().clone();
                        selectedCard = moveableCards.get(i).get(0);
                        break;

                    }

                    for (j = 1; j < moveableCards.get(i).size(); j++) {
                        if (moveableCards.get(i).get(j).getX() <= previousPoint.getX() && previousPoint.getX() <= moveableCards.get(i).get(j).getX() + 100.0
                                && moveableCards.get(i).get(j).getY() + 40 <= previousPoint.getY() && previousPoint.getY() <= moveableCards.get(i).get(j).getY() + 60.0) {
                            imageCorner = (Point) columns.get(i).get(columns.get(i).size() - (j + 1)).getImage_corner().clone();
                            previousImageCorner = (Point) columns.get(i).get(columns.get(i).size() - (j + 1)).getImage_corner().clone();
                            selectedCard = moveableCards.get(i).get(j);
                            break outer;

                        }
                    }
                }
            }
        }
        public void mouseReleased(MouseEvent evt) {
            if (i < 12 && j < moveableCards.get(i).size()) {
                if (i != 0 && columns.get(i).size() >= (j + 2) && immoveableCards.get(i) != null && !immoveableCards.get(i).isEmpty() && !columns.get(i).get(columns.get(i).size() - (j + 2)).checkIsShown()) {
                    newCardShown = true;
                } else {
                    newCardShown = false;
                }
                System.out.println(newCardShown);
                moveCardController.execute(imageCorner, columns.get(i).get(columns.get(i).size() - (j + 1)), j + 1, newCardShown, i);
                SetupState state = setupViewModel.getState();
                canBeDropped = state.getCanBeMoved();

                System.out.println(canBeDropped);
                if (!canBeDropped || i == state.getMovedColumn()) {
                    for (int k = j; k >= 0; k--) {
                        cardsPanel.remove(moveableCards.get(i).get(k));

                        moveableCards.get(i).get(k).setBounds((int) previousImageCorner.getX(), (int) previousImageCorner.getY() + (j - k) * 20, 100, 140);

                        cardsPanel.add(moveableCards.get(i).get(k), 0);
                    }
                    repaint();
                } else {

                    System.out.println("drop");

                    if (i == 0 ) {
                        listCardsDrawn.remove(listCardsDrawn.size() - 1);
                        if (!listCardsDrawn.isEmpty()) {
                            moveableCards.get(i).add(listCardsDrawn.get(listCardsDrawn.size() - 1));
                        }
                    }
                    if (imageCorner.getX() >= 840 || state.getMovedColumn() >= 8) {
                        System.out.println("drop to foundation");
                        cardsPanel.remove(moveableCards.get(i).get(0));
                        Card card = columns.get(i).get(columns.get(i).size() - 1); //card being moved
                        if (-30 <= imageCorner.getY() && imageCorner.getY() <= 100) {
                            card.setImage_corner(880, 0); //card being moved
                            moveableCards.get(8).add(0, moveableCards.get(i).get(0));
                            moveableCards.get(i).get(0).setBounds(880, 0, 100, 140);
                        } else if (120 <= imageCorner.getY() && imageCorner.getY() <= 250) {
                            card.setImage_corner(880, 150);
                            moveableCards.get(9).add(0, moveableCards.get(i).get(0));
                            moveableCards.get(i).get(0).setBounds(880, 150, 100, 140);
                        } else if (270 <= imageCorner.getY() && imageCorner.getY() <= 400) {
                            card.setImage_corner(880, 300);
                            moveableCards.get(10).add(0, moveableCards.get(i).get(0));
                            moveableCards.get(i).get(0).setBounds(880, 300, 100, 140);
                        } else if (420 <= imageCorner.getY() && imageCorner.getY() <= 550) {
                            card.setImage_corner(880, 450);
                            moveableCards.get(11).add(0, moveableCards.get(i).get(0));
                            moveableCards.get(i).get(0).setBounds(880, 450, 100, 140);
                        }
                        cardsPanel.add(moveableCards.get(i).get(0), 0);
                        moveableCards.get(i).remove(0);
                        repaint();
                    } else {
                        for (int k = j; k >= 0; k--) {
                            cardsPanel.remove(moveableCards.get(i).get(k));
                            moveableCards.get(i).get(k).setBounds(110 * (state.getMovedColumn() - 1) + 110, columns.get(state.getMovedColumn()).size() * 20 + (j - k) * 20, 100, 140);
                            cardsPanel.add(moveableCards.get(i).get(k), 0);
                            Card card = columns.get(i).get(columns.get(i).size() - (k + 1)); //card being moved
                            System.out.println(k);
                            card.setImage_corner(110 * (state.getMovedColumn() - 1) + 110, columns.get(state.getMovedColumn()).size() * 20 + (j - k) * 20); //card being moved

                            if (!moveableCards.containsKey(state.getMovedColumn()) || moveableCards.get(state.getMovedColumn()) == null) {
                                moveableCards.put(state.getMovedColumn(), new ArrayList<>());
                                moveableCards.get(state.getMovedColumn()).add(moveableCards.get(i).get(k));
                            } else {
                                System.out.println(state.getMovedColumn());
                                moveableCards.get(state.getMovedColumn()).add(0, moveableCards.get(i).get(k));
                            }
                            moveableCards.get(i).remove(k); //card being moved
                            repaint();
                        }
                    }
                    if (i != 0 && columns.get(i).size() >= (j + 2) && immoveableCards.get(i) != null && !immoveableCards.get(i).isEmpty() && !columns.get(i).get(columns.get(i).size() - (j + 2)).checkIsShown()) {

                        cardsPanel.remove(immoveableCards.get(i).get(immoveableCards.get(i).size() - 1)); //hidden card that was right below the card being moved
                        addCard(cardsPanel, columns.get(i).get(columns.get(i).size() - (j + 2)).getImageLink(), false, i, immoveableCards.get(i).get(immoveableCards.get(i).size() - 1).getX(),
                                immoveableCards.get(i).get(immoveableCards.get(i).size() - 1).getY());

                        //hidden card that was right below the card being moved
                        columns.get(i).get(columns.get(i).size() - (j + 2)).showCard(); //hidden card that was right below the card being moved
                        shownCardsImage.add(columns.get(i).get(columns.get(i).size() - (j + 2)).getImageLink());

                        columns.get(i).get(columns.get(i).size() - (j + 2)).setImage_corner(immoveableCards.get(i).get(immoveableCards.get(i).size() - 1).getX(),
                                immoveableCards.get(i).get(immoveableCards.get(i).size() - 1).getY());
                        //hidden card that was right below the card being moved

                        immoveableCards.get(i).remove(immoveableCards.get(i).size() - 1); //hidden card that was right below the card being moved
                    }

                    for (int k = 0; k <= j; k++) {
                        Card card = columns.get(i).get(columns.get(i).size() - 1); //card being moved
                        columns.get(i).remove(columns.get(i).size() - 1); //card being moved
                        columns.get(state.getMovedColumn()).add(columns.get(state.getMovedColumn()).size() - k, card); //card being moved
                    }


                    //card being moved

                }

                imageCorner = null;
                isDragged = false;

                //selectedCard = null;
            }
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent evt) {
            isDragged = true;
            Point currentPoint = evt.getPoint();


            //image_corner = columns.get(7).get(6).getImage_corner();
            if (imageCorner != null) {
                imageCorner.translate(
                        (int) (currentPoint.getX() - previousPoint.getX()),
                        (int) (currentPoint.getY() - previousPoint.getY())
                );

                for (int k = j; k >= 0; k--) {
                    //cardsPanel.remove(selectedCard);
                    cardsPanel.remove(moveableCards.get(i).get(k));
                    moveableCards.get(i).get(k).setBounds((int) Math.max(0,Math.min(imageCorner.getX(), cardsPanel.getWidth() - 110)),
                            (int) Math.max((j - k) * 20, Math.min(imageCorner.getY() + (j - k) * 20, cardsPanel.getHeight() - 150 - j * 20)), 100, 140);
                    //addCard();
                    cardsPanel.add(moveableCards.get(i).get(k), 0);
                }
                previousPoint = currentPoint;
                repaint();
            }

        }
    }

    private void drawCard() {
        //System.out.println(columns.get(-1));
        if (columns.get(-1).isEmpty()) {
            for (JLabel card : listCardsDrawn) {
                moveableCards.get(0).remove(card);
                addDeck(cardsPanel,0);
                columns.get(0).get(columns.get(0).size() - 1).setImage_corner(0,0);
                columns.get(0).get(columns.get(0).size() - 1).hideCard();
                columns.get(-1).add(columns.get(0).get(columns.get(0).size() - 1));
                columns.get(0).remove(columns.get(0).size() - 1);
                cardsPanel.remove(card);
                repaint();
            }
            listCardsDrawn.clear();

        } else {
            cardsPanel.remove(immoveableCards.get(0).get(immoveableCards.get(0).size() - 1));
            System.out.println(immoveableCards.get(0).size());
            immoveableCards.get(0).remove(immoveableCards.get(0).size() - 1);
            addCard(cardsPanel, columns.get(-1).get(columns.get(-1).size() - 1).getImageLink(), true, 0, 0, 150);
            repaint();

            columns.get(-1).get(columns.get(-1).size() - 1).setImage_corner(0, 150);
            columns.get(-1).get(columns.get(-1).size() - 1).showCard();
            columns.get(0).add(columns.get(-1).get(columns.get(-1).size() - 1));
            columns.get(-1).remove(columns.get(-1).size() - 1);
            System.out.println(columns.get(0).size());
        }
    }

    private void handleDrawCardButtonClick() {
        drawCardController.drawCard();
    }


    private void resetTimer(){
        startTime = 0;
        timerLabel.setText("00:00:00");
        score = 0;
        moves = 0;
        scoreLabel.setText("                   score: " + score);
        movesLabel.setText("                   moves: " + moves);
    }




}