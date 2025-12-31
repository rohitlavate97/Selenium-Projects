package testng;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {
	@DataProvider
	public Object[][] dataBank(){
		Object[][] data = new Object[2][2];
		data[0][0] ="pramod";
		data[0][1] ="admin";
		data[1][0] ="rohit";
		data[1][1] ="@dmin";
//		Object[][] data = new Object[][] {      //Interview-safe
//		    {"pramod", "admin"},
//		    {"rohit", "@dmin"}
//		};
		
//		List<Object[]> list = new ArrayList<>();
//
//		list.add(new Object[]{"pramod", "admin"});
//		list.add(new Object[]{"rohit", "@dmin"});
//
//		Object[][] data = list.toArray(new Object[0][0]);
		
//		Object[][] data = new Object[][] {     //Using Arrays.asList()
//		    {"pramod", "admin"},
//		    {"rohit", "@dmin"}
//		};



		return data;
	}
	
	@Test(dataProvider="dataBank")
	public void testParameterization(String un,String pwd) {
		Reporter.log(un+" "+pwd);
	}
}
