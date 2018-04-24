package gov.nist.blocks;

@SuppressWarnings("serial")
public class BlockWorldException extends Exception {

	public BlockWorldException() {}

	public BlockWorldException(String message) {
		super(message);
	}

	public BlockWorldException(Throwable cause) {
		super(cause);
	}

	public BlockWorldException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlockWorldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
