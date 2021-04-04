package CurrencyConverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

class ConversionTest{
    CurrencyData constructTestData(){ // Generate some consistent test data
        CurrencyData data = new CurrencyData();

        ArrayList<Float> rates = new ArrayList<>();
        data.addCurrency(new Date(), "AUD", rates);
        rates.add(0.9f);
        data.addCurrency(new Date(), "FLO", rates);
        rates.add(0.5f);
        data.addCurrency(new Date(), "POU", rates);
        rates.add(1.2f);
        data.addCurrency(new Date(), "TAL", rates);
        rates.add(2.1f);
        data.addCurrency(new Date(), "RAD", rates);

        return data;
    }

    @Test void conversion() {
        CurrencyData data = constructTestData();
        Conversion c = new Conversion();
        String value = c.convert(data, 2.0f,"AUD","FLO");
        assertEquals(value, "2.22FLO");
    }
}