package proj1;

import proj1.Block;

public class File {
	
		public File(String name2, int bytes2) {
			
			name = name2;
			bytes = bytes2;
			
		}
			
		public String name;
		
		public int bytes;
		
		public MyLinkedList<Block> FileBlocks = new MyLinkedList<Block>();

		public void createBlockFileList(Block freeBlock) {
			FileBlocks.add(freeBlock);
		}
			
}
