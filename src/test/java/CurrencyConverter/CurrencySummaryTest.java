package CurrencyConverter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencySummaryTest {

    // No zero or negative values should be in this stage, so none should be tested

    @Test void testMean(){
        ArrayList<Float> values = new ArrayList<>(Arrays.asList(0.5f, 1.0f, 2.0f, 0.1f, 0.9f));
        assertEquals(CurrencySummary.summarise(values).get("avg"), 0.9f, "Should produce correct mean");
    }

    @Test void testMax(){
        ArrayList<Float> values = new ArrayList<>(Arrays.asList(0.5f, 1.0f, 2.0f, 0.1f, 0.9f));
        assertEquals(CurrencySummary.summarise(values).get("max"), 2.0f, "Should produce correct maximum");
    }

    @Test void testMin(){
        ArrayList<Float> values = new ArrayList<>(Arrays.asList(0.5f, 1.0f, 2.0f, 0.1f, 0.9f));
        assertEquals(CurrencySummary.summarise(values).get("min"), 0.1f, "Should produce correct minimum");
    }

    @Test void testMed(){
        ArrayList<Float> values = new ArrayList<>(Arrays.asList(0.5f, 1.0f, 2.0f, 0.1f, 0.9f));
        assertEquals(CurrencySummary.summarise(values).get("med"), 0.9f, "Should produce correct odd median");

        values = new ArrayList<>(Arrays.asList(0.5f, 1.0f, 2.0f, 0.1f, 0.9f, 10.1f));
        assertEquals(CurrencySummary.summarise(values).get("med"), 0.95f, "Should produce correct even median");
    }

    @Test void testSD(){
        ArrayList<Float> values = new ArrayList<>(Arrays.asList(0.5f, 1.0f, 2.0f, 0.1f, 0.9f));
        assertEquals(CurrencySummary.summarise(values).get("sd"), 0.6356099f, "Should produce correct standard deviation");
    }

}