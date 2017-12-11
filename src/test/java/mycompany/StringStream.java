
package mycompany;

/**
 * String-backed implementation of the {@link Stream} interface.
 */
public class StringStream implements Stream {

	private final char[] input;
	private int pos;
	
	public StringStream(String s) {
		input = s.toCharArray();
	}
	
	@Override
	public char read() 
	throws EndOfStreamException {
		if (pos < input.length) {
			return input[pos++];
		} else {
			throw new EndOfStreamException();
		}		
	}
}
