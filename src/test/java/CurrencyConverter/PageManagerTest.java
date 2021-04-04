package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import javax.swing.*;

import org.junit.jupiter.api.*;

class PageManagerTest{

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

        ArrayList<String> disNames = new ArrayList<>();
        disNames.add("AUD");
        disNames.add("FLO");
        disNames.add("POU");
        disNames.add("TAL");
        currencyData.setDisplayNames(disNames);
    }

    @Test void pageLoadingTestAdmin(){
        PageManager page = new PageManager(admin, currencyData);
        assertNotEquals(page.moneyConversionPage, null);
        assertEquals(((JLabel)page.moneyConversionPage.currentPanel.getComponent(6)).getText(), 
        "<html><h1 style = \"text-align: center;\">Currency Conversion</h1></html>",
                "currencyRatePage should be initiated correctly with correct title");

        assertNotEquals(page.currencyRatePage, null);
        assertEquals(((JLabel)page.currencyRatePage.currentPanel.getComponent(6)).getText(), "<html><h1 style = \"text-align: center;\">Currency Rate</h1></html>",
                "currencyRatePage should be initiated correctly with correct title");

        assertNotEquals(page.conversionRateSummaryPage, null);
        assertEquals(((JLabel)page.conversionRateSummaryPage.currentPanel.getComponent(6)).getText(), "<html><h1 style = \"text-align: center;\">Conversion Rate Summary</h1></html>",
                "conversionRateSummaryPage should be initiated correctly with correct title");

        assertNotEquals(page.conversionRateUpdatePage, null);
        assertEquals(((JLabel)page.conversionRateUpdatePage.currentPanel.getComponent(6)).getText(), "<html><h1 style = \"text-align: center;\">Conversion Rate Update</h1></html>",
                "conversionRateUpdatePage should be initiated correctly with correct title as the user is admin");

        assertNotEquals(page.newCurrencyPage, null);
        assertEquals(((JLabel)page.newCurrencyPage.currentPanel.getComponent(6)).getText(), "<html><h1 style = \"text-align: center;\">Add New Currency</h1></html>",
                "newCurrencyPage should be initiated correctly with correct title as the user is admin");

        assertEquals(page.pageList.size(), 5, "Total number of page constructed should be 5 because of admin user authorisation");

    }

    @Test void pageLoadingTestGuest(){
        PageManager page = new PageManager(guest, currencyData);
        assertNotEquals(page.moneyConversionPage, null);
        assertEquals(((JLabel)page.moneyConversionPage.currentPanel.getComponent(4)).getText(), "<html><h1 style = \"text-align: center;\">Currency Conversion</h1></html>",
                "moneyConversionPage should be initiated correctly with correct title");

        assertNotEquals(page.currencyRatePage, null);
        assertEquals(((JLabel)page.currencyRatePage.currentPanel.getComponent(4)).getText(), "<html><h1 style = \"text-align: center;\">Currency Rate</h1></html>"
                ,"moneyConversionPage should be initiated correctly with correct title");

        assertNotEquals(page.conversionRateSummaryPage, null);
        assertEquals(((JLabel)page.conversionRateSummaryPage.currentPanel.getComponent(4)).getText(), "<html><h1 style = \"text-align: center;\">Conversion Rate Summary</h1></html>",
                "moneyConversionPage should be initiated correctly with correct title");

        assertEquals(page.conversionRateUpdatePage, null,"As User is guest, conversionRateUpdatePage should not be initialised");
        assertEquals(page.newCurrencyPage, null,"As User is guest, newCurrencyPage should not be initialised");

        assertEquals(page.pageList.size(), 3,"Total number of page constructed should be 5 because user is not admin");
    }

}