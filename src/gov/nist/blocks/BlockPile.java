package gov.nist.blocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that represents a pile of blocks. It has a reference to the original block, allowing to
 * efficiently retrieve the block's current coordinates.
 * 
 * Only getter methods are public to prevent clients from modifying the object's state.
 * 
 * The available operations run in linear time.
 *
 */
public class BlockPile {
	private final List<Block> blocks;
	private final int pileIndex;
	private final Block originalBlock;
	
	public BlockPile( int pileIndex ) {
		this( pileIndex, BlockWorld.MAX_BLOCK_NUMBER );
	}
	
	/**
	 * Creates a pile containing a single block corresponding to the pile index
	 * 
	 * @param pileIndex index of this pile in the list of all piles
	 * @param initialCapacity initial capacity for allocating the block list
	 */
	public BlockPile( int pileIndex, int initialCapacity ) {
		this.pileIndex = pileIndex;
		blocks = new ArrayList<>( initialCapacity );
		originalBlock = new Block( pileIndex );
		blocks.add( originalBlock );
	}
	
	public Block getOriginalBlock() {
		return originalBlock;
	}
	
	public int getPileIndex() {
		return pileIndex;
	}
	
	/**
	 * 
	 * @return unmodifiable view of blocks in this pile
	 */
	public List<Block> getBlocks() {
		return Collections.unmodifiableList( blocks );
	}
	
	/**
	 * Detaches the block on top of this pile by removing it from the pile and setting
	 * the block's coordinates to -1, -1
	 * 
	 * @return the detached top block or null if the pile is empty
	 */
	protected Block detach() {
		if ( blocks.isEmpty() ) {
			return null;
		}
		
		Block b = blocks.remove( blocks.size() - 1 );
		b.setCurrentXY( -1, -1 );
		return b;
	}
	
	/**
	 * Detaches the block(s) starting at <code>index</code> by removing them from the pile and setting
	 * each block's coordinates to -1, -1
	 *
	 * @param index at which blocks will be detached, inclusive
	 * @return the (possibly empty) list of detached blocks
	 */
	protected List<Block> detach( int index ) {
		// in reverse order to avoid shifting the remaining elements after each removal
		List<Block> detached = new ArrayList<>();
		for ( int i = blocks.size() - 1; i >= index; i-- ) {
			Block b = blocks.remove( i );
			b.setCurrentXY( -1, -1 );
			detached.add( b );
		}
		Collections.reverse( detached );
		return detached;
	}
	
	/**
	 * Add a block to the top of the pile and set the block's current coordinates accordingly
	 * 
	 * @param block the block to be added to the top of this pile
	 */
	protected void push( Block block ) {
		blocks.add( block );
		block.setCurrentXY( pileIndex, blocks.size() - 1 );
	}
	
	/**
	 * Add all blocks in the list, in order, to the top of the pile and set their coordinates accordingly
	 *  
	 * @param blocks the list of blocks to be added to this pile
	 */
	protected void push( List<Block> blocks ) {
		for ( Block b: blocks ) {
			push( b );
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "Pile: ").append( pileIndex );
		sb.append( "{");
		for ( Block b : blocks ) {
			sb.append( "(" ).append( b ).append( ")" );
		}
		sb.append( "}" );
		return sb.toString();
	}
}
