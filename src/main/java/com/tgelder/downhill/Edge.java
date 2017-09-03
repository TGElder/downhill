package com.tgelder.downhill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Edge {
	
	private int ax;
	private int ay;
	private int bx;
	private int by;
	
	Edge() {
		
	}
	
	public String toString() {
		return "("+ax+","+ay+") to ("+bx+","+by+")";
	}

}
