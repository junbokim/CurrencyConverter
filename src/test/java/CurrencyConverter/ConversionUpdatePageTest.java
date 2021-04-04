package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import javax.swing.*;

import org.junit.jupiter.api.*;

public class ConversionUpdatePageTest {

    private static CurrencyData currencyData;
    private static User admin;

    @BeforeAll static void init(){
        currencyData = new CurrencyData();

        admin = new User("admin", "admin", true);

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

        ArrayList<String> disNames = new ArrayList<>();
        disNames.add("AUD");
        disNames.add("FLO");
        disNames.add("POU");
        disNames.add("TAL");
        currencyData.setDisplayNames(disNames);
    }

    @Test void basicLayoutTest(){

        PageManager pageManager = new PageManager(admin, currencyData);
        ConversionRateUpdatePage page = pageManager.conversionRateUpdatePage;

        //titleLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(6)).getText(), "<html><h1 style = \"text-align: center;\">Conversion Rate Update</h1></html>", "titleLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(6).getBounds().x, 50, "titleLabel should have expected x value of 100");
        assertEquals(page.currentPanel.getComponent(6).getBounds().y, 15, "titleLabel should have expected y value of 75");
        assertEquals(page.currentPanel.getComponent(6).getBounds().width, 350, "titleLabel should have expected width value of 250");
        assertEquals(page.currentPanel.getComponent(6).getBounds().height, 75, "titleLabel should have expected height value of 50");
        
        //fromCurrencyLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(7)).getText(), "Currency to convert from", "fromCurrencyLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(7).getBounds().x, 150, "fromCurrencyLabel should have expected x value of 150");
        assertEquals(page.currentPanel.getComponent(7).getBounds().y, 200, "fromCurrencyLabel should have expected y value of 200");
        assertEquals(page.currentPanel.getComponent(7).getBounds().width, 200, "fromCurrencyLabel should have expected width value of 200");
        assertEquals(page.currentPanel.getComponent(7).getBounds().height, 25, "fromCurrencyLabel should have expected height value of 25");
        
        //fromNationality
        assertEquals(page.currentPanel.getComponent(8).getBounds().x, 150, "fromNationality should have expected x value of 150");
        assertEquals(page.currentPanel.getComponent(8).getBounds().y, 250, "fromNationality should have expected y value of 250");
        assertEquals(page.currentPanel.getComponent(8).getBounds().width, 100, "fromNationality should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(8).getBounds().height, 50, "fromNationality should have expected height value of 50");
        
        //Arrow
        assertEquals(((JLabel)page.currentPanel.getComponent(9)).getText(), "=======>", "Arrow should have expected value");
        assertEquals(page.currentPanel.getComponent(9).getBounds().x, 350, "Arrow should have expected x value of 350");
        assertEquals(page.currentPanel.getComponent(9).getBounds().y, 250, "Arrow should have expected y value of 250");
        assertEquals(page.currentPanel.getComponent(9).getBounds().width, 100, "Arrow should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(9).getBounds().height, 50, "Arrow should have expected height value of 50");

        //toCurrencyLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(10)).getText(), "Currency to convert to", "toCurrencyLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(10).getBounds().x, 500, "toCurrencyLabel should have expected x value of 500");
        assertEquals(page.currentPanel.getComponent(10).getBounds().y, 200, "toCurrencyLabel should have expected y value of 200");
        assertEquals(page.currentPanel.getComponent(10).getBounds().width, 200, "toCurrencyLabel should have expected width value of 200");
        assertEquals(page.currentPanel.getComponent(10).getBounds().height, 25, "toCurrencyLabel should have expected height value of 25");
        
        //toNationality
        assertEquals(page.currentPanel.getComponent(11).getBounds().x, 500, "toNationality should have expected x value of 500");
        assertEquals(page.currentPanel.getComponent(11).getBounds().y, 250, "toNationality should have expected y value of 250");
        assertEquals(page.currentPanel.getComponent(11).getBounds().width, 100, "toNationality should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(11).getBounds().height, 50, "toNationality should have expected height value of 50");
                
        //currencyRateLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(12)).getText(), "Please Enter The Updated Rate", "currencyRateLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(12).getBounds().x, 300, "currencyRateLabel should have expected x value of 300");
        assertEquals(page.currentPanel.getComponent(12).getBounds().y, 400, "currencyRateLabel should have expected y value of 400");
        assertEquals(page.currentPanel.getComponent(12).getBounds().width, 200, "currencyRateLabel should have expected width value of 200");
        assertEquals(page.currentPanel.getComponent(12).getBounds().height, 25, "currencyRateLabel should have expected height value of 25");

        //currencyRate
        assertEquals(page.currentPanel.getComponent(13).getBounds().x, 325, "currencyRate should have expected x value of 325");
        assertEquals(page.currentPanel.getComponent(13).getBounds().y, 450, "currencyRate should have expected y value of 450");
        assertEquals(page.currentPanel.getComponent(13).getBounds().width, 100, "currencyRate should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(13).getBounds().height, 25, "currencyRate should have expected height value of 25");
                
        //warningLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(14)).getText(), "Warning: ", "warningLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(14).getBounds().x, 325, "warningLabel should have expected x value of 500");
        assertEquals(page.currentPanel.getComponent(14).getBounds().y, 500, "warningLabel should have expected y value of 200");
        assertEquals(page.currentPanel.getComponent(14).getBounds().width, 400, "warningLabel should have expected width value of 200");
        assertEquals(page.currentPanel.getComponent(14).getBounds().height, 50, "warningLabel should have expected height value of 25");
        assertFalse(page.currentPanel.getComponent(14).isVisible(), "Initially warningLabel should be invisible");
                
        //Record
        assertEquals(page.currentPanel.getComponent(15).getBounds().x, 325, "Record should have expected x value of 325");
        assertEquals(page.currentPanel.getComponent(15).getBounds().y, 600, "Record should have expected y value of 600");
        assertEquals(page.currentPanel.getComponent(15).getBounds().width, 100, "Record should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(15).getBounds().height, 50, "Record should have expected height value of 50");        
    }
    
    @Test void updateRateCorrectTest(){
        PageManager pageManager = new PageManager(admin, currencyData);
        ConversionRateUpdatePage page = pageManager.conversionRateUpdatePage;

        page.toNationality.setSelectedIndex(2);
        page.currencyRate.setText("22");
        page.updateRate();
        assertEquals(currencyData.getTableValues().get(0).get(2), 22, "Correlating corrency data for currencydata value should be 22");

        assertEquals(pageManager.currencyRatePage.labels.get(1).get(3).getText(),"22.00 /\\", "Value should be 22 as CurrencyRatePage should have been updated");
    }

    @Test void sameCurrencyErrTest(){
        PageManager pageManager = new PageManager(admin, currencyData);
        ConversionRateUpdatePage page = pageManager.conversionRateUpdatePage;

        page.updateRate();
        assertEquals(page.warningLabel.getText(),"Warning: Same Currency has been selected","Warning label should show that same country was selected if so");
        assertTrue(page.warningLabel.isVisible(), "Warning label should be visible");
    }

    @Test void invalidInputErrTest(){
        PageManager pageManager = new PageManager(admin, currencyData);
        ConversionRateUpdatePage page = pageManager.conversionRateUpdatePage;

        page.toNationality.setSelectedIndex(5);
        page.fromNationality.setSelectedIndex(2);
        page.currencyRate.setText("Test");
        page.updateRate();
        assertEquals(page.warningLabel.getText(),"Warning: Value input was not Numbers","Warning label should show that that input was not of a numeric type");
        assertTrue(page.warningLabel.isVisible(), "Warning label should be visible");
    }

}
