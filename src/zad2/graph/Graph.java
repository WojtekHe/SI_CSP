package zad2.graph;

public class Graph {
	
	private Color[] graph;
	private short dimension; 
	
//	public Graph(short dimension) {
//		this.dimension = dimension;
//		graph = new 
//	}
	
	public Graph(short dimension, Color[] graph) {
		this.dimension = dimension;
		this.graph = graph;
	}
	
	public int size(){
		return dimension*dimension;
	}
	
	public short getDimension(){
		return dimension;
	}
	
	public Color at(int index){
		return graph[index];
	}

	public Color at(int x, int y) {
		return graph[x * dimension + y];
	}
	
	public void setAt(int index, Color color){
		graph[index] = color;
	}
	
	public void setAt(int x, int y, Color color) {
		graph[x * dimension + y] = color;
	}
	
//	public static int getX(int nodeNumber, int dimension){
//		return nodeNumber/dimension;
//	}
//	
//	public static int getY(int nodeNumber, int dimension){
//		return nodeNumber%dimension;
//	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if(at(j, i) == null)
					sb.append("["+99+"]");
				else
					sb.append(at(j, i).toString());
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
