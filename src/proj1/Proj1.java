package proj1;
import LinkedList.*;

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
	static String printList = "PRINT";
	
	public static void main(String[] args) throws IOException {	
		/*
		int blocks = Integer.parseInt(args[0]);
		int bytes = Integer.parseInt(args[1]);
		String filepath = args[2];
		
		getCommand(filepath);
		
		*/
		
		
		int blocks = 100;
		int btyes = 1024;
		
		getCommand("command.dat");
		
		Object storage = new Object();
		
		int storage.blocks = 100;
		int storage bytes = 1024;
		String storage.filename = "command.dat"
				
				

	}
	
	static void getCommand(Object filename) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(filename));
		StringTokenizer stok = new StringTokenizer(r.readLine());
		String Command = stok.nextToken();
		Object storage = new Object();
		MyLinkedList<Object> fileManager = new MyLinkedList(Object);
		
		if (Command.equals(create))
		{
			String storageName = stok.nextToken();
			int blocks = Integer.parseInt(stok.nextToken());
			
			
			
		}
		else if (Command.equals(delete))
		{
			String storageName = stok.nextToken();
			
		}
		else if (Command.equals(extend))
		{
			String storageName = stok.nextToken();
			int blocks = Integer.parseInt(stok.nextToken());
			
		}
		else if (Command.equals(truncate))
		{
			String storageName = stok.nextToken();
			int blocks = Integer.parseInt(stok.nextToken());
			
		}
		else if (Command.equals(printList))
		{
			
			
		}
		
		r.close();	
	}
}
