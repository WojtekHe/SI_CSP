package zad2.graph;

import java.util.LinkedList;

import zad2.graph_heuristics.IGettingNextNode;

public class GraphOperator {

	// short dimension;
	int numberOfColors;
	Graph graph;
	Color[] avalibleColors;
	// static LinkedList<Color[]> solutions;
	// static LinkedList<Graph> solutions;
	static long numberOfSolutions;
	static long triedNodes;

	public GraphOperator(short dimension) {
		// this.dimension = dimension;
		// graph = new Color[dimension * dimension];
		numberOfSolutions = 0;
		graph = new Graph(dimension, new Color[dimension * dimension]);
		numberOfColors = dimension % 2 == 0 ? 2 * dimension : 2 * dimension + 1;
		avalibleColors = new Color[numberOfColors];
		colorsInitialization();
	}

	public GraphOperator(GraphOperator otherGO) {
		// dimension = otherGO.dimension;
		short dimension = otherGO.graph.getDimension();
		graph = new Graph(dimension, new Color[dimension * dimension]);
		numberOfColors = otherGO.numberOfColors;
		avalibleColors = new Color[numberOfColors];
		for (int i = 0; i < numberOfColors; i++) {
			avalibleColors[i] = new Color(otherGO.avalibleColors[i]);
		}
		// przypisanie odpowiedniego koloru z dostêpnych dla nowego grafu -
		// przypisanie odpowiencji;
		for (int i = 0; i < graph.size(); i++) {
			if (otherGO.graph.at(i) != null)
				graph.setAt(i, avalibleColors[otherGO.graph.at(i).getColorValue()]);
		}
	}

	private Color[] colorsInitialization() {
		for (int i = 0; i < numberOfColors; i++) {
			avalibleColors[i] = new Color(i, avalibleColors);
		}
		return avalibleColors;
	}

	private void assignColorsToNodesBT(int nodeNumber, int colorIndex, IGettingNextNode getNextNode) {
		assignColor(nodeNumber, avalibleColors[colorIndex]);
		if (getNextNode.isLastNode(nodeNumber, graph.getDimension())) {
			// solutions.add(graph);
			numberOfSolutions++;
			// System.out.println(printGraph());
		} else {
			for (int i = 0; i < numberOfColors; i++) {
				triedNodes++;
				if (checkPotentialAssignment(
						getNextNode.selectNextNode(nodeNumber, graph.getDimension()), avalibleColors[i])) {
					(new GraphOperator(this)).assignColorsToNodesBT(
							getNextNode.selectNextNode(nodeNumber, graph.getDimension()), i, getNextNode);
				}
			}
		}
	}

	private void assignColorsToNodesFC(int nodeNumber, int colorIndex, IGettingNextNode getNextNode) {
		assignColor(nodeNumber, avalibleColors[colorIndex]);
//		System.out.println(graph);
		if (getNextNode.isLastNode(nodeNumber, graph.getDimension())) {
			// solutions.add(graph);
			numberOfSolutions++;
//			System.out.println(graph);
		} else {
			LinkedList<Integer> allowedColors = getAvalibleAssigmentsTo(
					getNextNode.selectNextNode(nodeNumber, graph.getDimension()));
			// for (Integer colorVal: allowedColors)
			// System.out.println(colorVal);
			for (Integer colorVal : allowedColors) {
				triedNodes++;
				(new GraphOperator(this)).assignColorsToNodesFC(
						getNextNode.selectNextNode(nodeNumber, graph.getDimension()), colorVal, getNextNode);
			}
		}
	}

	// private boolean checkPotentialAssigment(int x, int y, IColor color){
	// return checkPotentialAssigment(x, y, (ColorBT)color);
	// }

	private boolean checkPotentialAssignmentBT(int x, int y, Color color) {
		boolean colorCanBeAssigned = true;

		if (x < graph.getDimension() - 1 && graph.at(x + 1, y) != null)
			colorCanBeAssigned = color.canBePairedWithColor(graph.at(x + 1, y));
		if (colorCanBeAssigned && x > 0 && graph.at(x - 1, y) != null)
			colorCanBeAssigned = color.canBePairedWithColor(graph.at(x - 1, y));
		if (colorCanBeAssigned && y < graph.getDimension() - 1 && graph.at(x, y + 1) != null)
			colorCanBeAssigned = color.canBePairedWithColor(graph.at(x, y + 1));
		if (colorCanBeAssigned && y > 0 && graph.at(x, y - 1) != null)
			colorCanBeAssigned = color.canBePairedWithColor(graph.at(x, y - 1));
		return colorCanBeAssigned;
	}

	private boolean checkPotentialAssignment(int nodeNumber, Color color) {
		return checkPotentialAssignmentBT(nodeNumber / graph.getDimension(), nodeNumber % graph.getDimension(), color);
	}

	private LinkedList<Integer> getAvalibleAssigmentsTo(int nodeNumber) {
		return getAvalibleAssigmentsTo(nodeNumber / graph.getDimension(), nodeNumber % graph.getDimension());
	}

	private LinkedList<Integer> getAvalibleAssigmentsTo(int x, int y) {
		LinkedList<Integer> assignments = new LinkedList<>();
		boolean[] possibleAssigments = new boolean[avalibleColors.length];
		// for(int i = 0; i < possibleAssigments.length; i++)
		// possibleAssigments[i] = true;
		Color temp = null;

		if (x < graph.getDimension() - 1 && graph.at(x + 1, y) != null) {
			temp = graph.at(x + 1, y);
			checkAssignments(temp, possibleAssigments);
		}
		if (x > 0 && graph.at(x - 1, y) != null) {
			temp = graph.at(x - 1, y);
			checkAssignments(temp, possibleAssigments);
		}
		if (y < graph.getDimension() - 1 && graph.at(x, y + 1) != null) {
			temp = graph.at(x, y + 1);
			checkAssignments(temp, possibleAssigments);
		}
		if (y > 0 && graph.at(x, y - 1) != null) {
			temp = graph.at(x, y - 1);
			checkAssignments(temp, possibleAssigments);
		}

		for (int i = 0; i < possibleAssigments.length; i++) {
			if (!possibleAssigments[i])
				assignments.add(i);
		}
		return assignments;
	}

	private void checkAssignments(Color color, boolean[] assignments) {
		boolean[] colorAssigments = color.getAllowedColors();
		for (int i = 0; i < colorAssigments.length; i++) {
			// ALLOWED == false
			assignments[i] = assignments[i] || colorAssigments[i];
		}
	}

	private Graph assignColor(int x, int y, Color color) {
		graph.setAt(x, y, color);
		if (x < graph.getDimension() - 1 && graph.at(x + 1, y) != null){ // prawy
			color.pairWithColor(graph.at(x + 1, y));
//			System.out.println("przyp prawy");
		}
		if (x > 0 && graph.at(x - 1, y) != null){ // lewy
			color.pairWithColor(graph.at(x - 1, y));
//			System.out.println("przyp lewy");
		}
		if (y < graph.getDimension() - 1 && graph.at(x, y + 1) != null){ // dolny
			color.pairWithColor(graph.at(x, y + 1));
//			System.out.println("przyp dolny");
		}
		if (y > 0 && graph.at(x, y - 1) != null){ //górny
			color.pairWithColor(graph.at(x, y - 1));
//			System.out.println("przyp górny");
		}
		return graph;
	}

	private Graph assignColor(int nodeNumber, Color color) {
		return assignColor(nodeNumber / graph.getDimension(), nodeNumber % graph.getDimension(), color);
	}

	public long createSolutionsForGraphBT(IGettingNextNode getNode) {
		// solutions = new LinkedList<>();
		for (int i = 0; i < numberOfColors; i++)
			assignColorsToNodesBT(getNode.getFirstNode(graph.getDimension()), i, getNode);
		return numberOfSolutions;
	}

	public long createSolutionsForGraphFC(IGettingNextNode getNode) {
		// solutions = new LinkedList<>();
		for (int i = 0; i < numberOfColors; i++)
			assignColorsToNodesFC(getNode.getFirstNode(graph.getDimension()), i, getNode);
		return numberOfSolutions;
	}

	public String opisGrafu() {
		return "Wymiar grafu: " + graph.getDimension() + "\nLiczba kolorów: " + numberOfColors + "\nLiczba wêz³ów: "
				+ graph.size() + "\nLiczba rozwi¹zañ: " + numberOfSolutions + "\nLiczba porównañ wêz³ów: " + triedNodes;
	}

}
