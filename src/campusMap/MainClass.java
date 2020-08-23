package campusMap;

public class MainClass {

	public static void main(String[] args) {
		
		MAP map = new MAP();
		Dijkstra dij = new Dijkstra();
		
		map.initialize();
		map.assignWeight();
		map.setStartandEnd();
		dij.dijkstra(map);
		
		int distance = dij.distance;
		System.out.println("The distance is: " + distance);
		map.outPutRoute();
	}
}
