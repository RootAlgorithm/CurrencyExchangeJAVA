import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Talimere on 23.04.2017.
 * Coded by RootAlgorithm
 */

class ExchangeReceiver
{
    private String baseCurrency;
    private double nok, sek, gbp, usd, eur;
    
    ExchangeReceiver(String baseCurrency) throws Exception
    {
        setBaseCurrency(baseCurrency);
        parseCurrencies();
    }
    
    void parseCurrencies() throws Exception
    {
        //Sets all the rates to 1.0 so the base currency will not display incorrectly (mathematically)
        resetRates();

        //Sets up a request URL based on which currency is the base
        String requestURL = String.format("http://api.fixer.io/latest?base=%s", getBaseCurrency());
        URL fixerURL = new URL(requestURL);

        //Connects to the URL
        URLConnection connection = fixerURL.openConnection();

        //Receives the response
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = bufferedReader.readLine();

        //Parses the response to a JSON Object
        JSONObject jsonResponse = new JSONObject(response);

        //Sets the currency rates if the requested rate is not the base currency
        if(!getBaseCurrency().equals("NOK"))
        {
            setNok(jsonResponse.getJSONObject("rates").getDouble("NOK"));
        }
        if(!getBaseCurrency().equals("SEK"))
        {
            setSek(jsonResponse.getJSONObject("rates").getDouble("SEK"));
        }
        if(!getBaseCurrency().equals("GBP"))
        {
            setGbp(jsonResponse.getJSONObject("rates").getDouble("GBP"));
        }
        if(!getBaseCurrency().equals("USD"))
        {
            setUsd(jsonResponse.getJSONObject("rates").getDouble("USD"));
        }
        if(!getBaseCurrency().equals("EUR"))
        {
            setEur(jsonResponse.getJSONObject("rates").getDouble("EUR"));
        }

        bufferedReader.close();
    }
    
    private void resetRates()
    {
        setNok(1.0);
        setSek(1.0);
        setGbp(1.0);
        setUsd(1.0);
        setEur(1.0);
    }

    /**
     * IntelliJ generated methods
     */

    private String getBaseCurrency() {
        return baseCurrency;
    }
    
    void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
    
    double getNok() {
        return nok;
    }
    
    private void setNok(double nok) {
        this.nok = nok;
    }
    
    double getSek() {
        return sek;
    }
    
    private void setSek(double sek) {
        this.sek = sek;
    }
    
    double getGbp() {
        return gbp;
    }
    
    private void setGbp(double gbp) {
        this.gbp = gbp;
    }
    
    double getUsd() {
        return usd;
    }
    
    private void setUsd(double usd) {
        this.usd = usd;
    }
    
    double getEur() {
        return eur;
    }
    
    private void setEur(double eur) {
        this.eur = eur;
    }
}
