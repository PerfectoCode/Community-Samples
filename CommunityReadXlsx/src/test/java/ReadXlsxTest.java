
/**
 * @author Lee Shoham 
 * @date Aug 8, 2016
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReadXlsxTest {

	public static void main(String[] args) throws MalformedURLException, IOException {
		System.out.println("Run started");

		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		String host = "cloud.perfectomobile.com";
		capabilities.setCapability("user", "user@perfectomobile.com");
		capabilities.setCapability("password", "password");

		// TODO: Change your device ID
		capabilities.setCapability("deviceName", "device#");

		// Use the automationName capability to define the required framework -
		// Appium (this is the default) or PerfectoMobile.
		capabilities.setCapability("automationName", "PerfectoMobile");

		RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"),
				capabilities);

		try {
			// Navigating to website
			driver.get("https://www.national-lottery.co.uk/games/lotto");

			// Getting location of wanted element to scroll to and scrolling
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Add lines")));
			Point elem = driver.findElement(By.linkText("Add lines")).getLocation();
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + (elem.getY() - 300) + ");");

			// Read file from file system in Java
			File table = new File("lottery.xlsx");
			FileInputStream fileInput = new FileInputStream(table);

			// Finds the workbook instance for XLSX file
			// In case of an xls file format, the correct workbook 
			// and sheet implementation to use is HSSF instead of 
			// XSSF. The rest is the same.
			XSSFWorkbook workBook = new XSSFWorkbook(fileInput);

			// Return first sheet from the XLSX workbook
			XSSFSheet sheet = workBook.getSheetAt(0);

			// Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();

			// Traversing over each row of XLSX file
			// Moving 1 row forward to skip table headers
			Row row = rowIterator.next();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String xpath = "(//input[@id='lotto_playslip_line_" + (row.getRowNum() - 1) + "_pool_0_col_"
							+ cell.getColumnIndex() + "'])[1]";
					driver.findElement(By.xpath(xpath)).sendKeys(String.valueOf(cell.getNumericCellValue()));
				}
			}
			
			fileInput.close();
			workBook.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				driver.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			driver.quit();
		}

		System.out.println("Run ended");
	}
}
