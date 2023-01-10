import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
public class Main {

    /* Disclaimer: This program will work until and unless no major change applied on myUCF portal. */


    static String YourEmailToGetNotified = "xyz@knights.edu";  // your emailID to get notified. (Knights Email Preferred. Tried and Tested)
                                                                        // Avoid using gmail otherwise mail will be gone into
                                                                        // spam folder as it is automated email.(In that you need
                                                                        // to whitelist this email : shishir47singh@zohomail.com
    static String SubjectCode = "CAP"; //3-Letter Subject Code
    static String CourseNumber = "5115"; // Mention Course Number
    static String YourUcfNID = "AAxxxxxx"; // Your Ucf NID
    static String YourUcfPassword = "yourpwd"; // Your myUCF Password. It is totally safe to use your password over here, as it is not sent anywhere.
    public static void main(String[] args) throws InterruptedException {
        checkAvailability();
    }

    public static void checkAvailability() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        try{
        Thread.sleep(5000);
        driver.get("https://my.ucf.edu/psp/IHPROD/EMPLOYEE/CSPROD/c/SA_LEARNER_SERVICES.SSS_STUDENT_CENTER.GBL?pt_fname=FX_STUDENT_SLFSRV_MENU_90&FolderPath=PORTAL_ROOT_OBJECT.FX_STUDENT_SLFSRV_MENU_90&IsFolder=true");
        Thread.sleep(5000);

        WebElement username=driver.findElement(By.xpath("/html/body/div[@class='container']/div[@class='row'][2]/div[@class='col-md-5']/div[@class='panel panel-default']/div[@class='panel-body']/form/div[@class='form-group'][1]/input[@id='username']"));
        username.sendKeys(YourUcfNID);
        WebElement password=driver.findElement(By.xpath("/html/body/div[@class='container']/div[@class='row'][2]/div[@class='col-md-5']/div[@class='panel panel-default']/div[@class='panel-body']/form/div[@class='form-group'][2]/input[@id='password']"));
        password.sendKeys(YourUcfPassword);
        WebElement signin=driver.findElement(By.xpath("/html/body/div[@class='container']/div[@class='row'][2]/div[@class='col-md-5']/div[@class='panel panel-default']/div[@class='panel-body']/form/div[@class='form-element-wrapper']/button[@class='btn btn-primary btn-block btn-lg']"));
        Thread.sleep(5000);
        signin.click();
        Thread.sleep(5000);
        driver.switchTo().frame("ptifrmtgtframe");
        WebElement search=driver.findElement(By.xpath("//*[@id=\"DERIVED_SSS_SCR_SSS_LINK_ANCHOR1\"]"));
        search.click();
        Thread.sleep(5000);
        WebElement subject=driver.findElement(By.id("SSR_CLSRCH_WRK_SUBJECT$0"));
        subject.sendKeys(SubjectCode);
        Thread.sleep(5000);
        WebElement courseNumber=driver.findElement(By.xpath("//*[@id=\"SSR_CLSRCH_WRK_CATALOG_NBR$1\"]"));
        courseNumber.sendKeys(CourseNumber);
        Thread.sleep(5000);

        WebElement courseCareerElement = driver.findElement(By.name("SSR_CLSRCH_WRK_ACAD_CAREER$3"));
        Actions actions = new Actions(driver);
        actions.moveToElement(courseCareerElement).click().sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.RETURN).perform();
        Thread.sleep(5000);
        WebElement searchBtn=driver.findElement(By.xpath("//*[@id=\"CLASS_SRCH_WRK2_SSR_PB_CLASS_SRCH\"]"));
        searchBtn.click();
        Thread.sleep(5000);
        } catch (Exception e1){
            throw (e1);
        }

        try{
            if (driver.findElement(By.xpath("//*[@id=\"DERIVED_CLSMSG_ERROR_TEXT\"]"))!= null) {
                driver.close();
                driver.quit();
                checkAvailability();
            }
        }catch (Exception e2) {
            sendEmail.sendingEmail(SubjectCode , CourseNumber);
            driver.close();
            driver.quit();
        }
    }
}

