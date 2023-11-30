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
    private long startTime;
    private JPanel deckPanel;
    private JPanel foundationPanel;
    private java.util.List<String> shownCardsImage;
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
    private JLabel selectedCard;
    private boolean canBeDropped;
    private boolean isDragged;
    private Point previousImageCorner;
    private int i;
    private int j;
    private int counter = 0;

    public Gameview(SetupViewModel setupViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel, SetupController setupController, MoveCardController moveCardController) {
        this.setupViewModel = setupViewModel;
        this.setupController = setupController;
        this.moveCardController = moveCardController;
        this.homeViewModel = homeViewModel;
        this.setupViewModel.addPropertyChangeListener(this);
        this.shownCardsImage = new ArrayList<>();
        this.columns = new HashMap<Integer, ArrayList<Card>>();
        this.moveableCards = new HashMap<>();
        this.canBeDropped = false;
        this.isDragged = false;
        // Create the DrawcardViewModel first
        drawCardViewModel = new DrawcardViewModel();

        // Pass the DrawcardViewModel to the DrawcardPresenter
        drawCardPresenter = new DrawcardPresenter(drawCardViewModel);

        // Now create the DrawcardController with the DrawcardPresenter
        drawCardController = new DrawcardController(new DrawcardInteractor(drawCardPresenter));



        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);

        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);

        // Set the frame to full-screen mode
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(false);

        // Initialize the start time to zero
        startTime = 0;

        // Create and configure the timer label
        timerLabel = new JLabel("00:00:00");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        timerLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create a panel to hold the cards
        cardsPanel = new JLayeredPane();
        cardsPanel.setBounds(0,0, 800, 500);
        //cardsPanel.setLayout(new GridBagLayout());
        //cardsPanel.setLayout(new GridLayout(1, 7));



        cardsPanel.addPropertyChangeListener(String.valueOf(shownCardsImage), this);

        // Parse the poker card string into an array
//        SetupState currentState = setupViewModel.getState();
        System.out.println(shownCardsImage);
//        if (shownCards != null) {
//            String[] cardCodes = shownCards.split(",");
//
//            // Create and configure JLabels for each card and card backs
//            for (int i = 0; i < 7; i++) {
//                if (i > 0) {
//                    // Add card backs starting from the second pile
//                    int numCardBacks = i - 1;
//                    for (int j = 0; j < numCardBacks; j++) {
//                        addCardBack(cardsPanel);
//                    }
//                }
//
//                // Get the card image URL based on the card code
//                String cardCode = cardCodes[i].trim();
//                addCard(cardsPanel, cardCode);
//            }
//        }


        // Create a panel for the deck
        deckPanel = new JPanel();
        deckPanel.setLayout(new OverlayLayout(deckPanel));

        deckPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawCard();
            }
        });


        // Add 24 closely stacked card back images to the deck panel
        for (int i = 0; i < 24; i++) {
            addDeck(deckPanel, i);
            //System.out.println();
        }

        // Create a panel for the foundation
        foundationPanel = new JPanel();
        foundationPanel.setLayout(new BoxLayout(foundationPanel, BoxLayout.Y_AXIS));

        // Add 4 vertically separated cards to the foundation panel with grey filter
        addFilteredCard(foundationPanel, "https://deckofcardsapi.com/static/img/AS.png");
        addFilteredCard(foundationPanel, "https://deckofcardsapi.com/static/img/AC.png");
        addFilteredCard(foundationPanel, "https://deckofcardsapi.com/static/img/AH.png");
        addFilteredCard(foundationPanel, "https://deckofcardsapi.com/static/img/AD.png");

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

        // Add the quit button
//        JButton quitButton = new JButton("Exit to Desktop");
//        quitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int result = JOptionPane.showConfirmDialog(Gameview.this,
//                        "Are you certain to leave the game?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
//                if (result == JOptionPane.YES_OPTION) {
//                     Close the Gameview
//                    dispose();
//                     You can also perform any additional cleanup or actions here
//                     For example, returning to the Homeview, etc.
//                }
//            }
//        });
//        miniMenuPanel.add(quitButton);

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
//                    Homeview homeview = new Homeview();
//                    homeview.setVisible(true); // Show Homeview
                }
            }
        });
        miniMenuPanel.add(returnButton);



        // Add components to the content pane
//        getContentPane().setLayout(new BorderLayout());
//        getContentPane().add(timerLabel, BorderLayout.NORTH);
//        getContentPane().add(cardsPanel, BorderLayout.CENTER);
//        getContentPane().add(deckPanel, BorderLayout.WEST);
////        getContentPane().add(miniMenuPanel, BorderLayout.SOUTH);
//        getContentPane().add(foundationPanel, BorderLayout.EAST);
        miniMenuPanel.setVisible(true);
        this.setLayout(new BorderLayout());
        this.add(timerLabel, BorderLayout.NORTH);
        this.add(deckPanel, BorderLayout.WEST);
        this.add(cardsPanel, BorderLayout.CENTER);

        this.add(miniMenuPanel, BorderLayout.SOUTH);
        this.add(foundationPanel, BorderLayout.EAST);

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
    private void addCardBack(Container panel, int x, int y) {


        URL cardBackImageUrl = getCardBackImageURL();
        if (cardBackImageUrl != null) {
            try {
                BufferedImage cardBackImage = ImageIO.read(cardBackImageUrl);
                Image image = cardBackImage.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
                Icon icon = new ImageIcon(image);

                JLabel cardBackLabel = new JLabel(icon);
                cardBackLabel.setOpaque(true);
                //cardBackLabel.setLocation(0,5 * j);
                //cardBackLabel.setAlignmentY(0.05f * (j + 1));
                cardBackLabel.setBounds(x, y, 100, 140);
                //cardBackLabel.setBackground(Color.red);

                panel.add(cardBackLabel);


                // panel.add(cardBackLabel, new BoxLayout(panel, BoxLayout.Y_AXIS));
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
                cardBackLabel.setAlignmentY(0.0f);
                panel.add(cardBackLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to add a card image to the panel
    private void addCard(Container panel, String cardCode, int i, int x, int y) {
        URL imageUrl = getCardImageURL(cardCode);
        if (imageUrl != null) {
            try {
                BufferedImage cardImage = ImageIO.read(imageUrl);
                Image image = cardImage.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
                Icon icon = new ImageIcon(image);
                JLabel cardLabel = new JLabel(icon);
                cardLabel.setOpaque(true);

                cardLabel.setBounds(x, y, 100, 140);


                panel.add(cardLabel, 0);
                ArrayList<JLabel> list = new ArrayList<JLabel>();
                list.add(cardLabel);
                moveableCards.put(i, list);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    private URL getCardImageURL(String cardCode) {
        try {

            return new URL(cardCode);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper method to add a filtered card image to the panel
    private void addFilteredCard(Container panel, String cardImageUrl) {
        URL imageUrl = getCardImageURL(cardImageUrl);
        if (imageUrl != null) {
            try {
                BufferedImage cardImage = ImageIO.read(imageUrl);
                Image filteredImage = applyGreyFilter(cardImage);

                Icon icon = new ImageIcon(filteredImage);
                JLabel cardLabel = new JLabel(icon);
                panel.add(cardLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to apply a grey filter to an image
    private Image applyGreyFilter(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        colorConvert.filter(bufferedImage, bufferedImage);

        return bufferedImage.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof SetupState state) {
            if (state.getIsNewGame()) {
                state.setIsNewGame(false);
                cardsPanel.removeAll();
                setCards(state);
//                this.remove(timerLabel);
//                startTime = 0;
//                timerLabel = new JLabel("00:00:00");
//                timerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
//                timerLabel.setHorizontalAlignment(JLabel.CENTER);
//
//                gameTimer = new Timer(1000, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//
//                        startTime += 1000;
//                        long seconds = startTime / 1000;
//                        long minutes = seconds / 60;
//                        long hours = minutes / 60;
//                        String timeString = String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
//                        timerLabel.setText(timeString);
//                    }
//                });
//                this.add(timerLabel, BorderLayout.NORTH);
                gameTimer.start();
            }
            counter++;
        }

    }

    private void setCards(SetupState state) {
        shownCardsImage = state.getCurrentlyShownCardsImage();
        columns = state.getColumns();

         //Create and configure JLabels for each card and card backs
        for (int i = 0; i < 7; i++) {
            //JLayeredPane columnPanel = new JLayeredPane();
            //columnPanel.setBounds(110 * i + 70,0, 200, 400);
            columns.get(i + 1).get(columns.get(i + 1).size() - 1).setImage_corner(110 * i + 70, i * 20);

            // addCard(cardsPanel, shownCardsImage.get(i), 110 * i + 70, i * 20);
            addCard(cardsPanel, columns.get(i + 1).get(i).getImageLink(), i + 1,  110 * i + 70, i * 20);


            if (i > 0) {
                // Add card backs starting from the second pile
                for (int j = 0; j < columns.get(i + 1).size(); j++) {
                    addCardBack(cardsPanel, 110 * i + 70, 20 * (i - j - 1));

//                    JPanel filler = new JPanel();
//                    columnPanel.add(filler);
                }
            }







        }

    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent evt) {

            previousPoint = evt.getPoint();
            outer: for (i = 1; i < 8; i++) {
//                System.out.println(moveableCards.get(i).getX());
//                System.out.println(previousPoint.getX());
                if (!moveableCards.get(i).isEmpty()) {
                    j = 0;
                    if (moveableCards.get(i).get(0).getX() <= previousPoint.getX() - 100 && previousPoint.getX() - 100 <= moveableCards.get(i).get(0).getX() + 100.0
                            && moveableCards.get(i).get(0).getY() + 20 <= previousPoint.getY() && previousPoint.getY() <= moveableCards.get(i).get(0).getY() + 160.0) {
                        imageCorner = (Point) columns.get(i).get(columns.get(i).size() - 1).getImage_corner().clone();
                        //imageCorner.move(imageCorner.getX() + 1, imageCorner.getY());
                        previousImageCorner = (Point) columns.get(i).get(columns.get(i).size() - 1).getImage_corner().clone();
                        selectedCard = moveableCards.get(i).get(0);
                        break;

                    }

                    for (j = 1; j < moveableCards.get(i).size(); j++) {
                        if (moveableCards.get(i).get(j).getX() <= previousPoint.getX() - 100 && previousPoint.getX() - 100 <= moveableCards.get(i).get(j).getX() + 100.0
                                && moveableCards.get(i).get(j).getY() + 20 <= previousPoint.getY() && previousPoint.getY() <= moveableCards.get(i).get(j).getY() + 40.0) {
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
            if (i < 8) {
                if (isDragged) {
                    moveCardController.execute(imageCorner, columns.get(i).get(columns.get(i).size() - 1));
                }
                SetupState state = setupViewModel.getState();
                canBeDropped = state.getCanBeMoved();
                System.out.println(canBeDropped);
                if (!canBeDropped) {
                    cardsPanel.remove(selectedCard);

                    selectedCard.setBounds((int) previousImageCorner.getX(), (int) previousImageCorner.getY(), 100, 140);

                    cardsPanel.add(selectedCard, 0);
                    //imageCorner = previousImageCorner;
                    repaint();
                } else {
                    Card card = columns.get(i).get(columns.get(i).size() - (j + 1));
                    card.setImage_corner(110 * (state.getMovedColumn() - 1) + 70, columns.get(state.getMovedColumn()).size() * 20);
                    //columns.get(state.getMovedColumn()).get(columns.get(state.getMovedColumn()).size() - 1).setImage_corner(110 * (state.getMovedColumn() - 1) + 70, columns.get(i + 1).size() * 20 - 20);
                    columns.get(i).remove(columns.get(i).size() - (j + 1));
                    columns.get(state.getMovedColumn()).add(card);
                    moveableCards.get(i).remove(0);
                    moveableCards.get(state.getMovedColumn()).add(0, selectedCard);

                }
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

                cardsPanel.remove(selectedCard);
                selectedCard.setBounds((int) imageCorner.getX(), (int) imageCorner.getY(), 100, 140);

                cardsPanel.add(selectedCard, 0);


//                if (!canBeDropped) {
//                    cardsPanel.remove(selectedCard);
//                    selectedCard.setBounds((int) previousImageCorner.getX(), (int) previousImageCorner.getY(), 100, 140);
//
//                    cardsPanel.add(selectedCard, 0);
//                }

                    previousPoint = currentPoint;


                //validate();
                //invalidate();
                repaint();
                System.out.println(imageCorner);
                //System.out.println(moveableCardPoints.get(7));
            }

        }
    }
    int a=0;
    SinglePlayerGame singlePlayerGame = new SinglePlayerGame();
    private void drawCard() {
        String drawnCards = singlePlayerGame.drawCard(1); // Draw one card for simplicity

        // Check if the drawnCards string contains the "image" field
        if (drawnCards.contains("\"image\":")) {
            int imageIndex = drawnCards.indexOf("\"image\":");
            int startQuoteIndex = drawnCards.indexOf("\"", imageIndex + 8);
            int endQuoteIndex = drawnCards.indexOf("\"", startQuoteIndex + 1);

            if (startQuoteIndex != -1 && endQuoteIndex != -1) {
                String imageLink = drawnCards.substring(startQuoteIndex + 1, endQuoteIndex);

                // Add the drawn card to the deckPanel
                addCard(deckPanel, imageLink, i + 1, 0, 0);  // Adjust the position as needed
                System.out.println("Drawn Card: " + drawnCards);
                System.out.println(a++);
            }
        } else {
            // No more cards, show a pop-up message
            JOptionPane.showMessageDialog(this, "No more cards", "Deck Empty", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void handleDrawCardButtonClick() {
        drawCardController.drawCard();
    }



}