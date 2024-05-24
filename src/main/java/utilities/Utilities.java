package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Utilities {
    public static String captureScreenshot(String imagePath , WebDriver driver) throws IOException , NullPointerException {
        TakesScreenshot camera = (TakesScreenshot) driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        Files.move(screenshot.toPath(), Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);

        return imagePath;

    }
}
