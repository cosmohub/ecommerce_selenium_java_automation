package practiceautomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import practiceautomation.shoppingApplication.LoginPage;

public class BaseTest {
	public WebDriver driver;
	Properties prop = new Properties();
	public LoginPage LoginPage;

	public void initializeDriver() throws IOException {
		FileInputStream filePath = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\practiceautomation\\testdata\\Globaldata.properties");
		prop.load(filePath);
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions opt = new EdgeOptions();
			opt.addArguments("--guest");
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();

	}
	
	@BeforeMethod
	public LoginPage launchApplication() throws IOException {
        initializeDriver();
		String url = prop.getProperty("url");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		LoginPage = new LoginPage(driver);
		return LoginPage;
	
	}
	
    @AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
	}
	
	public String takeScreenshot(String fileName, WebDriver driver) throws IOException {
		String date = new SimpleDateFormat("dd-MM-YYYY_hms").format(new Date());
		String path = System.getProperty("user.dir")+"\\Screenshots\\"+fileName+"_"+date+".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(source, dest);
		return path;
	}
}
