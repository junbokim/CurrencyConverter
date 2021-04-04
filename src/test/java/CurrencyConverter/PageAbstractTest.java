package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import javax.swing.*;

import org.junit.jupiter.api.*;

public class PageAbstractTest {

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

    @Test void navBarPositioningAdmin(){
        MoneyConversionPage page = new MoneyConversionPage(admin, currencyData);
        JPanel panel = page.currentPanel;

        //Testing the First element: profileDescription JLabel
        assertEquals(((JLabel)panel.getComponent(0)).getText(), "<html> Current User: admin <br> Admin auth: true</html>", "Profile descriptor that shows characteristics of User.");
        assertEquals(panel.getComponent(0).getBounds().x, 625, "profileDescription JLabel should have x position value of 625");
        assertEquals(panel.getComponent(0).getBounds().y, 50,"profileDescription JLabel should have x position value of 625");
        assertEquals(panel.getComponent(0).getBounds().width, 200, "profileDescription JLabel should have x position value of 625");
        assertEquals(panel.getComponent(0).getBounds().height, 50, "profileDescription JLabel should have x position value of 625");

        //Testing the Second element: convertPage JButton
        assertEquals(((JButton)panel.getComponent(1)).getText(), "Money Conversion", "Second component of panel should be LogIn JButton");
        assertEquals(panel.getComponent(1).getBounds().x, 0, "convertPage JButton should have x position value of 0");
        assertEquals(panel.getComponent(1).getBounds().y, 0,"convertPage JButton should have x position value of 0");
        assertEquals(panel.getComponent(1).getBounds().width, 150, "convertPage JButton should have x position value of 150");
        assertEquals(panel.getComponent(1).getBounds().height, 25, "convertPage JButton should have x position value of 25");

        //Testing the Third element: conversionRatePage JButton
        assertEquals(((JButton)panel.getComponent(2)).getText(), "Conversion Rate", "Second component of panel should be LogIn JButton");
        assertEquals(panel.getComponent(2).getBounds().x, 150, "conversionRatePage JButton should have x position value of 0");
        assertEquals(panel.getComponent(2).getBounds().y, 0,"conversionRatePage JButton should have x position value of 0");
        assertEquals(panel.getComponent(2).getBounds().width, 150, "conversionRatePage JButton should have x position value of 150");
        assertEquals(panel.getComponent(2).getBounds().height, 25, "conversionRatePage JButton should have x position value of 25");

        //Testing the Fourth element: conversionRateSummaryPage JButton
        assertEquals(((JButton)panel.getComponent(3)).getText(), "Rate Summary", "Second component of panel should be LogIn JButton");
        assertEquals(panel.getComponent(3).getBounds().x, 300, "conversionRateSummaryPage JButton should have x position value of 0");
        assertEquals(panel.getComponent(3).getBounds().y, 0,"conversionRateSummaryPage JButton should have x position value of 0");
        assertEquals(panel.getComponent(3).getBounds().width, 150, "conversionRateSummaryPage JButton should have x position value of 150");
        assertEquals(panel.getComponent(3).getBounds().height, 25, "conversionRateSummaryPage JButton should have x position value of 25");

        //Testing the Fifth element: rateUpdatePage JButton
        assertEquals(((JButton)panel.getComponent(4)).getText(), "Update Conversion Rate", "Second component of panel should be LogIn JButton");
        assertEquals(panel.getComponent(4).getBounds().x, 450, "rateUpdatePage JButton should have x position value of 0");
        assertEquals(panel.getComponent(4).getBounds().y, 0,"rateUpdatePage JButton should have x position value of 0");
        assertEquals(panel.getComponent(4).getBounds().width, 175, "rateUpdatePage JButton should have x position value of 150");
        assertEquals(panel.getComponent(4).getBounds().height, 25, "rateUpdatePage JButton should have x position value of 25");

        //Testing the Sixth element: newCurrencyPage JButton
        assertEquals(((JButton)panel.getComponent(5)).getText(), "New Currency", "Second component of panel should be LogIn JButton");
        assertEquals(panel.getComponent(5).getBounds().x, 625, "newCurrencyPage JButton should have x position value of 0");
        assertEquals(panel.getComponent(5).getBounds().y, 0,"newCurrencyPage JButton should have x position value of 0");
        assertEquals(panel.getComponent(5).getBounds().width, 125, "newCurrencyPage JButton should have x position value of 150");
        assertEquals(panel.getComponent(5).getBounds().height, 25, "newCurrencyPage JButton should have x position value of 25");
    }

    @Test void navBarPositioningGuest(){
        MoneyConversionPage page = new MoneyConversionPage(guest, currencyData);
        JPanel panel = page.currentPanel;

        //Testing the First element: profileDescription JLabel
        assertEquals(((JLabel)panel.getComponent(0)).getText(), "<html> Current User: guest <br> Admin auth: false</html>", "Profile descriptor that shows characteristics of User.");
        assertEquals(panel.getComponent(0).getBounds().x, 625, "profileDescription JLabel should have x position value of 625");
        assertEquals(panel.getComponent(0).getBounds().y, 50,"profileDescription JLabel should have x position value of 625");
        assertEquals(panel.getComponent(0).getBounds().width, 200, "profileDescription JLabel should have x position value of 625");
        assertEquals(panel.getComponent(0).getBounds().height, 50, "profileDescription JLabel should have x position value of 625");

        //Testing the Second element: convertPage JButton
        assertEquals(((JButton)panel.getComponent(1)).getText(), "Money Conversion", "Second component of panel should be LogIn JButton");
        assertEquals(panel.getComponent(1).getBounds().x, 0, "convertPage JButton should have x position value of 0");
        assertEquals(panel.getComponent(1).getBounds().y, 0,"convertPage JButton should have x position value of 0");
        assertEquals(panel.getComponent(1).getBounds().width, 150, "convertPage JButton should have x position value of 150");
        assertEquals(panel.getComponent(1).getBounds().height, 25, "convertPage JButton should have x position value of 25");

        //Testing the Third element: conversionRatePage JButton
        assertEquals(((JButton)panel.getComponent(2)).getText(), "Conversion Rate", "Second component of panel should be LogIn JButton");
        assertEquals(panel.getComponent(2).getBounds().x, 150, "conversionRatePage JButton should have x position value of 0");
        assertEquals(panel.getComponent(2).getBounds().y, 0,"conversionRatePage JButton should have x position value of 0");
        assertEquals(panel.getComponent(2).getBounds().width, 150, "conversionRatePage JButton should have x position value of 150");
        assertEquals(panel.getComponent(2).getBounds().height, 25, "conversionRatePage JButton should have x position value of 25");

        //Testing the Fourth element: conversionRateSummaryPage JButton
        assertEquals(((JButton)panel.getComponent(3)).getText(), "Rate Summary", "Second component of panel should be LogIn JButton");
        assertEquals(panel.getComponent(3).getBounds().x, 300, "conversionRateSummaryPage JButton should have x position value of 0");
        assertEquals(panel.getComponent(3).getBounds().y, 0,"conversionRateSummaryPage JButton should have x position value of 0");
        assertEquals(panel.getComponent(3).getBounds().width, 150, "conversionRateSummaryPage JButton should have x position value of 150");
        assertEquals(panel.getComponent(3).getBounds().height, 25, "conversionRateSummaryPage JButton should have x position value of 25");

        //Testing the Fifth element: newCurrencyPage JButton
        assertEquals(((JLabel)panel.getComponent(4)).getText(), "<html><h1 style = \"text-align: center;\">Currency Conversion</h1></html>",
                "moneyConversionPage should be initiated correctly with correct title");
    }

    @Test void setVisibilityTest(){
        MoneyConversionPage page = new MoneyConversionPage(guest, currencyData);
        assertFalse(page.currentPage.isVisible(), "Initially current page should be set to invisible");
        page.setVisible();
        assertTrue(page.currentPage.isVisible(), "After calling setvisible(), the current page should be visible");
        page.setInvisible();
        assertFalse(page.currentPage.isVisible(), "After calling setInvisible, the current page should be invisible");
    }

    @Test void navigateTestToMoneyConversion(){
        PageManager pageManager = new PageManager(admin, currencyData);
        pageManager.moneyConversionPage.navigate(PagesEnum.MONEYCONVERSION);
        assertTrue(pageManager.moneyConversionPage.currentPage.isVisible(), "Because the PagesEnum refered to itself, it should still be visible");
    }

    @Test void navigateTestToCurrencyRate(){
        PageManager pageManager = new PageManager(admin, currencyData);
        pageManager.moneyConversionPage.navigate(PagesEnum.CURRENCYRATE);
        assertFalse(pageManager.moneyConversionPage.currentPage.isVisible(), "Because PageEnum refers to Currency Rate, current page should be invisible");
        assertTrue(pageManager.currencyRatePage.currentPage.isVisible(), "Currency Rate Page should be visible not MoneyConversionPage");
    }

    @Test void navigateTestToCurrencySummary(){
        PageManager pageManager = new PageManager(admin, currencyData);
        pageManager.moneyConversionPage.navigate(PagesEnum.CURRENCYSUMMARY);
        assertFalse(pageManager.moneyConversionPage.currentPage.isVisible(), "Because PageEnum refers to Currency Rate, current page should be invisible");
        assertTrue(pageManager.conversionRateSummaryPage.currentPage.isVisible(), "Conversion Rate Summary Page should be visible not MoneyConversionPage");
    }

    @Test void navigateTestToConversionRateUpdate(){
        PageManager pageManager = new PageManager(admin, currencyData);
        pageManager.moneyConversionPage.navigate(PagesEnum.CONVERSIONRATEUPDATE);
        assertFalse(pageManager.moneyConversionPage.currentPage.isVisible(), "Because PageEnum refers to Currency Rate, current page should be invisible");
        assertTrue(pageManager.conversionRateUpdatePage.currentPage.isVisible(), "Conversion Rate Updat Page should be visible not MoneyConversionPage");
    }

    @Test void navigateTestToNewCurrency(){
        PageManager pageManager = new PageManager(admin, currencyData);
        pageManager.moneyConversionPage.navigate(PagesEnum.NEWCURRENCY);
        assertFalse(pageManager.moneyConversionPage.currentPage.isVisible(), "Because PageEnum refers to Currency Rate, current page should be invisible");
        assertTrue(pageManager.newCurrencyPage.currentPage.isVisible(), "New Currency Page should be visible not MoneyConversionPage");
    }
    
}
