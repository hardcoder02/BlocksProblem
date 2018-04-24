package gov.nist.blocks;

@SuppressWarnings("serial")
public class CommandParsingException extends BlockWorldException {

	public CommandParsingException() {}

	public CommandParsingException(String message) {
		super(message);
	}

	public CommandParsingException(Throwable cause) {
		super(cause);
	}

	public CommandParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
