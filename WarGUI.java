package CS110FinalProject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WarGUI extends JFrame
{
    private Game g;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton nextStepButton;

    private ImageIcon[][] cardImages = new ImageIcon[4][13];
    private ImageIcon cardBackImage;

    private JLabel playerHand;
    private JLabel playerPile;
    private JLabel computerPile;
    private JLabel computerHand;
    
    private JLabel statusMessage;

    public WarGUI() throws FileNotFoundException
    {
        g = new Game();
        //Load images
        try
        {
            cardBackImage = new ImageIcon(ImageIO.read(new File("CS110FinalProject/cardImages/back.jpg")));
        }
        catch (IOException ex)
        {
            throw new FileNotFoundException("Card image: back.jpg not found.");
        }
        // Loop through each suit
        for (Suit s : Suit.values())
        {
            // And each value
            for (Value v : Value.values())
            {
                // And load an image based on those.
                String fileName = "CS110FinalProject/cardImages/"+v+s+".jpg";
                try
                {
                    cardImages[s.ordinal()][v.ordinal()] = new ImageIcon(ImageIO.read(new File(fileName)));
                }
                catch (IOException ex)
                {
                    throw new FileNotFoundException("Card image: "+v+s+".jpg not found.");
                }
            }

        }
        // Initialize all components.
        
        playerHand = new JLabel(cardBackImage);
        computerHand = new JLabel(cardBackImage);
        // Leave the piles blank for now.
        playerPile = new JLabel();
        computerPile = new JLabel();
        

        setLayout(new GridLayout(2,1));
        topPanel = new JPanel(new GridLayout(1,4));
        topPanel.add(playerHand);
        topPanel.add(playerPile);
        topPanel.add(computerPile);
        topPanel.add(computerHand);
        bottomPanel = new JPanel(new GridLayout(2,1));
        statusMessage = new JLabel("Welcome to WAR!");
        nextStepButton = new JButton("Begin Game");
        nextStepButton.addActionListener(new ButtonListener());
        bottomPanel.add(statusMessage);
        bottomPanel.add(nextStepButton);

        this.add(topPanel);
        this.add(bottomPanel);
    }
    /**
     * Updates the images displayed.
     */
    private void updateCardImages()
    {
    	// Set the player's hand to the back of a card if it has cards in it, otherwise set it to nothing.
        if (g.playerHandSize() != 0)
        {
            playerHand.setIcon(cardBackImage);
        }
        else
        {
            playerHand.setIcon(null);
        }
        playerHand.revalidate(); // Repaint
        
        // Set the player's top card on the pile to the correct image if there are cards on the pile, otherwise set it to nothing.
        if (g.playerTopCard() != null)
        {
            playerPile.setIcon(cardImages[g.playerTopCard().getSuit().ordinal()][g.playerTopCard().getValue().ordinal()]);
        }
        else
        {
            playerPile.setIcon(null);
        }
        playerPile.revalidate(); // Repaint
        
        // Repeat above code for computer's hand and pile
        if (g.computerHandSize() != 0)
        {
        	computerHand.setIcon(cardBackImage);
        }
        else
        {
        	computerHand.setIcon(null);
        }
        computerHand.revalidate();
        
        if (g.computerTopCard() != null)
        {
        	computerPile.setIcon(cardImages[g.computerTopCard().getSuit().ordinal()][g.computerTopCard().getValue().ordinal()]);
        }
        else
        {
        	computerPile.setIcon(null);
        }
        computerPile.revalidate();
    }
    private void updateStatusMessage()
    {
    	String newMessage = "<html>"+g.getStatusMessage()
    			+ "<br>Your hand size: " + g.playerHandSize()
    			+ "<br>Computer's hand size: " + g.computerHandSize()
    			+ "</html>";
    	try
    	{
    		statusMessage.setText(newMessage);
    
    	}
    	catch (NullPointerException ex)
    	{
    		return; //Work around a Java bug...
    	}
    }
    private class ButtonListener implements ActionListener
    {
        // Called when the button is pushed.
        public void actionPerformed(ActionEvent e)
        {
        	nextStepButton.setText("Continue Game");
            g.step();
            updateCardImages();
            updateStatusMessage();
            
            // If the game is over
            if (g.getGameState() != 0)
            {
            	// Figure out who won
	            if (g.getGameState() < 0)
	            {
	            	javax.swing.JOptionPane.showMessageDialog(null, "The computer won.");
	            }
	            else //g.getGameState() > 0
	            {
	            	javax.swing.JOptionPane.showMessageDialog(null, "You win!");
	            }
	            // And finish up.
	            updateStatusMessage(); // To get the "You win!"/"Computer Wins." message
	            nextStepButton.setEnabled(false); // Disable the continue button.
            }
            

        }
    }
    public static void main(String[] args)
    {
        WarGUI wgui;
        try
        {
            wgui = new WarGUI();
        }
        catch (FileNotFoundException ex)
        {
            javax.swing.JOptionPane.showMessageDialog(null,ex.toString());
            return;
        }
        wgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wgui.setSize(700,500);
        wgui.validate();
        wgui.setVisible(true);
    }
}
