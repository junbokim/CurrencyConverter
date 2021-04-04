package CurrencyConverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CurrencyDataTest {
    @Test void testCreation() { // Test if it actually makes
        CurrencyData data = new CurrencyData();
        assertNotNull(data, "Database should generate");
    }

    @Test void testFileRead() { // Test the ability to update individual currencies (and the table to display it)
        CurrencyData data = new CurrencyData();

        assertTrue(data.readFromFile("defaultData.txt"), "Data read should be successful");
        assertEquals(data.getExchangeRate("GBP", "USD"), 1.29f, "Conversion rate should match");

        ArrayList<ArrayList<Float>> tableValues = data.getTableValues();
        //System.out.println(tableValues);
        String testString = "[[1.0, 0.55, 52.2, 2.95], [1.81, 1.0, 94.66, 5.35], [0.02, 0.01, 1.0, 0.06], [0.34, 0.19, 17.71, 1.0]]";
        assertEquals(tableValues.toString(), testString, "Table values should match");

        data = new CurrencyData();
        assertFalse(data.readFromFile("nonexistent.txt"), "Should not be able to read nonexistent data");
        assertFalse(data.readFromFile("invalidData/badDisplayNames.txt"), "Should return false for bad display names");
        assertFalse(data.readFromFile("invalidData/badTableStruc.txt"), "Should return false for bad table structure");
        assertFalse(data.readFromFile("invalidData/badTableValues.txt"), "Should return false for bad table values");
        assertFalse(data.readFromFile("invalidData/sizeMismatch.txt"), "Should return false for size mismatch");
    }

    CurrencyData constructTestData(){ // Generate some consistent test data
        CurrencyData data = new CurrencyData();

        ArrayList<Float> rates = new ArrayList<>();
        data.addCurrency(new Date(), "AUD", rates);
        rates = new ArrayList<>(Arrays.asList(0.9f));
        data.addCurrency(new Date(), "FLO", rates);
        rates = new ArrayList<>(Arrays.asList(0.9f, 0.5f));
        data.addCurrency(new Date(), "POU", rates);
        rates = new ArrayList<>(Arrays.asList(0.9f, 0.5f, 1.2f));
        data.addCurrency(new Date(), "TAL", rates);
        rates = new ArrayList<>(Arrays.asList(0.9f, 0.5f, 1.2f, 2.1f));
        data.addCurrency(new Date(), "RAD", rates);

        return data;
    }

    @Test void testConstruction() { // Make sure it can have a sequence of currencies added to it
        CurrencyData data = constructTestData();
        assertNotNull(data, "Database should generate");
    }

    @Test void testInvalidCurrencyAddition() { // Try to add an invalid currency with too many values
        CurrencyData data = constructTestData();
        ArrayList<Float> rates = new ArrayList<>();
        rates.add(2.1f);
        rates.add(1.2f);
        assertFalse(data.addCurrency(new Date(), "DRA", rates), "Should return false instead of updating");

        rates = new ArrayList<>();
        data = new CurrencyData();
        data.addCurrency(new Date(), "DRA", rates);
        rates = new ArrayList<>(Arrays.asList(0.0f));
        assertFalse(data.addCurrency(new Date(), "INB", rates), "Should return false instead of updating");
        rates = new ArrayList<>(Arrays.asList(-1.0f));
        assertFalse(data.addCurrency(new Date(), "INB", rates), "Should return false instead of updating");


    }

    @Test void getNames() { // Test that the currency names can be extracted in the correct order
        CurrencyData data = constructTestData();
        assertEquals(data.getNames().get(0),"AUD", "Names should match");
        assertEquals(data.getNames().get(3),"TAL", "Names should match");
    }

    @Test void testDisplayNames() { // Test the ability to add and retrieve display names
        CurrencyData data = constructTestData();
        ArrayList<String> disNames = new ArrayList<>();
        disNames.add("FLO");
        disNames.add("TAL");
        disNames.add("RAD");
        disNames.add("AUD");
        assertTrue(data.setDisplayNames(disNames),"Display names should add");
        assertEquals(disNames, data.getDisplayNames(), "New display names should match");

        ArrayList<String> longNames = new ArrayList<>(disNames);
        longNames.add("POU");
        assertFalse(data.setDisplayNames(longNames),"Display names should not add");
        ArrayList<String> wrongNames = new ArrayList<>(disNames);
        wrongNames.set(2, "INV");
        assertFalse(data.setDisplayNames(wrongNames),"Display names should not add");

        assertEquals(disNames, data.getDisplayNames(), "Invalid display names should not update");
    }

    @Test void testTableValues() { // Test the formatting and extraction of table values
        CurrencyData data = constructTestData();
        ArrayList<String> disNames = new ArrayList<>();
        disNames.add("FLO");
        disNames.add("TAL");
        disNames.add("RAD");
        disNames.add("AUD");
        data.setDisplayNames(disNames);
        ArrayList<ArrayList<Float>> tableValues = data.getTableValues();
        //System.out.println(tableValues);
        String testString = "[[1.0, 2.0, 2.0, 0.9], [0.5, 1.0, 0.48, 0.9], [0.5, 2.1, 1.0, 0.9], [1.11, 1.11, 1.11, 1.0]]";
        assertEquals(tableValues.toString(), testString, "Table values should match");
    }

    @Test void testCurrencyUpdate() { // Test the ability to update individual currencies (and the table to display it)
        CurrencyData data = constructTestData();
        ArrayList<String> disNames = new ArrayList<>();
        disNames.add("FLO");
        disNames.add("TAL");
        disNames.add("RAD");
        disNames.add("AUD");
        data.setDisplayNames(disNames);

        data.updateCurrency(new Date(), "AUD","TAL", 3.0f);
        data.updateCurrency(new Date(), "TAL","RAD", 4.0f);

        ArrayList<ArrayList<Float>> tableValues = data.getTableValues();

        String testString = "[[1.0, 2.0, 2.0, 0.9], [0.5, 1.0, 4.0, 0.33], [0.5, 0.25, 1.0, 0.9], [1.11, 3.0, 1.11, 1.0]]";
        assertEquals(tableValues.toString(), testString, "Table values should match");
    }

    @Test void testCurrencyUpdateFail() { // Test the validity of currency updates
        CurrencyData data = constructTestData();
        ArrayList<String> disNames = new ArrayList<>();
        disNames.add("FLO");
        disNames.add("TAL");
        disNames.add("RAD");
        disNames.add("AUD");
        data.setDisplayNames(disNames);

        // Invalid names
        assertTrue(data.updateCurrency(new Date(), "AUD","TAL", 3.0f),"Should be valid currency update");
        assertFalse(data.updateCurrency(new Date(), "AUD","INV", 3.0f),"Should be invalid currency update");
        assertFalse(data.updateCurrency(new Date(), "INV","TAL", 3.0f),"Should be invalid currency update");

        // Invalid amounts
        assertFalse(data.updateCurrency(new Date(), "AUD","TAL", 0.0f),"Should be invalid currency update");
        assertFalse(data.updateCurrency(new Date(), "AUD","TAL", -1.3f),"Should be invalid currency update");


    }

    @Test void testTableDirections() { // Test that the table correctly displays currency directions
        CurrencyData data = constructTestData();
        ArrayList<String> disNames = new ArrayList<>();
        disNames.add("FLO");
        disNames.add("TAL");
        disNames.add("RAD");
        disNames.add("AUD");
        data.setDisplayNames(disNames);

        data.updateCurrency(new Date(), "AUD","TAL", 3.0f);
        data.updateCurrency(new Date(), "TAL","RAD", 4.0f);
        data.updateCurrency(new Date(), "FLO","RAD", 2.0f); // Will not change values

        ArrayList<ArrayList<Integer>> tableValues = data.getTableDirections();
        // This is the previous and current output data so you can see the construction of the directions
        //"[[1.0, 2.0, 2.0, 0.9], [0.5, 1.0, 0.48, 0.9], [0.5, 2.1, 1.0, 0.9], [1.11, 1.11, 1.11, 1.0]]"
        //"[[1.0, 2.0, 2.0, 0.9], [0.5, 1.0, 4.0, 0.33], [0.5, 0.25, 1.0, 0.9], [1.11, 3.0, 1.11, 1.0]]"
        String testString = "[[0, 0, 0, 0], [0, 0, 1, -1], [0, -1, 0, 0], [0, 1, 0, 0]]";
        assertEquals(tableValues.toString(), testString, "Table values should match");
    }

    @Test void testExchangeHistory() {
        CurrencyData data = constructTestData();

        data.updateCurrency(new Date(), "AUD","TAL", 3.0f);
        data.updateCurrency(new Date(), "AUD","TAL", 4.0f);

        data.updateCurrency(new Date(new Date().getTime() - 2000), "AUD","TAL", 5.0f);

        data.updateCurrency(new Date(), "AUD","TAL", 2.0f);

        data.updateCurrency(new Date(new Date().getTime() + 2000), "AUD","TAL", 0.2f);

        ArrayList<Float> history = data.getExchangeHistory(new Date(new Date().getTime() - 1000), new Date(new Date().getTime() + 1000), "AUD", "TAL");

        assertEquals(history.get(0), 1.11f, "Historical values should match");
        assertEquals(history.get(1), 3.0f, "Historical values should match");
        assertEquals(history.get(2), 4.0f, "Historical values should match");
        assertEquals(history.get(3), 2.0f, "Historical values should match");

        assertNull(data.getExchangeHistory(new Date(), new Date(), "AUD", "INV"), "Should return null history");
        assertNull(data.getExchangeHistory(new Date(), new Date(), "INV", "TAL"), "Should return null history");

    }

    @Test void testGetExchangeRate() {
        CurrencyData data = constructTestData();
        float exc = data.getExchangeRate("AUD", "FLO");
        assertEquals(exc, 1.11f);
    }

}
