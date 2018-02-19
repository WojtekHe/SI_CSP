package zad2.graph_heuristics;

public class MoreConstraintsFirst implements IGettingNextNode {
	
	private static MoreConstraintsFirst instance = new MoreConstraintsFirst();

	private MoreConstraintsFirst() {

	}

	@Override
	public int selectNextNode(int nodeNumber, int dimension) {
//		System.out.println("przypis pola " + nodeNumber);
		if (dimension > 2) {
			if (nodeNumber > dimension && nodeNumber % dimension == dimension - 2
					&& nodeNumber != dimension * dimension - 2) // 2
			{
//				System.out.println("next2");
				return (nodeNumber + 3);
			} else if (nodeNumber % dimension == 0)// 5
			{
//				System.out.println("next5");
				return (nodeNumber + dimension);
			} 
			else if (nodeNumber > dimension && nodeNumber % dimension < dimension - 2 ||
					nodeNumber == dimension*dimension-2) // 1
			{
//				System.out.println("next1");
				return nodeNumber+1;
				
			} else if (nodeNumber < dimension)// 4
			{
//				System.out.println("next4");
				return (nodeNumber-1);
			} else if (nodeNumber % dimension == dimension - 1) // 3
			{
//				System.out.println("next3");
				return (nodeNumber - dimension);
			}
			else
				return -1;//nodeNumber+1;
		} else
			return nodeNumber+1;
	}

	@Override
	public boolean isLastNode(int nodeNumber, int dimension) {
		if (dimension > 2)
			return nodeNumber == dimension * dimension - dimension;
		else
			return nodeNumber == dimension * dimension - 1;
	}

	@Override
	public int getFirstNode(int dimension) {
		return dimension > 2 ? dimension + 1 : 0;
	}

	public static IGettingNextNode getInstance() {
		return instance;
	}

}
