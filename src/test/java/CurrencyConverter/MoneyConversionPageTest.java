package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import javax.swing.*;

import org.junit.jupiter.api.*;

public class MoneyConversionPageTest {
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

    @Test void pageSetUpTest(){
        MoneyConversionPage page = new MoneyConversionPage(admin, currencyData);
        JPanel panel = page.currentPanel;

        //titleLabel
        assertEquals(((JLabel)panel.getComponent(6)).getText(), "<html><h1 style = \"text-align: center;\">Currency Conversion</h1></html>", "Title Label should have expected label text");
        assertEquals(((JLabel)panel.getComponent(6)).getBounds().x, 50,"titleLabel should have x value of 50");
        assertEquals(((JLabel)panel.getComponent(6)).getBounds().y, 15,"titleLabel should have y value of 15");
        assertEquals(((JLabel)panel.getComponent(6)).getBounds().width, 350,"titleLabel should have width value of 350");
        assertEquals(((JLabel)panel.getComponent(6)).getBounds().height, 75,"titleLabel should have height value of 75");

        //fromLabel
        assertEquals(((JLabel)panel.getComponent(7)).getText(), "From Currency", "fromLabel should have \"From Currency\"");
        assertEquals(((JLabel)panel.getComponent(7)).getBounds().x, 200,"fromLabel should have x value of 200");
        assertEquals(((JLabel)panel.getComponent(7)).getBounds().y, 200,"fromLabel should have y value of 200");
        assertEquals(((JLabel)panel.getComponent(7)).getBounds().width, 150,"fromLabel should have width value of 150");
        assertEquals(((JLabel)panel.getComponent(7)).getBounds().height, 25,"fromLabel should have height value of 25");

        //fromDrop
        assertEquals(((JComboBox)panel.getComponent(8)).getBounds().x, 200,"fromLabel should have x value of 200");
        assertEquals(((JComboBox)panel.getComponent(8)).getBounds().y, 250,"fromLabel should have y value of 250");
        assertEquals(((JComboBox)panel.getComponent(8)).getBounds().width, 150,"fromLabel should have width value of 150");
        assertEquals(((JComboBox)panel.getComponent(8)).getBounds().height, 25,"fromLabel should have height value of 25");

        //toLabel
        assertEquals(((JLabel)panel.getComponent(9)).getText(), "To Currency", "toLabel should have \"To Currency\"");
        assertEquals(((JLabel)panel.getComponent(9)).getBounds().x, 400,"toLabel should have x value of 400");
        assertEquals(((JLabel)panel.getComponent(9)).getBounds().y, 200,"toLabel should have y value of 200");
        assertEquals(((JLabel)panel.getComponent(9)).getBounds().width, 150,"toLabel should have width value of 150");
        assertEquals(((JLabel)panel.getComponent(9)).getBounds().height, 25,"toLabel should have height value of 25");

        //toDrop
        assertEquals(((JComboBox)panel.getComponent(10)).getBounds().x, 400,"toDrop should have x value of 400");
        assertEquals(((JComboBox)panel.getComponent(10)).getBounds().y, 250,"toDrop should have y value of 250");
        assertEquals(((JComboBox)panel.getComponent(10)).getBounds().width, 150,"toDrop should have width value of 150");
        assertEquals(((JComboBox)panel.getComponent(10)).getBounds().height, 25,"toDrop should have height value of 25");

        //fromAmountLabel
        assertEquals(((JLabel)panel.getComponent(11)).getText(), "Enter Amount", "fromAmountLabel should have \"Enter Amount\"");
        assertEquals(((JLabel)panel.getComponent(11)).getBounds().x, 200,"fromAmountLabel should have x value of 200");
        assertEquals(((JLabel)panel.getComponent(11)).getBounds().y, 300,"fromAmountLabel should have y value of 300");
        assertEquals(((JLabel)panel.getComponent(11)).getBounds().width, 200,"fromAmountLabel should have width value of 200");
        assertEquals(((JLabel)panel.getComponent(11)).getBounds().height, 25,"fromAmountLabel should have height value of 25");

        //fromAmount
        assertEquals(((JTextField)panel.getComponent(12)).getBounds().x, 200,"fromAmount should have x value of 200");
        assertEquals(((JTextField)panel.getComponent(12)).getBounds().y, 350,"fromAmount should have y value of 350");
        assertEquals(((JTextField)panel.getComponent(12)).getBounds().width, 200,"fromAmount should have width value of 150");
        assertEquals(((JTextField)panel.getComponent(12)).getBounds().height, 25,"fromAmount should have height value of 25");

        //convertButton
        assertEquals(((JButton)panel.getComponent(13)).getBounds().x, 200,"convertButton should have x value of 200");
        assertEquals(((JButton)panel.getComponent(13)).getBounds().y, 400,"convertButton should have y value of 400");
        assertEquals(((JButton)panel.getComponent(13)).getBounds().width, 150,"convertButton should have width value of 150");
        assertEquals(((JButton)panel.getComponent(13)).getBounds().height, 50,"convertButton should have height value of 50");
    }

    @Test void validUserInputTest(){
        MoneyConversionPage page = new MoneyConversionPage(admin, currencyData);
        JPanel panel = page.currentPanel;

        assertFalse(panel.getComponent(16).isVisible(),"Initially warningLabel should be invisible");

        assertEquals(((JComboBox)panel.getComponent(8)).getSelectedIndex(), ((JComboBox)panel.getComponent(10)).getSelectedIndex(), "Initially, The selected item for drop down value should be the same");

        page.validUserInput();

        assertTrue(panel.getComponent(16).isVisible(), "If Error, warningLabel should be visible");
        assertEquals(((JLabel)panel.getComponent(16)).getText(), "Warning: Same Currency Selected", "The warning sign should tell that same elements were selected");
     
        ((JComboBox)panel.getComponent(10)).setSelectedIndex(2);
        
        ((JTextField)panel.getComponent(12)).setText("test");

        page.validUserInput();
        assertTrue(panel.getComponent(16).isVisible(), "If Error, warningLabel should be visible");
        assertEquals(((JLabel)panel.getComponent(16)).getText(), "Warning: Value input was not Numbers", "The warning sign should tell when non-float value was submitted");

        ((JTextField)panel.getComponent(12)).setText("");
        page.validUserInput();
        assertTrue(panel.getComponent(16).isVisible(), "If Error, warningLabel should be visible");
        assertEquals(((JLabel)panel.getComponent(16)).getText(), "Warning: Value input was not Numbers", "The warning sign should show not Number error if no input was submitted");
    }

    @Test void conversionTest(){
        MoneyConversionPage page = new MoneyConversionPage(admin, currencyData);
        JPanel panel = page.currentPanel;

        ((JComboBox)panel.getComponent(8)).setSelectedIndex(1);

        ((JComboBox)panel.getComponent(10)).setSelectedIndex(2);

        ((JTextField)panel.getComponent(12)).setText("22.0");

        page.validUserInput();

        assertEquals(((JLabel) panel.getComponent(15)).getText(), "11.00POU", "The correct value for the Converted would be 11.00POU");
        assertFalse(panel.getComponent(16).isVisible(), "Warning Lable should be invisible because its a correct value");
    }

}
