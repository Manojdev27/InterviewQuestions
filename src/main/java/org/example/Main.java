package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        String downloadFilePath = "D:\\Selenium Projects\\InterviewQuestions\\downloaded files";
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFilePath);
        prefs.put("profile.default_content_setting_values.notifications", 2);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        driver.get("https://romsunlocked.com/");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"s\"]"));
        element.sendKeys("God Hand");
        element.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div[1]/div[2]/div[1]/a")).click();
        WebElement element1 = driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div[1]/div[3]/p[5]/a"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", element1);
        WebDriverWait webDriverWait =new WebDriverWait(driver, 50);
        webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for(String windowHandle : driver.getWindowHandles()){
            if(!originalWindow.contentEquals(windowHandle)){
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        webDriverWait.until(ExpectedConditions.titleIs("UploadHaven - File Sharing Made Simple"));
        System.out.println(driver.getTitle());
        Thread.sleep(30000);
//        WebElement element2 = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"submitFree\"]")));
        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"submitFree\"]"));
        JavascriptExecutor javascriptExecutor1 = (JavascriptExecutor) driver;
        javascriptExecutor1.executeScript("arguments[0].click();", element2);

    }
}