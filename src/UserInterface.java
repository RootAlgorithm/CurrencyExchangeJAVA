import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Talimere on 24.04.2017.
 *
 */

public class UserInterface extends JFrame
{
    private JTextField fromValue;
    private JTextField toValue;
    private JComboBox currenciesList;
    private static final String[] currenciesArray = {"NOK", "SEK", "GBP", "USD", "EUR"};
    private String selectedItem = currenciesArray[0];
    private JButton testButton;
    
    public UserInterface()
    {
        super("Valutakalkulator");
        JPanel inputPanel = new JPanel(new FlowLayout());
        JPanel outputPanel = new JPanel(new FlowLayout());
        currenciesList = new JComboBox(currenciesArray);
        currenciesList.setMaximumRowCount(3);
        currenciesList.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if(e.getStateChange() == ItemEvent.SELECTED)
                {
                    setSelectedItem(currenciesList.getSelectedItem().toString());
                }
            }
        });
        inputPanel.add(currenciesList);
        fromValue = new JTextField(10);
        inputPanel.add(fromValue);
        toValue = new JTextField(10);
        outputPanel.add(toValue);
        testButton = new JButton("Test");
        outputPanel.add(testButton);
        this.setLayout(new BorderLayout());
        this.add(inputPanel, BorderLayout.WEST);
        this.add(outputPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public String getSelectedItem() {
        return selectedItem;
    }
    
    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}
