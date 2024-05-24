package bookingPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageBase.PageBase;

public class P02DetailsScreen extends PageBase {
    public P02DetailsScreen(WebDriver driver) {
        super(driver);
    }
    private final By i_will_reserve_CTA = By.xpath("//*[@class='bui-button__text js-reservation-button__text']");
    private final By rooms_number_dropDown = By.xpath("(//*[@data-component='hotel/new-rooms-table/select-rooms'])[1]");
    private final By large_double_bed = By.xpath("(//input[@name='bedPreference_78883120' and @type='radio'])[2]");
    private final By two_single_bed = By.xpath("(//input[@name='bedPreference_78883120' and @type='radio'])[3]");
    private final By tolip_title = By.xpath("//*[normalize-space()='Tolip Hotel Alexandria']");
    private void validateTolipDisplayedSuccessfully(){
        scrollToElement(tolip_title);
        scrollToElement(By.xpath("//*[normalize-space()='" + extractDatesSplit(date,1) + "']"));
        scrollToElement(By.xpath("//*[normalize-space()='" + extractDatesSplit(date,2) + "']"));
        System.out.println("dates displayed successfully");
    }
    private void selectNumberOfRooms(int numberOfRooms){
        scrollToElement(rooms_number_dropDown);
        try{
            selectOptionByIndex(rooms_number_dropDown,numberOfRooms);
        }catch (Exception e){
            System.out.println("only on  type available");
        }
    }
    private void selectRoomType(String roomType){
        switch (roomType.toLowerCase()){
            case "large" :
                scrollToElement(large_double_bed);
                clickOnElement(large_double_bed);
                break;
            case "small" :
                scrollToElement(two_single_bed);
                clickOnElement(two_single_bed);
                break;
            default:
                System.out.println("no room type selected");
        }
    }
    public void reserveRoom(String bedSizeLargeOrSmall , int numberOfRooms){
        validateTolipDisplayedSuccessfully();
        selectNumberOfRooms(numberOfRooms);
        selectRoomType(bedSizeLargeOrSmall);
        clickOnElement(i_will_reserve_CTA);
    }
}
