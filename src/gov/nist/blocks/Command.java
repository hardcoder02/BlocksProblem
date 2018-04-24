package gov.nist.blocks;

import java.util.Objects;

public interface Command {
	void parse( String command ) throws CommandParsingException;

	void execute( BlockWorld blocks ) throws BlockWorldException;

	/**
	 * A factory method to look up commands by name. May be modified to be more dynamic if
	 * more commands are added.
	 * 
	 * @param commandName
	 * @return the command corresponding to commandName
	 * @throws CommandNotFoundException if command is not found
	 */
	static Command lookupCommand(String commandName) throws CommandNotFoundException {
		Objects.requireNonNull(commandName);
		switch (commandName.toLowerCase()) {
		case "move":
			return new MoveCommandImpl();
		case "pile":
			return new PileCommandImpl();
		case "quit":
			return new QuitCommandImpl();
		default:
			throw new CommandNotFoundException("Command not found:" + commandName);
		}
	}
}
