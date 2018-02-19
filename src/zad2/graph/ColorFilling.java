package zad2.graph;

import java.util.Calendar;

import zad2.graph_heuristics.ByRows;
import zad2.graph_heuristics.MoreConstraintsFirst;
import zad2.options.HeuristicsOptions;
import zad2.options.RunOptions;

public class ColorFilling {
	
	static GraphOperator graphOperator;
//	static LinkedList<Graph> solutions;
	static long numOfSolutions;
	
	private ColorFilling() {
	
	}
	
	public static void run(short dimension, RunOptions runopt, HeuristicsOptions heuopt){
		graphOperator = new GraphOperator(dimension);
//		solutions = new LinkedList<>();
		
		System.out.println(runopt+ " " + heuopt);
		System.out.println("Pocz¹tek: " + Calendar.getInstance().getTime());
		System.out.println();
		
		long start = System.currentTimeMillis();
		
		if(runopt == RunOptions.Backtracking){
			if(heuopt == HeuristicsOptions.ByRows)
			numOfSolutions = graphOperator.createSolutionsForGraphBT(
					ByRows.getInstance());
			else
				if(heuopt == HeuristicsOptions.ByNumberOfConstraints)
					numOfSolutions = graphOperator.createSolutionsForGraphBT(
							MoreConstraintsFirst.getInstance());
		}
		else
			if(runopt == RunOptions.ForwardChecking){
//				numOfSolutions = graphOperator.createSolutionsForGraphFC();
				
				if(heuopt == HeuristicsOptions.ByRows)
					numOfSolutions = graphOperator.createSolutionsForGraphFC(
							ByRows.getInstance());
					else
						if(heuopt == HeuristicsOptions.ByNumberOfConstraints)
							numOfSolutions = graphOperator.createSolutionsForGraphFC(
									MoreConstraintsFirst.getInstance());
			}
		
		
		double time = (System.currentTimeMillis()-start)/1000.0;
		System.out.println(graphOperator.opisGrafu());
		System.out.println("czas wykonania: " + time + " sekund");

		System.out.println();
		System.out.println("Koniec: " + Calendar.getInstance().getTime());
	}
	
	

}
