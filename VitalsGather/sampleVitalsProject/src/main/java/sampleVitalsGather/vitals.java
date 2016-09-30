package sampleVitalsGather;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import vitalsgatherer.VitalsGather;

public class vitals {

	

	public static void main(String[] args) throws XPathExpressionException, IOException, URISyntaxException,
			ParserConfigurationException, SAXException {
		VitalsGather vg = new VitalsGather();
		
		// @define the results hashMap to store the results from the gather process
		// @fileLocation, define the path where you want the csv or json file stored
		// @fileName, define the name of the csv or json file stored in the location
		// @dt, define the date/time offset to read from
		Map<VitalsGather.resultOptions, String> results = new HashMap<VitalsGather.resultOptions, String>();
		String fileLocation = "d:\\data\\";
		String fileName = "data";
		Date dt = new Date("09/21/2016 11:20:17");
		
		//@0 ensure you store the return value of getVitals in the results hashmap that you defined above
		//@1 your host name cloud.perfectomobile.com
		//@2 your user name user@perfectomobile.com
		//@3 your password password1234
		//@4 scriptKey (optional) Public:MyScripts/DoTransaction -- location of the script on the perfecto cloud if NATIVE this will isolate
		// the lookup of execution specifically to this test and not target EVERYTHING
		//@5 pass the above dt variable to this parameter, this is the start time to read from
		//@6 offSet (optional) read more about offset here https://community.perfectomobile.com/series/19955/posts/940718
		//@7 time.type available as enum VitalsGather.availableTimeTypes read more here https://community.perfectomobile.com/series/19955/posts/940718
		//@8 fileName variable from above passed here
		//@9 fileLocation variable from above passed here
		//@10 should we also output a Json file as boolean
		//@11 should we overwrite the files found if the name are the same as boolean (if false we append to the file)
		results = vg.getVitals("yourcloud.perfectomobile.com", "yourUsername@whereever.com", "yourpassword", "", dt, "",
				VitalsGather.availableTimeTypes.started, fileName, fileLocation, true, true);


		//all results are available as enum values VitalsGather.resultOptions... you can check the enum to see what
		//options are available, example usage is below
		results.get(VitalsGather.resultOptions.statusAndroid).equals("success");
		
	}
}
