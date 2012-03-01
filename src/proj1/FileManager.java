package proj1;

import proj1.MyLinkedList;
import proj1.Block;
import proj1.File;

public class FileManager {

	MyLinkedList<File> listOfFiles = new MyLinkedList<File>();
	MyLinkedList<Block> freeBlocks = new MyLinkedList<Block>();
	public int blocks;
	public int bytesPerBlock;
	
	public FileManager(int blocks2, int bytesPerBlock2) {
		int Id = 0;
		Block block = new Block(Id, bytesPerBlock2, bytesPerBlock2);
		for(Id = 0; Id < blocks2; Id++) {
			block.Id = Id;
			freeBlocks.add(Id, block);
		}
		
		blocks = blocks2;
		bytesPerBlock = bytesPerBlock2;
		
		
	}
	
	public void addFile(String name, int bytes) {
		File newFile = new File(name, bytes);
		int freeBytes = -1;
		Block freeBlock;
			
		while (freeBytes <= 0) {
			freeBytes = bytesPerBlock - bytes;
			if (freeBytes <= 0) {
				freeBlock = getFreeBlock();
				freeBlock.bytesFree = 0;
				freeBlock.bytes = bytesPerBlock;
				bytes =  bytes - bytesPerBlock;
			}
			else {
				freeBlock = getFreeBlock();
				freeBlock.bytesFree = freeBytes;
				freeBlock.bytes = bytes;
			}
			
			newFile.createBlockFileList(freeBlock);
			freeBytes = bytesPerBlock - bytes;
		}
		
		listOfFiles.add(newFile);
	}
	
	
	public void deleteFile(String name) {
		File file = findFileByName(name);
		int fileBlockListSize = file.FileBlocks.size();
		
		
		
	}
	
	public void extendFile(String name, int bytes) {
		
		
	}
	
	public void truncateFile(String name, int bytes) {
		
	}
	
	public void print() {
		int allocatedBlocks = blocks - freeBlocks.size();
		
		System.out.println(""); // Empty Line
		
		System.out.println("File Manager Status");
		System.out.println("-------------------");
		System.out.println("Disk Block Size:  " + bytesPerBlock);
		System.out.println("Number of Blocks: " + blocks);
		System.out.println("Allocated Blocks: " + allocatedBlocks);
		System.out.println("FreeBlocks:       " + freeBlocks.size());
		System.out.println(""); // Empty Line
		
		System.out.println("Free List");
		System.out.println("---------");
		System.out.println("" );
		System.out.println(""); // Empty Line
		
		System.out.println("Files");
		System.out.println("-----");
		
		int allocatedBytes = 0;
		for (int i=0; i < listOfFiles.size(); i++) {
			File file = listOfFiles.get(i);
			allocatedBytes = bytesPerBlock * file.FileBlocks.size();
			
			System.out.println("File: " + file.name );
			System.out.println("        Actual Size:    " + file.bytes);
			System.out.println("        Allocated Size: " + allocatedBytes);
			System.out.println("        Num Blocks:     " + file.FileBlocks.size());
			System.out.println("        Disk Block:  " + location(file.FileBlocks));
			System.out.println(""); // Empty Line
		}
	}
	
	private String location (MyLinkedList<Block> list) {
		String locationOfBytes = "";
		int locationStart;
		int locationEnd;
		int prevIndex;
		int currentIndex;
			
		for (int i = 0; i < list.size(); i++) {
			Block block = list.get(i);
			if (list.size() == 1)
			{
				locationOfBytes = " [ " + block.Id + " , " +  block.Id + " ]";
			}
			else {
				locationStart = -1;
				locationEnd = -1;
				prevIndex = -2;
				currentIndex = -2;
				for (int k = 0; k < list.size(); k++) {
					currentIndex = block.Id;
					if ( prevIndex == -2 ) {
						locationStart = block.Id;
						locationEnd = block.Id;
					}
					else if (prevIndex + 1 == currentIndex) {
						locationEnd = currentIndex;
					}
					else {
						
					}
						
					prevIndex = block.Id;	
				}
				
				locationOfBytes = " [ " + locationStart + " , " +  locationEnd + " ]";
			}
		}
		
		return locationOfBytes;
	}
	
	private File findFileByName (String Name) {
		File file = null;
		File temp = null;
		int listFileSize = listOfFiles.size();
		
		
		for (int i = 0; i < listFileSize; i++ ) {
			temp = listOfFiles.get(i);
			
			if (temp.name == Name) {
				file = temp;
			}
		}
		
		return file;
	}
	
	private Block getFreeBlock() {
		Block freeBlock = null;	
		java.util.Iterator<Block> itr =  freeBlocks.iterator();
		
		if (itr.hasNext()) {
			freeBlock = itr.next();
			itr.remove();
		}
		
		return freeBlock;
	}
}
