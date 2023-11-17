package proj1;

import io.github.pixee.security.BoundedLineReader;
import proj1.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Proj1 {

	static String create = "CREATE";
	static String delete = "DELETE";
	static String extend = "EXTEND";
	static String truncate = "TRUNCATE";
	static String printState = "PRINT";
	
	public static void main(String[] args)  throws IOException {
		int blocks = Integer.parseInt(args[0]);
		int bytesPerBlock = Integer.parseInt(args[1]);
		String filepath = args[2];
		
		/*
		int blocks = 100;
		int bytesPerBlock = 1024;
		String filepath = "a1.cmds";
		*/
		
		getCommand(blocks, bytesPerBlock, filepath);

	}
	
	static void getCommand(int blocks, int bytesPerBlock, String filename) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(filename));
		//StringTokenizer token = new StringTokenizer(r.readLine());
		
		
		FileManager file = new FileManager(blocks, bytesPerBlock);
		
		String storageName;
		int bytes;
		String line;
		String Command = null;
		
		while((line = BoundedLineReader.readLine(r, 5_000_000)) != null) {
			StringTokenizer token;
			token = new StringTokenizer (line);
			
			if (token.hasMoreTokens()) {
				Command = token.nextToken();
			}
			else {
				Command = "#";
			}
            
			if ( "#".equals(Command)) {
				// Do nothing skip line
			}
			else if (Command.equals(create))
			{
				storageName = token.nextToken();
				bytes = Integer.parseInt(token.nextToken());

				file.addFile(storageName, bytes);

			}
			else if (Command.equals(delete))
			{
				storageName = token.nextToken();

				file.deleteFile(storageName);

			}
			else if (Command.equals(extend))
			{
				storageName = token.nextToken();
				bytes = Integer.parseInt(token.nextToken());

				file.extendFile(storageName, bytes);

			}
			else if (Command.equals(truncate))
			{
				storageName = token.nextToken();
				bytes = Integer.parseInt(token.nextToken());

				file.truncateFile(storageName, bytes);

			}
			else if (Command.equals(printState))
			{
				file.print();
			}

		}
		
		r.close();
	}
	
}
