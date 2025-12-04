//    package org.example.backend.E2E;
//
//    import org.junit.jupiter.api.AfterEach;
//    import org.junit.jupiter.api.BeforeEach;
//    import org.junit.jupiter.api.Test;
//
//    import org.openqa.selenium.By;
//    import org.openqa.selenium.WebDriver;
//    import org.openqa.selenium.WebElement;
//    import org.openqa.selenium.chrome.ChromeDriver;
//    import org.openqa.selenium.chrome.ChromeOptions;
//
//    import static org.junit.jupiter.api.Assertions.assertTrue;
//
//    class PersoonE2ETest {
//
//        @BeforeEach
//        void setUp() {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-dev-shm-usage");
//            driver = new ChromeDriver(options);
//        }
//
//
//        private WebDriver driver;
//
//        @AfterEach
//        void tearDown() {
//            if (driver != null) {
//                driver.quit();
//            }
//        }
//
//        @Test
//        void testRegisterPersoon_viaFrontend() {
//            driver.get("http://localhost:5173/register");
//
//            driver.findElement(By.name("username")).sendKeys("john_doe");
//            driver.findElement(By.name("age")).sendKeys("30");
//            driver.findElement(By.name("password")).sendKeys("Banaan12");
//            driver.findElement(By.name("email")).sendKeys("john@example.com");
//
//            WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
//            submitButton.click();
//
//            assertTrue(driver.getPageSource().contains("john_doe"));
//        }
//    }
