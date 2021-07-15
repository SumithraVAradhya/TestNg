import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;

public class Screenshot {
    public static WebDriver driver;
    public String demoUrl = "http://practice.automationtesting.in/" ;


    @DataProvider(name = "demoData")
    public Object[][] demofunc(){
        return new Object[][]{
                {"gnasumithra@321", "sumi@32"}

        };
    } //sumi@32sumi@32
    @DataProvider(name = "demoData2")
    public Object[][] demofunc2(){
        return new Object[][]{
                {"test2", "test31"}
        };
    }


    @BeforeMethod
    public void setupDriver(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(demoUrl);
    }

    @Test(dataProvider = "demoData")
    public void demoTest(String user, String pass){
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.id("username")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/form/p[3]/input[3]")).click();

        String url=driver.getCurrentUrl();
        Assert.assertEquals(url,"http://practice.automationtesting.in/my-account/");
    }

    @Test(dataProvider = "demoData2")
    public void demoTest2(String username, String password){
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/form/p[3]/input[3]")).click();
        String url=driver.getCurrentUrl();
        Assert.assertEquals(url,"http://practice.automationtesting.in/my-account/");

    }



    @AfterMethod
    public void quitBrowser(){
        driver.quit();
    }


    public static void screenShot(){
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        File destinationFile = new File("beforeClick.jpeg");

        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
////*[@id="customer_login"]/div[1]/form/p[3]/input[3]
