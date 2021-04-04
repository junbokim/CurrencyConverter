package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import javax.swing.*;

import org.junit.jupiter.api.*;

public class ConversionRatePageTest {
    private static CurrencyData currencyData;
    private static User admin;
    private static User guest;

    @BeforeAll static void init(){
        currencyData = new CurrencyData();

        admin = new User("admin", "admin", true);
        guest = new User("guest", "guest", false);

        ArrayList<Float> rates = new ArrayList<>();
        currencyData.addCurrency(new Date(), "AUD", rates);
        rates.add(1f);
        currencyData.addCurrency(new Date(), "FLO", rates);
        rates.add(2f);
        currencyData.addCurrency(new Date(), "POU", rates);
        rates.add(3f);
        currencyData.addCurrency(new Date(), "TAL", rates);
        rates.add(4f);
        currencyData.addCurrency(new Date(), "RAD", rates);
        rates.add(4f);
        currencyData.addCurrency(new Date(), "WON", rates);
        rates.add(4f);
        currencyData.addCurrency(new Date(), "PEL", rates);

        ArrayList<String> disNames = new ArrayList<>();
        disNames.add("AUD");
        disNames.add("FLO");
        disNames.add("POU");
        disNames.add("TAL");
        currencyData.setDisplayNames(disNames);
    }

    @Test void labelLayoutTest(){
        CurrencyRatePage page = new CurrencyRatePage(admin, currencyData);
        int expectedXVal = 100;
        int expectedYVal = 75;

        for(int row = 0; row < 5;row++){
            for(int col = 0; col<5;col++){
                expectedXVal = 100 * col;
                assertEquals(page.labels.get(row).get(col).getBounds().height, 100, String.format("JLabel at the %dth row and %dth column should have a height of %d", row,col,100));
                assertEquals(page.labels.get(row).get(col).getBounds().width, 100, String.format("JLabel at the %dth row and %dth column should have a width of %d", row,col,100));
                assertEquals(page.labels.get(row).get(col).getBounds().x, expectedXVal + 75, String.format("JLabel at the %dth row and %dth column should have a x position value of %d", row,col,100));
                assertEquals(page.labels.get(row).get(col).getBounds().y, expectedYVal, String.format("JLabel at the %dth row and %dth column should have a y position value of %d", row,col,100));
            }
            expectedYVal += 100;
        }
    }

    @Test void labelValueTest(){
        CurrencyRatePage page = new CurrencyRatePage(admin, currencyData);

        for(int row = 1; row < 5; row++){
            for(int col = 1; col < 5; col++){
                if(row == col){
                    assertEquals(page.labels.get(row).get(col).getText(), "-", "Each Cell with same row and column index should have - as a positional value");
                }
                else{
                    ArrayList<ArrayList<Integer>> currencyRateDirection = currencyData.getTableDirections();
                    String sign = "";
                    if(currencyRateDirection.get(row-1).get(col-1) >0){
                        sign = "/\\";
                    }
                    else if(currencyRateDirection.get(row-1).get(col-1) < 0){
                        sign = "\\/";
                    }
                    else{
                        sign = "";
                    }
                    assertEquals(page.labels.get(row).get(col).getText(), String.format("%.2f %s", currencyData.getTableValues().get(row-1).get(col-1), sign), "Each cell should hold the corresponding row and column exchange rate given by the currencyData's getTableValues function");
                }
            }
        }
    }

    @Test void labelValueNamesTest(){
        CurrencyRatePage page = new CurrencyRatePage(admin, currencyData);
        assertEquals(page.labels.get(0).get(0).getText(), "From \\ To", "The cell positioned 0,0 should have the value of From \\ To" );
        for(int row = 1; row < 5; row++){
            assertEquals(page.labels.get(row).get(0).getText(), this.currencyData.getDisplayNames().get(row-1), "Each column's first cell should have the first 4 currency name from CurrencyData class");
        }
        for(int col = 1; col < 5; col++){
            assertEquals(page.labels.get(0).get(col).getText(), this.currencyData.getDisplayNames().get(col-1),"Each row's first cell should have the first 4 currency name from CurrencyData class");
        }
    }

    @Test void refreshTest(){
        CurrencyRatePage page = new CurrencyRatePage(admin, currencyData);
        ArrayList<ArrayList<Integer>> currencyRateDirection = currencyData.getTableDirections();
        String sign = "";
        if(currencyRateDirection.get(1).get(2) >0){
            sign = "/\\";
        }
        else if(currencyRateDirection.get(1).get(2) < 0){
            sign = "\\/";
        }
        else{
            sign = "";
        }
        assertEquals(page.labels.get(2).get(3).getText(), String.format("%.2f %s", currencyData.getTableValues().get(1).get(2),sign),"The value should portray the value given in currencyData.getTableValues()");
        

        currencyData.updateCurrency(new Date(), currencyData.getNames().get(1), currencyData.getNames().get(2), 55);
        assertNotEquals(page.labels.get(2).get(3).getText(), String.format("%.2f %s", currencyData.getTableValues().get(1).get(2),sign),"The value should portray the value given in currencyData.getTableValues()");

        page.refresh(1, 2);
        if(currencyData.getTableDirections().get(1).get(2) > 0){
            sign = "/\\";
        }
        else if(currencyData.getTableDirections().get(1).get(2) < 0){
            sign = "\\/";
        }
        else{
            sign = "";
        }
        assertEquals(page.labels.get(2).get(3).getText(), String.format("%.2f %s", currencyData.getTableValues().get(1).get(2),sign),"The value should portray the value given in currencyData.getTableValues()");
    
        //this should not change anything because the refresh value parameter was over 4.
        page.refresh(4, 4);
        labelValueTest();

        page.refresh(2, 5);
        labelValueTest();

        page.refresh(5, 2);
        labelValueTest();
    }

    @Test void replacePopularAdmin(){
        CurrencyRatePage page = new CurrencyRatePage(admin, currencyData);
        
        //replacedCurrency
        assertEquals(page.replacedCurrency.getBounds().height, 25, "ReplacedCurrency should have a height value of 25");
        assertEquals(page.replacedCurrency.getBounds().width, 100, "ReplacedCurrency should have a width value of 200");
        assertEquals(page.replacedCurrency.getBounds().x, 50, "ReplacedCurrency should have a x value of 50");
        assertEquals(page.replacedCurrency.getBounds().y, 600, "ReplacedCurrency should have a y value of 600");
        //replacemenetCurrency
        assertEquals(page.replacementCurrency.getBounds().height, 25, "replacementCurrency should have a height value of 25");
        assertEquals(page.replacementCurrency.getBounds().width, 100, "replacementCurrency should have a width value of 200");
        assertEquals(page.replacementCurrency.getBounds().x, 300, "replacementCurrency should have a x value of 300");
        assertEquals(page.replacementCurrency.getBounds().y, 600, "replacementCurrency should have a y value of 600");

        //Warning
        assertEquals(page.warning.getBounds().height, 25, "warning should have a height value of 25");
        assertEquals(page.warning.getBounds().width, 600, "warning should have a width value of 600");
        assertEquals(page.warning.getBounds().x, 50, "warning should have a x value of 50");
        assertEquals(page.warning.getBounds().y, 625, "warning should have a y value of 600");
        assertFalse(page.warning.isVisible());
    }

    @Test void replacePopularguest(){
        CurrencyRatePage page = new CurrencyRatePage(guest, currencyData);

        assertNull(page.replacedCurrency, "should be null as it shouldn't be initialised");
        assertNull(page.replacementCurrency, "should be null as it shouldn't be initialised");
        assertNull(page.warning, "should be null as it shouldn't be initialised");
    }

    @Test void replacePopularValidTest(){
        CurrencyRatePage page = new CurrencyRatePage(admin, currencyData);

        page.replacePopular();
        assertEquals(page.warning.getText(),"Warning: Same Currency Selected", "If the same country was selected in both drop down field it should state in warning label" );
        assertTrue(page.warning.isVisible(), "Warning label should be visible if the same country was selected");

        page.replacedCurrency.setSelectedIndex(3);
        page.replacementCurrency.setSelectedIndex(2);

        page.replacePopular();
        assertEquals(page.warning.getText(),"Warning: Both currencies selected already exist in the table", "If the currency exists in table for selected currency in both drop down field it should state in warning label" );
        assertTrue(page.warning.isVisible(), "Warning label should be visible if both currency already exist in the table");

        page.warning.setVisible(false);
        page.warning.setText("");       

        page.replacementCurrency.setSelectedIndex(6); 
        page.replacePopular();
        assertEquals(page.warning.getText(),"Update Success!", "User Feedback that Update was applied successfully" );
        assertTrue(page.warning.isVisible(), "User feedback, warning label should be visible");
    }

    @Test void replacePopularTest(){
        CurrencyRatePage page = new CurrencyRatePage(admin, currencyData);
        page.replacementCurrency.setSelectedIndex(5); 
        page.replacePopular();
        assertEquals(page.labels.get(0).get(1).getText(), "WON", "it should be WON as AUD should have changed to WON");
        assertEquals(page.labels.get(1).get(0).getText(), "WON","it should be WON as AUD should have changed to WON");

        for(int i = 2; i< 5; i++){
            String sign = "";
            if(currencyData.getTableDirections().get(i-1).get(0) >0){
                sign = "/\\";
            }
            else if(currencyData.getTableDirections().get(i-1).get(0) < 0){
                sign = "\\/";
            }
            else{
                sign = "";
            }
            assertEquals(page.labels.get(i).get(1).getText(), String.format("%.2f %s", currencyData.getTableValues().get(i-1).get(0),sign),String.format("The value at %dth row where AUD exchange rate was should have updated value", i));
        }

        for(int i = 2; i< 5; i++){
            String sign = "";
            if(currencyData.getTableDirections().get(0).get(i-1) >0){
                sign = "/\\";
            }
            else if(currencyData.getTableDirections().get(0).get(i-1) < 0){
                sign = "\\/";
            }
            else{
                sign = "";
            }
            assertEquals(page.labels.get(1).get(i).getText(), String.format("%.2f %s", currencyData.getTableValues().get(0).get(i-1),sign),String.format("The value at %dth column where AUD exchange rate was should have updated value", i));
        }

    }


}
