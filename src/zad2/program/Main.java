package zad2.program;

import zad2.graph.ColorFilling;
import zad2.options.HeuristicsOptions;
import zad2.options.RunOptions;

public class Main {
	
	public static void main(String [] args){
		short dim = 4;
		ColorFilling.run(dim, RunOptions.ForwardChecking,
				HeuristicsOptions.ByNumberOfConstraints);
		
//		GraphOperator go = new GraphOperator(dim);
		
		
//		for (Graph graph : solutions) {
//			System.out.println(graph);
//		}
	}
}
