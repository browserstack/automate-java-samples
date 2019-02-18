package com.browserstack.demos;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStack {

    public static void main(String args[]) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = "BROWSERSTACK_USERNAME";
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = "BROWSERSTACK_ACCESS_KEY";
        }

        WebDriver driver = new RemoteWebDriver(
                new URL("http://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"), options);
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack");
        element.submit();
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}
