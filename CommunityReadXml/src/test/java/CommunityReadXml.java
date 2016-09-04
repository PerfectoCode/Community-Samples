
/**
 * @author Lee Shoham
 * @date Aug 9, 2016
 */

import java.io.IOException; 
import java.net.MalformedURLException;
import java.net.URL;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommunityReadXml {

	public static void main(String[] args) throws MalformedURLException, IOException {
		System.out.println("Run started");

		String host = "cloud.perfectomobile.com";
		DesiredCapabilities capabilities = PerfectoHelpers.setUp("mobileOS", "user@perfectomobile.com", "password",
				"Android", "PerfectoMobile");
		RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"),
				capabilities);

		try {
			// Navigating to website
			driver.get("https://www.national-lottery.co.uk/games/lotto");

			// Getting location of wanted element to scroll to and scrolling
			// down
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Add lines")));
			Point elem = driver.findElement(By.linkText("Add lines")).getLocation();
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + (elem.getY() - 300) + ");");

			// Get the DOM Builder Factory
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

			// Get the DOM Builder
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

			// Load and Parse the XML document
			// document contains the complete XML as a Tree
			Document doc = docBuilder.parse(new File("lottery.xml"));

			// Normalize text representation
			doc.getDocumentElement().normalize();

			// Get a NodeList of all row nodes
			NodeList listOfRows = doc.getElementsByTagName("row");

			// Iterating through rows
			for (int s = 0; s < listOfRows.getLength(); ++s) {

				// Get current row node
				Node rowNode = listOfRows.item(s);
				if (rowNode instanceof Element) {

					Element rowElement = (Element) rowNode;

					// Get list of current row's child val nodes
					NodeList listOfValsInRow = rowElement.getElementsByTagName("val");

					// Iterating through val nodes, creating xpath and inserting
					// data to input fields by extracting data
					for (int i = 0; i < listOfValsInRow.getLength(); ++i) {
						if (listOfValsInRow.item(i) instanceof Element) {

							Element valInRow = (Element) listOfValsInRow.item(i);

							String xpath = "(//input[@id='lotto_playslip_line_" + rowElement.getAttribute("id")
									+ "_pool_0_col_" + i + "'])[1]";
							driver.findElement(By.xpath(xpath)).sendKeys(valInRow.getTextContent());
						}
					}
				}
			}
		} catch (SAXException ec) {
			Exception x = ec.getException();
			((x == null) ? ec : x).printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {

				driver.close();
				// Download a pdf version of the execution report
				PerfectoHelpers.downloadReport(driver, "pdf", "C:\\Users\\user\\Desktop\\report");

			} catch (Exception e) {
				e.printStackTrace();
			}

			driver.quit();
		}

		System.out.println("Run ended");
	}
}
