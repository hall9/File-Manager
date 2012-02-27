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
		int bytes = 1024;
		String filename = "command.dat";
		
		getCommand("command.dat");		

	}
	
	static void getCommand(String filename) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(filename));
		StringTokenizer stok = new StringTokenizer(r.readLine());
		String Command = stok.nextToken();
		
		MyLinkedList fileManager = new MyLinkedList();
		
		String storageName;
		int bytes;
		
		if (Command.equals(create))
		{
			storageName = stok.nextToken();
			bytes = Integer.parseInt(stok.nextToken());
			
			
			
		}
		else if (Command.equals(delete))
		{
			storageName = stok.nextToken();
			
		}
		else if (Command.equals(extend))
		{
			storageName = stok.nextToken();
			bytes = Integer.parseInt(stok.nextToken());
			
		}
		else if (Command.equals(truncate))
		{
			storageName = stok.nextToken();
			bytes = Integer.parseInt(stok.nextToken());
			
		}
		else if (Command.equals(printList))
		{
			
			
		}
		
		r.close();	
	}
}
