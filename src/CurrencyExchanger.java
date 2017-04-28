/**
 * Created by Talimere on 23.04.2017.
 * Coded by RootAlgorithm
 *
 * Mandatory assignment in PGR101
 */

public class CurrencyExchanger
{
    public static void main(String[] args) throws Exception
    {
        SecondUserInterface secondUserInterface = new SecondUserInterface();
        secondUserInterface.setSize(500, 150);
        secondUserInterface.setResizable(false);
        secondUserInterface.setVisible(true);

        UserInterface userInterface = new UserInterface();
        userInterface.setSize(500,240);
        userInterface.setResizable(false);
        userInterface.setVisible(true);
    }
}
