package campusMap;

public class MainClass {

	public static void main(String[] args) {
		MAP map = new MAP();
		map.initialize();
		map.assignWeight();
		map.setStartandEnd();
		Dijkstra(map);
		map.outPutRoute();
	}
	
	private static void Dijkstra(MAP map) {
		boolean[] vis = new boolean[map.num];  // Mark whether a vertex is visited
		int[] dis = new int[map.num];    // Store the shortest distance of every vertices from the start point 
		for (int i = 0; i < map.num; i++) { // Initialize the visiting status and the distances
			vis[i] = false;
			dis[i] = 500;
		}
		dis[map.start] = 0;     // The distance between start and itself is always 0
		
		for (int i = 0; i < map.num; i++) {
			int u = -1;
			int min = 500;
		
			for (int j = 0; j < map.num; j++) {
				// Once a vertex has not been visited yet 
				// and the distance is smaller than the last smallest mark, visit it
				if (!vis[j] && dis[j] < min) {  
					u = j;
					min = dis[j];  // The latest smallest mark
				}
			}

			if (u == -1) return;  // If we cannot find any other adjacent vertices, the function is done
			vis[u] = true;        // Mark the vertices visited
			for (int v = 0; v < map.num; v++) {
				// Find the vertex has shortest distance from the pre-visited one
				if (!vis[v] && dis[u] + map.W[u][v] < dis[v]) {
					dis[v] = dis[u] + map.W[u][v];
					map.R[v] = u;    // Store the information into the route index array
				}
			}
		}
		
		System.out.println("The distance is: " + dis[map.end]);
	}
}
