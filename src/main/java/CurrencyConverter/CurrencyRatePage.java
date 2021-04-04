package CurrencyConverter;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

public class CurrencyRatePage extends PageAbstract implements TableRefresh, updateJComboInterface{

    protected ArrayList<ArrayList<JLabel>> labels;
    protected JComboBox replacedCurrency;
    protected JComboBox replacementCurrency;
    protected JLabel warning;

    protected int y_pos;

    public CurrencyRatePage(User currentUser, CurrencyData currencyData){
        super(currentUser,currencyData);

        labels = new ArrayList<ArrayList<JLabel>>();

        JLabel titleLabel = new JLabel("<html><h1 style = \"text-align: center;\">Currency Rate</h1></html>");
        titleLabel.setBounds(50,15,350,75);
        this.currentPanel.add(titleLabel);

        setTable();

        if(currentUser.isAdmin()){
            updatePopular();
        }
    }

    @Override
    public void refresh(int row, int col){
        currencyData.setDisplayNames(currencyData.getDisplayNames());
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                this.currentPanel.remove(labels.get(i).get(j));
            }
        }
        labels.clear();

        setTable();
    }

    private void setTable(){
        ArrayList<ArrayList<Integer>> currencyRateDirection = currencyData.getTableDirections();

        //Initial Position for the first Tile to start from.
        int x_pos = 100;
        y_pos = 75;

        //Square tile dimension
        int tile_width = 100;
        
        //temporary Label to store the new created label
        JLabel tempLabel;

        //content that the tile should hold.
        String Content = "";

        //Column counter
        for (int j = 0; j <= 4; j++) {
            labels.add(new ArrayList<JLabel>());

            //Row counter
            for (int i = 0; i <= 4; i++) {

                //If it is the Very first element, it should hold the From-To.
                if (i == 0 && j == 0) {
                    Content = "From \\ To";
                } 
                // If first Row or Column, then it should have the nationality name
                else if (j == 0) {
                    Content = this.currencyData.getDisplayNames().get(i - 1);
                } 
                else if (i == 0) {
                    Content = this.currencyData.getDisplayNames().get(j - 1);
                } 
                //If Row and Columns are the same, it means conversion is to same currency, therefore NULL
                else if (i == j) {
                    Content = "-";
                } 

                //Else, it should hold the correct Currency Values.
                else {
                    String sign = "";
                    if(currencyRateDirection.get(j-1).get(i-1) >0){
                        sign = "/\\";
                    }
                    else if(currencyRateDirection.get(j-1).get(i-1) < 0){
                        sign = "\\/";
                    }
                    else{
                        sign = "";
                    }
                    Content = String.format("%.2f %s", currencyData.getTableValues().get(j-1).get(i-1), sign);
                }

                tempLabel = new JLabel(Content);
                x_pos = tile_width * i;

                //Setting the Aesthetic Attributes for the label
                tempLabel.setBounds(x_pos + 75, y_pos, 100, 100);
                tempLabel.setHorizontalAlignment(0);
                tempLabel.setOpaque(true);
                tempLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
                tempLabel.setBackground(Color.lightGray);

                //Adding them into the corresponding position in the list
                labels.get(j).add(tempLabel);
                currentPanel.add(tempLabel);
            }
            y_pos += tile_width;
        }
    
    }

    protected void updatePopular(){
        warning = new JLabel("");
        warning.setBounds(50,y_pos + 50,600,25);  
        warning.setVisible(false);
        this.currentPanel.add(warning);

        JLabel replacedCurrencyLabel = new JLabel("Currency to replace");
        replacedCurrencyLabel.setBounds(50,y_pos,200,25);  
        this.currentPanel.add(replacedCurrencyLabel);

        //Drop down menu that holds information about currency Nationality.
        replacedCurrency = new JComboBox(currencyData.getDisplayNames().toArray());
        replacedCurrency.setBounds(50,y_pos+25,100,25);
        this.currentPanel.add(replacedCurrency);

        JLabel Arrow = new JLabel("=======>");
        Arrow.setBounds(200, y_pos+25, 100, 25);
        this.currentPanel.add(Arrow);

        JLabel replacementCurrencyLabel = new JLabel("Currency to replaceTo");
        replacementCurrencyLabel.setBounds(300,y_pos,200,25);
        this.currentPanel.add(replacementCurrencyLabel);

        //Drop down menu that holds information about currency Nationality.
        replacementCurrency = new JComboBox( this.currencyData.getNames().toArray());
        replacementCurrency.setBounds(300, y_pos+25, 100, 25);
        this.currentPanel.add(replacementCurrency);

        JButton replaceButton = new JButton("Replace");
        replaceButton.addActionListener(e -> replacePopular());
        replaceButton.setBounds(500,y_pos,100,50);
        this.currentPanel.add(replaceButton);
    }

    protected void replacePopular(){
        ArrayList<String> displayNames = currencyData.getDisplayNames();
        int indexToReplace =  displayNames.indexOf((String)replacedCurrency.getSelectedItem());

        if(replacedCurrency.getSelectedIndex() == replacementCurrency.getSelectedIndex()){
            warning.setText("Warning: Same Currency Selected");
            warning.setVisible(true);
            return;
        }
        else if(this.currencyData.getDisplayNames().indexOf((String)replacementCurrency.getSelectedItem()) >=0){
            warning.setText("Warning: Both currencies selected already exist in the table");
            warning.setVisible(true);
            return;
        }

        displayNames.set(indexToReplace, (String)replacementCurrency.getSelectedItem());
        currencyData.setDisplayNames(displayNames);
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                this.currentPanel.remove(labels.get(i).get(j));

            }
        }
        labels.clear();

        setTable();

        this.currentPanel.updateUI();
        warning.setText("Update Success!");
        warning.setVisible(true);

        this.replacedCurrency.setModel(new DefaultComboBoxModel<>(this.currencyData.getDisplayNames().toArray()));
    }

    @Override
    public void currencyDataUpdate(CurrencyData newCurrencyData) {
        this.currencyData = newCurrencyData;

        this.currentPanel.remove(replacedCurrency);
        replacedCurrency = new JComboBox(currencyData.getNames().toArray());
        replacedCurrency.setBounds(50, y_pos+25, 100, 25);
        this.currentPanel.add(replacedCurrency);


        this.currentPanel.remove(replacementCurrency);
        replacementCurrency = new JComboBox(currencyData.getNames().toArray());
        replacementCurrency.setBounds(300, y_pos+25, 100, 25);
        this.currentPanel.add(replacementCurrency);
    }


}