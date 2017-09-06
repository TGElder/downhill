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
	
	public final int getX(int mx, int my) {
		return x[mx][my];
	}
	
	public final int getY(int mx, int my) {
		return y[mx][my];
	}
	
	public final int getZ(int mx, int my) {
		return z[mx][my];
	}
	
	public final void setX(int mx, int my, int value) {
		x[mx][my] = value;
	}
	
	public final void setY(int mx, int my, int value) {
		y[mx][my] = value;
	}
	
	public final void setZ(int mx, int my, int value) {
		z[mx][my] = value;
	}
	
	public final void set(int mx, int my, int x, int y, int z) {
		setX(mx,my,x);
		setY(mx,my,y);
		setZ(mx,my,z);
	}
	
	public static Mesh of3x3(){
		Mesh out = new Mesh(3);
		
		int max = Integer.MAX_VALUE;
		int half = max/2;
		
		for (int x=0; x<3; x++) {
			for (int y=0; y<3; y++) {
				out.set(x, y, half*x, half*y, 0);
			}
		}
		
		out.setZ(0, 0, max);
		
		return out;
	}

	
	
}
