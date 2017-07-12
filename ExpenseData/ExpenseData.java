import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExpenseData {
	private FileReader dataSource;
	private BufferedReader dataIn;
	private String _currExp;
	private String _currAmnt;
	
	ExpenseData (String file) throws FileNotFoundException {
		
		try {
			dataSource = new FileReader(file);
			dataIn = new BufferedReader(dataSource);
		} catch (IOException e) {
			System.out.println("Could not find the data file!");
			throw new FileNotFoundException();
		}
	}
	
	public boolean nextRecord() {
		String nextLine;
		int numBytes;
		
		// code should read next two strings from the input Stream
		try {
			nextLine = dataIn.readLine();
			if (nextLine != null) {
				numBytes = nextLine.indexOf(':');
				_currExp = nextLine.substring(0, numBytes);
				_currAmnt = nextLine.substring(numBytes + 1, nextLine.length());
				return true;
			} else {
				_currExp = null;
				_currAmnt = null;
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public String get_expDesc() {
		return _currExp;
	}

	public String get_expAmount() {
		return _currAmnt;
	}
	
}
