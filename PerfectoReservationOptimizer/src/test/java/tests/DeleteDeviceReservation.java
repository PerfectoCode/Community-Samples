package tests;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import utils.PerfectoReservationActivity;

public class DeleteDeviceReservation {

	//Enter your cloud Name
	static String cloudName = "MyCloud.perfectomobile.com";
	 
    static String securityToken = "Enter your Security Token";
      
public static void main(String args[] ) throws Exception, IOException, ParserConfigurationException, SAXException, ParseException{
         
    PerfectoReservationActivity activity = new PerfectoReservationActivity(cloudName, securityToken);
 
    //Either set time for inactive to release the reservations. If not set, 31 minutes is default
    activity.setTimings(45);
     
    
    //Delete Device Reservation for specified time.
    activity.deleteUnusedReservations();
            
    System.out.println("Deleted Unused Device reservation for the specified time");
}
}
