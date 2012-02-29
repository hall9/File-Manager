package proj1;

import proj1.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.StringTokenizer;

public class Proj1 {

	static String create = "CREATE";
	static String delete = "DELETE";
	static String extend = "EXTEND";
	static String truncate = "TRUNCATE";
	static String printState = "PRINT";
	
	public static void main(String[] args) throws IOException 
	{	

		int blocks = Integer.parseInt(args[0]);
		int bytesPerBlock = Integer.parseInt(args[1]);
		String filepath = args[2];
		
		/*
		int blocks = 100;
		int bytes = 1024;
		String filepath = "command.dat";
		*/
		
		getCommand(blocks, bytesPerBlock, filepath);		

	}
	
	static void getCommand(int blocks, int bytesPerBlock, String filename) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(filename));
		StringTokenizer stok = new StringTokenizer(r.readLine());
		String Command = stok.nextToken();
		FileManager file = new FileManager();
		
		String storageName;
		int bytes;
		
		if (Command.equals(create))
		{
			storageName = stok.nextToken();
			bytes = Integer.parseInt(stok.nextToken());
			
			file.addFile(storageName, bytesPerBlock, bytes);
			
		}
		else if (Command.equals(delete))
		{
			storageName = stok.nextToken();
			
			file.deleteFile(storageName);
			
		}
		else if (Command.equals(extend))
		{
			storageName = stok.nextToken();
			bytes = Integer.parseInt(stok.nextToken());
			
			file.extendFile(storageName, bytes);
			
		}
		else if (Command.equals(truncate))
		{
			storageName = stok.nextToken();
			bytes = Integer.parseInt(stok.nextToken());
			
			file.truncateFile(storageName, bytes);
			
		}
		else if (Command.equals(printState))
		{
			file.print();
		}
		
		r.close();	
	}
}
