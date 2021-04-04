package CurrencyConverter;

import java.util.Date;

public class ConversionUpdate {
    private Date date;
    private float value;

    ConversionUpdate(Date updateTime, float value){
        this.date = updateTime;
        this.value = Math.round(value * 100.0f) / 100.0f;
    }

    public Date getDate(){
        return this.date;
    }

    public float getValue(){
        return this.value;
    }
}
