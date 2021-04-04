package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import javax.swing.*;

import org.junit.jupiter.api.*;

public class NewCurrencyPageTest{

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
        NewCurrencyPage page = pageManager.newCurrencyPage;

        //titleLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(6)).getText(), "<html><h1 style = \"text-align: center;\">Add New Currency</h1></html>", "titleLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(6).getBounds().x, 50, "titleLabel should have expected x value of 50");
        assertEquals(page.currentPanel.getComponent(6).getBounds().y, 15, "titleLabel should have expected y value of 15");
        assertEquals(page.currentPanel.getComponent(6).getBounds().width, 250, "titleLabel should have expected width value of 250");
        assertEquals(page.currentPanel.getComponent(6).getBounds().height, 75, "titleLabel should have expected height value of 75");

        //submit
        assertEquals(((JButton)page.currentPanel.getComponent(7)).getText(), "Update", "submit should have expected value");
        assertEquals(page.currentPanel.getComponent(7).getBounds().x, 500, "submit should have expected x value of 500");
        assertEquals(page.currentPanel.getComponent(7).getBounds().y, 75, "submit should have expected y value of 75");
        assertEquals(page.currentPanel.getComponent(7).getBounds().width, 100, "submit should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(7).getBounds().height, 50, "submit should have expected height value of 50");


        //currencyNameLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(8)).getText(), "Please enter the name of new Currency", "currencyNameLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(8).getBounds().x, 100, "currencyNameLabel should have expected x value of 100");
        assertEquals(page.currentPanel.getComponent(8).getBounds().y, 75, "currencyNameLabel should have expected y value of 75");
        assertEquals(page.currentPanel.getComponent(8).getBounds().width, 250, "currencyNameLabel should have expected width value of 250");
        assertEquals(page.currentPanel.getComponent(8).getBounds().height, 50, "currencyNameLabel should have expected height value of 50");

        //newCurrencyName
        assertEquals(page.currentPanel.getComponent(9).getBounds().x, 100, "newCurrencyName should have expected x value of 100");
        assertEquals(page.currentPanel.getComponent(9).getBounds().y, 125, "newCurrencyName should have expected y value of 125");
        assertEquals(page.currentPanel.getComponent(9).getBounds().width, 100, "newCurrencyName should have expected width value of 100");
        assertEquals(page.currentPanel.getComponent(9).getBounds().height, 25, "newCurrencyName should have expected height value of 25");

        //warningLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(10)).getText(), "Warning: ", "warningLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(10).getBounds().x, 250, "warningLabel should have expected x value of 250");
        assertEquals(page.currentPanel.getComponent(10).getBounds().y, 125, "warningLabel should have expected y value of 125");
        assertEquals(page.currentPanel.getComponent(10).getBounds().width, 600, "warningLabel should have expected width value of 600");
        assertEquals(page.currentPanel.getComponent(10).getBounds().height, 25, "warningLabel should have expected height value of 25");

        //currentCurrencyLabel
        assertEquals(((JLabel)page.currentPanel.getComponent(11)).getText(), "<html><h3>Conversion rate from <br>New Currency<br>to Existing Currency</h3></html>", "currentCurrencyLabel should have expected value");
        assertEquals(page.currentPanel.getComponent(11).getBounds().x, 75, "currentCurrencyLabel should have expected x value of 75");
        assertEquals(page.currentPanel.getComponent(11).getBounds().y, 150, "currentCurrencyLabel should have expected y value of 150");
        assertEquals(page.currentPanel.getComponent(11).getBounds().width, 250, "currentCurrencyLabel should have expected width value of 250");
        assertEquals(page.currentPanel.getComponent(11).getBounds().height, 100, "currentCurrencyLabel should have expected height value of 100");
    }

    @Test void textFieldentityLayoutTesting(){
        PageManager pageManager = new PageManager(admin, currencyData);
        NewCurrencyPage page = pageManager.newCurrencyPage;
        int x = 75;
        int y = 250;
        int width = 100;
        int height = 25;
        int spacer = 25;

        for(int i = 0; i < currencyData.getNames().size(); i++){
            assertEquals(page.currentPanel.getComponent(12 + (3*i)).getBounds().x, 75, String.format("JLabel Existing Currency Name Element at %d position of form should have x value of %d",i,75 ));
            assertEquals(page.currentPanel.getComponent(12 + (3*i)).getBounds().y, y + (height * i), String.format("JLabel Existing Currency Name Element at %d position of form should have y value of %d",i,y + (height * i)));
            assertEquals(page.currentPanel.getComponent(12 + (3*i)).getBounds().width, 100, String.format("JLabel Existing Currency Name Element at %d position of form should have width value of %d",i,100 ));
            assertEquals(page.currentPanel.getComponent(12 + (3*i)).getBounds().height, 25, String.format("JLabel Existing Currency Name Element at %d position of form should have height value of %d",i,25 ));

            assertEquals(page.currentPanel.getComponent(12 + (3*i) + 1).getBounds().x, x + width + spacer, String.format("JTextField New currency Rate Element at %d position of form should have x value of %d",i,x + width + spacer ));
            assertEquals(page.currentPanel.getComponent(12 + (3*i) + 1).getBounds().y, y + (height*i), String.format("JTextField New currency Rate Element at %d position of form should have y value of %d",i,y + (height*i)));
            assertEquals(page.currentPanel.getComponent(12 + (3*i) + 1).getBounds().width, 100, String.format("JTextField New currency Rate Element at %d position of form should have width value of %d",i,100 ));
            assertEquals(page.currentPanel.getComponent(12 + (3*i) + 1).getBounds().height, 25, String.format("JTextField New currency Rate Element at %d position of form should have height value of %d",i,25 ));

            assertEquals(page.currentPanel.getComponent(12 + (3*i) + 2).getBounds().x, x + (width * 2) + (spacer * 2), String.format("JLabel Warning Element at %d position of form should have x value of %d",i,x + (width * 2) + (spacer * 2) ));
            assertEquals(page.currentPanel.getComponent(12 + (3*i) + 2).getBounds().y, y + (height *i), String.format("JLabel Warning Element at %d position of form should have y value of %d",i,y + (height*i) ));
            assertEquals(page.currentPanel.getComponent(12 + (3*i) + 2).getBounds().width, 400, String.format("JLabel Warning Element at %d position of form should have width value of %d",i,400 ));
            assertEquals(page.currentPanel.getComponent(12 + (3*i) + 2).getBounds().height, 25, String.format("JLabel Warning Element at %d position of form should have height value of %d",i,25 ));
            assertFalse(page.currentPanel.getComponent(12 + (3*i) + 2).isVisible(), String.format("Warning Element should be initially invisible"));

            y += spacer;
        }
    }

    @Test void textFieldValuesTesting(){
        PageManager pageManager = new PageManager(admin, currencyData);
        NewCurrencyPage page = pageManager.newCurrencyPage;
        for(int i = 0; i < currencyData.getNames().size(); i++){
            assertEquals(((JLabel)page.currentPanel.getComponent(12 + (3*i))).getText(),currencyData.getNames().get(i), "Each JLabel element should hold the Names of each existing currency's name");
            
            assertEquals(((JLabel)page.currentPanel.getComponent(12 + (3*i)+2)).getText(),"Warning: ", "Should be the warning sign to inform users if something is wrong with the corresponding value");
        }
    }

    @Test void correctInput(){
        PageManager pageManager = new PageManager(admin, currencyData);
        NewCurrencyPage page = pageManager.newCurrencyPage;

        int initialNamesLength = currencyData.getNames().size();

        for(int i = 0; i<currencyData.getNames().size();i++){
            page.newCurrencyRate.get(i).setText("1");
        }

        page.newCurrencyName.setText("WON");

        page.validUserInput();

        assertEquals(page.warningLabelList.get(0).getText(), "Update Success!", "WarningLabel should give feedback that update is complete");
        assertTrue(page.warningLabelList.get(0).isVisible());

        for(int i = 1; i<page.warningLabelList.size();i++){
            assertFalse(page.warningLabelList.get(i).isVisible(), "All other warning label should be invisible");
        }

        assertEquals(page.currentPanel.getComponentCount(), 30, "Should be 30 as it should have added 3 new elements when success");
        assertEquals(((JLabel)page.currentPanel.getComponent(27)).getText(),"WON");

        assertEquals(currencyData.getNames().size(),initialNamesLength + 1, "Should be initialNamesLength + 1 because there is a new updated value added to currencyData");
        assertEquals(currencyData.getNames().get(currencyData.getNames().size()-1), "WON", "should be Won if added properly");
    }

    @Test void invalidUserInputTest(){
        PageManager pageManager = new PageManager(admin, currencyData);
        NewCurrencyPage page = pageManager.newCurrencyPage;
        int initialNamesLength = currencyData.getNames().size();

        for(int i = 0; i<currencyData.getNames().size();i++){
            page.newCurrencyRate.get(i).setText("asd");
        }

        page.newCurrencyName.setText("WON");

        page.validUserInput();

        assertEquals(initialNamesLength, currencyData.getNames().size(), "The currencyData.getNames().size() should not change as the input was invalid");
        
        for(int i = 0; i < currencyData.getNames().size();i++){
            assertEquals(((JLabel)page.currentPanel.getComponent(12 + (3*i)+2)).getText(),"Warning: Value input was not Numbers", "Warning label should show that value added was not a number");
            assertTrue(page.currentPanel.getComponent(12 + (3*i)+2).isVisible());
        }
    }

    @Test void alreadyExistingCurrencyNameTest(){
        PageManager pageManager = new PageManager(admin, currencyData);
        NewCurrencyPage page = pageManager.newCurrencyPage;
        int initialNamesLength = currencyData.getNames().size();

        for(int i = 0; i<currencyData.getNames().size();i++){
            page.newCurrencyRate.get(i).setText("1");
        }

        page.newCurrencyName.setText("AUD");

        page.validUserInput();

        assertEquals(initialNamesLength, currencyData.getNames().size(), "The currencyData.getNames().size() should not change as the input was invalid");

        assertTrue(page.warningLabelList.get(0).isVisible());
        assertEquals(page.warningLabelList.get(0).getText(), "Warning, The provided currency already exists");
    }

}