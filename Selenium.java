import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

public class Selenium {
    
    public static void main(String[] args) throws InterruptedException {
    
        WebDriver driver = new ChromeDriver();
        driver.get("웹사이트 주소");
        
        driver.findElement(By.id("id")).sendKeys("아이디");
        driver.findElement(By.id("pw")).sendKeys("비밀번호");
        driver.findElement(By.id("login")).click();
        
        driver.wait(10000);
        driver.quit();
    }
}