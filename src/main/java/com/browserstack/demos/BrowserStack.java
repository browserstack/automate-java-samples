package com.browserstack.demos;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStack {

    public static void main(String args[]) throws MalformedURLException {
        HashMap<String, String> bstackOptions = new HashMap<String, String>();
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "7");
        bstackOptions.put("sessionName", "BStack automate-java");
        bstackOptions.put("buildName", "browserstack-build-1");
        bstackOptions.put("source", "automate-java:sample-master:v1.0");
        bstackOptions.put("projectName", "My Awesome App");
        bstackOptions.put("debug", "true");

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", "Chrome");
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
