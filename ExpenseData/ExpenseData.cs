using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;

namespace ExpMgr_Data
{
    class ExpenseData
    {
	    private StreamReader dataIn;
	    private String _currExp;
	    private String _currAmnt;
        private string p;
	
	    public ExpenseData (String fileN) {
		
		    try {
			    dataIn = new StreamReader(fileN);
		    } catch (IOException e) {
			    Trace.WriteLine("Could not find the data file!");
			    throw new FileNotFoundException();
		    }
	    }


	    public Boolean NextRecord() {
		    String nextLine;
		    int numBytes;

		    // if finished reading return false
            if (dataIn.EndOfStream)
                return false;
		    // code should read next two strings from the input Stream

		    nextLine = dataIn.ReadLine();
		    if (nextLine != string.Empty) {
				numBytes = nextLine.IndexOf(':');
				_currExp = nextLine.Substring(0, numBytes);
				_currAmnt = nextLine.Substring(numBytes + 1);
				return true;
			} else {
				_currExp = null;
				_currAmnt = null;
				return false;
			}
	    }
	
	    public String Get_expDesc() {
		    return _currExp;
	    }

	    public String Get_expAmount() {
		    return _currAmnt;
	    }
    }
}
