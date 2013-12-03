package cs110finalproject;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WarGUI extends JFrame
{
    private Game g;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton nextStepButton;

    public WarGUI()
    {
        setLayout(new GridLayout(2,1));
        topPanel = new JPanel();
        bottomPanel = new JPanel(new GridLayout(1,1));
        nextStepButton = new JButton("Flip a card");
        bottomPanel.add(nextStepButton);

        this.add(topPanel);
        this.add(bottomPanel);
    }
    public static void main(String[] args)
    {
        WarGUI wgui = new WarGUI();
        wgui.setSize(500,500);
        wgui.validate();
        wgui.setVisible(true);
    }
}
