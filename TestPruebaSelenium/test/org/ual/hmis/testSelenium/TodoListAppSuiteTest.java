package org.ual.hmis.testSelenium;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class TodoListAppSuiteTest {
	private WebDriver driver;
	  private Map<String, Object> vars;
	  private int browser= 0;
	  JavascriptExecutor js;
	  @Before
	  public void setUp() {
		  switch (browser) {
		    case 0:  // firefox
		    	// Firefox
		    	// Descargar geckodriver de https://github.com/mozilla/geckodriver/releases
		    	// Descomprimir el archivo geckodriver.exe en la carpeta drivers
		    	
//		    	System.setProperty("webdriver.gecko.driver",  "drivers/geckodriver.exe"); //HAY QUE COMENTAR ESTO EN JENKINS
		    	driver = new FirefoxDriver();
		    	break;
		    case 1: // chrome
		    	// Chrome
		    	// Descargar Chromedriver de https://chromedriver.chromium.org/downloads
		    	// Descomprimir el archivo chromedriver.exe en la carpeta drivers

//		    	System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); //HAY QUE COMENTAR ESTO EN JENKINS
		    	driver = new ChromeDriver();
		    	break;

		    default:
		    	fail("Please select a browser");
		    	break;
		    }

		//Chrome
//	    driver = new ChromeDriver();
//	    ChromeOptions chromeOptions = new ChromeOptions();
//	    chromeOptions.setHeadless(true);
//	    driver = new ChromeDriver(chromeOptions);
	    
	    //Firefox
//	    driver = new FirefoxDriver();
//	    FirefoxOptions firefoxOptions = new FirefoxOptions();
//	    firefoxOptions.setHeadless(true);
//	    driver = new FirefoxDriver(firefoxOptions);
	    js = (JavascriptExecutor) driver;
	    vars = new HashMap<String, Object>();
	  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void testCrearTarea() {
    // Test name: TestCrearTarea
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://coresqlfernandezcamacho.azurewebsites.net/");
    // 2 | setWindowSize | 1080x824 | 
    driver.manage().window().setSize(new Dimension(1080, 824));
    // 3 | click | linkText=Create New | 
    driver.findElement(By.linkText("Create New")).click();
    // 4 | click | id=Description | 
    driver.findElement(By.id("Description")).click();
    // 5 | type | id=Description | Tarea1
    driver.findElement(By.id("Description")).sendKeys("Tarea1");
    // 6 | click | id=CreatedDate | 
    driver.findElement(By.id("CreatedDate")).click();
    // 7 | type | id=CreatedDate | 2022-06-17
    driver.findElement(By.id("CreatedDate")).sendKeys("2022-06-17");
    // 8 | click | css=.btn | 
    driver.findElement(By.cssSelector(".btn")).click();
    // 9 | assertElementPresent | //td[normalize-space(text()) = 'Tarea1'] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//td[normalize-space(text()) = \'Tarea1\']"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void testCrearTareaCasoIncorrecto() {
    // Test name: TestCrearTareaCasoIncorrecto
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://coresqlfernandezcamacho.azurewebsites.net/");
    // 2 | setWindowSize | 1080x824 | 
    driver.manage().window().setSize(new Dimension(1080, 824));
    // 3 | click | linkText=Create New | 
    driver.findElement(By.linkText("Create New")).click();
    // 4 | click | css=.btn | 
    driver.findElement(By.cssSelector(".btn")).click();
    // 5 | assertElementPresent | id=CreatedDate-error | 
    {
      List<WebElement> elements = driver.findElements(By.id("CreatedDate-error"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void testListarTarea() {
    // Test name: TestListarTarea
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://coresqlfernandezcamacho.azurewebsites.net/");
    // 2 | assertElementPresent | //td[normalize-space(text()) = 'Tarea1'] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//td[normalize-space(text()) = \'Tarea1\']"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void testListarTareaInexistente() {
    // Test name: TestListarTareaInexistente
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://coresqlfernandezcamacho.azurewebsites.net/");
    // 2 | assertElementNotPresent | //td[normalize-space(text()) = 'inexistente'] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//td[normalize-space(text()) = \'inexistente\']"));
      assert(elements.size() == 0);
    }
  }
  @Test
  public void editarTareaCasoBackToList() {
    // Test name: EditarTareaCasoBackToList
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://coresqlfernandezcamacho.azurewebsites.net/");
    // 2 | setWindowSize | 1080x824 | 
    driver.manage().window().setSize(new Dimension(1080, 824));
    // 3 | click | linkText=Edit | 
    driver.findElement(By.linkText("Edit")).click();
    // 4 | click | linkText=Back to List | 
    driver.findElement(By.linkText("Back to List")).click();
    // 5 | assertElementPresent | //td[normalize-space(text()) = 'Tarea1'] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//td[normalize-space(text()) = \'Tarea1\']"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void editarTareaCasoCorrecto() {
    // Test name: EditarTareaCasoCorrecto
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://coresqlfernandezcamacho.azurewebsites.net/");
    // 2 | setWindowSize | 1080x824 | 
    driver.manage().window().setSize(new Dimension(1080, 824));
    // 3 | click | linkText=Edit | 
    driver.findElement(By.linkText("Edit")).click();
    // 4 | click | css=.col-md-4 | 
    driver.findElement(By.cssSelector(".col-md-4")).click();
    // 5 | type | id=Description | Tarea2
    driver.findElement(By.id("Description")).sendKeys("Tarea2");
    // 6 | click | id=CreatedDate | 
    driver.findElement(By.id("CreatedDate")).click();
    // 7 | type | id=CreatedDate | 2022-06-18
    driver.findElement(By.id("CreatedDate")).sendKeys("2022-06-18");
    // 8 | click | css=.btn | 
    driver.findElement(By.cssSelector(".btn")).click();
    // 9 | assertElementPresent | //td[normalize-space(text()) = 'Tarea2'] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//td[normalize-space(text()) = \'Tarea2\']"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void testEliminarTareaBackToList() {
    // Test name: TestEliminarTareaBackToList
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://coresqlfernandezcamacho.azurewebsites.net/");
    // 2 | setWindowSize | 1080x824 | 
    driver.manage().window().setSize(new Dimension(1080, 824));
    // 3 | click | linkText=Delete | 
    driver.findElement(By.linkText("Delete")).click();
    // 4 | click | linkText=Back to List | 
    driver.findElement(By.linkText("Back to List")).click();
    // 5 | assertElementPresent | //td[normalize-space(text()) = "Tarea2"] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//td[normalize-space(text()) = \"Tarea2\"]"));
      assert(elements.size() > 0);
    }
  }
  @Test
  public void testEliminarTarea() {
    // Test name: TestEliminarTarea
    // Step # | name | target | value
    // 1 | open | / | 
    driver.get("https://coresqlfernandezcamacho.azurewebsites.net/");
    // 2 | setWindowSize | 1080x824 | 
    driver.manage().window().setSize(new Dimension(1080, 824));
    // 3 | click | linkText=Delete | 
    driver.findElement(By.linkText("Delete")).click();
    // 4 | click | css=.btn | 
    driver.findElement(By.cssSelector(".btn")).click();
    // 5 | assertElementNotPresent | //td[normalize-space(text()) = "Tarea2"] | 
    {
      List<WebElement> elements = driver.findElements(By.xpath("//td[normalize-space(text()) = \"Tarea2\"]"));
      assert(elements.size() == 0);
    }
  }
}
