package utilities;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Input {

	static Log log = new Log();
	static XSSFSheet oSheet;
	int Input_pointer;
	FrameworkWorksheet oFrmwrk;
	public static Input oInput = new Input();

	// static private Result result = Result.getInstance();
	public Input() {
		try {
			oFrmwrk = FrameworkWorksheet.getInstance();
			Input_pointer = 1;

			oSheet = oFrmwrk.getWorkbook().getSheetAt(0);

			// print("Execution Log..","");
		} catch (Exception e) {
			Log.error("Unable to read test data ..." + e.getMessage());
		}

	}

	public static Input getInstance() {

		return oInput;

	}

	public String ReadGlobal(String varName) {
		XSSFRow oRow;
		int pointer = 1;
		String varCurrent = "";

		while (varCurrent.compareTo("Local Variables") == 0) {

			try {
				oRow = oSheet.getRow(pointer++);
				varCurrent = oRow.getCell(1).toString().trim();

				if (varCurrent.compareTo(varName.trim()) == 1) {
					String val = oRow.getCell(0).toString();
					return val;
				}

			} catch (NullPointerException npe) {

				Log.error("Unable to read global data ..." + npe.getMessage());
			}

		} // End of while
		return "";
	}

	public static String Read(String testCase, String varName, int... dataSets) {
		XSSFRow oRow;
		int pointer = 1;
		String varCurrent = "";
		// int dataSet = 1;
		while (pointer < 900) {

			try {
				oRow = oSheet.getRow(pointer++);
				varCurrent = oRow.getCell(0).toString().trim();

				if (varCurrent.compareTo(testCase.trim()) == 0) {
					// int new_pointer = pointer + 16;
					try {
						while ((oRow = oSheet.getRow(pointer++)) != null) {

							// oRow = oSheet.getRow(pointer++);
							varCurrent = oRow.getCell(1).toString();
							if (varCurrent.trim().length() == 0) {
								// result.print("Variable not found in input sheet", "Fail");
								return "";

							}

							if (varCurrent.compareTo(varName.trim()) == 0) {

								String val = oRow.getCell(dataSets[0] + 1).toString();

								/*if (val.equalsIgnoreCase("RANDOM")) {

									val = Utils.generateNumber();

								}
*/
								return val;
							}
						}
					} catch (NullPointerException npe) {

						Log.error("Unable to read test data ..." + npe.getMessage());
						
						return "";
						
					}

				}

			} catch (NullPointerException npe) {
				Log.error("Unable to read test data ..." + npe.getMessage());
			}

		} // End of while
		return "";
	}

}
