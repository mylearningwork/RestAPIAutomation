package utilities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.*;

public class TestRunner {

	static TestNG testng;

	public static void main(String[] args) {

		try {
			
			XmlSuite suite = new XmlSuite();
			suite.setName("TmpSuite");
			 
			XmlTest test = new XmlTest(suite);
			test.setName("TmpTest");
			List<XmlClass> classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("src.test.java.com.C4PM.testpages.ECRtest"));
			test.setXmlClasses(classes) ;

			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(suite);
			TestNG tng = new TestNG();
			tng.setXmlSuites(suites);
			tng.run();
			

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}