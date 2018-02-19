package zad2.graph_heuristics;

public interface IGettingNextNode {
	static IGettingNextNode getInstance(){return null;}
	int selectNextNode(int nodeNumber, int dimension);
	boolean isLastNode(int nodeNumber, int dimension);
	int getFirstNode(int dimension);
}
