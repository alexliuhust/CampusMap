package campusMap;

import java.util.Scanner;

public class MAP {
	// import the map data
	MapData mapdata = new MapData();
	private int[] list = mapdata.list; // Used to store the data from the text file
	String[] label = mapdata.label;
	
	// Set properties of the map
	int num = 15; // The amount of the vertices
	int[][] W = new int[num][num]; // The weight matrix for the vertices
	int[] R = new int[num]; // The route index array used for store the route index
	int start, end; // The start and end point
	String Start, End;
	
	public void initialize() {
		for (int i = 0; i < num; i++) 
			for (int j = 0; j < num; j++) 
				this.W[i][j] = 10000; // Can't set them as Integer.MAX because of the Integer overflow
		for (int i = 0; i < num; i++) this.R[i] = -1;
	}
	
	public void assignWeight() {
		int count = 0;
		// Since the graph is undirected, we may consider it as between every two vertices
		// there are both back and forth arrows, with equal weight
		while (count < list.length) {
			int x = list[count];
			int y = list[count + 1];
			int value = list[count + 2];
			W[x][y] = value;
			W[y][x] = value;
			count += 3;
		} 
	}
	
	public void setStartandEnd() {
		Scanner input = new Scanner(System.in);
		System.out.println("The accepted inputs are as follow:");
		System.out.println("23a, 23h, 24, 26, 28, 30, 38, 41, 42, 43, 50, 52, 57, 59, 60");
		System.out.println("Please input the START point");
		this.Start = input.next();
		System.out.println("Please input the END point");
		this.End = input.next();
		// Get the index of the start point
		for (int i = 0; i < num; i++)
			if (Start.equals(label[i])) {
				start = i;
				break;
			}
		// Get the index of the end point
		for (int i = 0; i < num; i++) 
			if (End.equals(label[i])) {
				end = i;
				break;
			}
	}
	
	public void outPutRoute() {
		String[] Route = new String[10];   // The real route array that store the real name of the buildings
		int p = this.end;
		Route[0] = label[end];
		int count = 1;
		// Map back the indexes to the real code names
		while (R[p] != -1) {
			Route[count] = this.label[R[p]];
			count++;
			p = R[p];
		}

		// The raw route starts from the end and the end is the start
		// So, output the route in reverse order
		for (int i = 6; i >= 0; i--) 
			if (Route[i] != null) 
				System.out.print(" -> " + Route[i]);
	}
	
}
