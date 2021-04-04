package CurrencyConverter;

import java.util.ArrayList;

public interface PageInterface{
       
    //Set Current page to Visible
    public void setVisible();
    
    //Set Current page to Invisible
    public void setInvisible();
    
    //Set PageList attribute
    public void setPageList(ArrayList<PageInterface> pageList);
    
}