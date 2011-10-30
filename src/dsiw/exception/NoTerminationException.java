package dsiw.exception;

/**
 * Diese Exception wird geworfen, wenn das Programm nicht terminiert. Es wird abgebrochen.
 * @author DSIW
 *
 */
@SuppressWarnings("serial")
public class NoTerminationException extends RuntimeException {
	/**
	 * Programm wird abgebrochen.
	 */
	public NoTerminationException() {
		printStackTrace();
		System.exit(0);
	}
}