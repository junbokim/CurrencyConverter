package CurrencyConverter;

public class Conversion{    
    public Conversion(){
    }
    public String convert(CurrencyData data, float amount, String nameFrom, String nameTo){
        // amount comes from standard input
        float exc = data.getExchangeRate(nameFrom, nameTo);
        String convertedAmount = String.format("%.2f", amount*exc) + nameTo;

        return convertedAmount;

    }
}
