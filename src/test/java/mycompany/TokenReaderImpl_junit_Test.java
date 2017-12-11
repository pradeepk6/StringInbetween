package mycompany;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TokenReaderImpl_junit_Test {
	
	
	@Parameter(0)
    public String streamStr;
	@Parameter(1)
    public String startMarker;
	@Parameter(2)
    public String endMarker;
	@Parameter(3)
    public String expectedToken;
	@Parameter(4)
    public Class<Throwable> exceptionClass;
	
	@Rule
    public ExpectedException expectedException = ExpectedException.none();
	
	StringStream stringStream;
	TokenReaderImpl tokenReaderImpl;
	
	@Before
	public void setUp() {
		tokenReaderImpl = new TokenReaderImpl();
	}
	
    @Parameters(name = "streamString startMarker endMarker expectedToken exceptionClass")
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
	
	@Test
	public void testReadToken() {
        //if expecting exceptions to be thrown
		if (exceptionClass != null) {
			expectedException.expect(exceptionClass);
        }
		
		stringStream = new StringStream(streamStr);
		String actualToken=null;
		try {
			actualToken = tokenReaderImpl.readToken(stringStream,startMarker,endMarker);
		}catch(EndOfStreamException eose) {
			eose.printStackTrace();
		}
		System.out.println("Actual Token:" + actualToken + ":expectedToken:" +expectedToken+":");
		
		//run non-exception tests
		if (exceptionClass == null) {
			if (expectedToken==null) assertNull(actualToken);
			else assertEquals(actualToken,expectedToken);		
        } 		
		
	}
	
	 

}
