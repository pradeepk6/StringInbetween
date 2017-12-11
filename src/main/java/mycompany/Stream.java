
package mycompany;

/**
 * Represents a stream of characters.
 */
public interface Stream {

	/**
	 * Returns the next character in the stream. If no character is available, this method blocks 
	 * until one becomes available.
	 * @return next character in the stream.
	 * @throws EndOfStreamException when the end of the stream is reached.
	 */
	char read() throws EndOfStreamException;
	
}
