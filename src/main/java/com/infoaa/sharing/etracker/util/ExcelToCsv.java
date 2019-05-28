package com.infoaa.sharing.etracker.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infoaa.sharing.etracker.model.MemberExpense;
import com.infoaa.sharing.etracker.service.CategoryService;
import com.infoaa.sharing.etracker.service.MemberExpenseService;
import com.infoaa.sharing.etracker.service.MemberService;

@Service
public class ExcelToCsv
{
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberExpenseService memberExpenseService;

	@Value("${formattedExcelOutput}")
	private String formattedExcelOutput;

	@Value("${exceltocsv}")
	private String exceltocsv;

	public void xls(File inputFile, File outputFile)
	{
		// For storing data into CSV files
		StringBuffer data = new StringBuffer();
		try
		{
			FileOutputStream fos = new FileOutputStream(outputFile);

			// Get the workbook object for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputFile));
			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			Cell cell;
			Row row;

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{
				row = rowIterator.next();
				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext())
				{
					cell = cellIterator.next();

					switch (cell.getCellType())
					{
					case Cell.CELL_TYPE_BOOLEAN:
						data.append(cell.getBooleanCellValue() + ",");
						break;

					case Cell.CELL_TYPE_NUMERIC:
						data.append(cell.getNumericCellValue() + ",");
						/*Date javaDate= DateUtil.getJavaDate(42804.0);
						System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(javaDate));*/
						break;

					case Cell.CELL_TYPE_STRING:
						data.append(cell.getStringCellValue() + ",");
						break;

					case Cell.CELL_TYPE_BLANK:
						data.append("" + ",");
						break;

					default:
						data.append(cell + ",");
					}


				}
				data.append('\n');
			}
			fos.write(data.toString().getBytes());
			fos.close();


		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public  void cleanCSV(File InputFile) throws IOException{
		FileReader fr = new FileReader(InputFile);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(formattedExcelOutput);
		String line;

		while((line = br.readLine()) != null)
		{

			int count = 0;
			for( int i=0; i<line.length(); i++ ) {
				if( line.charAt(i) == ',' ) {
					count++;
				}
			}
			System.out.println(count);
			line = line.trim(); // remove leading and trailing whitespace
			if (count<5) // don't write out blank lines
			{
				continue;
			}
			else{
				fw.write(line+"\n");

			}
		}
		fr.close();
		fw.close();
	}

	public  void convertCsvToObject(File InputFile,String memberName){

		//List<MemberExpense> expenseList = new ArrayList<MemberExpense>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try{
			br = new BufferedReader(new FileReader(InputFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
				MemberExpense expense = new MemberExpense();
				// use comma as separator
				String[] array = line.split(cvsSplitBy);

				for (String string : array) {
					System.out.println(string);
				}
				expense.setExpenseName(array[0]);
				Double date=  Double.parseDouble(array[1]);
				Date javaDate= DateUtil.getJavaDate(date);
				// System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(javaDate));
				expense.setCreatedDate(javaDate);
				expense.setComments(array[2]);
				System.out.println(array[3]);
				System.out.println(array[3].trim());
				expense.setCategoryId(categoryService.saveOrgetCategory(array[3]));
				Double amount=  Double.parseDouble(array[4]);
				expense.setAmount(amount);
				expense.setMemberId(memberService.getMemberIdByName(memberName));

				memberExpenseService.saveMemberExpense(expense);
				//expenses.add(expense);
			}



		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public  void caller(File inputFile, String memberName) throws IOException
	{
		//File inputFile = new File("C:/test/Download/excel.xls");
		File outputFile = new File(exceltocsv);
		xls(inputFile, outputFile);
		cleanCSV(outputFile);
		File formatted = new File(formattedExcelOutput);
		convertCsvToObject(formatted, memberName);
	}
}