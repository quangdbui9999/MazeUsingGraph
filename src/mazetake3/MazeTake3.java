
/**
 * CSC-223 FINAL EXAM <br>
 * DUE DATE: 12/21/2018 <br>
 * DATE SUBMITTED: <br>
 * PROGRAMMED BY: Quang Bui <br>
 * DESCRIPTION: Using a graph solution, determine a "best path" through <br>
 * a maze; if there is no path, report "NO EXIT" <br>
 *
 * Note: startingVertex is adjustable (see variable below); exiting vertex is
 * "hardcoded" as numVertices-1.
 */
 

package mazetake3;

import java.util.Scanner;

/**
 *
 * @author Anita
 */
public class MazeTake3 {
    public static int inputStartPosition(int numberVertices){
        Scanner in = new Scanner(System.in);
        
        int input = 0;
        boolean validInterger = false;
        
        do{
            try{
                do{
                    System.out.println(" Enter a number between [0 - " 
                            + (numberVertices - 1) + "]: ");
                    System.out.print("You select the number: ");
                    
                    input = Integer.parseInt(in.nextLine());
                    validInterger = true;
                }while(input < 0 || input > (numberVertices - 1));
            }catch(NumberFormatException e){
                System.out.println("Invalid input");
                validInterger = false;
            }
        }while(!validInterger);
        return input;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int NUMBER_VERTICES = 15;
        Integer[] parentList = new Integer[NUMBER_VERTICES];
        AMUndirGraph myGraph = new AMUndirGraph(NUMBER_VERTICES);
        
        myGraph.addEdge(1, 0, 1);
        myGraph.addEdge(1, 2, 1);
        myGraph.addEdge(3, 2, 1);
        myGraph.addEdge(3, 5, 1);
        myGraph.addEdge(3, 4, 1);
        myGraph.addEdge(8, 5, 1);
        myGraph.addEdge(5, 6, 1);
        myGraph.addEdge(6, 7, 1);
        myGraph.addEdge(10, 11, 1);
        myGraph.addEdge(11, 9, 1);
        myGraph.addEdge(10, 12, 1);
        myGraph.addEdge(2, 9, 1);
        myGraph.addEdge(13, 11, 1);
        myGraph.addEdge(13, 14, 1);
        
        int startingVertex = inputStartPosition(NUMBER_VERTICES);   // starting vertex in maze
        BreadthFirstSearch bfs = new BreadthFirstSearch(myGraph, startingVertex);

        parentList = bfs.runBFSearch();
        for (Integer i : parentList) {
            System.out.println(i);
        }
        System.out.println(bfs.printBestPath());
    }
}
