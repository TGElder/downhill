package com.tgelder.downhill;

import lombok.Getter;

public class Mesh {
	
	@Getter
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

	boolean inBounds(int tx, int ty) {
		return (tx>=0)&&(ty>=0)&&(tx<width)&&(ty<width);
	}
	
	
}
