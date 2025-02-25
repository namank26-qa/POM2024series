package com.qa.opencart.utility;

import java.io.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/opencarttestdata.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	private static Object testdata[][];

	public static Object[][] getTestData(String sheetName) {

		try {
			// connect file stream with test data path
			FileInputStream fis = new FileInputStream(TEST_DATA_SHEET_PATH);
			// create workbook using the filestream
			book = WorkbookFactory.create(fis);
			// pass the sheet into excel workbook
			sheet = book.getSheet(sheetName);

			// read data from sheet
			int totalRowIndex = sheet.getLastRowNum();
			int totalColumnIndex = sheet.getRow(0).getLastCellNum();
			testdata = new Object[totalRowIndex][totalColumnIndex]; // [last row][last cell value from zeroth row]

			// fill data in each row
			for (int i = 0; i < totalRowIndex; i++) { // iterating on total rows from index = 1 becoz 0th row is header;
														// to get testdata index
				// iterate
				for (int j = 0; j < totalColumnIndex; j++) { // iterating to get for different values of test data
					testdata[i][j] = sheet.getRow(i + 1).getCell(j).toString(); // testdata[0][1] = testdatasheet[1][]
																				// => productname
																				// testdata[0][2] = testdatasheet[1][2]
																				// => productType

				}
			}

		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testdata;

	}
}
