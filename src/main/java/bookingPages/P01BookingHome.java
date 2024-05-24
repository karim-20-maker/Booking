package bookingPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageBase.PageBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class P01BookingHome extends PageBase {
    public P01BookingHome(WebDriver driver) {
        super(driver);
    }
    private final By select_place_input = By.xpath("//input[@aria-label='Where are you going?']");
    private final By check_in = By.xpath("//*[@data-testid='date-display-field-start']");
    private final By check_out = By.xpath("//span[normalize-space()='Check-out Date']");
    private final By nextArrow = By.xpath("//button[@aria-label='Next month']");
    private final By search_CTA = By.xpath("//span[normalize-space()='Search']");
    private final By dateCell = By.xpath("//*[@role='gridcell']");
    private final By Tolip = By.xpath("//*[normalize-space()='Tolip Hotel Alexandria']");
    private final By selected_date_label = By.xpath("//*[@data-testid='searchbox-dates-container']");
    private final By alex_from_dropdown = By.xpath("//div[normalize-space()='Alexandria Governorate, Egypt']");
    private final By clear_search_input = By.xpath("//span[@data-testid='input-clear']//*[name()='svg']");

    private void selectCheckInAndCheckOutDate(String checkInDate, String checkOutDate) {
        waitForVisibilityOfElement(dateCell);
        By checkIn_date = By.xpath("//*[@data-date='" + checkInDate + "']");
        By checkOut_date = By.xpath("//*[@data-date='" + checkOutDate + "']");
        List<WebElement> dates = driver.findElements(dateCell);
        String latestDateDisplayed = dates.get(dates.size() - 1).findElement(By.xpath("//span[@data-date]")).getAttribute("data-date");
        checkDateDisplayedIntoCalender(latestDateDisplayed,checkInDate,checkIn_date);
        checkDateDisplayedIntoCalender(latestDateDisplayed,checkOutDate,checkOut_date);
    }
    private Boolean compareDates(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse(date1, formatter);
        LocalDate localDate2 = LocalDate.parse(date2, formatter);

        return !localDate1.isAfter(localDate2);
    }
    private void validateBookingSearchComponent(){
        ElementsValidator(search_CTA,select_place_input,check_in,check_out);
    }
    private void searchWithSpecificDateAndLocation(String Location , String checkInDate , String checkOutDate){
        validateBookingSearchComponent();
        sendTextToInputField(Location, select_place_input);
        try{
            waitForTime(5000);
            driver.findElement(alex_from_dropdown).click();
        }catch (Exception e){
            dismissLoginPopup();
            clickOnElement(clear_search_input);
            sendTextToInputField(Location, select_place_input);
            clickOnElement(alex_from_dropdown);
        }
//        clickOnElement(By.xpath(""));
        waitForVisibilityOfElement(check_in);
        selectCheckInAndCheckOutDate(checkInDate, checkOutDate);
        clickOnElement(search_CTA);
    }
    private void selectTolip(){
        try {
            scrollToElement(Tolip);
        }catch (Exception e){
            scrollToElement(Tolip);
        }
        switchScreen(Tolip,By.xpath("(//*[@data-testid='availability-cta-btn'])[" + getTolipIndex() + "]"));
        waitForVisibilityOfElement(By.xpath("//*[normalize-space()='Info & prices']"));
    }
    private void validateDatesANdSelectTolip(){
        waitForVisibilityOfElement(search_CTA);
        scrollToElement(selected_date_label);
        waitForTime(5000);
        date = driver.findElement(selected_date_label).getText();
        System.out.println(date);
        selectTolip();
    }
    private int getTolipIndex() {
        List<WebElement> hotels = driver.findElements(By.xpath("//*[@data-testid='title']"));
        int index = 0;

        for (WebElement hotel : hotels) {
            if (hotel.getText().contains("Tolip Hotel Alexandria")) {
                return index + 1;
            }
            index++;
        }
        return -1;
    }
    private void checkDateDisplayedIntoCalender(String latestDateDisplayed, String checktDate , By DateLocator){
        while (true) {
            if (compareDates( latestDateDisplayed,  checktDate)) {
                try {
                    if (driver.findElement(DateLocator).isDisplayed()) {
                        break;
                    }
                } catch (Exception e) {
                    scrollToElement(nextArrow);
                    clickOnElement(nextArrow);
                }
            } else {
                clickOnElement(nextArrow);
            }
        }
        scrollToElement(DateLocator);
        clickOnElement(DateLocator);
    }
    public void checkSearchFunctionality(String Location , String checkInDate , String checkOutDate){
        searchWithSpecificDateAndLocation(Location,checkInDate,checkOutDate);
        validateDatesANdSelectTolip();

    }



}
