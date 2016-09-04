package com.excelReadTimer;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
* ExcelDriver Library.
* 
* <P>Running data-driven tests from Excel files.
*  
* @author Avner Gershtansky.
* @version 1.1
* 
*/

public class ExcelDriver {
	private String filePath = "";
	private String sheetName = "";
	private String resultColumn = "";
	
	private int testCycleColumnNumber;
	
	private XSSFWorkbook workbook;
	protected static  XSSFSheet sheet;

	private CreationHelper createHelper;
	
	public static Lock lock;
	
	public ExcelDriver(){
		if(lock == null){
			lock= new Lock();
		}
	}
	
	public ExcelDriver(String path, String sheetName, boolean addSheet) throws Exception{
		// Get Excel file path
	  	//this.filePath = new File("").getAbsolutePath();
		if(lock == null){
			lock= new Lock();
		}
	  	this.filePath = path;
	  
	  	// Open workbook
  	  	this.setWorkbook(this.filePath);
  	  	this.setSheet(sheetName, addSheet);
  	  	
		
	}
	// Open the Excel file in "path" and sets it as active workbook
//	public void setWorkbook(String path) throws Exception{
//		this.filePath = path;
//		
//		try{
//			FileInputStream inputFile = new FileInputStream(this.filePath);
//			this.workbook = new XSSFWorkbook(inputFile);
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	public void setWorkbook(String path) throws Exception{
		this.filePath = path;
		
		lock.lock();
		// Check if file exists
		File f = null;
		try{
			f = new File(this.filePath);
		}
		catch(Exception e){
			lock.unlock();
	    	e.printStackTrace();
		}
		if(!f.exists() || f.isDirectory()){
		    
		    try{
		    	this.workbook = new XSSFWorkbook();
		    	FileOutputStream fileOut = new FileOutputStream(this.filePath);
		    	this.workbook.write(fileOut);
		    	fileOut.close();
		    }
		    catch(Exception e){
		    	lock.unlock();
		    	e.printStackTrace();
		    }

		   
		}
		try{
			
			FileInputStream inputFile = new FileInputStream(this.filePath);
			this.workbook = new XSSFWorkbook(inputFile);
		}
		catch(Exception e){
			lock.unlock();
			e.printStackTrace();
		}
		lock.unlock();
	}
	

	// Save the active workbook to the same file,
	// with all the changes that were made.
	public void closeWorkbook() throws IOException{
			this.workbook.close();
	}
	
	// Writes the current worksheet to file
	protected void flushWorkbook() throws InterruptedException{
		try{
			FileOutputStream file = new FileOutputStream(this.filePath);
			this.workbook.write(file);
			file.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Saves active workbook to different file at <filePath>.
	public void saveWorkbookAs(String filePath){
		try{
			FileOutputStream file = new FileOutputStream(filePath);
			this.workbook.write(file);
			this.workbook.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	// Sets the active sheet by name
	public void setSheet(String sheetName, boolean addIfNotExists) throws Exception{
		this.sheetName = WorkbookUtil.createSafeSheetName(sheetName);
		
		lock.lock();
		try{
			this.refreshSheet();
		}
		catch(Exception e){
			e.printStackTrace();
			lock.unlock();
			return;
		}
		
		
		XSSFSheet tempSheet = this.workbook.getSheet(this.sheetName);
		if(tempSheet == null){
			if(addIfNotExists){
				this.sheet = this.workbook.createSheet(this.sheetName);
				XSSFRow row = this.sheet.createRow(0);
				if(this.createHelper == null){
					createHelper = this.workbook.getCreationHelper();
				}
				Cell cell = row.createCell(0);
				cell.setCellValue(createHelper.createRichTextString("Test Parameters"));

				this.flushWorkbook();
				lock.unlock();
			}
			else{
				System.out.println("Sheet '" + this.sheetName + "' doesn't exists"
						+ " and addSheet flag is false");
				lock.unlock();
				throw new Exception();
			}
		}
		else{
			this.sheet = this.workbook.getSheet(this.sheetName);
			lock.unlock();
		}
	}
	
	// Reads the sheet from file, to update any change
	// which was made in other thread.
	// Mostly for addCellPass() and addCellFail() functions,
	// So they don't overwrite other cell names which were
	// added during the test
	protected void refreshSheet() throws Exception{
		try{
			FileInputStream inputFile = new FileInputStream(this.filePath);
			this.workbook = new XSSFWorkbook(inputFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		this.sheet = this.workbook.getSheet(this.sheetName);
	}
	
	public void setResultColumn(String columnName, boolean addIfNotExists) throws Exception{
		
		boolean columnFound = false;
		int rowSize = 0;
		this.resultColumn = columnName;
		
		lock.lock();
		try{
			this.refreshSheet();
		}
		catch(Exception e){
			e.printStackTrace();
			lock.unlock();
			return;
		}
		// If sheet is not empty
		if(this.sheet.getPhysicalNumberOfRows() != 0 ){
			
			XSSFRow row = this.sheet.getRow(0);
			rowSize = row.getPhysicalNumberOfCells();
			for(Cell cell : row){
				if (cell.getStringCellValue().equals(this.resultColumn)){
					this.testCycleColumnNumber = cell.getColumnIndex();
					columnFound = true;
					lock.unlock();
					break;
				}
			}
		}
		if(!columnFound){
			if(addIfNotExists){
				XSSFRow row;
				Cell cell;
				if(rowSize == 0){
					row = this.sheet.createRow(0);
					cell = row.createCell(1);
					this.testCycleColumnNumber = 1;
				}
				else{
					cell = this.sheet.getRow(0).createCell(rowSize);
					this.testCycleColumnNumber = rowSize;
				}
				if(this.createHelper == null){
					createHelper = this.workbook.getCreationHelper();
				}
				
				cell.setCellValue(createHelper.createRichTextString(columnName));
				this.flushWorkbook();
				lock.unlock();
			}
			else{
				lock.unlock();
				System.out.println("Column '" + this.resultColumn + "' doesn't exists"
						+ " and addColumn flag is false");
				throw new Exception();
			}
		}
	}
	public void addColumnsFromMap(Map<String, String> map) throws Exception{
		
		lock.lock();
		try{
			this.refreshSheet();
			if(this.sheet.getPhysicalNumberOfRows() != 0 ){	
				XSSFRow row = this.sheet.getRow(0);
				boolean isColumnExist = false;
				int lastColumn = row.getPhysicalNumberOfCells();
				if(lastColumn == 1){
					lastColumn = 0;
				}
				
				for(Map.Entry<String, String> entry : map.entrySet()){
					for(Cell cell : row){
						if(entry.getKey().toLowerCase().equals(cell.toString().toLowerCase())){
							isColumnExist = true;
							break;
						}
					}
					if(!isColumnExist){
						setCellAsString(0, lastColumn, entry.getKey());
						lastColumn++;
					}
					isColumnExist = false;
				}
			}
			else{
				int lastColumn = 0;
				for(Map.Entry<String, String> entry : map.entrySet()){
					setCellAsString(0, lastColumn, entry.getKey());
					lastColumn++;
				}
			}
			this.flushWorkbook();
		}
		catch(Exception e){
			e.printStackTrace();
			lock.unlock();
			return;
		}
		lock.unlock();
	}
	
	public void addResultsToDetailedSheet(HashMap<String, String> properties)
			throws Exception{
		
		lock.lock();
		try{
			this.refreshSheet();
			int lastRow = this.sheet.getPhysicalNumberOfRows();
			for(Map.Entry<String, String> entry : properties.entrySet()){
				this.setCellByColName(lastRow, entry.getKey(), entry.getValue());
				if(entry.getKey().equals("screenshot")){
					int col = getColIndexByName(entry.getKey());
					addScreenshotByRowAndColumnIndexesAsLink(lastRow, col, entry.getValue());
				}
			}

			this.flushWorkbook();
		}
		catch(Exception e){
			e.printStackTrace();
			lock.unlock();
			return;
		}

		lock.unlock();
		
	}
	
	public void setResultByColumnName(boolean isPass, String... params) throws Exception{
		int resultRow = -1;
		String stepParams = "";
		
		
		if(this.resultColumn.equals("")){
			System.out.println("Test cycle is not set");
			throw new NoSuchFieldException();
		}
		for(String s : params){
			stepParams += s + ", ";
		}
		stepParams = stepParams.substring(0, stepParams.lastIndexOf(","));
		
		lock.lock();
		try{
			this.refreshSheet();
		}
		catch(Exception e){
			e.printStackTrace();
			lock.unlock();
			return;
		}
		int tempResultRow = findCellRowByValue(stepParams);
		
		// If step doesn't exsist in sheet
		if(tempResultRow == -1){
			resultRow = this.sheet.getPhysicalNumberOfRows();
			XSSFRow row = this.sheet.createRow(resultRow);
			if(this.createHelper == null){
				createHelper = this.workbook.getCreationHelper();
			}
			Cell cell = row.createCell(0);
			cell.setCellValue(createHelper.createRichTextString(stepParams));
			
			this.flushWorkbook();
		}
		else{
			resultRow = tempResultRow;
		}
		
		if(isPass){
			this.setPassByCell(resultRow, this.testCycleColumnNumber);
		}
		else{
			this.setFailByCell(resultRow, this.testCycleColumnNumber);
		}
		this.flushWorkbook();
		
		lock.unlock();
	}
	
	private int findCellRowByValue(String value){
		int rowNum;
		
		for(Row row : this.sheet){
			for(Cell cell : row){
				if(cell.getStringCellValue().equals(value)){
					rowNum = cell.getRowIndex();
					return rowNum;
				}
			}
		}
		return -1;
	}
	
	@SuppressWarnings("unused")
	private int findCellColumnByValue(String value){
		int colNum;
		
		for(Row row : this.sheet){
			for(Cell cell : row){
				if(cell.getStringCellValue().equals(value)){
					colNum = cell.getColumnIndex();
					return colNum;
				}
			}
		}
		return -1;
	}
	
	//@SuppressWarnings("unused")
	public int[] findCellByValue(String value){
		int[] cellCoordinates = new int[2];
		
		for(Row row : this.sheet){
			for(Cell cell : row){
				if(cell.getStringCellValue().equals(value)){
					cellCoordinates[0] = cell.getRowIndex();
					cellCoordinates[1] = cell.getColumnIndex();
					return cellCoordinates;
				}
			}
		}
		return null;
		
	}
	// Sets cell(row, col) with "FAIL" status
	public void setFailByCell(int row, int col){
		setCellAsString(row, col, "FAIL");
		setCellColor(row, col, CellColors.RED);
	}
	
	// Sets cell(row, col) with "PASS" status
	public void setPassByCell(int row, int col){
		setCellAsString(row, col, "PASS");
		setCellColor(row, col, CellColors.GREEN);
	}
	
	// Resets cell(row, col) including text and background color
	public void clearCell(int row, int col){
		setCellAsString(row, col, "");
		setCellColor(row, col, CellColors.WHITE);
	}
	
	// Sets the last cell in the row to "FAIL" status
	private void addCellFail(int row, String cellName) throws Exception{
		this.refreshSheet();
		int newCellIndex = this.sheet.getRow(0).getLastCellNum();
		setCellAsString(0, newCellIndex, cellName);
		setCellAsString(row, newCellIndex, "FAIL");
		setCellColor(row, newCellIndex, CellColors.RED);
	}
	
	//Sets the last cell in the row to "PASS" status
	private void addCellPass(int row, String cellName) throws Exception{
		this.refreshSheet();
		int newCellIndex = this.sheet.getRow(0).getLastCellNum();
		setCellAsString(0, newCellIndex, cellName);
		setCellAsString(row, newCellIndex, "PASS");
		setCellColor(row, newCellIndex, CellColors.GREEN);
	}
	
	@SuppressWarnings("unused")
	private void clearLastCell(int row){
		int lastCellIndex = this.sheet.getRow(row).getLastCellNum();
		setCellAsString(row, lastCellIndex, "");
		setCellColor(row, lastCellIndex, CellColors.WHITE);
	}
	
	// Sets the cell with column name = "Result" to "PASS"
	// the column name IS NOT case sensitive
	// The boolean addColumn is used in case there is no "Result" column,
	// then it will add the column if addColumn == true, or will do nothing
	// if addColumn == false
	public void setResultPass(int row, boolean addColumn) throws Exception{
		int col = -1;
		ArrayList<String> arr = this.getRow(0);
		for(String s : arr){
			if(s.toLowerCase().equals("result")){
				col = arr.indexOf(s);
				this.setPassByCell(row, col);
				return;
			}
		}
		if(col == -1 && addColumn == true){
			this.addCellPass(row, "Result");
		}
	}
	
	// Sets the cell with column name = "Result" to "FAIL"
	// the column name IS NOT case sensitive
	// The boolean addColumn is used in case there is no "Result" column,
	// then it will add the column if addColumn == true, or will do nothing
	// if addColumn == false
	public void setResultFail(int row, boolean addColumn) throws Exception{
		int col = -1;
		ArrayList<String> arr = this.getRow(0);
		for(String s : arr){
			if(s.toLowerCase().equals("result")){
				col = arr.indexOf(s);
				this.setFailByCell(row, col);
				return;
			}
		}
		if(col == -1 && addColumn == true){
			this.addCellFail(row, "Result");
		}
	}
	
	// Sets the cell with column name = <deviceModel> to "PASS"
	// the column name IS NOT case sensitive
	// The boolean addColumn is used in case there is no "Result" column,
	// then it will add the column if addColumn == true, or will do nothing
	// if addColumn == false
	public void setDevicePass(int row, String deviceModel, boolean addColumn) throws Exception{
		int col = -1;
		ArrayList<String> arr = this.getRow(0);
		for(String s : arr){
			if(s.toLowerCase().equals(deviceModel.toLowerCase())){
				col = arr.indexOf(s);
				this.setPassByCell(row, col);
				this.flushWorkbook();
				return;
			}
		}
		if(col == -1 && addColumn == true){
			this.addCellPass(row, deviceModel);
		}
		this.flushWorkbook();
	}
	
	// Sets the cell with column name = <deviceModel> to "FAIL"
	// the column name IS NOT case sensitive
	// The boolean addColumn is used in case there is no "Result" column,
	// then it will add the column if addColumn == true, or will do nothing
	// if addColumn == false
	public void setDeviceFail(int row, String deviceModel, boolean addColumn) throws Exception{
		int col = -1;
		ArrayList<String> arr = this.getRow(0);
		for(String s : arr){
			if(s.toLowerCase().equals(deviceModel)){
				col = arr.indexOf(s);
				this.setFailByCell(row, col);
				return;
			}
		}
		if(col == -1 && addColumn == true){
			this.addCellFail(row, deviceModel);
		}
		this.flushWorkbook();
	}
	
	// Returns an array of strings, by row number.
	// First row is 0
	public ArrayList<String> getRow(int rowNum){
		ArrayList<String> row = new ArrayList<String>();
		for(Cell cell : this.sheet.getRow(rowNum)){
			row.add(cell.getStringCellValue());
		}
		return row;
	}
	
	// Returns a 2-dim array of strings,
	// with all the data from the active sheet.
	// This function can be used as is, for the TestNG data provider.
	// Size of the row is based on the header row.
	
	public Object[][] getData(int numOfCols){
		int rowsCount = this.sheet.getLastRowNum();
		//int colsCount = this.sheet.getRow(0).getLastCellNum();
		
		// Remove empty lines (false POI result)
		boolean emptyFlag = true;
		for(int i = rowsCount; i>1; i--){
			for(int j = 0; j<10; j++){
				if(this.sheet.getRow(i).getCell(j) != null){
					emptyFlag = false;
				}
			}
			if(emptyFlag == true){
				rowsCount -= 1;
				emptyFlag = true;
			}
		}
		
		String[][] data = new String[rowsCount][numOfCols];
		for(int i = 1; i <= rowsCount; i++){
			for(int j = 0; j < numOfCols; j++){
				//String val = this.sheet.getRow(i).getCell(j).getStringCellValue();
				if(this.sheet.getRow(i).getCell(j) != null){
					
					//String val = this.sheet.getRow(i).getCell(j).getStringCellValue();
					String val = getCellAsString(i,j);
					data[i-1][j] = val;
				}
				else{
					data[i-1][j] = "";
				}
				
				//data[i-1][j] = (val != null) ? val : "";
			}
		}
		
		return data;
	}
	// Returns a 2-dim array of strings,
	// with all the data from the active sheet.
	// The first node in every line is the line number.
	// This function can be used as is, for the TestNG data provider.
	// Size of the row is based on the header row.
	
	public Object[][] getDataWithIndex(int numOfCols){
		int rowsCount = this.sheet.getLastRowNum();
		//int colsCount = this.sheet.getRow(0).getLastCellNum();
		
		// Remove empty lines (false POI result)
		// If column 1-3 are empty, row is considered empty
		for(int i = rowsCount; i>1; i--){
			if (this.sheet.getRow(i).getCell(0) == null && 
				this.sheet.getRow(i).getCell(1) == null && 
				this.sheet.getRow(i).getCell(2) == null){
				rowsCount -= 1;
			}
			else{
				break;
			}
		}
		
		String[][] data = new String[rowsCount][numOfCols + 1];
		for(int i = 1; i <= rowsCount; i++){
			data[i-1][0] = Integer.toString(i);
			for(int j = 0; j < numOfCols; j++){
				if(this.sheet.getRow(i).getCell(j) != null){
					//String val = this.sheet.getRow(i).getCell(j).getStringCellValue();
					String val = getCellAsString(i,j);
					data[i-1][j+1] = val;
				}
				else{
					data[i-1][j+1] = "";
				}
			}
		}
		
		return data;
	}
	
	// Returns a HashMap 
	public ArrayList<HashMap<String,String>> getDataWithHeadersAsHashMap(){
		
		int numOfCols = this.sheet.getRow(0).getLastCellNum();
		int rowsCount = this.sheet.getLastRowNum();
		
		ArrayList<String> headersArr = new ArrayList<String>();
		for(int i=0; i <= numOfCols; i++){
			headersArr.add(getCellAsString(0, i));
		}
		
		// Remove empty lines (false POI result)
		boolean emptyFlag = true;
		for(int i = rowsCount; i>1; i--){
			for(int j = 0; j<numOfCols; j++){
				if(this.sheet.getRow(i).getCell(j) != null){
					emptyFlag = false;
				}
			}
			if(emptyFlag == true){
				rowsCount -= 1;
				emptyFlag = true;
			}
		}
		ArrayList<HashMap<String,String>> listMap = new ArrayList<HashMap<String,String>>();
		for(int i = 1; i <= rowsCount; i++){
			HashMap<String,String> map = new HashMap<String,String>();
			for(int j = 0; j < numOfCols; j++){
				if(this.sheet.getRow(i).getCell(j) != null){
					String key = headersArr.get(j);
					String value = getCellAsString(i,j);
					if(!value.equals("")){
						map.put(key, value);
					}
				}
			}
			listMap.add(map);
		}
		return listMap;
	}
	
	public void addScreenshotByRowAndColumnIndexesAsLink(int row, int col, String scrLink){
		//link to a file in the file system
		
	    if(this.createHelper == null){
	    	createHelper = this.workbook.getCreationHelper();
	    }
	    this.createHelper.createHyperlink(Hyperlink.LINK_FILE);
	    Hyperlink link = this.createHelper.createHyperlink(Hyperlink.LINK_FILE);
	    scrLink = scrLink.replace("\\", "/");
	    link.setAddress(scrLink);
	    this.sheet.getRow(row).getCell(col).setHyperlink(link);
	    //cell.setCellStyle(hlink_style);
	}
	
	public void addScreenshotByRowNameAsLink(String scrLink, String... params) throws Exception{
		//link to a file in the file system
		
	    if(this.createHelper == null){
	    	createHelper = this.workbook.getCreationHelper();
	    }
	    String stepParams = "";
	    for(String s : params){
			stepParams += s + ", ";
		}
		stepParams = stepParams.substring(0, stepParams.lastIndexOf(","));
		
		lock.lock();
		try{
			this.refreshSheet();
		}
		catch(Exception e){
			e.printStackTrace();
			lock.unlock();
			return;
		}
	    int row = findCellRowByValue(stepParams);
	    if(row == -1){
	    	System.out.println("Result row isn't found");
	    	throw new NoSuchFieldException();
	    }
	    CreationHelper createHelper = workbook.getCreationHelper();
//	    XSSFCellStyle hlinkstyle = workbook.createCellStyle();
//	    XSSFFont hlinkfont = workbook.createFont();
//	    hlinkfont.setUnderline(XSSFFont.DEFAULT_CHARSET);
//	    hlinkfont.setColor(HSSFColor.BLACK.index);
//	    hlinkstyle.setFont(hlinkfont);
	      
	    String content = this.sheet.getRow(row).getCell(this.testCycleColumnNumber).toString();
	    XSSFCell cell = this.sheet.getRow(row).getCell(this.testCycleColumnNumber);
	    cell.setCellValue(content);
	    XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(Hyperlink.LINK_FILE);
	    //link.setAddress("C:\\Users\\AvnerG\\git\\Beton\\Beton\\test-output\\screenshots\\2015-07-27-02-04-41-IDT.png.png");
	    scrLink = scrLink.replace("\\", "/");
	    //scrLink = scrLink.replace(" ", "/ ");
	    scrLink = URLEncoder.encode(scrLink, "UTF-8");
	    //link.setAddress("C:/Users/AvnerG/git/Beton/Beton/test-output/screenshots/2015-07-27-02-04-41-IDT.png.png");
	    try{
	    	link.setAddress(scrLink);
		    cell.setHyperlink(link);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
//	    cell.setCellStyle(hlinkstyle);
	    this.flushWorkbook();
	    lock.unlock();
	      
	      
	   /*  
	    //this.createHelper.createHyperlink(Hyperlink.LINK_FILE);
	    Hyperlink link = this.createHelper.createHyperlink(Hyperlink.LINK_FILE);
	    //XSSFHyperlink link=new XSSFHyperlink(XSSFHyperlink.LINK_FILE);
	    link.setAddress("file:///c://test.csv");
	    //link.setAddress("file:///" + scrLink);
	    this.sheet.getRow(row).getCell(this.testCycleColumnNumber).setHyperlink(link);
	    //cell.setCellStyle(hlink_style);
	     * 
	     * */
	}
	
	// Returns a string from cell (row,col).
	// Top left corner is (0,0)
	public String getCellAsString(int row, int col){
		Cell cell = this.sheet.getRow(row).getCell(col);

		if (cell!=null) {
		    switch (cell.getCellType()) {
		        case Cell.CELL_TYPE_BOOLEAN:
		        	return String.valueOf(cell.getBooleanCellValue());
		            //System.out.println(cell.getBooleanCellValue());
		        case Cell.CELL_TYPE_NUMERIC:
		        	return String.valueOf(cell.getNumericCellValue());
		            //System.out.println(cell.getNumericCellValue());
		        case Cell.CELL_TYPE_STRING:
		        	return cell.getStringCellValue();
		            //System.out.println(cell.getStringCellValue());
		        case Cell.CELL_TYPE_BLANK:
		            return "";
		        case Cell.CELL_TYPE_ERROR:
		        	return String.valueOf(cell.getErrorCellValue());
		            //System.out.println(cell.getErrorCellValue());
		        case Cell.CELL_TYPE_FORMULA: 
		            break;
		    }
		}
		return null;
	}
	
	// Returns a string from cell by row number and column name.
	public String getCellByColNameAsString(int row, String colName){
		int col = this.getColIndexByName(colName);
		return this.sheet.getRow(row).getCell(col).getStringCellValue();
	}
	
	// Returns the index of the column, with the header = colName
	public int getColIndexByName(String colName){
		ArrayList<String> colNames = this.getRow(0);
		return(colNames.indexOf(colName));
	}
	
	public void setCellByColName(int row, String colName, String cellVal){
		if(this.createHelper == null){
			createHelper = this.workbook.getCreationHelper();
		}
		int col = this.getColIndexByName(colName);
		
		if(col == -1){
			int newCellIndex = this.sheet.getRow(0).getLastCellNum();
			setCellAsString(0, newCellIndex, colName);
			col = newCellIndex;
		}
		
		if(this.sheet.getRow(row) == null){
			this.sheet.createRow(row);
		}
		Cell cell = this.sheet.getRow(row).createCell(col);
		cell.setCellValue(createHelper.createRichTextString(cellVal));
		
	}
	public void setCellAsString(int row, int col, String cellVal){
		if(this.createHelper == null){
			createHelper = this.workbook.getCreationHelper();
		}
		Cell cell = this.sheet.getRow(row).createCell(col);
		cell.setCellValue(createHelper.createRichTextString(cellVal));
	}
	
	public void setCellColor(int row, int col, CellColors color){
		 	CellStyle style = this.workbook.createCellStyle();
		 	switch(color){
			 	case WHITE:
			 		style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			 		break;
			 	case YELLOW:
			 		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			 		break;
			 	case RED:
			 		style.setFillForegroundColor(IndexedColors.RED.getIndex());
			 		break;
			 	case GREEN:
			 		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			 		break;
		 	}
		    
		    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		    this.sheet.getRow(row).getCell(col).setCellStyle(style);
		    //Cell cell = this.sheet.getRow(row).createCell(col);
		    //cell.setCellStyle(style);
	}
	public enum CellColors{
		WHITE, YELLOW, RED, GREEN
	}
	
	public void setAutoSize() throws Exception{

		if(this.sheet.getPhysicalNumberOfRows() != 0){
			lock.lock();
			try{
				this.refreshSheet();
			}
			catch(Exception e){
				e.printStackTrace();
				lock.unlock();
				return;
			}
			Row row = this.sheet.getRow(this.sheet.getFirstRowNum());
			for(Cell cell : row){
				this.sheet.autoSizeColumn(cell.getColumnIndex());
			}
			this.flushWorkbook();
			lock.unlock();
		}
	}
}
