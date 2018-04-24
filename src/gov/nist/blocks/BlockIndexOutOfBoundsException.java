package gov.nist.blocks;

@SuppressWarnings("serial")
public class BlockIndexOutOfBoundsException extends BlockWorldException {

	public BlockIndexOutOfBoundsException() {
	}

	public BlockIndexOutOfBoundsException(String message) {
		super(message);
	}

	public BlockIndexOutOfBoundsException(Throwable cause) {
		super(cause);
	}

	public BlockIndexOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BlockIndexOutOfBoundsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
