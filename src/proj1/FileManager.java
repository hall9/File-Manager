package proj1;

import proj1.MyLinkedList;
import proj1.Block;
import proj1.File;
import java.util.Comparator;

public class FileManager implements Comparator<File> {

	MyLinkedList<File> listOfFiles = new MyLinkedList<File>();
	MyLinkedList<Block> freeBlocks = new MyLinkedList<Block>();
	public int blocks;
	public int bytesPerBlock;
	
	public FileManager(int blocks2, int bytesPerBlock2) {
		int Id = 0;
		for(Id = 0; Id < blocks2; Id++) {
			Block block = new Block(Id, bytesPerBlock2, bytesPerBlock2);
			block.Id = Id;
			freeBlocks.add(Id, block);
		}
		
		blocks = blocks2;
		bytesPerBlock = bytesPerBlock2;
		
		
	}
	
	public int compare(File file1, File file2) {
		String name1 = file1.name;
		String name2 = file2.name;
		
		if(!(name1.equals(name2))) {
			return name1.compareToIgnoreCase(name2);
		}
		else {
			return name1.compareToIgnoreCase(name2);
		}
		
		
	}
	
	public void addFile(String name, int bytes) {
		File newFile = new File(name, bytes);
		int freeBytes;
		Block freeBlock;
		
		if (bytesPerBlock < bytes) {
			while (bytesPerBlock < bytes) {
				freeBlock = getFreeBlock();
				freeBlock.bytesFree = 0;
				freeBlock.bytes = bytesPerBlock;
				
				newFile.createBlockFileList(freeBlock);
				bytes =  bytes - bytesPerBlock;
			}
		}
		
	    if (bytesPerBlock >= bytes ) {
				freeBlock = getFreeBlock();
				freeBytes = bytesPerBlock - bytes;
				freeBlock.bytesFree = freeBytes;
				freeBlock.bytes = bytes;
				
				newFile.createBlockFileList(freeBlock);
			}
		
		listOfFiles.add(newFile);
	}
	
	
	public void deleteFile(String name) {
		int fileIndex = findFileByName(name);
        
        if (fileIndex != -1 ) {
            File file = listOfFiles.get(fileIndex);
            int fileBlockListSize = file.FileBlocks.size();
		
            int i;
            for(i = 0; i < fileBlockListSize; i++) {
                Block block = file.FileBlocks.get(i);
                block.bytes = 0;
                block.bytesFree = bytesPerBlock;
                freeBlocks.add(i, block);
            }
		
            listOfFiles.remove(fileIndex);
        }
        else {
            System.out.println(name + " file can not be found.."); 
        }
	}
	
	public void extendFile(String name, int bytes) {
		int fileIndex = findFileByName(name);
		File file = listOfFiles.get(fileIndex);
		int fileBlockListSize = file.FileBlocks.size();
		int fileBlockListBytesFree = file.FileBlocks.get(fileBlockListSize -1).bytesFree; 
		
		int freeBytes;
		Block freeBlock;
		int bytesInstance = bytes;
			
		if (fileBlockListBytesFree <= bytesInstance) {
			if (bytes > 0) {
				while (bytesInstance > 0) {
					freeBlock = getFreeBlock();
					freeBlock.bytesFree = 0;
					freeBlock.bytes = bytesPerBlock;

					file.createBlockFileList(freeBlock);
					bytesInstance =  bytesInstance - bytesPerBlock;
				}
			}
		}
		
	    if (bytesPerBlock  >= bytes ) {
				freeBlock = getFreeBlock();
				freeBytes = fileBlockListBytesFree - bytes;
				freeBlock.bytesFree = freeBytes;
				freeBlock.bytes = bytes;
				
				file.createBlockFileList(freeBlock);
			}
		
		file.bytes += bytes;
	}
	
	public void truncateFile(String name, int bytes) {
		int fileIndex = findFileByName(name);
		File file = listOfFiles.get(fileIndex);
		int fileBlockListSize = file.FileBlocks.size();
		
		int removingByteFiles = bytes/bytesPerBlock;
		
		if (file.FileBlocks.get(fileBlockListSize - 1).bytes > bytes) {
			file.FileBlocks.get(fileBlockListSize - 1).bytes -= bytes;
			file.FileBlocks.get(fileBlockListSize - 1).bytesFree += bytes;
			file.bytes -= bytes;
		}
		else {
			for (int m = 0; m < removingByteFiles; m++) {
				file.FileBlocks.get(fileBlockListSize - 1).bytes -= bytes;
				file.FileBlocks.get(fileBlockListSize - 1).bytesFree += bytes;
				file.bytes -= bytes;

			}
		}
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
		System.out.println("" + location(freeBlocks));
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
			
		int startId = -1;
		int prevId = -1;
		int k;
		for (k = 0; k < list.size(); k++) {	
			Block block = list.get(k);
			//startId = block.Id;
			//prevId = block.Id;
			if (list.size() == 1) {
				startId = block.Id;
				prevId = block.Id;
			}
			else if (k==0) {
				startId = block.Id;
				prevId = block.Id;
			}
			else {
				if (prevId+1 == block.Id) {
					prevId = block.Id;
				}
				else {
					locationOfBytes += " [ " + startId + " , " +  prevId + " ]";
					startId = block.Id;
					prevId = block.Id;
				}
			}
		}
		locationOfBytes += " [ " + startId + " , " +  prevId + " ]";
		
		return locationOfBytes;
	}
		
	private int findFileByName (String Name) {
		File file = new File(Name, 0);
		File temp = null;
		int listFileSize = listOfFiles.size();
		int fileIndex = -1;
		
		for (int i = 0; i < listFileSize; i++ ) {
			temp = listOfFiles.get(i);
			
			if (compare(file,temp) == 0) {
				file = temp;
				fileIndex = i;
			}
		}
		
		return fileIndex;
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
