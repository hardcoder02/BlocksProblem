package gov.nist.blocks;

public class QuitCommandImpl extends AbstractCommand implements Command {
	public static final String NAME = "quit";

	@Override
	public void parse( String command ) throws CommandParsingException {
		if ( !command.equalsIgnoreCase( NAME ) ) {
			throw new CommandParsingException( "Error parsing command: " + command );
		}
	}

	@Override
	public void execute( BlockWorld blocks ) throws BlockWorldException {
		for ( BlockPile p : blocks.getBlockPiles() ) {
			System.out.print( p.getPileIndex() + ":" );
			for ( Block b : p.getBlocks() ) {
				System.out.print( " " + b.getBlockNumber() );
			}
			System.out.println("");
		}
	}

}
