package gov.nist.blocks;

public class PileCommandImpl extends AbstractShiftCommand implements Command {
	public static final String NAME = "pile";
	
	@Override
	protected String getName() {
		return NAME;
	}

	@Override
	public void execute(BlockWorld blocks) throws BlockWorldException {
		validate( blocks );
		
		// unstack b only for ONTO
		if ( unstackOption.equals( UnstackOptions.ONTO ) ) {
			blocks.unstackAbove( toBlock );
		}
		
		blocks.move( fromBlock, toBlock );
	}

}
