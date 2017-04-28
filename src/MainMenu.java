import javax.swing.*;
import java.awt.*;

/**
 * Created by Talimere on 28/04/17.
 * Coded by RootAlgorithm
 */

class MainMenu extends JFrame
{
    MainMenu()
    {
        this.setLayout(new GridLayout(2, 1));

        JButton badOne = new JButton("Sånn som oppgaven sier");
        badOne.addActionListener(click ->
        {
            try
            {
                oldUserInterface badUI = new oldUserInterface();
                badUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                badUI.setSize(500, 150);
                badUI.setResizable(false);
                badUI.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        this.add(badOne);

        JButton goodOne = new JButton("Sånn som jeg ville gjort det");
        goodOne.addActionListener(theActionThatExecutesWhenThisButtonIsClicked -> {
            try
            {
                newUserInterface goodUI = new newUserInterface();
                goodUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                goodUI.setSize(500, 240);
                goodUI.setResizable(false);
                goodUI.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        this.add(goodOne);
    }
}
