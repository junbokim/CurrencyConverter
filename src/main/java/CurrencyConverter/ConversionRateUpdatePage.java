package CurrencyConverter;

import java.util.Date;

import javax.swing.*;

public class ConversionRateUpdatePage extends PageAbstract implements updateJComboInterface{

    //Swings Attributes that is used to extract User Input data
    /***
     * fromNationality: Drop down menu that holds information about the nationality name for the currency that conversion is comming from
     * toNationality: Drop down menu that holds information about the nationality name for the currency that conversion is converting to
     * currencyRate: New Currency Conversion Rate to be updated to.
     */
    protected JComboBox fromNationality;
    protected JComboBox toNationality;
    protected JTextField currencyRate;

    protected JLabel warningLabel;
    protected TableRefresh currencyRateRefresh;


    public ConversionRateUpdatePage(User currentUser, CurrencyData currencyData, TableRefresh currencyRateRefresh){
        super(currentUser,currencyData);

        this.currencyRateRefresh = currencyRateRefresh;

        setUpdateUI();
    }

    private void setUpdateUI(){

        JLabel titleLabel = new JLabel("<html><h1 style = \"text-align: center;\">Conversion Rate Update</h1></html>");
        titleLabel.setBounds(50,15,350,75);
        this.currentPanel.add(titleLabel);

        JLabel fromCurrencyLabel = new JLabel("Currency to convert from");
        fromCurrencyLabel.setBounds(150,200,200,25);  
        this.currentPanel.add(fromCurrencyLabel);

        //Drop down menu that holds information about currency Nationality.
        fromNationality = new JComboBox(currencyData.getNames().toArray());
        fromNationality.setBounds(150,250,100,50);
        this.currentPanel.add(fromNationality);

        JLabel Arrow = new JLabel("=======>");
        Arrow.setBounds(350, 250, 100, 50);
        this.currentPanel.add(Arrow);

        JLabel toCurrencyLabel = new JLabel("Currency to convert to");
        toCurrencyLabel.setBounds(500,200,200,25);
        this.currentPanel.add(toCurrencyLabel);

        //Drop down menu that holds information about currency Nationality.
        toNationality = new JComboBox( currencyData.getNames().toArray());
        toNationality.setBounds(500, 250, 100, 50);
        this.currentPanel.add(toNationality);

        JLabel currencyRateLabel = new JLabel("Please Enter The Updated Rate");
        currencyRateLabel.setBounds(300,400,200,25);
        this.currentPanel.add(currencyRateLabel);

        //TextField that admin enters the new rate to update to.
        currencyRate = new JTextField();
        currencyRate.setBounds(325,450,100,25);
        this.currentPanel.add(currencyRate);
        
        warningLabel = new JLabel("Warning: ");
        warningLabel.setBounds(325,500,400,50);
        warningLabel.setVisible(false);
        this.currentPanel.add(warningLabel);

        // Button to record changes.
        JButton Record = new JButton("Update");
        Record.addActionListener(e -> updateRate());
        Record.setBounds(325,600,100,50);
        this.currentPanel.add(Record);
    }

    protected void updateRate(){
        int fromIndex = fromNationality.getSelectedIndex();
        int toIndex = toNationality.getSelectedIndex();

        //If the two country currency is the same, then there is no changes.
        if(fromIndex == toIndex){
            warningLabel.setText("Warning: Same Currency has been selected");
            warningLabel.setVisible(true);
            return;
        }

        //Try to change the input value as double value, if it is not, then print that the value input was wrong.
        try{
            float value = Float.valueOf(currencyRate.getText());
            currencyData.updateCurrency(new Date(), (String) fromNationality.getSelectedItem() , (String) toNationality.getSelectedItem() , value);
            if(this.currencyData.getDisplayNames().indexOf((String)fromNationality.getSelectedItem()) >=0 &&this.currencyData.getDisplayNames().indexOf((String)toNationality.getSelectedItem()) >=0){
                currencyRateRefresh.refresh(fromNationality.getSelectedIndex(), toNationality.getSelectedIndex());
            }
            warningLabel.setText("Update Complete!");
            warningLabel.setVisible(true);
        }
        catch(NumberFormatException e){
            warningLabel.setText("Warning: Value input was not Numbers");
            warningLabel.setVisible(true);
            return;
        }
    }

    @Override
    public void currencyDataUpdate(CurrencyData newCurrencyData) {
        this.currencyData = newCurrencyData;
        this.currentPanel.remove(fromNationality);

        fromNationality = new JComboBox(currencyData.getNames().toArray());
        fromNationality.setBounds(150,250,100,50);
        this.currentPanel.add(fromNationality);


        this.currentPanel.remove(toNationality);
        toNationality = new JComboBox(currencyData.getNames().toArray());
        toNationality.setBounds(500, 250, 100, 50);
        this.currentPanel.add(toNationality);
    }
}