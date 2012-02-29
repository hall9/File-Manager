package proj1;

import proj1.Block;

public class File {
	
		public File(int id2, String name2, int bytesPerBlock2, int bytes2) {
			
			Id = id2;
			name = name2;
			bytesPerBlock = bytesPerBlock2;
			bytes = bytes2;
			
		}
		
		public int Id;
		
		public String name;
		
		public int bytesPerBlock;
	
		public int bytes;
		
		
		MyLinkedList blockList = new MyLinkedList();
		
		public void addBytes(int bytesPerBlock, int bytes) {
			Block blocks = new Block(bytesPerBlock, bytes);
			blockList.add(blocks);
			System.out.println(blocks.bytesUsed);
		}
		
		public void removeBytes() {
			
		}
		
		
		
}
