package CurrencyConverter;

import java.util.Currency;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInPage{
    private UserData userData;
    
    protected JFrame LogInFrame;
    protected JPanel LogInPanel;

    protected JTextField userName;
    protected JPasswordField password;

    protected JLabel warning;
    private CurrencyData currencyData;

    public LogInPage(UserData userData, CurrencyData currencyData){
        this.currencyData = currencyData;
        this.userData = userData;

        LogInFrame = new JFrame("Currency Conversion App");
        LogInPanel = new JPanel();
        
        LogInPanel.setLayout(null);
        LogInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LogInFrame.add(LogInPanel);
        LogInFrame.setSize(400,300);

        createforms();
        LogInFrame.setVisible(true);
    }

    protected void verifyUser(){
        String usernameVal = userName.getText();
        String passwordVal = password.getText();

        User userProfile = userData.tryLogIn(usernameVal, passwordVal);
        if(Objects.isNull(userProfile)){
              warning.setVisible(true);
        }
        else{
            LogInFrame.setVisible(false);
            new PageManager(userProfile, currencyData);
        }
    }

    private void createforms(){
        warning = new JLabel("Warning: Log in credentials were incorrect");
        warning.setBounds(55,40,300,25);
        warning.setVisible(false);
        LogInPanel.add(warning);

        JLabel userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(25,75,100,25);
        LogInPanel.add(userNameLabel);

        userName = new JTextField();
        userName.setBounds(125,75,100,25);
        LogInPanel.add(userName);

        JLabel passwordLabel = new JLabel("PassWord");
        passwordLabel.setBounds(25,125,100,25);
        LogInPanel.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(125,125,100,25);
        LogInPanel.add(password);

        JButton LogIn = new JButton("Log In");
        LogIn.setBounds(250,96,75,30);
        LogIn.addActionListener(e -> verifyUser());
        LogInPanel.add(LogIn);
    }

}