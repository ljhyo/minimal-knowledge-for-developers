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

public class myClass extends Exception {
    public static void main(String[] args) {
        try {
            /*
             * 예외가 발생할 수 있는 프로그램 코드 영역
             */
        } catch (Exception e) {
            /*
             * 예외 발생시 예외를 처리할 코드 영역
             */
        } finally {
            /*
             * 예외 발생과 관계 없이 반드시 수행되어야 하는 코드 영역
             */
        }
    }
}