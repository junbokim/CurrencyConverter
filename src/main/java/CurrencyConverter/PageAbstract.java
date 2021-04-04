package CurrencyConverter;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PageAbstract implements PageInterface {

    protected JFrame currentPage;
    protected JPanel currentPanel;

    //PageList for Navigating around different page
    protected ArrayList<PageInterface> pageList;

    //Boolean value to store whether user is admin or not.
    protected boolean admin;

    protected CurrencyData currencyData;

    protected PageAbstract(User currentUser, CurrencyData currencyData){
        this.currencyData = currencyData;
        this.admin = currentUser.isAdmin();
        
        defaultPageSetup(currentUser);
    }
    
    protected void defaultPageSetup(User currentUser){
        this.currentPage = new JFrame();
        this.currentPanel = new JPanel();
        this.currentPanel.setLayout(null);

        //Default page setups
        this.currentPage.setSize(800,700);
        this.currentPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.currentPage.add(currentPanel);

        //User Profile Description settings
        String profileDescription = String.format("<html> Current User: %s <br> Admin auth: %b</html>", currentUser.getUsername(), currentUser.isAdmin());
        JLabel profile = new JLabel(profileDescription);
        profile.setBounds(625,50,200,50);
        this.currentPanel.add(profile);
        setNavBar();
    }

    //Navigate to the page that was clicked from NavBar
    protected void navigate(PagesEnum pageType){
        //Hide current page
        this.currentPage.setVisible(false);
    
        //Find pages to navigate to
        int pageIndex = 0;
        switch(pageType){
            case MONEYCONVERSION:
                pageIndex = 0;
                break;
            case CURRENCYRATE:
                pageIndex = 1;
                break;
            case CURRENCYSUMMARY:
                pageIndex = 2;
                break;
            case CONVERSIONRATEUPDATE:
                pageIndex = 3;
                break;
            case NEWCURRENCY:
                pageIndex = 4;
                break;
        }
    
            //Show the specified page
        pageList.get(pageIndex).setVisible();
    }
    
    //Create and assign Navigation Bar buttons
    private void setNavBar(){
        JButton convertPage = new JButton("Money Conversion");
        convertPage.setBounds(0,0,150,25);
        convertPage.addActionListener(e -> navigate(PagesEnum.MONEYCONVERSION));
        currentPanel.add(convertPage);
    
        JButton conversionRatePage = new JButton("Conversion Rate");
        conversionRatePage.setBounds(150,0,150,25);
        conversionRatePage.addActionListener(e -> navigate(PagesEnum.CURRENCYRATE));
        currentPanel.add(conversionRatePage);
        
        JButton conversionRateSummaryPage = new JButton("Rate Summary");
        conversionRateSummaryPage.setBounds(300,0,150,25);
        conversionRateSummaryPage.addActionListener(e -> navigate(PagesEnum.CURRENCYSUMMARY));
        currentPanel.add(conversionRateSummaryPage);
    
        if(admin){
            JButton rateUpdatePage = new JButton("Update Conversion Rate");
            rateUpdatePage.setBounds(450,0,175,25);
            rateUpdatePage.addActionListener(e -> navigate(PagesEnum.CONVERSIONRATEUPDATE));
            currentPanel.add(rateUpdatePage);
        
            JButton newCurrencyPage = new JButton("New Currency");
            newCurrencyPage.setBounds(625,0,125,25);
            newCurrencyPage.addActionListener(e -> navigate(PagesEnum.NEWCURRENCY));
            currentPanel.add(newCurrencyPage); 
        }
    }

    //Set Current page to Visible
    public void setVisible(){
        this.currentPage.setVisible(true);
    }
    
    //Set Current page to Invisible
    public void setInvisible(){
        this.currentPage.setVisible(false);
    }
    
    //Set PageList attribute
    public void setPageList(ArrayList<PageInterface> pageList){
        this.pageList = pageList;
    }

}
