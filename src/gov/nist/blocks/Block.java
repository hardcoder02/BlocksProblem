package gov.nist.blocks;

/**
 * A single block that stores the original block number and the current coordinates to allow efficient
 * block lookup, where X is the current pile number and Y is the index in the pile.
 * 
 * Only getter methods are public to prevent clients from modifying the object's state.
 *
 */
public class Block {
	private final int blockNumber;
	
	private int currentX;
	private int currentY;
	
	public Block( int blockNumber ) {
		this.blockNumber = blockNumber;
		this.currentX = blockNumber;
		this.currentY = 0;
	}
	
	public int getBlockNumber() {
		return blockNumber;
	}
	
	/**
	 * 
	 * @return the pile number that currently contains this block. -1 indicates a detached state
	 */
	public int getCurrentX() {
		return currentX;
	}
	
	/**
	 * 
	 * @return the index of this block in the pile that currently contains it. -1 indicates a detached state
	 */
	public int getCurrentY() {
		return currentY;
	}
	
	protected void setCurrentX( int x ) {
		this.currentX = x;
	}
	
	protected void setCurrentY( int y ) {
		this.currentY = y;
	}
	
	protected void setCurrentXY( int x, int y ) {
		this.currentX = x;
		this.currentY = y;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "Block: ").append( blockNumber );
		sb.append( ", Pile: ").append( currentX );
		sb.append( ", Pos: ").append( currentY );
		return sb.toString();
	}
}
