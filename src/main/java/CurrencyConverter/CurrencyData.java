package CurrencyConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CurrencyData {

    private ArrayList<ArrayList<ArrayList<ConversionUpdate>>> conversionData;
    private ArrayList<String> names;

    private ArrayList<String> displayNames;
    private ArrayList<Integer> displayIndices;
    private int tableSize = 4;

    CurrencyData(){
        // Read in from file
        names = new ArrayList<>();
        conversionData = new ArrayList<>();
    }


    public boolean readFromFile(String filepath){

        boolean success = false;

        try {
            Date updateDate = null;
            String rawNames = null;
            String rawDisplayNames = null;
            ArrayList<String> rawRates = new ArrayList<>();

            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            int lineCounter = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (lineCounter == 0){
                    updateDate = new Date(Long.parseLong(data));
                } else if (lineCounter == 1){
                    rawNames = data;
                }else if (lineCounter == 2){
                    rawDisplayNames = data;
                }else {
                    rawRates.add(data);
                }
                lineCounter += 1;
            }
            myReader.close();

            // Parse list of names for later processing
            String names[] = rawNames.split(" ");
            String arrayDisplayNames[] = rawDisplayNames.split(" ");
            ArrayList<String> displayNames = new ArrayList<>(Arrays.asList(arrayDisplayNames));

            if (names.length == rawRates.size()){

                boolean allValid = true;
                for (int i=rawRates.size() - 1; i>=0; i--){
                    if (rawRates.get(i).split(" ").length != names.length){
                        allValid = false;
                    }
                }

                if (allValid) {
                    success = true;

                    ArrayList<ArrayList<Float>> allValues = new ArrayList<>();

                    for (int i = 0; i < rawRates.size(); i++) {
                        String valueString = rawRates.get(i);
                        String arrayValues[] = valueString.split(" ");
                        ArrayList<Float> values = new ArrayList<>();
                        for (int j = arrayValues.length - 1; i < j; j--) {
                            try {
                                values.add(Float.parseFloat(arrayValues[j]));
                            }
                            catch (Exception e) {
                                //The handling for the code
                            }
                        }
                        allValues.add(values);
                    }

                    for (int i=0; i<rawRates.size(); i++){
                        ArrayList<Float> values = new ArrayList<>();
                        for (int j = 0; j < allValues.size(); j++) {
                            int index = rawRates.size() - i - 1;
                            if (index < allValues.get(j).size()){
                                float value = allValues.get(j).get(index);
                                values.add(1.0f / value);
                            }

                        }
                        success = success && this.addCurrency(updateDate, names[i], values);
                    }

                    success = success && this.setDisplayNames(displayNames);

                }
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return success;
    }

    public boolean updateCurrency(Date date, String from, String to, float value){
        boolean success = false;

        // Get indices for update
        int fromInd = names.indexOf(from);
        int toInd = names.indexOf(to);

        // Check that the names actually exist (better to have more error checking)
        if (fromInd != -1 && toInd != -1){
            if (value > 0.0f) { // Value must be strictly positive

                // Generate the updates
                ConversionUpdate update = new ConversionUpdate(date, value);
                ConversionUpdate inverse = new ConversionUpdate(date, 1.0f / value);

                // Add the updates
                conversionData.get(fromInd).get(toInd).add(update);
                conversionData.get(toInd).get(fromInd).add(inverse);

                // Mark it as a success
                success = true;
            }
        }

        return success;
    }

    public boolean addCurrency(Date date, String curNew, ArrayList<Float> rowValues){
        // Values input should be a row - curNew -> other

        boolean success = false;

        boolean allPos = true;
        for (int l=0; l<rowValues.size();l++){
            if (rowValues.get(l) <= 0.0f){
                allPos = false;
            }

        }

        // Check that value array is correct length
        if (rowValues.size() == conversionData.size()){



            if (allPos){ // Check that *every* value is strictly positive

                // Add a new column for the new currency
                ArrayList<ArrayList<ConversionUpdate>> newRow = new ArrayList<>();
                for (int i=0; i<rowValues.size(); i++){
                    ArrayList<ConversionUpdate> newHistory = new ArrayList<>();
                    newHistory.add(new ConversionUpdate(date, rowValues.get(i)));
                    newRow.add(newHistory);
                }

                // Add a final value for self-to-self, to make it square
                ArrayList<ConversionUpdate> newHistory = new ArrayList<>();
                newHistory.add(new ConversionUpdate(date, 1));
                newRow.add(newHistory);

                conversionData.add(newRow);

                // Fill out the column
                for (int j=0; j<rowValues.size(); j++){
                    ArrayList<ArrayList<ConversionUpdate>> currentColumn = conversionData.get(j);
                    ArrayList<ConversionUpdate> newColumnHistory = new ArrayList<>();
                    newColumnHistory.add(new ConversionUpdate(date, 1.0f / rowValues.get(j)));
                    currentColumn.add(newColumnHistory);
                }

                // Add the name to the list
                names.add(curNew);

                // Say it worked
                success = true;
            }



        }

        return success;
    }

    public ArrayList<String> getDisplayNames(){
        return this.displayNames;
    }

    public ArrayList<String> getNames(){
        return this.names;
    }

    public ArrayList<Float> getExchangeHistory(Date fromDate, Date toDate, String from, String to){
        int fromInd = names.indexOf(from);
        int toInd = names.indexOf(to);

        ArrayList<Float> output = null;

        // Check that the names actually exist (better to have more error checking)
        if (fromInd != -1 && toInd != -1) {

            ArrayList<ConversionUpdate> history = conversionData.get(fromInd).get(toInd);

            Collections.sort(history, new Comparator<ConversionUpdate>() {
                @Override
                public int compare(ConversionUpdate o1, ConversionUpdate o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            });

            output = new ArrayList<>();

            for (int i=0; i< history.size(); i++){
                ConversionUpdate item = history.get(i);
                long time = item.getDate().getTime();
                if (time > fromDate.getTime() && time < toDate.getTime()){
                    output.add(item.getValue());
                }
            }

        }

        return output;
    }

    public boolean setDisplayNames(ArrayList<String> newDispNames){

        boolean success = true;

        if (newDispNames.size() != 4){
            success = false;
        }

        ArrayList<Integer> indices = new ArrayList<>();

        for (int i=0; i<newDispNames.size(); i++){
            int index = this.names.indexOf(newDispNames.get(i));
            if (index == -1){success = false;}
            indices.add(index);
        }

        if (success) {
            this.displayIndices = indices;
            this.displayNames = newDispNames;
        }

        return success;
    }

    public ArrayList<ArrayList<Float>> getTableValues() {

        ArrayList<ArrayList<Float>> outputTable = new ArrayList<>();
        for (int i=0; i<this.tableSize; i++){
            int indFrom = this.displayIndices.get(i);

            ArrayList<Float> newRow = new ArrayList<>();
            for (int j=0; j<this.tableSize; j++){
                int indTo = this.displayIndices.get(j);

                ArrayList<ConversionUpdate> history = conversionData.get(indFrom).get(indTo);
                ConversionUpdate lastUpdate = history.get(history.size() - 1);
                newRow.add(lastUpdate.getValue());
            }

            outputTable.add(newRow);
        }

        return outputTable;
    }

    public ArrayList<ArrayList<Integer>> getTableDirections(){

        ArrayList<ArrayList<Integer>> outputTable = new ArrayList<>();
        for (int i=0; i<this.tableSize; i++){
            int indFrom = this.displayIndices.get(i);

            ArrayList<Integer> newRow = new ArrayList<>();
            for (int j=0; j<this.tableSize; j++){
                int indTo = this.displayIndices.get(j);

                ArrayList<ConversionUpdate> history = conversionData.get(indFrom).get(indTo);

                ConversionUpdate lastUpdate = history.get(history.size() - 1);
                float latestValue = lastUpdate.getValue();
                float prevValue = latestValue;
                if (history.size() > 1){
                    prevValue = history.get(history.size() - 2).getValue();
                }
                int direction = 0;
                if (latestValue > prevValue){
                    direction = 1;
                }else if (prevValue > latestValue){
                    direction = -1;
                }
                newRow.add(direction);
            }

            outputTable.add(newRow);
        }

        return outputTable;

    }

    public float getExchangeRate(String nameFrom, String nameTo){
        int fromInd = names.indexOf(nameFrom);
        int toInd = names.indexOf(nameTo);
        ArrayList<ConversionUpdate> excRates = conversionData.get(fromInd).get(toInd);
        float exc = excRates.get(excRates.size()-1).getValue();
        return exc;
    }


}
