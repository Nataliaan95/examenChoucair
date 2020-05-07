package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.Keys.TAB;

public class demo {

    private static WebDriver driver;

    By txtUser = By.id("user_login");
    By txtPass = By.id("user_pass");
    By btnLogin = By.id("wp-submit");
    By menuPost = By.xpath("//div[contains(text(),'Posts')]");
    By btnAddPost = By.xpath("//a[@class='page-title-action']");
    By txtTituloPost = By.id("post-title-0");
    By txtDescripcionPost = By.xpath("//p[@role='textbox']");
    By equis = By.xpath("//*[@id=\"editor\"]/div/div/div/div[5]/div/div/div/div/button");
    By btnPublish1 = By.xpath("//button[@class='components-button editor-post-publish-panel__toggle is-button is-primary']");
    By btnPublish2 = By.xpath("//button[@class='components-button editor-post-publish-button is-button is-default is-primary is-large']");
    By linkViewPost = By.xpath("//a[@class='components-button components-notice__action is-link']");
    By menuOpensource = By.xpath("//a[@class='ab-item'][contains(text(),'opensourcecms')]");
    By txtTituloPublish = By.xpath("//h1[@class='entry-title']");


    By menuPage = By.xpath("//div[@class='wp-menu-image dashicons-before dashicons-admin-page']");
    By btnAddPage = By.xpath("//a[@class='page-title-action']");
    By txtTituloPage = By.id("post-title-0");
    By getTxtDescripcionPage = By.xpath("//p[@class='block-editor-rich-text__editable editor-rich-text__editable wp-block-paragraph']");
    By linkViewPage = By.xpath("//a[@class='components-button components-notice__action is-link']");

    @BeforeClass
    public void setUp(){
       WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php");
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void login() throws InterruptedException {
        driver.findElement(txtUser).clear();
        driver.findElement(txtUser).sendKeys("opensourcecms");
        driver.findElement(txtPass).clear();
        driver.findElement(txtPass).sendKeys("opensourcecms");
        Thread.sleep(1000);
        driver.findElement(btnLogin).click();
        //Assert.assertEquals("Invalid  Credentials",lblmensaje.getText());
    }
    @Test (dependsOnMethods = {"login"})
    public void nuevoPost() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(menuPost).click();
        Thread.sleep(1000);
        driver.findElement(btnAddPost).click();
        Thread.sleep(2500);
        driver.findElement(equis).click();
        Thread.sleep(1000);
        driver.findElement(txtTituloPost).sendKeys("Titulo POST 1");
        driver.findElement(txtTituloPost).sendKeys(TAB);
        driver.findElement(txtDescripcionPost).sendKeys("Descripcion POST 1");
        driver.findElement(btnPublish1).click();
        Thread.sleep(2000);
        driver.findElement(btnPublish2).click();
        Thread.sleep(3000);
        driver.findElement(linkViewPost).click();
        Thread.sleep(2000);
        Assert.assertEquals("Titulo POST 1",driver.findElement(txtTituloPublish).getText());
        driver.findElement(menuOpensource).click();

    }

   @Test (dependsOnMethods = {"nuevoPost"})
    public void nuevoPage() throws InterruptedException {

       Thread.sleep(2000);
       driver.findElement(menuPage).click();
       Thread.sleep(1000);
       driver.findElement(btnAddPage).click();
       Thread.sleep(2500);
       driver.findElement(txtTituloPage).sendKeys("TITULO PAGE 001");
       driver.findElement(txtTituloPage).sendKeys(TAB);
       driver.findElement(getTxtDescripcionPage).sendKeys("Descripcion PAGE 001");
       driver.findElement(btnPublish1).click();
       Thread.sleep(2000);
       driver.findElement(btnPublish2).click();
       Thread.sleep(3000);
       driver.findElement(linkViewPage).click();
       Thread.sleep(2000);
       Assert.assertEquals("TITULO PAGE 001",driver.findElement(txtTituloPublish).getText());

   }

    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.close();
        }
    }

}
