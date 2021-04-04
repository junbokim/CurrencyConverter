package CurrencyConverter;

import java.util.ArrayList;
import java.util.Date;

public class PageManager {
	protected User currentUser;

    protected MoneyConversionPage moneyConversionPage;
    protected CurrencyRatePage currencyRatePage;
    protected ConversionRateSummaryPage conversionRateSummaryPage;
    protected ConversionRateUpdatePage conversionRateUpdatePage;
    protected NewCurrencyPage newCurrencyPage;
    protected CurrencyData currencyData;
    protected ArrayList<PageInterface> pageList;

    public PageManager(User currentUser, CurrencyData currencyData) {
        this.currentUser = currentUser;
        this.currencyData = currencyData;


        setPages();
        setLinks();

        this.moneyConversionPage.setVisible();
    }

    private void setPages(){
        this.moneyConversionPage = new MoneyConversionPage(currentUser,currencyData);
        this.currencyRatePage = new CurrencyRatePage(currentUser,currencyData);
        this.conversionRateSummaryPage = new ConversionRateSummaryPage(currentUser,currencyData);
        ArrayList<updateJComboInterface> updateDropdown = new ArrayList<>();
        if(currentUser.isAdmin()){
            this.conversionRateUpdatePage = new ConversionRateUpdatePage(currentUser,currencyData, currencyRatePage);
            updateDropdown.add(moneyConversionPage);
            updateDropdown.add(currencyRatePage);
            updateDropdown.add(conversionRateSummaryPage);
            updateDropdown.add(conversionRateUpdatePage);

            this.newCurrencyPage = new NewCurrencyPage(currentUser,currencyData,updateDropdown);
        }
    }

    private void setLinks(){
        pageList = new ArrayList<PageInterface>();
        pageList.add(this.moneyConversionPage);
        pageList.add(this.currencyRatePage);
        pageList.add(this.conversionRateSummaryPage);
        if(currentUser.isAdmin()){
            pageList.add(this.conversionRateUpdatePage);
            pageList.add(this.newCurrencyPage);
        }
        this.moneyConversionPage.setPageList(pageList);
        this.currencyRatePage.setPageList(pageList);
        this.conversionRateSummaryPage.setPageList(pageList);
        if(currentUser.isAdmin()){
            this.conversionRateUpdatePage.setPageList(pageList);
            this.newCurrencyPage.setPageList(pageList);
        }
    }

}