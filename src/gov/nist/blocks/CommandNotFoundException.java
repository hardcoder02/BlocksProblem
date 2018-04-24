package gov.nist.blocks;

@SuppressWarnings("serial")
public class CommandNotFoundException extends BlockWorldException {

	public CommandNotFoundException() {}

	public CommandNotFoundException(String message) {
		super(message);
	}

	public CommandNotFoundException(Throwable cause) {
		super(cause);
	}

	public CommandNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
