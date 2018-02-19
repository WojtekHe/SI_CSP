package zad2.graph_heuristics;

public class ByRows implements IGettingNextNode {
	
	private static ByRows instance = new ByRows();
	
	private ByRows() {
		
	}

	@Override
	public int selectNextNode(int nodeNumber, int dimension) {
		return nodeNumber+1;
//		return nodeNumber-1;
	}

	@Override
	public boolean isLastNode(int nodeNumber, int dimension) {
		return nodeNumber == dimension*dimension-1;
//		return nodeNumber == 0;
	}

	@Override
	public int getFirstNode(int dimension) {
		return 0;
//		return dimension*dimension-1;
	}

	public static IGettingNextNode getInstance() {
		return instance;
	}

}
