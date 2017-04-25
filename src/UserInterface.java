import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by Talimere on 24.04.2017.
 * Coded by RootAlgorithm
 */

class UserInterface extends JFrame
{
    private JTextField fromValue;
    private JTextField nokVal, sekVal, gbpVal, usdVal, eurVal;

    private JComboBox currenciesList;
    
    //Creating an array that will hold unchangeable data
    private static final String[] currenciesArray = {"NOK", "SEK", "GBP", "USD", "EUR"};

    //Initializing the selected item to NOK
    private String selectedItem = currenciesArray[0];
    
    private ExchangeReceiver exchangeReceiver;
    
    private double inputAmount;
    
    UserInterface() throws Exception
    {
        //Using the super to set the title instead of the setTitle method
        super("Valutakalkulator");
        
        //Initializing the exchange receiver
        exchangeReceiver = new ExchangeReceiver(getSelectedItem());
        
        //Creating the panel for user input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        
        //Adding a combo box for selecting the base currency
        currenciesList = new JComboBox<>(currenciesArray);
        currenciesList.setMaximumRowCount(5);

        //IntelliJ suggested that I use a lambda here
        currenciesList.addItemListener(event ->
        {
            if(event.getStateChange() == ItemEvent.SELECTED)
            {
                setSelectedItem(currenciesList.getSelectedItem().toString());
            }
        });
        inputPanel.add(currenciesList);
        
        //Adding the input text field
        fromValue = new JTextField(10);
        inputPanel.add(fromValue);
        
        //Creating the panel for displaying data
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(0, 2, 5, 10));
        
        //Creating the labels for the currencies
        JLabel nokLabel = new JLabel("Norske kroner ");
        nokLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        outputPanel.add(nokLabel);
        nokVal = new JTextField(10);
        nokVal.setEditable(false);
        outputPanel.add(nokVal);
    
        JLabel sekLabel = new JLabel("Svenske kroner ");
        sekLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        outputPanel.add(sekLabel);
        sekVal = new JTextField(10);
        sekVal.setEditable(false);
        outputPanel.add(sekVal);
    
        JLabel gbpLabel = new JLabel("Britiske pund ");
        gbpLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        outputPanel.add(gbpLabel);
        gbpVal = new JTextField(10);
        gbpVal.setEditable(false);
        outputPanel.add(gbpVal);
    
        JLabel usdLabel = new JLabel("Amerikanske dollar ");
        usdLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        outputPanel.add(usdLabel);
        usdVal = new JTextField(10);
        usdVal.setEditable(false);
        outputPanel.add(usdVal);
    
        JLabel eurLabel = new JLabel("Euro ");
        eurLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        outputPanel.add(eurLabel);
        eurVal = new JTextField(10);
        eurVal.setEditable(false);
        outputPanel.add(eurVal);
        
        //Adding a panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        JButton calButton = new JButton("Kalkuler");
        buttonPanel.add(calButton);
        
        JButton exitButton = new JButton("Avslutt");

        //Added the action listener as a lambda
        exitButton.addActionListener(event -> System.exit(0));
        buttonPanel.add(exitButton);

        //Setting up the event handlers for the buttons
        ButtonEvent buttonEvent = new ButtonEvent();
        calButton.addActionListener(buttonEvent);
        exitButton.addActionListener(buttonEvent);
        
        //Setting up the JFrame
        this.setLayout(new BorderLayout());
        this.add(inputPanel, BorderLayout.WEST);
        this.add(outputPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
    //Creating an inner class for action handling
    private class ButtonEvent implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String action = event.getActionCommand();

            /*
             * I changed the exit button from being sent to the action handling class
             * to being implemented as a lambda instead.
             */

            /*
            if(action.equals("Avslutt"))
            {
                System.exit(0);
            }
            */
            if(action.equals("Kalkuler"))
            {
                //Sets the base currency in the exchange receiver to whatever is selected as the base currency
                exchangeReceiver.setBaseCurrency(getSelectedItem());

                try
                {
                    exchangeReceiver.parseCurrencies();
                }
                catch (Exception e)
                {
                    System.err.println("Noe gikk galt!");
                }

                try
                {
                    inputAmount = Double.parseDouble(fromValue.getText());
                    exchangeHandler();
                }
                catch (NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null, "Du har ikke skrevet inn en sum!", "Feil", JOptionPane.ERROR_MESSAGE);
                    nokVal.setText(String.format("%s", "Nå"));
                    sekVal.setText(String.format("%s", "har"));
                    gbpVal.setText(String.format("%s", "du"));
                    usdVal.setText(String.format("%s", "gjort"));
                    eurVal.setText(String.format("%s", "feil..."));
                }
            }
        }
    }
    
    //Creating method for handling the exchange class
    private void exchangeHandler()
    {
        nokVal.setText(String.format("%.2f", exchangeReceiver.getNok() * inputAmount));
        sekVal.setText(String.format("%.2f", exchangeReceiver.getSek() * inputAmount));
        gbpVal.setText(String.format("%.2f", exchangeReceiver.getGbp() * inputAmount));
        usdVal.setText(String.format("%.2f", exchangeReceiver.getUsd() * inputAmount));
        eurVal.setText(String.format("%.2f", exchangeReceiver.getEur() * inputAmount));
    }
    
    
    private String getSelectedItem() {
        return selectedItem;
    }
    
    private void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    
}