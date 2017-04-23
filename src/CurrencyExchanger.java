
/**
 * Created by Talimere on 23.04.2017.
 * Coded by RootAlgorithm.
 */

public class CurrencyExchanger
{
    public static void main(String[] args) throws Exception
    {
        ExchangeReceiver test = new ExchangeReceiver("NOK");
        System.out.println(test.getEur());
        System.out.println(test.getGbp());
        System.out.println(test.getNok());
        
        test.setBaseCurrency("GBP");
        test.parseCurrencies();
        System.out.println(test.getNok());
        System.out.println(test.getEur());
    }
}
