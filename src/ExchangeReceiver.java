import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Talimere on 23.04.2017.
 * Coded by RootAlgorithm
 */

public class ExchangeReceiver
{
    private String baseCurrency;
    private double nok;
    private double sek;
    private double gbp;
    private double usd;
    private double eur;
    
    public ExchangeReceiver(String baseCurrency) throws Exception
    {
        setBaseCurrency(baseCurrency);
        parseCurrencies();
    }
    
    public void parseCurrencies() throws Exception
    {
        resetRates();
        String requestURL = String.format("http://api.fixer.io/latest?base=%s", getBaseCurrency());
        URL fixerURL = new URL(requestURL);
        URLConnection connection = fixerURL.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = bufferedReader.readLine();
        JSONObject jsonResponse = new JSONObject(response);
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
    
    public void resetRates()
    {
        setNok(1.0);
        setSek(1.0);
        setGbp(1.0);
        setUsd(1.0);
        setEur(1.0);
    }
    
    public String getBaseCurrency() {
        return baseCurrency;
    }
    
    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
    
    public double getNok() {
        return nok;
    }
    
    public void setNok(double nok) {
        this.nok = nok;
    }
    
    public double getSek() {
        return sek;
    }
    
    public void setSek(double sek) {
        this.sek = sek;
    }
    
    public double getGbp() {
        return gbp;
    }
    
    public void setGbp(double gbp) {
        this.gbp = gbp;
    }
    
    public double getUsd() {
        return usd;
    }
    
    public void setUsd(double usd) {
        this.usd = usd;
    }
    
    public double getEur() {
        return eur;
    }
    
    public void setEur(double eur) {
        this.eur = eur;
    }
}
