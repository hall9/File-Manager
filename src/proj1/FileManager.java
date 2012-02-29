package proj1;

import proj1.MyLinkedList;
import proj1.File;

public class FileManager {

	MyLinkedList dataBaseManager = new MyLinkedList();
	
	public FileManager() {
		
		
	}
	
	private int Id = 0;
	
	public void addFile(String name, int bytesPerBlock, int bytes) {
		
		File newFile = new File(++Id, name, bytesPerBlock, bytes);
		newFile.addBytes(bytesPerBlock, bytes);
		
		dataBaseManager.add(newFile);
		
	}
	
	public void deleteFile(String name) {
		
		
	}
	
	public void extendFile(String name, int bytes) {
		
		
	}
	
	public void truncateFile(String name, int bytes) {
		
	}
	
	public void print() {

		System.out.println("" ); // Empty Line
		
		System.out.println("File Manager Status");
		System.out.println("-------------------");
		System.out.println("Disk Block Size:  " );
		System.out.println("Number of Blocks: " );
		System.out.println("Allocated Blocks: " );
		System.out.println("FreeBlocks:       " );
		System.out.println("" ); // Empty Line
		
		System.out.println("Free List");
		System.out.println("---------");
		System.out.println("" );
		System.out.println("" ); // Empty Line
		
		System.out.println("Files");
		System.out.println("-----");
		
		for (int i=0; i < Id; i++) {
			System.out.println("File: " );
			System.out.println("        Actual Size:    " );
			System.out.println("        Allocated Size: " );
			System.out.println("        Num Blocks:     " );
			System.out.println("        Disk Block:     " );
			System.out.println("" ); // Empty Line
		}
	
	}
	
	
}
