package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import javax.swing.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LogInTest {

    private static UserData userData;
    private static LogInPage page;
    private static CurrencyData currencyData;

    @BeforeAll static void init(){
        userData = new UserData();
        currencyData = new CurrencyData();

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


        page = new LogInPage(userData,currencyData);
        userData.addUser("guest", "guest", true);
        userData.addUser("guest", "guest", false);
    }

    @Test void basicPageSetupTest(){

        //Testing LogInFrame attributes.
        assertEquals(page.LogInFrame.getBounds().height, 300, "Height of LogInFrame should be 300");
        assertEquals(page.LogInFrame.getBounds().width, 400, "Width of LogInFrame should be 400");
        assertEquals(page.LogInFrame.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE, "LogInFrame's default closing operation should be JFrame.EXIT_ON_CLOSE");
        assertEquals(page.LogInFrame.getContentPane().getComponent(0), page.LogInPanel, "LogInFrame's first component should be the LogInPanel");
        assertEquals(page.LogInFrame.isVisible(), true, "LogInFrame should be visible");
        //Testing LogInPanel attributes.
        assertEquals(page.LogInPanel.getLayout(), null, "LogInPanel's Layout should be null");
    }

    @Test void LogInFormCreationTest(){

        //First Element testing: Warning JLabel
        assertEquals(((JLabel)page.LogInPanel.getComponent(0)).getText(), "Warning: Log in credentials were incorrect","First component of LogInPanel should be warning JLabel");
        assertEquals(((JLabel)page.LogInPanel.getComponent(0)).getBounds().height, 25, "Warning JLabel should have height of 25");
        assertEquals(((JLabel)page.LogInPanel.getComponent(0)).getBounds().width, 300, "Warning JLabel should have width of 300");
        assertEquals(((JLabel)page.LogInPanel.getComponent(0)).getBounds().x, 55, "Warning JLabel should have x position value of 300");
        assertEquals(((JLabel)page.LogInPanel.getComponent(0)).getBounds().y, 40, "Warning JLabel should have y position value of 300");
        assertEquals(((JLabel)page.LogInPanel.getComponent(0)).isVisible(), false, "Warning JLabel should begin with visibility of false.");

        //Second Element testing: userNameLabel JLabel
        assertEquals(((JLabel)page.LogInPanel.getComponent(1)).getText(), "User Name","Second component of LogInPanel should be userNameLabel JLabel");
        assertEquals(((JLabel)page.LogInPanel.getComponent(1)).getBounds().height, 25, "userNameLabel JLabel should have height of 25");
        assertEquals(((JLabel)page.LogInPanel.getComponent(1)).getBounds().width, 100, "userNameLabel JLabel should have width of 100");
        assertEquals(((JLabel)page.LogInPanel.getComponent(1)).getBounds().x, 25, "userNameLabel JLabel should have x position value of 25");
        assertEquals(((JLabel)page.LogInPanel.getComponent(1)).getBounds().y, 75, "userNameLabel JLabel should have y position value of 75");

        //Third Element testing: userName JTextField
        assertEquals(((JTextField)page.LogInPanel.getComponent(2)).getBounds().height, 25, "userName JTextField should have height of 25");
        assertEquals(((JTextField)page.LogInPanel.getComponent(2)).getBounds().width, 100, "userName JTextField should have width of 100");
        assertEquals(((JTextField)page.LogInPanel.getComponent(2)).getBounds().x, 125, "userName JTextField should have x position value of 125");
        assertEquals(((JTextField)page.LogInPanel.getComponent(2)).getBounds().y, 75, "userName JTextField should have y position value of 75");

        //Fourth Element testing: passwordLabel JLabel
        assertEquals(((JLabel)page.LogInPanel.getComponent(3)).getText(), "PassWord","Fourth component of LogInPanel should be passwordLabel JLabel");
        assertEquals(((JLabel)page.LogInPanel.getComponent(3)).getBounds().height, 25, "passwordLabel JLabel should have height of 25");
        assertEquals(((JLabel)page.LogInPanel.getComponent(3)).getBounds().width, 100, "passwordLabel JLabel should have width of 100");
        assertEquals(((JLabel)page.LogInPanel.getComponent(3)).getBounds().x, 25, "passwordLabel JLabel should have x position value of 25");
        assertEquals(((JLabel)page.LogInPanel.getComponent(3)).getBounds().y, 125, "passwordLabel JLabel should have y position value of 125");

        //Fifth Element testing: password JTextField
        assertEquals(((JTextField)page.LogInPanel.getComponent(4)).getBounds().height, 25, "password JTextField should have height of 25");
        assertEquals(((JTextField)page.LogInPanel.getComponent(4)).getBounds().width, 100, "password JTextField should have height of 100");
        assertEquals(((JTextField)page.LogInPanel.getComponent(4)).getBounds().x, 125, "password JTextField should have height of 125");
        assertEquals(((JTextField)page.LogInPanel.getComponent(4)).getBounds().y, 125, "password JTextField should have height of 125");

        //Sixth Element testing: LogIn JLabel
        assertEquals(((JButton)page.LogInPanel.getComponent(5)).getText(), "Log In","Sixth component of LogInPanel should be LogIn JButton");
        assertEquals(((JButton)page.LogInPanel.getComponent(5)).getBounds().height, 30, "LogIn JLabel should have height of 30");
        assertEquals(((JButton)page.LogInPanel.getComponent(5)).getBounds().width, 75, "LogIn JLabel should have height of 75");
        assertEquals(((JButton)page.LogInPanel.getComponent(5)).getBounds().x, 250, "LogIn JLabel should have height of 250");
        assertEquals(((JButton)page.LogInPanel.getComponent(5)).getBounds().y, 96, "LogIn JLabel should have height of 96");
    }

    @Test void LogInValidationTestAdmin(){
        //Creation of UserData object
        UserData userdata = new UserData();

        //Addition of admin profile
        userdata.addUser("admin", "admin", true);

        //Setting up the Log in page
        LogInPage page = new LogInPage(userdata,currencyData);
        assertEquals(page.LogInFrame.isVisible(), true, "Initially, Log In Frame should be visible");

        //Adding Values to the userName and password JTextField
        page.userName.setText("admin");
        page.password.setText("admin");

        //calling verifyUser() so that LogInPage would verify the user credentials.
        page.verifyUser();

        //After Validation was successful, LogInFrame should be invisible
        assertEquals(page.LogInFrame.isVisible(), false, "User credentials were validated so LogInFrame should be invisible");
    }

    @Test void LogInValidationTestGuest(){
        //Creation of UserData object
        UserData userdata = new UserData();

        //Addition of guest profile
        userdata.addUser("guest", "guest", false);

        //Setting up the Log in page
        LogInPage page = new LogInPage(userdata,currencyData);
        assertEquals(page.LogInFrame.isVisible(), true, "Initially, Log In Frame should be visible");

        //Adding Values to the userName and password JTextField
        page.userName.setText("guest");
        page.password.setText("guest");

        //calling verifyUser() so that LogInPage would verify the user credentials.
        page.verifyUser();
        assertEquals(page.LogInFrame.isVisible(), false, "User credentials were validated so LogInFrame should be invisible");
    }

    @Test void LogInValidationWrong(){
        //Creation of UserData object
        UserData userdata = new UserData();

        //Addition of admin and guest profile
        userdata.addUser("guest", "guest", true);
        userdata.addUser("guest", "guest", false);

        //Setting up the Log in page
        LogInPage page = new LogInPage(userdata,currencyData);
        assertEquals(page.LogInFrame.isVisible(), true, "Initially, Log In Frame should be visible");

        //Adding Values to the userName and password JTextField
        page.userName.setText("admin");
        page.password.setText("guest");

        //calling verifyUser() so that LogInPage would verify the user credentials.
        page.verifyUser();

        //If User Credentials were incorrect
        assertEquals(page.LogInFrame.isVisible(), true,"LogInFrame should still be Visible");
        assertEquals(page.LogInPanel.getComponent(0).isVisible(), true, "Warning JLabel should be visible");
    }

    @Test void LogInValidationTestCaseSensitive(){
        //Creation of UserData object
        UserData userdata = new UserData();

        //Addition of admin and guest profile
        userdata.addUser("guest", "guest", true);
        userdata.addUser("guest", "guest", false);

        //Setting up the Log in page
        LogInPage page = new LogInPage(userdata,currencyData);
        assertEquals(page.LogInFrame.isVisible(), true, "Initially, Log In Frame should be visible");

        //Adding Values to the userName and password JTextField
        page.userName.setText("ADMIN");
        page.password.setText("GUEST");

        //calling verifyUser() so that LogInPage would verify the user credentials.
        page.verifyUser();

        //If User Credentials were incorrect
        assertEquals(page.LogInFrame.isVisible(), true,"LogInFrame should still be Visible");
        assertEquals(page.LogInPanel.getComponent(0).isVisible(), true, "Warning JLabel should be visible");
    }


}