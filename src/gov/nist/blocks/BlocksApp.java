package gov.nist.blocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BlocksApp {

	public static void main(String[] args) {
		BlockWorld bw = new BlockWorld();
		
		try ( BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) ) ) {
			// first command is number of blocks
			String command = br.readLine();
			if ( command == null ) {
				return;
			}
			Command allocCmd = new BlockAllocationCommandImpl();
			allocCmd.parse( command );
			allocCmd.execute( bw );
			
			// execute commands until "quit", ignoring exceptions resulting from bad input
			while ( ( command = br.readLine() ) != null ) {
				String[] cmdTokens =  command.split( "\\s+" );
				if ( cmdTokens.length < 1 ) {
					continue;
				}
				
				try {
					Command cmd = Command.lookupCommand( cmdTokens[0] );
					cmd.parse( command );
					cmd.execute( bw );
					if ( cmd instanceof QuitCommandImpl ) {
						return;
					}
				}
				catch ( BlockWorldException ex ) {
					// ignore
				}
			}
		}
		catch ( IOException | BlockWorldException ex ) {
			// should not happen
			ex.printStackTrace();
		}
	}
}
