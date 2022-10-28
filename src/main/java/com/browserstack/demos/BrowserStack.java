package com.browserstack.demos;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStack {

    public static void main(String args[]) throws MalformedURLException {
        DesiredCapabilities bstackOptions = new DesiredCapabilities();
        bstackOptions.setCapability("os", "Windows");
        bstackOptions.setCapability("osVersion", "7");
        bstackOptions.setCapability("sessionName", "BStack automate-java");
        bstackOptions.setCapability("buildName", "browserstack-build-1");
        bstackOptions.setCapability("source", "automate-java:sample-selenium-4:v1.0");
        bstackOptions.setCapability("projectName", "My Awesome App");
        bstackOptions.setCapability("debug", true);
        bstackOptions.setCapability("seleniumVersion", "3.12.0");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserstack.use_w3c", true);
        capabilities.setCapability("browserstack.browserName", "Chrome");
        capabilities.setCapability("bstack:options", bstackOptions);

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = "BROWSERSTACK_USERNAME";
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = "BROWSERSTACK_ACCESS_KEY";
        }

        WebDriver driver = new RemoteWebDriver(
                new URL("http://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"), capabilities);
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack");
        element.submit();
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}
