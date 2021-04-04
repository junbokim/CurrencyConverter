package CurrencyConverter;

import javax.swing.*;


public class MoneyConversionPage extends PageAbstract implements updateJComboInterface{

    protected JComboBox fromDrop;
    protected JComboBox toDrop;
    protected JTextField fromAmount;
    protected JLabel warningLabel;
    protected JLabel resultAmount;
    protected JLabel resultLabel;

    

    public MoneyConversionPage( User currentUser, CurrencyData currencyData){
        super(currentUser, currencyData);
        setPage();
    }
    
    protected void setPage(){
        JLabel titleLabel = new JLabel("<html><h1 style = \"text-align: center;\">Currency Conversion</h1></html>");
        titleLabel.setBounds(50,15,350,75);
        this.currentPanel.add(titleLabel);

        // Label to show that the TextField is for the Currency Selection for the currency that User is converting FROM
        JLabel fromLabel = new JLabel("From Currency");
        fromLabel.setBounds(200,200,150,25);
        this.currentPanel.add(fromLabel);

        // Drop down menu with potential Currency selection for the currency that User is converting FROM
        fromDrop = new JComboBox(currencyData.getNames().toArray());
        fromDrop.setBounds(200,250,150,25);
        this.currentPanel.add(fromDrop);

        // Label to show that the TextField is for the Currency Selection for the currency that User is converting TO
        JLabel toLabel = new JLabel("To Currency");
        toLabel.setBounds(400,200,150,25);
        this.currentPanel.add(toLabel);

        // Drop down menu with potential Currency selection for the currency that User is converting TO
        toDrop = new JComboBox(currencyData.getNames().toArray());
        toDrop.setBounds(400,250,150,25);
        this.currentPanel.add(toDrop);

        // Label to instruct User to enter their amount that they whish to Convert from.
        JLabel fromAmountLabel = new JLabel("Enter Amount");
        fromAmountLabel.setBounds(200,300,200,25);
        this.currentPanel.add(fromAmountLabel);

        // TextField that User enters their Converting Amount to.
        fromAmount = new JTextField();
        fromAmount.setBounds(200,350,200,25);
        this.currentPanel.add(fromAmount);

        // Button to convert        
        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(200,400,150,50);
        convertButton.addActionListener(e -> validUserInput());
        this.currentPanel.add(convertButton);

        // The Label that instructs the User that the label below is the converted amount
        resultLabel = new JLabel("Converted Amount");
        resultLabel.setVisible(false);
        resultLabel.setBounds(450,300,200,25);
        this.currentPanel.add(resultLabel);

        // The label that has the converted amonut.
        resultAmount = new JLabel("Amount of $$$");
        resultAmount.setBounds(450,350,200,25);
        resultAmount.setVisible(false);
        this.currentPanel.add(resultAmount);

        warningLabel = new JLabel("Warning: ");
        warningLabel.setBounds(450,400,300,50);
        warningLabel.setVisible(false);
        this.currentPanel.add(warningLabel);
    }
    
    protected void validUserInput(){
        if(fromDrop.getSelectedIndex() == toDrop.getSelectedIndex()){
            warningLabel.setText("Warning: Same Currency Selected");
            warningLabel.setVisible(true);
        }
        else{
            try{
                float value = Float.parseFloat(fromAmount.getText());
                warningLabel.setVisible(false);
                Conversion conversion = new Conversion();
                String convertedAmount = conversion.convert(currencyData, value, (String)fromDrop.getSelectedItem(), (String)toDrop.getSelectedItem());
                resultLabel.setText(String.format("Converted Amount to %s", (String)toDrop.getSelectedItem()));
                resultLabel.setVisible(true);
                this.resultAmount.setVisible(true);
                this.resultAmount.setText(convertedAmount);
            }
            catch(NumberFormatException e){
                warningLabel.setText("Warning: Value input was not Numbers");
                warningLabel.setVisible(true);
            }
        }
    }


    @Override
    public void currencyDataUpdate(CurrencyData newCurrencyData) {
        this.currencyData = newCurrencyData;
        this.currentPanel.remove(fromDrop);

        fromDrop = new JComboBox(currencyData.getNames().toArray());
        fromDrop.setBounds(200,250,150,25);
        this.currentPanel.add(fromDrop);


        this.currentPanel.remove(toDrop);
        toDrop = new JComboBox(currencyData.getNames().toArray());
        toDrop.setBounds(400,250,150,25);
        this.currentPanel.add(toDrop);
    }

} 