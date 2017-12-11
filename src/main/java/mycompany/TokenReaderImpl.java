package mycompany;

/**
 * <p>Gets the String that is nested in between two Strings.
 * Only the first match is returned.</p>
 *
 * <p>A <code>null</code> input String returns <code>null</code>.
 * A <code>null</code> startMarker/endMarker returns <code>null</code> (no match).
 * An empty ("") open and close returns an empty string.</p>
 *
 * <pre>
 * read("wx[b]yz", "[", "]") = "b"
 * read("", "", "")          = ""
 * read("", "", "]")         = null
 * read("", "[", "]")        = null
 * read("yabcz", "", "")     = ""
 * read("yabcz", "y", "z")   = "abc"
 * read("yabczyabcz", "y", "z")   = "abc"
 * </pre>
 *
 *
 */
public class TokenReaderImpl implements TokenReader {

	public String readToken(Stream stream, String startMarker, String endMarker) throws EndOfStreamException {
		
		if((stream==null) || (startMarker==null) || (endMarker==null)) {
			throw new NullPointerException();
		}
		if((startMarker.isEmpty()) && (endMarker.isEmpty())) {
			return "";
		}

		int startMatchPos = 0;
		int endMatchPos = 0;
		boolean startMarkerFound = startMarker.isEmpty();
		boolean endMarkerFound= false ;
		//boolean endMarkerFound = endMarker.isEmpty();
		StringBuilder token = new StringBuilder("");
		char c;	
		
		while(!endMarkerFound) { //while endMarker has not been found
			
			try {
				c = stream.read();
			}catch(EndOfStreamException eose) {
				eose.printStackTrace();
				if (startMarkerFound && endMarker.isEmpty() ) return token.toString();
				return null;
			}
			
			if(startMarkerFound) {
				token.append(c);
			}else if (c==startMarker.charAt(startMatchPos)) { 
				startMatchPos++;
				if (startMatchPos == startMarker.length()) {
					startMarkerFound = true;
					if (startMarkerFound && endMarker.isEmpty() ) return token.toString();
				}
			} else if(c==startMarker.charAt(0)) { //if equal to first character of startMarker
				startMatchPos=1; 
			} else startMatchPos =0;//reset
			
			if (!endMarker.isEmpty()) {
				if (c==endMarker.charAt(endMatchPos)) {
					endMatchPos++;
					if (endMatchPos == endMarker.length()) {
						endMarkerFound = true;
						if (startMarkerFound && token.length()>=endMarker.length()) {
							//delete the endMarker from the token
							token = token.delete(token.length()-endMarker.length(),token.length());
						}else return null;
					}
				}else if(c==endMarker.charAt(0)) { //if equal to first character of end
					endMatchPos=1; 
				}else endMatchPos=0; //reset					
			}								
		}		
		return token.toString();
	}

}
