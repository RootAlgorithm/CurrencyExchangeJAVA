import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Talimere on 28/04/17.
 * Coded by RootAlgorithm
 */

class oldUserInterface extends JFrame
{
    private JTextField nokValue, otherValue;

    private double gbp, eur, usd, sek;

    oldUserInterface() throws Exception
    {
        super("VALUTAKALKULATOR");

        ExchangeReceiver exchangeReceiver = new ExchangeReceiver("NOK");

        setGbp(exchangeReceiver.getGbp());
        setEur(exchangeReceiver.getEur());
        setUsd(exchangeReceiver.getUsd());
        setSek(exchangeReceiver.getSek());

        this.setLayout(new BorderLayout(0, 0));

        JPanel inOutPanel = new JPanel();
        inOutPanel.setLayout(new FlowLayout());

        JPanel currencyButtonPanel = new JPanel();
        currencyButtonPanel.setLayout(new GridLayout(2, 2));

        JPanel operatorButtonPanel = new JPanel();
        operatorButtonPanel.setLayout(new FlowLayout());

        JLabel nokLabel = new JLabel("NOK");
        inOutPanel.add(nokLabel);

        nokValue = new JTextField(12);
        inOutPanel.add(nokValue);

        JLabel otherLabel = new JLabel("Annen Valuta");
        inOutPanel.add(otherLabel);

        otherValue = new JTextField(12);
        inOutPanel.add(otherValue);

        JButton gbpButton = new JButton("GBP");
        gbpButton.addActionListener(this::doCalculation);
        currencyButtonPanel.add(gbpButton);

        JButton eurButton = new JButton("EUR");
        eurButton.addActionListener(this::doCalculation);
        currencyButtonPanel.add(eurButton);

        JButton usdButton = new JButton("USD");
        usdButton.addActionListener(this::doCalculation);
        currencyButtonPanel.add(usdButton);

        JButton sekButton = new JButton("SEK");
        sekButton.addActionListener(this::doCalculation);
        currencyButtonPanel.add(sekButton);

        JButton removeNokButton = new JButton("Fjern NOK");
        removeNokButton.addActionListener(click -> nokValue.setText(""));
        operatorButtonPanel.add(removeNokButton);

        JButton removeOtherButton = new JButton("Fjern annen valuta");
        removeOtherButton.addActionListener(click -> otherValue.setText(""));
        operatorButtonPanel.add(removeOtherButton);

        JButton removeBothButton = new JButton("Fjern begge");
        removeBothButton.addActionListener(click ->
        {
            nokValue.setText("");
            otherValue.setText("");
        });
        operatorButtonPanel.add(removeBothButton);

        JButton exitButton = new JButton("Avslutt");
        exitButton.addActionListener(click -> System.exit(0));
        operatorButtonPanel.add(exitButton);

        this.add(inOutPanel, BorderLayout.NORTH);
        this.add(currencyButtonPanel, BorderLayout.CENTER);
        this.add(operatorButtonPanel, BorderLayout.SOUTH);

    }

    private boolean checkForErrors()
    {
        if((nokValue.getText().equals("")) && (otherValue.getText().equals("")))
        {
            JOptionPane.showMessageDialog(this, "Begge felt er tomme", "Feil", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        else if(!(nokValue.getText().equals("")) && !(otherValue.getText().equals("")))
        {
            JOptionPane.showMessageDialog(this, "Begge felt har tekst", "Feil", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private void doCalculation(ActionEvent event)
    {
        if(!checkForErrors())
        {
            switch (event.getActionCommand())
            {
                case "GBP":
                    if(whichWay().equals("fromnok"))
                    {
                        otherValue.setText(String.format("%.2f", parseVal(nokValue.getText()) * getGbp()));
                    }
                    else if(whichWay().equals("fromother"))
                    {
                        nokValue.setText(String.format("%.2f", parseVal(otherValue.getText()) / getGbp()));
                    }
                    break;
                case "EUR":
                    if(whichWay().equals("fromnok"))
                    {
                        otherValue.setText(String.format("%.2f", parseVal(nokValue.getText()) * getEur()));
                    }
                    else if(whichWay().equals("fromother"))
                    {
                        nokValue.setText(String.format("%.2f", parseVal(otherValue.getText()) / getEur()));
                    }
                    break;
                case "USD":
                    if(whichWay().equals("fromnok"))
                    {
                        otherValue.setText(String.format("%.2f", parseVal(nokValue.getText()) * getUsd()));
                    }
                    else if(whichWay().equals("fromother"))
                    {
                        nokValue.setText(String.format("%.2f", parseVal(otherValue.getText()) / getUsd()));
                    }
                    break;
                case "SEK":
                    if(whichWay().equals("fromnok"))
                    {
                        otherValue.setText(String.format("%.2f", parseVal(nokValue.getText()) * getSek()));
                    }
                    else if(whichWay().equals("fromother"))
                    {
                        nokValue.setText(String.format("%.2f", parseVal(otherValue.getText()) / getSek()));
                    }
                    break;
                default:
                    break;
            }
        }

    }

    private double parseVal(String valString)
    {
        double retVal = 0.0;
        try
        {
            retVal = Double.parseDouble(valString);
        }
        catch (NumberFormatException nfe)
        {
            nfe.printStackTrace();
        }
        return retVal;
    }


    private String whichWay()
    {
        if(!nokValue.getText().equals(""))
        {
            return "fromnok";
        }
        return "fromother";
    }

    private double getGbp()
    {
        return gbp;
    }

    private void setGbp(double gbp)
    {
        this.gbp = gbp;
    }

    private double getEur()
    {
        return eur;
    }

    private void setEur(double eur)
    {
        this.eur = eur;
    }

    private double getUsd()
    {
        return usd;
    }

    private void setUsd(double usd)
    {
        this.usd = usd;
    }

    private double getSek()
    {
        return sek;
    }

    private void setSek(double sek)
    {
        this.sek = sek;
    }
}

