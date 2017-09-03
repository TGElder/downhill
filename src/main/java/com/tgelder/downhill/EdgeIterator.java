package com.tgelder.downhill;

import java.util.Iterator;

public class EdgeIterator implements Iterator<Edge> {


	private Mesh mesh;
	private final Edge edge = new Edge();
	private int ax=0;
	private int ay=0;
	private int bx;
	private int by;
	private Iterator<Object> mode;
	private final EvenMode evenMode = new EvenMode();
	private final OddMode oddMode = new OddMode();
	private final OddMode2 oddMode2 = new OddMode2();
	
	EdgeIterator(Mesh mesh) {
		this.mesh = mesh;
		
		mode = evenMode;
		
	}
	
	
	
	public boolean hasNext() {
		return mode.hasNext();
	}

	public Edge next() {
		
		mode.next();
		
		while(!mesh.inBounds(edge.getBx(), edge.getBy())) {
			mode.next();
		}
		
		return edge;			
	}
	
	

	
	private class EvenMode implements Iterator<Object> {
		
		private int [] dxs = {0,1};
		private int d=0;
		
		public boolean hasNext() {
			return (ay!=(mesh.getWidth()-1))||(ax!=(mesh.getWidth()-1))||(d!=1);
		}
		
		public Object next() {
			
			bx = ax+dxs[d];
			by = ay;
			
			edge.setAx(ax);
			edge.setAy(ay);
			edge.setBx(bx);
			edge.setBy(by);
						
			d=(d+1)%2;
			if (d==0) {
				ax++;
				if (ax==mesh.getWidth()) {
					ax=0;
					if (ay%2==0) {
						mode = oddMode;
					}
					else {
						mode = oddMode2;
					}
					
				}
			}
			
			return null;
		}



	}
	
	private class OddMode implements Iterator<Object> {

		private int [] dxs = {-1,0,1};
		private int d=0;
		
		public boolean hasNext() {
			return true;
		}
		
		public Object next() {
			
			
			bx = ax+dxs[d];
			by = ay+1;
			
			edge.setAx(ax);
			edge.setAy(ay);
			edge.setBx(bx);
			edge.setBy(by);
			
			d=(d+1)%3;
			if (d==0) {
				ax++;
				if (ax==mesh.getWidth()) {
					ax=0;
					ay++;
					mode = evenMode;
				}
				else {
					mode = oddMode2;
				}
			}
			
			return null;
		}
		
	}
	
	private class OddMode2 implements Iterator<Object> {
		
		public boolean hasNext() {
			return true;
		}
		
		public Object next() {
			
			bx = ax;
			by = ay+1;
			
			edge.setAx(ax);
			edge.setAy(ay);
			edge.setBx(bx);
			edge.setBy(by);
			
			ax++;
			if (ax==mesh.getWidth()) {
				ax=0;
				ay++;
				mode = evenMode;
			}
			else {
				mode = oddMode;
			}
			
			return null;
		}
		
	}
	

	


}
