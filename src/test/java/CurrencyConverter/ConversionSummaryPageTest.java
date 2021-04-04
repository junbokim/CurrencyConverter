package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

import org.junit.jupiter.api.*;

public class ConversionSummaryPageTest {
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

        ArrayList<String> disNames = new ArrayList<>();
        disNames.add("AUD");
        disNames.add("FLO");
        disNames.add("POU");
        disNames.add("TAL");
        currencyData.setDisplayNames(disNames);
    }

    @Test void basicLayoutTest(){
        PageManager pageManager = new PageManager(admin, currencyData);
        ConversionRateSummaryPage page = pageManager.conversionRateSummaryPage;

        //titleLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(6)).getText(), "<html><h1 style = \"text-align: center;\">Conversion Rate Summary</h1></html>", "titleLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(6).getBounds().x, 50, "titleLabel should have expected x value of 100");
        assertEquals(page.currentPanel.getComponent(6).getBounds().y, 15, "titleLabel should have expected y value of 75");
        assertEquals(page.currentPanel.getComponent(6).getBounds().width, 350, "titleLabel should have expected width value of 250");
        assertEquals(page.currentPanel.getComponent(6).getBounds().height, 75, "titleLabel should have expected height value of 75");

        //fromCurrencyLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(7)).getText(), "Currency to convert from", "fromCurrencyLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(7).getBounds().x, 50, "fromCurrencyLabel should have expected x value of 50");
        assertEquals(page.currentPanel.getComponent(7).getBounds().y, 75, "fromCurrencyLabel should have expected y value of 75");
        assertEquals(page.currentPanel.getComponent(7).getBounds().width, 200, "fromCurrencyLabel should have expected width value of 200");
        assertEquals(page.currentPanel.getComponent(7).getBounds().height, 25, "fromCurrencyLabel should have expected height value of 25");
        
        //fromNationality
        assertEquals(page.currentPanel.getComponent(8).getBounds().x, 50, "fromNationality should have expected x value of 50");
        assertEquals(page.currentPanel.getComponent(8).getBounds().y, 100, "fromNationality should have expected y value of 100");
        assertEquals(page.currentPanel.getComponent(8).getBounds().width, 100, "fromNationality should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(8).getBounds().height, 35, "fromNationality should have expected height value of 35");
        
        //Arrow
        assertEquals(((JLabel)page.currentPanel.getComponent(9)).getText(), "=======>", "Arrow should have expected value");
        assertEquals(page.currentPanel.getComponent(9).getBounds().x, 220, "Arrow should have expected x value of 220");
        assertEquals(page.currentPanel.getComponent(9).getBounds().y, 100, "Arrow should have expected y value of 100");
        assertEquals(page.currentPanel.getComponent(9).getBounds().width, 100, "Arrow should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(9).getBounds().height, 35, "Arrow should have expected height value of 35");

        //toCurrencyLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(10)).getText(), "Currency to convert to", "toCurrencyLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(10).getBounds().x, 350, "toCurrencyLabel should have expected x value of 350");
        assertEquals(page.currentPanel.getComponent(10).getBounds().y, 75, "toCurrencyLabel should have expected y value of 75");
        assertEquals(page.currentPanel.getComponent(10).getBounds().width, 200, "toCurrencyLabel should have expected width value of 200");
        assertEquals(page.currentPanel.getComponent(10).getBounds().height, 25, "toCurrencyLabel should have expected height value of 25");

        //toNationality
        assertEquals(page.currentPanel.getComponent(11).getBounds().x, 350, "toNationality should have expected x value of 350");
        assertEquals(page.currentPanel.getComponent(11).getBounds().y, 100, "toNationality should have expected y value of 100");
        assertEquals(page.currentPanel.getComponent(11).getBounds().width, 100, "toNationality should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(11).getBounds().height, 35, "toNationality should have expected height value of 35");

        //fromTimLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(12)).getText(), "<html>Start Date<br>DD/MM/YYYY, eg. 26/03/2020</html>", "fromTimLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(12).getBounds().x, 50, "fromTimLabel should have expected x value of 50");
        assertEquals(page.currentPanel.getComponent(12).getBounds().y, 150, "fromTimLabel should have expected y value of 150");
        assertEquals(page.currentPanel.getComponent(12).getBounds().width, 300, "fromTimLabel should have expected width value of 300");
        assertEquals(page.currentPanel.getComponent(12).getBounds().height, 50, "fromTimLabel should have expected height value of 50");

        //fromTime
        assertEquals(page.currentPanel.getComponent(13).getBounds().x, 50, "fromTime should have expected x value of 350");
        assertEquals(page.currentPanel.getComponent(13).getBounds().y, 200, "fromTime should have expected y value of 200");
        assertEquals(page.currentPanel.getComponent(13).getBounds().width, 100, "fromTime should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(13).getBounds().height, 35, "fromTime should have expected height value of 35");

        //toTimeLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(14)).getText(), "<html>End Date<br>DD/MM/YYYY, eg. 26/03/2020</html>", "toTimeLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(14).getBounds().x, 350, "toTimeLabel should have expected x value of 350");
        assertEquals(page.currentPanel.getComponent(14).getBounds().y, 150, "toTimeLabel should have expected y value of 150");
        assertEquals(page.currentPanel.getComponent(14).getBounds().width, 200, "toTimeLabel should have expected width value of 200");
        assertEquals(page.currentPanel.getComponent(14).getBounds().height, 50, "toTimeLabel should have expected height value of 50");

        //toTime
        assertEquals(page.currentPanel.getComponent(15).getBounds().x, 350, "toTime should have expected x value of 350");
        assertEquals(page.currentPanel.getComponent(15).getBounds().y, 200, "toTime should have expected y value of 200");
        assertEquals(page.currentPanel.getComponent(15).getBounds().width, 100, "toTime should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(15).getBounds().height, 35, "toTime should have expected height value of 35");

        //getSummary
        assertEquals(((JButton)page.currentPanel.getComponent(16)).getText(), "Get Summary", "getSummary should have expected value");
        assertEquals(page.currentPanel.getComponent(16).getBounds().x, 550, "getSummary should have expected x value of 550");
        assertEquals(page.currentPanel.getComponent(16).getBounds().y, 125, "getSummary should have expected y value of 125");
        assertEquals(page.currentPanel.getComponent(16).getBounds().width, 150, "getSummary should have expected width value of 150");
        assertEquals(page.currentPanel.getComponent(16).getBounds().height, 50, "getSummary should have expected height value of 50");

        //warning
        assertEquals(((JLabel)page.currentPanel.getComponent(17)).getText(), "Warning: ", "warning should have expected value");
        assertEquals(page.currentPanel.getComponent(17).getBounds().x, 50, "warning should have expected x value of 50");
        assertEquals(page.currentPanel.getComponent(17).getBounds().y, 250, "warning should have expected y value of 250");
        assertEquals(page.currentPanel.getComponent(17).getBounds().width, 600, "warning should have expected width value of 600");
        assertEquals(page.currentPanel.getComponent(17).getBounds().height, 25, "warning should have expected height value of 25");

        //conversionRateLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(18)).getText(), "All Recorded Conversion Rates (From Latest to Oldest):", "conversionRateLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(18).getBounds().x, 50, "conversionRateLabel should have expected x value of 50");
        assertEquals(page.currentPanel.getComponent(18).getBounds().y, 300, "conversionRateLabel should have expected y value of 300");
        assertEquals(page.currentPanel.getComponent(18).getBounds().width, 200, "conversionRateLabel should have expected width value of 200");
        assertEquals(page.currentPanel.getComponent(18).getBounds().height, 25, "conversionRateLabel should have expected height value of 25");

        //scrollPane
        assertEquals(page.currentPanel.getComponent(19).getBounds().x, 50, "scrollPane should have expected x value of 50");
        assertEquals(page.currentPanel.getComponent(19).getBounds().y, 350, "scrollPane should have expected y value of 350");
        assertEquals(page.currentPanel.getComponent(19).getBounds().width, 500, "scrollPane should have expected width value of 500");
        assertEquals(page.currentPanel.getComponent(19).getBounds().height, 200, "scrollPane should have expected height value of 200");

        for(int i = 20; i <= 24; i++){
            assertEquals(((JLabel)page.currentPanel.getComponent(i)).getText(), "", "All Summary Element should initially begin with no Text elements");
            assertEquals(page.currentPanel.getComponent(i).getBounds().x, 600, "Each Summary Element should have expected x value of 600");
            assertEquals(page.currentPanel.getComponent(i).getBounds().y, 300 + (50*(i-20)), "Each Summary Element should have expected y value that starts from 300 and increments higher by 50");
            assertEquals(page.currentPanel.getComponent(i).getBounds().width, 200, "Each Summary Element should have expected width value of 200");
            assertEquals(page.currentPanel.getComponent(i).getBounds().height, 25, "Each Summary Element should have expected height value of 25");
        }

    }

    
    @Test void dateFormatErr(){
        PageManager pageManager = new PageManager(admin, currencyData);
        ConversionRateSummaryPage page = pageManager.conversionRateSummaryPage;
        page.toNationality.setSelectedIndex(2);
        page.fromTime.setText("");

        page.getInput();
        //When empty value is put in Time TextField, it should cause warning label to show
        assertTrue(page.warning.isVisible() ,"Warning should be visible");
        assertEquals(page.warning.getText(), "Warning: Date format was incorrect", "Warning should say the date format was incorrect");


        page.warning.setVisible(false);
        page.warning.setText("");

        page.fromTime.setText("20-02-2020");
        page.getInput();
        //When Date format is input incorrectly in Time TextField, it should cause warning label to show
        assertTrue(page.warning.isVisible() ,"Warning should be visible");
        assertEquals(page.warning.getText(), "Warning: Date format was incorrect", "Warning should say the date format was incorrect");

        page.fromTime.setText("20/02/2020");
        page.toTime.setText("20/02/2022");
        assertTrue(page.warning.isVisible() ,"Warning should be invisible");

        page.toTime.setText("");

        page.getInput();
        //When empty value is put in Time TextField, it should cause warning label to show
        assertTrue(page.warning.isVisible() ,"Warning should be visible");
        assertEquals(page.warning.getText(), "Warning: Date format was incorrect", "Warning should say the date format was incorrect");


        page.warning.setVisible(false);
        page.warning.setText("");

        page.toTime.setText("20-02-2020");
        page.getInput();
        //When Date format is input incorrectly in Time TextField, it should cause warning label to show
        assertTrue(page.warning.isVisible() ,"Warning should be visible");
        assertEquals(page.warning.getText(), "Warning: Date format was incorrect", "Warning should say the date format was incorrect");
    }

    @Test void InputValidationTest() throws ParseException{
        PageManager pageManager = new PageManager(admin, currencyData);
        ConversionRateSummaryPage page = pageManager.conversionRateSummaryPage;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate1 = simpleDateFormat.parse("20/02/2020");
        Date testDate2 = simpleDateFormat.parse("02/02/2022");

        page.inputValidation(testDate1, testDate2);

        assertTrue(page.warning.isVisible(), "Warning should show when same currency is chosen");
        assertEquals(page.warning.getText(), "Warning: Same Currency has been selected", "Warning should tell that same currency is chosen");

        page.warning.setVisible(false);

        page.toNationality.setSelectedIndex(3);
        page.inputValidation(testDate1, testDate1);
        assertTrue(page.warning.isVisible(), "Warning should show when same date is chosen");
        assertEquals(page.warning.getText(), "Warning: Same Date is Chosen", "Warning should show when same date is chosen");

        page.warning.setVisible(false);
        page.inputValidation(testDate2, testDate1);
        assertTrue(page.warning.isVisible(), "Warning should show if begin gate is after End date");
        assertEquals(page.warning.getText(), "Warning: Start Date begines after End Date", "Warning should show when begin date is after end date");
    }

}
