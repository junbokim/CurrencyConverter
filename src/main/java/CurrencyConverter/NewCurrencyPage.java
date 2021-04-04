package CurrencyConverter;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import org.checkerframework.checker.units.qual.h;

public class NewCurrencyPage extends PageAbstract{

    protected ArrayList<JTextField> newCurrencyRate;
    protected ArrayList<JLabel> warningLabelList;

    protected ArrayList<updateJComboInterface> updatedropdown;

    protected JTextField newCurrencyName;

    protected int lastIntertedY;

    public NewCurrencyPage(User currentUser, CurrencyData currencyData,ArrayList<updateJComboInterface> updatedropdown){
        super(currentUser,currencyData);

        this.newCurrencyRate = new ArrayList<JTextField>();
        this.warningLabelList = new ArrayList<JLabel>();
        this.updatedropdown = updatedropdown;

        setPage();
    }

    private void setPage(){
        JLabel titleLabel = new JLabel("<html><h1 style = \"text-align: center;\">Add New Currency</h1></html>");
        titleLabel.setBounds(50,15,250,75);
        this.currentPanel.add(titleLabel);

        newCurrencyBasic();

        setTextFieldExistingCurrency();
    }

    private void newCurrencyBasic(){
        JButton submit = new JButton("Update");
        submit.setBounds(500,75,100,50);
        submit.addActionListener(e -> validUserInput());
        this.currentPanel.add(submit);

        JLabel currencyNameLabel = new JLabel("Please enter the name of new Currency");
        currencyNameLabel.setBounds(100,75,250,50);
        this.currentPanel.add(currencyNameLabel);

        newCurrencyName = new JTextField();
        newCurrencyName.setBounds(100,125,100,25);
        this.currentPanel.add(newCurrencyName);

        JLabel warningLabel = new JLabel("Warning: ");
        warningLabel.setBounds(250,125,600,25);
        warningLabel.setVisible(false);
        this.warningLabelList.add(warningLabel);
        this.currentPanel.add(warningLabel);

        JLabel currentCurrencyLabel = new JLabel("<html><h3>Conversion rate from <br>New Currency<br>to Existing Currency</h3></html>");
        currentCurrencyLabel.setBounds(75,150,250,100);
        this.currentPanel.add(currentCurrencyLabel);
    }

    private void setTextFieldExistingCurrency(){
        int x = 75;
        int y = 250;
        int width = 100;
        int height = 25;
        int spacer = 25;
        JLabel tempJLabel;
        JTextField tempJTextField;
        JLabel tempWarningLabel;
        for(int i = 0; i < this.currencyData.getNames().size(); i++){

            tempJLabel = new JLabel(this.currencyData.getNames().get(i));
            tempJLabel.setBounds(x, y + (height * i), width, height);
            this.currentPanel.add(tempJLabel);

            tempJTextField = new JTextField();
            tempJTextField.setBounds(x + width + spacer, y + (height*i), width, height);
            this.currentPanel.add(tempJTextField);

            tempWarningLabel = new JLabel("Warning: ");
            tempWarningLabel.setBounds(x + (width * 2) + (spacer * 2),y + (height*i), 400, height );
            tempWarningLabel.setVisible(false);
            this.currentPanel.add(tempWarningLabel);

            this.newCurrencyRate.add(tempJTextField);
            this.warningLabelList.add(tempWarningLabel);
            y += spacer;
        }
        lastIntertedY = y + height * this.currencyData.getNames().size() - 2;
    }

    //To do

    protected void validUserInput(){
        int numExceptions = 0;
        for(int i = 0; i < newCurrencyRate.size(); i++){
            try{
                Float.valueOf(newCurrencyRate.get(i).getText());
                this.warningLabelList.get(i+1).setVisible(false);
            }
            catch(NumberFormatException e){
                this.warningLabelList.get(i+1).setText("Warning: Value input was not Numbers");
                this.warningLabelList.get(i+1).setVisible(true);
                numExceptions++;
            }
        }
        if(numExceptions == 0){
            ArrayList<Float> conversionRate = new ArrayList<>();
            for(int i = 0; i< newCurrencyRate.size(); i++){
                conversionRate.add(Float.parseFloat(newCurrencyRate.get(i).getText()));
            }
            if(currencyData.getNames().indexOf(newCurrencyName.getText()) >= 0){
                ((JLabel)warningLabelList.get(0)).setText("Warning, The provided currency already exists");
                warningLabelList.get(0).setVisible(true);
                return;
            }
            else if(newCurrencyName.getText().length() == 0){
                ((JLabel)warningLabelList.get(0)).setText("Warning: no name provided");
                warningLabelList.get(0).setVisible(true);
                return;
            }
            currencyData.addCurrency(new Date(), newCurrencyName.getText(), conversionRate);
            addCurency();
            for(int i = 0; i < updatedropdown.size(); i++){
                updatedropdown.get(i).currencyDataUpdate(currencyData);
            }
            for(int i = 0; i < warningLabelList.size();i++){
                ((JLabel)warningLabelList.get(i)).setText("");
                warningLabelList.get(i).setVisible(false);
            }
            warningLabelList.get(0).setText("Update Success!");
            warningLabelList.get(0).setVisible(true);
        }
    }

    private void addCurency(){
        int width = 100;
        int height = 25;
        int spacer = 25;
        JLabel tempJLabel;
        JTextField tempJTextField;
        JLabel tempWarningLabel;
        tempJLabel = new JLabel(this.currencyData.getNames().get(this.currencyData.getNames().size()-1));
        tempJLabel.setBounds(75, lastIntertedY , width, height);
        this.currentPanel.add(tempJLabel);

        tempJTextField = new JTextField();
        tempJTextField.setBounds(75 + width + spacer, lastIntertedY, width, height);
        this.currentPanel.add(tempJTextField);

        tempWarningLabel = new JLabel("Warning: ");
        tempWarningLabel.setBounds(75 + (width * 2) + (spacer * 2),lastIntertedY, width, height );
        tempWarningLabel.setVisible(false);
        this.currentPanel.add(tempWarningLabel);

        this.newCurrencyRate.add(tempJTextField);
        this.warningLabelList.add(tempWarningLabel);

        this.currentPanel.updateUI();

        lastIntertedY += spacer + height;
    }
}