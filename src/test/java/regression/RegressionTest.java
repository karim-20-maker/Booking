package regression;

import bookingPages.P01BookingHome;

import bookingPages.P02DetailsScreen;
import bookingPages.P03ReservationScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testBase.BaseTest;
import utilities.ExcelReader;

import java.io.IOException;

public class RegressionTest extends BaseTest {
    P01BookingHome home;
    P02DetailsScreen details;
    P03ReservationScreen reservation;
    @BeforeClass
    public void initiateObjects(){
        home = new P01BookingHome(driver);
        details = new P02DetailsScreen(driver);
        reservation = new P03ReservationScreen(driver);
    }
    @DataProvider(name="ReservationDetails")
    public Object[][] location() throws IOException
    {
        // get data from Excel Reader class
        ExcelReader ER = new ExcelReader();
        return ER.getExcelData("ReservationDetails", 3);
    }

    @Test(dataProvider = "ReservationDetails")
    public void TC1_validateSearchFunctionality(String location,String checkInDate,String checkOutDate){
        home.checkSearchFunctionality(location,checkInDate,checkOutDate);
    }
    @Test
    public void TC_2validateRoomReservation(){
        details.reserveRoom("small",3);
    }
    @Test
    public void TC_3validateTOLIPDisplayedIntoReserveScreen(){
        reservation.checkTOLIPSelectedIntoReservationScreen();
    }


}
