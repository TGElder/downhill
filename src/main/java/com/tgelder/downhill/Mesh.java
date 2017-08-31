package com.tgelder.downhill;

public class Mesh {
	
	private final int width;
	private final int[][] x;
	private final int[][] y;
	private final int[][] z;
	
	public Mesh(int width) {
		this.width = width;
		x = new int[width][width];
		y = new int[width][width];
		z = new int[width][width];
	}
	
	public void printEdges() {
		
		int count=0;
		
		for (int ty=0; ty<width; ty++) {
			for (int tx=0; tx<width; tx++) {
				
				if (tx<width-1) {
//					System.out.print("("+tx+","+ty+") to ");
//					System.out.println((tx+1)+","+ty);
					count++;
				}
				if (ty<width-1) {
//					System.out.print("("+tx+","+ty+") to ");
//					System.out.println(tx+","+(ty+1));
					count++;
				}
				if (isEven(tx,ty)) {
					if (inBounds(tx+1,ty+1)&&isEven(tx+1,ty+1)) {
//						System.out.print("("+tx+","+ty+") to ");
//						System.out.println((tx+1)+","+(ty+1));
						count++;
					}
					if (inBounds(tx-1,ty+1)&&isEven(tx-1,ty+1)) {
//						System.out.print("("+tx+","+ty+") to ");
//						System.out.println((tx-1)+","+(ty+1));
						count++;
					}
				}
				
			}
		}
		
		System.out.println(count+" combos");

	}
	
	private boolean inBounds(int tx, int ty) {
		return (tx>=0)&&(ty>=0)&&(tx<width)&&(ty<width);
	}
	
	private boolean isEven(int tx, int ty) {
		return !((tx % 2 == 0) ^ (ty % 2 == 0));
	}
}
