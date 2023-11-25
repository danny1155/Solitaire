package View;

import interface_adapter.Setup.SetupController;
import interface_adapter.Setup.SetupViewModel;
import interface_adapter.Setup.SetupState;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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

    public Gameview(SetupViewModel setupViewModel, SetupController setupController) {
        this.setupViewModel = setupViewModel;
        this.setupController = setupController;
        this.setupViewModel.addPropertyChangeListener(this);

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


        // Add 24 closely stacked card back images to the deck panel
        for (int i = 0; i < 24; i++) {
            addDeck(deckPanel, i);
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
        Timer gameTimer = new Timer(1000, new ActionListener() {
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
        gameTimer.start();

        // Set the default close operation to exit on close
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a mini-menu panel
//        JPanel miniMenuPanel = new JPanel(new FlowLayout());

        // Add the quit button
//        JButton quitButton = new JButton("Exit to Desktop");
//        quitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int result = JOptionPane.showConfirmDialog(Gameview.this,
//                        "Are you certain to leave the game?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
//                if (result == JOptionPane.YES_OPTION) {
        // Close the Gameview
//                    dispose();
        // You can also perform any additional cleanup or actions here
        // For example, returning to the Homeview, etc.
//                }
//            }
//        });
//        miniMenuPanel.add(quitButton);

        // Add a "Close" button to the mini-menu
//        JButton closeButton = new JButton("Close");
//        closeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                miniMenuPanel.setVisible(false); // Hide the mini-menu
//            }
//        });
//        miniMenuPanel.add(closeButton);
//
//        // Add a "Return to Home Menu" button to the mini-menu
//        JButton returnButton = new JButton("Return to Home Menu");
//        returnButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int result = JOptionPane.showConfirmDialog(Gameview.this,
//                        "Are you sure to return to Home Menu?", "Confirm Return", JOptionPane.YES_NO_OPTION);
//                if (result == JOptionPane.YES_OPTION) {
//                    // Terminate Gameview and return to Homeview
//                    dispose(); // Close Gameview
//                    Homeview homeview = new Homeview();
//                    homeview.setVisible(true); // Show Homeview
//                }
//            }
//        });
//        miniMenuPanel.add(returnButton);


        // Add components to the content pane
//        getContentPane().setLayout(new BorderLayout());
//        getContentPane().add(timerLabel, BorderLayout.NORTH);
//        getContentPane().add(cardsPanel, BorderLayout.CENTER);
//        getContentPane().add(deckPanel, BorderLayout.WEST);
////        getContentPane().add(miniMenuPanel, BorderLayout.SOUTH);
//        getContentPane().add(foundationPanel, BorderLayout.EAST);
//        miniMenuPanel.setVisible(false);
        this.setLayout(new BorderLayout());
        this.add(timerLabel, BorderLayout.NORTH);
        this.add(deckPanel, BorderLayout.WEST);
        this.add(cardsPanel, BorderLayout.CENTER);

//        getContentPane().add(miniMenuPanel, BorderLayout.SOUTH);
        this.add(foundationPanel, BorderLayout.EAST);

        // Register a KeyAdapter to listen for the Escape key
//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                    miniMenuPanel.setVisible(true); // Show the mini-menu
//                }
//            }
//        });
//
//        // Enable focus on the frame for key events
//        setFocusable(true);
//        requestFocus();
//
//        // Make the frame visible
//        setVisible(true);
    }

    // Helper method to add a card back image to the panel
    private void addCardBack(Container panel, int i, int j) {


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
                cardBackLabel.setBounds(0, 20 * (i - j - 1), 100, 140);
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
    private void addCard(Container panel, String cardCode, int i) {
        URL imageUrl = getCardImageURL(cardCode);
        if (imageUrl != null) {
            try {
                BufferedImage cardImage = ImageIO.read(imageUrl);
                Image image = cardImage.getScaledInstance(100, 140, Image.SCALE_DEFAULT);
                Icon icon = new ImageIcon(image);
                JLabel cardLabel = new JLabel(icon);
                cardLabel.setOpaque(true);

                cardLabel.setBounds(0, 20 * i, 100, 140);


                panel.add(cardLabel);
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
            setCards(state);
        }

    }

    private void setCards(SetupState state) {
        shownCardsImage = state.getCurrentlyShownCardsImage();


        // Create and configure JLabels for each card and card backs
        for (int i = 0; i < 7; i++) {
            JLayeredPane columnPanel = new JLayeredPane();
            columnPanel.setBounds(110 * i + 70,0, 200, 400);

            addCard(columnPanel, shownCardsImage.get(i), i);

            if (i > 0) {
                // Add card backs starting from the second pile
                for (int j = 0; j < i; j++) {
                    addCardBack(columnPanel, i,  j);

//                    JPanel filler = new JPanel();
//                    columnPanel.add(filler);
                }
            }



            cardsPanel.add(columnPanel);
            //cardsPanel.repaint();



        }

    }


}