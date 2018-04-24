package gov.nist.blocks;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Parent class for "move" and "pile" commands
 *
 */
public abstract class AbstractShiftCommand extends AbstractCommand {
	protected int fromBlock;
	protected int toBlock;
	protected UnstackOptions unstackOption;

	protected abstract String getName();
	
	@Override
	public void parse( String command ) throws CommandParsingException {
		try ( Scanner s = new Scanner( command ) ) {
			if ( !s.next().trim().equalsIgnoreCase( getName() ) ) {
				throw new CommandParsingException( "Command name not found: " + getName() );
			}
			fromBlock = s.nextInt();
			unstackOption = UnstackOptions.valueOf( s.next().trim().toUpperCase() );
			toBlock = s.nextInt();
			if ( s.hasNext() ) {
				throw new CommandParsingException( "Error parsing command, too many arguments");
			}
		}
		catch ( NoSuchElementException ex ) {
			throw new CommandParsingException( "Error parsing command tokens", ex );
		}
	}
	
	protected void validate( BlockWorld bw) throws BlockWorldException {
		if ( fromBlock < 0 || fromBlock >= bw.getSize() &&
				toBlock < 0 || toBlock >= bw.getSize() ) {
			throw new BlockIndexOutOfBoundsException( "Index must be between 0 and less than " + bw.getSize() );
		}
		if ( bw.getBlockCurrentX( fromBlock ) == bw.getBlockCurrentX( toBlock ) ) {
			throw new BlockWorldException( "Blocks " + fromBlock + " and " + toBlock + " are in the same column");
		}
	}
}
