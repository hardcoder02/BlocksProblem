package gov.nist.blocks;

public class BlockAllocationCommandImpl extends AbstractCommand implements Command {
	private int numBlocks;

	@Override
	public void parse( String command ) throws CommandParsingException {
		try {
			numBlocks = Integer.parseInt( command.trim() );
		}
		catch ( NumberFormatException ex ) {
			throw new CommandParsingException( "Error parsing command:" + command, ex );
		}
	}

	@Override
	public void execute( BlockWorld blocks ) throws BlockWorldException {
		blocks.allocateBlocks( numBlocks );
	}

}
