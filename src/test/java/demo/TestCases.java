package demo;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.utils.ExcelDataProvider;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */

        @Test
        public  void testCase01() throws InterruptedException {
                System.out.println("Start of Test case 01");       // Starting First Test Case
                //Visiting Youtube
                SoftAssert sa = new SoftAssert();

                
                driver.get("https://www.youtube.com");
                String title = driver.getTitle();
                boolean status=title.equals("https://www.youtube.com");

                sa.assertFalse(status,"Assertion Failed");
                WebElement about = driver.findElement(By.xpath("//a[text()='About']"));
                WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='About']")));
                about.click();
                Thread.sleep(5000);
                WebElement message = driver.findElement(By.xpath("//main[@id='content']"));
                String text = message.getText();
                System.out.println(text);
                sa.assertAll();
                System.out.println("End of Test case 01");        // Ending First Test Case
        }

        @Test
        public  void testCase02() throws InterruptedException { 
                System.out.println("Start of Test case 02");            // Starting Second Test Case
                driver.get("https://www.youtube.com");
                SoftAssert sa = new SoftAssert();
                Thread.sleep(3000);
                Wrappers.categryClick(driver, "Movies");
                Thread.sleep(10000);
                WebElement next = driver.findElement(By.xpath("//span[text()='Top selling']/ancestor::div[@class='grid-subheader style-scope ytd-shelf-renderer']/following-sibling::div[@id='contents']/descendant::button[@aria-label='Next']"));
                while(next.isDisplayed()){
        
                        next.click();
                
                }
                List<WebElement> category = driver.findElements(By.xpath("//span[text()='Top selling']//ancestor::div[@id='dismissible']//descendant::span[contains(@class,'grid-movie-renderer-metadata')]"));
                String lastCategory = category.get(category.size()-1).getText();
                System.out.println(lastCategory);
                sa.assertTrue(lastCategory.contains("Comedy") || lastCategory.contains("Animation") || lastCategory.contains("Drama"),"Assertion Failed for Movie Category" );
                List<WebElement> rating = driver.findElements(By.xpath("//span[text()='Top selling']//ancestor::div[@id='dismissible']//descendant::div[contains(@class,'badge-style-type-simple')]//child::p[contains(@class,'ytd-badge-supported-renderer')]"));
                String lastRating = rating.get(rating.size()-1).getText();
                System.out.println(lastRating);
                sa.assertFalse(lastRating.contains("A") || lastCategory.contains("U"),"Assertion Failed for Movie Rating" );
                
                

                sa.assertAll();


           System.out.println("End of Test case 02");        // Ending Second Test Case
        }

        @Test
        public  void testCase03() throws InterruptedException { 
           System.out.println("Start of Test case 03");            // Starting Third Test Case
           SoftAssert sa = new SoftAssert();
           driver.get("https://www.youtube.com");
           Thread.sleep(5000);
           Wrappers.categryClick(driver, "Music");
           Thread.sleep(5000);
           WebElement nextBtn = driver.findElement(By.xpath("(//span[contains(text(),'Biggest Hits')]//ancestor::div[@id='dismissible']//descendant::button[contains(@class,'yt-spec-button-shape-next')])[2]"));
           while(nextBtn.isDisplayed()){
                
                        nextBtn.click();
                
           }
                List<WebElement> category = driver.findElements(By.xpath("//span[contains(text(),'Biggest Hits')]//ancestor::div[@id='dismissible']//descendant::a[contains(@class,'ytd-compact-station-renderer')]//h3"));
                String lastCategoryMusic = category.get(category.size()-1).getText();
                System.out.println(lastCategoryMusic);
                boolean status = false;
                List<WebElement> count = driver.findElements(By.xpath("//span[contains(text(),'Biggest Hits')]//ancestor::div[@id='dismissible']//descendant::p[@id='video-count-text']"));
                String lastCount = count.get(count.size()-1).getText();
                String[] lastCount1=lastCount.split(" ");
                int count1 = Integer.parseInt(lastCount1[0]);
                System.out.println(lastCount);
        
                if(count1 <= 50){
                        status=true;
                }
                sa.assertTrue(status,"Assertion Failed for Count of tracks" );
                
                
           sa.assertAll();
           System.out.println("End of Test case 03");        // Ending Third Test Case
        }

        @Test
        public  void testCase04() throws InterruptedException { 
           System.out.println("Start of Test case 04");            // Starting Fourth Test Case
           SoftAssert sa = new SoftAssert();
           driver.get("https://www.youtube.com");
           Thread.sleep(2000);
           Wrappers.categryClick(driver, "News");
           Thread.sleep(5000);
           List<WebElement> newsTitle = driver.findElements(By.xpath("//span[text()='Latest news posts']/ancestor::div[@id='dismissible']/descendant::a[@id='author-text']"));
           List<WebElement> newsBody = driver.findElements(By.xpath("//span[text()='Latest news posts']/ancestor::div[@id='dismissible']/descendant::div[@id='post-text']"));
           List<WebElement> likes = driver.findElements(By.xpath("//span[text()='Latest news posts']/ancestor::div[@id='dismissible']//span[@id='vote-count-middle']"));
           int count = 0;
           int k = 0;
           for (int i = 0 ;i < 3 ; i++){
                String Title = newsTitle.get(i).getText();
                System.out.println(Title);
                String Body = newsBody.get(i).getText();
                System.out.println(Body);
                WebElement like = likes.get(i);
                String likesCount = likes.get(i).getText();
                int likesCount1 = Integer.parseInt(likesCount);
                if(like.isDisplayed()){
                        k = likesCount1;
                        count += k;
                }
                else{
                        k = 0;
                }
               
                System.out.println("Totla Number of Likes :" + count);    
           }
           sa.assertAll();
           System.out.println("End of Test case 04");        // Ending Second Fourth Case
        }

        @Test(dataProvider="excelData")
        public  void testCase05(String value) throws InterruptedException { 
           System.out.println("Start of Test case 05");     // Starting Fifth Test Case
           driver.get("https://www.youtube.com");     
           WebElement search = driver.findElement(By.xpath("//input[@id='search']"));
           Thread.sleep(2000);
           search.sendKeys(value);  
           Thread.sleep(2000);
           WebElement searchButton = driver.findElement(By.xpath("//button[@id='search-icon-legacy']"));
           Thread.sleep(2000);
           searchButton.click();
           Thread.sleep(2000);
           List<WebElement> views = driver.findElements(By.xpath("//div[@id='dismissible']/descendant::span[contains(text(),'views')]"));
           double count = 0.0;
           double k=0.0;
           for(WebElement views1:views){
                
                if(count <= 100000000){
           String[] viewsCount = views1.getText().split(" ");
           if(viewsCount[0].contains("M")){
           k = Double.parseDouble(viewsCount[0].replace("M", ""))*1000000;
           System.out.println(k);
           count += k ;
           }
           
           if(viewsCount[0].contains("K") ){
           k = Double.parseDouble(viewsCount[0].replace("K", ""))*1000;
           System.out.println(k);
           count += k ;
           }
           }
           else{
                break;
           }
           }
           System.out.println("Total Number of views : "+k);
           
           System.out.println("End of Test case 05");        // Ending Fifth Test Case
        }
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
        }

        @AfterTest
        public void endTest() {
                //driver.close();
                // .driver.quit();

        }
}