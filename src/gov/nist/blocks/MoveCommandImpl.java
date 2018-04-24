package gov.nist.blocks;

public class MoveCommandImpl extends AbstractShiftCommand implements Command {
	public static final String NAME = "move";

	@Override
	protected String getName() {
		return NAME;
	}

	@Override
	public void execute( BlockWorld blocks ) throws BlockWorldException {
		validate( blocks );
		
		// unstack a both for ONTO and OVER
		blocks.unstackAbove( fromBlock );
		
		// unstack b only for ONTO
		if ( unstackOption.equals( UnstackOptions.ONTO ) ) {
			blocks.unstackAbove( toBlock );
		}
		
		blocks.move( fromBlock, toBlock );
	}

}
