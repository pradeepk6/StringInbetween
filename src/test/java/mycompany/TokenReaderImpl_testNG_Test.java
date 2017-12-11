package mycompany;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;


//@RunWith(Parameterized.class)
public class TokenReaderImpl_testNG_Test {
	
	
	//@Parameter(0)
    public String streamStr;
	//@Parameter(1)
    public String startMarker;
	//@Parameter(2)
    public String endMarker;
	//@Parameter(3)
    public String expectedToken;
	//@Parameter(4)
    public Class<Throwable> exceptionClass;
	
	//@Rule
    //public ExpectedException expectedException = ExpectedException.none();
	
	StringStream stringStream;
	TokenReaderImpl tokenReaderImpl;
/*
	@DataProvider(name = "dataProvider")
	public Object[][] data()
	{
		public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{"qjfwsstartmy stringendplthrp","start","end","my string",null},
				{"","","","",null},
				{"abcd","","","",null},
				{"abcd","b","","",null},
				{"abcd","","c","ab",null},
				{"","","1",null,null},
				{"abcde","bc","","",null},
				{"12345","12","45","3",null},
				{"ab cd","b","c"," ",null},
				{"abcd","bc","bc",null,null},
				{"abcd","bc","cd",null,null},
				{"abcd","bc","c",null,null},
				{"a","a","a",null,null},
				{"abcd","b","c","",null},
				{"abcd","1","2",null,null},
				{"abcd","1","",null,null},
				{"abcd","","1",null,null},
				{"ab\tc\nd","\t","\n","c",null},
				{null,"a","b","",NullPointerException.class},
				{"abcd",null,"","",NullPointerException.class},
		});
	}
*/
	@BeforeTest
	public void setUp() {
		tokenReaderImpl = new TokenReaderImpl();
	}

   // @Parameters(name = "streamString startMarker endMarker expectedToken exceptionClass")
   @DataProvider(name = "dataProvider")
   public static Object[][] data() {
        return new Object[][]{
                {"qjfwsstartmy stringendplthrp","start","end","my string",null},
                {"","","","",null},
                {"abcd","","","",null}, 
                {"abcd","b","","",null},
                {"abcd","","c","ab",null},
                {"","","1",null,null},
                {"abcde","bc","","",null},
				{"12345","12","45","3",null},
                {"ab cd","b","c"," ",null},
                {"abcd","bc","bc",null,null},
                {"abcd","bc","cd",null,null},
                {"abcd","bc","c",null,null},
                {"a","a","a",null,null},
                {"abcd","b","c","",null},       
                {"abcd","1","2",null,null},
                {"abcd","1","",null,null},
                {"abcd","","1",null,null}, 
                {"ab\tc\nd","\t","\n","c",null},
                {null,"a","b","",NullPointerException.class},
                {"abcd",null,"","",NullPointerException.class},
        };
    }

	@Test(dataProvider = "dataProvider")
	public void testReadToken(String streamString,String startMarker,
							  String endMarker,String expectedToken,String exceptionClass ) {
		System.out.println("streamString = " + streamString);
		System.out.println("startMarker = " + startMarker);
		System.out.println("endMarker = " + endMarker);
		System.out.println("expectedToken = " + expectedToken);
		System.out.println("exceptionClass = " + exceptionClass);
        //if expecting exceptions to be thrown
		if (exceptionClass != null) {
			//expectedException.expect(exceptionClass);
        }
		
		stringStream = new StringStream(streamString);
		String actualToken=null;
		try {
			actualToken = tokenReaderImpl.readToken(stringStream,startMarker,endMarker);

		}catch(EndOfStreamException eose) {
			eose.printStackTrace();
		}
		System.out.println("Actual Token:" + actualToken + ":expectedToken:" +expectedToken+":");
		
		//run non-exception tests
		try {
		//if (exceptionClass == null) {
			if (expectedToken==null) assertNull(actualToken);
			else assertEquals(actualToken,expectedToken);
			if(exceptionClass != null) fail();
        } catch(NullPointerException e) {

		}
		
	}

}
