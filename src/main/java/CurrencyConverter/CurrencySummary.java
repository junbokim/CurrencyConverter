package CurrencyConverter;

import java.util.*;

public class CurrencySummary {
    public static HashMap<String, Float> summarise(ArrayList<Float> values){
        HashMap<String, Float> returnData = new HashMap<>();

        float total = 0.0f;
        float min = Float.MAX_VALUE;
        float max = 0.0f;

        for (float value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
            total += value;
        }

        float mean = total / (float) values.size();

        float intSD = 0.0f;

        for (float value : values) {
            intSD += Math.pow(value - mean, 2);
        }
        float SD = (float) Math.sqrt(intSD / (float) values.size());

        Collections.sort(values);
        float median = 0.0f;
        if (values.size() % 2 == 0){ // even
            median = (values.get(values.size() / 2) + values.get(values.size() / 2 - 1)) / 2.0f;
        }else{ // odd
            median = values.get(values.size() / 2);
        }

        returnData.put("avg", mean);
        returnData.put("min", min);
        returnData.put("max", max);
        returnData.put("sd", SD);
        returnData.put("med", median);

        return returnData;

    }
}
