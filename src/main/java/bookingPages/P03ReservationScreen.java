package bookingPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageBase.PageBase;

public class P03ReservationScreen extends PageBase {
    public P03ReservationScreen(WebDriver driver) {
        super(driver);
    }

    private final By TOLIP_Title = By.xpath("//h1[normalize-space()='Tolip Hotel Alexandria']");
    private final By Reservation_Screen_header = By.xpath("//*[@data-testid='header-custom-action-button']");
    public void checkTOLIPSelectedIntoReservationScreen(){
        waitForVisibilityOfElement(Reservation_Screen_header);
        scrollToElement(TOLIP_Title);
        Assert.assertTrue(assertElementDisplayed(TOLIP_Title));
    }
}
