package com.orangelogin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestNG extends BaseCase{
    WebDriver webDriver;

    @BeforeMethod
    public void setUp()
    {
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        webDriver = new FirefoxDriver();
        webDriver.get(Url);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test(priority = 0, enabled = true)
    public void doLoginOrangeHrmApplication() throws InterruptedException {



        boolean isDisplayed = webDriver.findElement(By.name("username")).isDisplayed();
        if(isDisplayed)
        {
            webDriver.findElement(By.name("username")).clear();
            webDriver.findElement(By.name("username")).sendKeys(username);
        }

        WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

        webDriver.findElement(By.name("password")).isDisplayed();
        webDriver.findElement(By.name("password")).clear();
        webDriver.findElement(By.name("password")).sendKeys(password);

        WebDriverWait wait2 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        //webDriver.findElement(By.xpath("//button[@type='submit']")).clear();
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);



    }


    @Test(priority = 1, enabled = true)
    public void validateHomepageTitleAfterLogin()
    {
        String expectedTitle="OrangeHRM";
        System.out.println(""+expectedTitle);
        String actualPageTitle= webDriver.getTitle();
        System.out.println(""+actualPageTitle);
        int a=5;
        int b=3;
        if (actualPageTitle==expectedTitle){
            System.out.println("Found Valid PageTitle");
        }

    }

    @AfterMethod
    public void tearDown()
    {
        webDriver.quit();
    }
}
