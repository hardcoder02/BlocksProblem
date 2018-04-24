package gov.nist.blocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class acts as a container for block piles and provides methods for manipulating blocks 
 *
 */
public class BlockWorld {
	/**
	 * The maximum permissible number of blocks in this world
	 */
	public static final int MAX_BLOCK_NUMBER = 24;
	
	private List<BlockPile> blockPiles;
	
	/**
	 * Constructor that does not allocate blocks. Blocks need to be allocated separately.
	 * 
	 * @see #allocateBlocks(int)
	 */
	public BlockWorld() {
	}
	
	/**
	 * Allocates block piles initially containing one corresponding block each.
	 * 
	 * @param numOfBlocks number of blocks / piles to allocate
	 * @throws BlockIndexOutOfBoundsException if the requested number of blocks exceeds <code>MAX_BLOCK_NUMBER</code> 
	 */
	public BlockWorld( int numOfBlocks ) throws BlockIndexOutOfBoundsException {
		allocateBlocks( numOfBlocks );
	}

	/**
	 * Allocates block piles initially containing one corresponding block each. This method can only
	 * be called once, either from constructor or separately.
	 * 
	 * @param numOfBlocks number of blocks / piles to allocate
	 * @throws BlockIndexOutOfBoundsException if the requested number of blocks exceeds <code>MAX_BLOCK_NUMBER</code>
	 * @throws IllegalStateException if the method is called more than once
	 */
	protected void allocateBlocks( int numOfBlocks ) throws BlockIndexOutOfBoundsException {
		if ( blockPiles != null) {
			throw new IllegalStateException( "Blocks have already been allocated" );
		}
		
		if ( numOfBlocks < 1 || numOfBlocks > MAX_BLOCK_NUMBER ) {
			throw new BlockIndexOutOfBoundsException( "Number of blocks must be between 1 and " + MAX_BLOCK_NUMBER );
		}
		
		blockPiles = new ArrayList<>( numOfBlocks );
		
		for ( int i = 0; i < numOfBlocks; i++ ) {
			BlockPile pile = new BlockPile( i, numOfBlocks );
			blockPiles.add( pile );
		}
	}
	
	/**
	 * The current pile index of the block with the specified original index
	 * 
	 * @param blockIndex the original block index
	 * @return index of the pile where the block is currently located
	 * @throws BlockIndexOutOfBoundsException if the specified index is outside of allocated block indices.
	 */
	public int getBlockCurrentX( int blockIndex ) throws BlockIndexOutOfBoundsException {
		checkIndex( blockIndex );
		return blockPiles.get( blockIndex ).getOriginalBlock().getCurrentX();
	}
	
	/**
	 * The current position of the block with the specified original index in the pile
	 * 
	 * @param blockIndex the original block index
	 * @return index in the pile where the block is currently located
	 * @throws BlockIndexOutOfBoundsException if the specified index is outside of allocated block indices.
	 */
	public int getBlockCurrentY( int blockIndex ) throws BlockIndexOutOfBoundsException {
		checkIndex( blockIndex );
		return blockPiles.get( blockIndex ).getOriginalBlock().getCurrentY();
	}
	
	public Block getOriginalBlock( int blockIndex ) throws BlockIndexOutOfBoundsException {
		checkIndex( blockIndex );
		return blockPiles.get( blockIndex ).getOriginalBlock();
	}
	
	/**
	 * Return all blocks stacked on top of block with <code>blockIndex</code> to their original positions
	 * 
	 * @param blockIndex index of the block, above which blocks are to be removed and returned to their
	 * 			initial position
	 * @throws BlockIndexOutOfBoundsException if block index is out of range
	 */
	public void unstackAbove( int blockIndex ) throws BlockIndexOutOfBoundsException {
		checkIndex( blockIndex );
		
		int pileIndex = getBlockCurrentX( blockIndex );
		int blockIndexInPile = getBlockCurrentY( blockIndex );
		List<Block> detached = blockPiles.get( pileIndex ).detach( blockIndexInPile + 1 );
		detached.forEach( b -> blockPiles.get( b.getBlockNumber() ).push( b ) );
	}
	
	/**
	 * Move a block and all blocks on top of it (if any) to another pile, preserving the order.
	 * 
	 * @param blockIndexFrom the original index of the block to move
	 * @param blockIndexTo the original index of the receiving block. If there are other blocks on top of the
	 * 			receiver block, the block(s) being moved are added on top of the pile.
	 * @throws BlockIndexOutOfBoundsException if block indices are out of range
	 */
	public void move( int blockIndexFrom, int blockIndexTo ) throws BlockIndexOutOfBoundsException {
		checkIndex( blockIndexFrom );
		checkIndex( blockIndexTo );
		
		int fromPileIndex = getBlockCurrentX( blockIndexFrom );
		int fromBlockIndexInPile = getBlockCurrentY( blockIndexFrom );
		List<Block> detached = blockPiles.get( fromPileIndex ).detach( fromBlockIndexInPile );

		int newPileIndex = getBlockCurrentX( blockIndexTo );
		blockPiles.get( newPileIndex ).push( detached );
	}
	
	/**
	 * 
	 * @return unmodifiable view of the piles in the current set-up
	 */
	public List<BlockPile> getBlockPiles() {
		return Collections.unmodifiableList( blockPiles );
	}
	
	public int getSize() {
		if ( blockPiles == null )
			return 0;
		
		return blockPiles.size();
	}
	
	private void checkIndex( int blockIndex ) throws BlockIndexOutOfBoundsException {
		if ( blockIndex < 0 || blockIndex >= blockPiles.size() ) {
			throw new BlockIndexOutOfBoundsException(
					"Block index must be between 0 and " + ( blockPiles.size() - 1 ) );
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "Block world: ");
		if ( blockPiles != null ) {
			sb.append( "{");
			for ( BlockPile p : blockPiles ) {
				sb.append( "(" ).append( p ).append( ")" );
			}
			sb.append( "}" );
		}
		return sb.toString();
	}
}
