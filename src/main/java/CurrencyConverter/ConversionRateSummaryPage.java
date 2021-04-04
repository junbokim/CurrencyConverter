package CurrencyConverter;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import javax.swing.*;

public class ConversionRateSummaryPage extends PageAbstract implements updateJComboInterface {

    protected JComboBox fromNationality;
    protected JComboBox toNationality;

    protected JTextField fromTime;
    protected JTextField toTime;
    protected JLabel warning;
    protected JScrollPane scrollPane;
    protected JLabel conversionRateLabel;
    protected JList pastExchangeRate;

    public ArrayList<JLabel> summaryLabels;

    public ConversionRateSummaryPage(User currentUser, CurrencyData currencyData) {
        super(currentUser, currencyData);
        summaryLabels = new ArrayList<JLabel>();

        setPage();
    }

    private void setPage() {
        JLabel titleLabel = new JLabel("<html><h1 style = \"text-align: center;\">Conversion Rate Summary</h1></html>");
        titleLabel.setBounds(50, 15, 350, 75);
        this.currentPanel.add(titleLabel);

        JLabel fromCurrencyLabel = new JLabel("Currency to convert from");
        fromCurrencyLabel.setBounds(50, 75, 200, 25);
        this.currentPanel.add(fromCurrencyLabel);

        // Drop down menu that holds information about currency Nationality.
        fromNationality = new JComboBox(currencyData.getNames().toArray());
        fromNationality.setBounds(50, 100, 100, 35);
        this.currentPanel.add(fromNationality);

        JLabel Arrow = new JLabel("=======>");
        Arrow.setBounds(220, 100, 100, 35);
        this.currentPanel.add(Arrow);

        JLabel toCurrencyLabel = new JLabel("Currency to convert to");
        toCurrencyLabel.setBounds(350, 75, 200, 25);
        this.currentPanel.add(toCurrencyLabel);

        // Drop down menu that holds information about currency Nationality.
        toNationality = new JComboBox(currencyData.getNames().toArray());
        toNationality.setBounds(350, 100, 100, 35);
        this.currentPanel.add(toNationality);

        JLabel fromTimLabel = new JLabel("<html>Start Date<br>DD/MM/YYYY, eg. 26/03/2020</html>");
        fromTimLabel.setBounds(50, 150, 300, 50);
        this.currentPanel.add(fromTimLabel);

        // Drop down menu that holds information about currency Nationality.
        fromTime = new JTextField();
        fromTime.setBounds(50, 200, 100, 35);
        this.currentPanel.add(fromTime);

        JLabel toTimeLabel = new JLabel("<html>End Date<br>DD/MM/YYYY, eg. 26/03/2020</html>");
        toTimeLabel.setBounds(350, 150, 200, 50);
        this.currentPanel.add(toTimeLabel);

        // Drop down menu that holds information about currency Nationality.
        toTime = new JTextField();
        toTime.setBounds(350, 200, 100, 35);
        this.currentPanel.add(toTime);

        JButton getSummary = new JButton("Get Summary");
        getSummary.setBounds(550, 125, 150, 50);
        getSummary.addActionListener(e -> getInput());
        this.currentPanel.add(getSummary);

        warning = new JLabel("Warning: ");
        warning.setBounds(50, 250, 600, 25);
        warning.setVisible(false);
        this.currentPanel.add(warning);

        // All ConversionRate
        conversionRateLabel = new JLabel("All Recorded Conversion Rates (From Latest to Oldest):");
        conversionRateLabel.setBounds(50, 300, 200, 25);
        conversionRateLabel.setVisible(false);
        this.currentPanel.add(conversionRateLabel);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 350, 500, 200);
        scrollPane.setVisible(false);
        this.currentPanel.add(scrollPane);

        // Average
        JLabel averageLabel = new JLabel("");
        averageLabel.setBounds(600, 300, 200, 25);
        this.currentPanel.add(averageLabel);
        summaryLabels.add(averageLabel);

        // Median
        JLabel medianLabel = new JLabel("");
        medianLabel.setBounds(600, 350, 200, 25);
        this.currentPanel.add(medianLabel);
        summaryLabels.add(medianLabel);

        // Maximum
        JLabel maximumLabel = new JLabel("");
        maximumLabel.setBounds(600, 400, 200, 25);
        this.currentPanel.add(maximumLabel);
        summaryLabels.add(maximumLabel);

        // Minimum
        JLabel minimumLabel = new JLabel("");
        minimumLabel.setBounds(600, 450, 200, 25);
        this.currentPanel.add(minimumLabel);
        summaryLabels.add(minimumLabel);

        // standard Deviation
        JLabel standardDeviationLabel = new JLabel("");
        standardDeviationLabel.setBounds(600, 500, 200, 25);
        this.currentPanel.add(standardDeviationLabel);
        summaryLabels.add(standardDeviationLabel);

    }

    @Override
    public void currencyDataUpdate(CurrencyData newCurrencyData) {
        this.currencyData = newCurrencyData;
        this.currentPanel.remove(fromNationality);

        fromNationality = new JComboBox(currencyData.getNames().toArray());
        fromNationality.setBounds(50, 100, 100, 35);
        this.currentPanel.add(fromNationality);

        this.currentPanel.remove(toNationality);
        toNationality = new JComboBox(currencyData.getNames().toArray());
        toNationality.setBounds(350, 100, 100, 35);
        this.currentPanel.add(toNationality);
    }

    protected void getInput() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date fromDate = simpleDateFormat.parse(fromTime.getText());
            Date toDate = simpleDateFormat.parse(toTime.getText());
            inputValidation(fromDate, toDate);
        } catch (ParseException e) {
            warning.setText("Warning: Date format was incorrect");
            warning.setVisible(true);
        }
    }

    protected void inputValidation(Date fromDate, Date toDate){
        if(fromDate.compareTo(toDate)>0){
            warning.setText("Warning: Start Date begines after End Date");
            warning.setVisible(true);
        }
        else if(fromDate.compareTo(toDate)==0){
            warning.setText("Warning: Same Date is Chosen");
            warning.setVisible(true);
        }
        else if(fromNationality.getSelectedIndex() == toNationality.getSelectedIndex()){
            warning.setText("Warning: Same Currency has been selected");
            warning.setVisible(true);
        }
        else{
            warning.setVisible(false);
            summarize(fromDate, toDate);
        }
    }

    protected void summarize(Date fromDate, Date toDate){
        ArrayList<Float> exchangeHistory =  currencyData.getExchangeHistory(fromDate, toDate, currencyData.getNames().get(fromNationality.getSelectedIndex()), currencyData.getNames().get(toNationality.getSelectedIndex()));
        Collections.reverse(exchangeHistory);
        pastExchangeRate = new JList<>(exchangeHistory.toArray());
        scrollPane.setViewportView(pastExchangeRate);
        scrollPane.setVisible(true);
        conversionRateLabel.setVisible(true);
        this.currentPanel.updateUI();
        setSummary(new CurrencySummary().summarise(exchangeHistory));
    }

    private void setSummary(HashMap<String, Float> summary) {

        // Average
        this.summaryLabels.get(0).setText(String.format("Average  =  %.02f", summary.get("avg")));

        // Median
        this.summaryLabels.get(1).setText(String.format("Median  =  %.02f", summary.get("med")));

        // Maximum
        this.summaryLabels.get(2).setText(String.format("Maximum  =  %.02f", summary.get("max")));

        // Minimum
        this.summaryLabels.get(3).setText(String.format("Minimum  =  %.02f", summary.get("min")));
        
        // standard Deviation
        this.summaryLabels.get(4).setText(String.format("Standard Deviation  =  %.02f", summary.get("sd")));
    }

}