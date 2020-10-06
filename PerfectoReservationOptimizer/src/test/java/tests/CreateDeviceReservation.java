package tests;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.xml.sax.SAXException;

import utils.PerfectoReservationActivity;

public class CreateDeviceReservation {
	
	// Enter your cloud Name
	static String cloudName = "Mycloud.perfectomobile.com";
	 
	//Enter your security token
    static String securityToken = "Enter your security Token";
  
public static void main(String args[] ) throws Exception, IOException, ParserConfigurationException, SAXException, ParseException{
     
    DesiredCapabilities caps = new DesiredCapabilities();
    // you could hardcode the deviceID if you want to reserve a specific device
    //caps.setCapability("deviceName", "R38M604TNBZ");
    
    //or use Regex to reserve any available devices with capabilities that you provide, below i am trying to reserve any available iOS devices with platform version 13
    
   caps.setCapability("platformName", "iOS");
   caps.setCapability("platformVersion", "13.*");
 
     
    PerfectoReservationActivity activity = new PerfectoReservationActivity(cloudName, securityToken);
 
    List<String> listofAvailableDevices = activity.getAvailableDevices(caps);
     
    String delim = ",";
 
    StringBuilder sb = new StringBuilder();
 
    int i = 0;
    while (i < listofAvailableDevices.size() - 1) {
        sb.append(listofAvailableDevices.get(i));
        sb.append(delim);
        i++;
    }
    sb.append(listofAvailableDevices.get(i));
 
    String availableDevices = sb.toString();
     
    System.out.println("List of Available Devices "+ availableDevices);
     
    activity.reserveDeviceNow(availableDevices, 60);
    
    System.out.println("Reservation completed for the Specified time");
         
}
}
