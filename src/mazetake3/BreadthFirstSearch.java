

/**
 * CSC-223 Weeks #14/15 Graphs <br>
 * DUE DATE: 12/21/2018 <br>
 * DATE SUBMITTED: <br>
 * PROGRAMMED BY: Quang Bui <br>
 
 *
 */
 
package mazetake3;

import java.util.Deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author A. Wright
 * CLASS DESCRIPTION: Using a Queue to visit each vertex, <br>
 * traverse a graph in a breadth-first fashion; produces a "parent" array of the
 * vertices so it is possible to retrace the path from one vertex to the <br>
 * "starting" vertex. Method isConnected() returns true if graph is connected
 * returns false otherwise.
 */
public class BreadthFirstSearch {

   private CircularArrayQueue<Integer> visitVertices;
   private Integer[] vParent;
   private boolean[] notVisited;
   private AMUndirGraph graph;
   private ArrayStack<Integer> mazePath;
   private int startingVertex;
   private static int count = 0;

   public BreadthFirstSearch(AMUndirGraph g, int root) {
       graph = g;
      mazePath = new ArrayStack(graph.getNumVertices());
      visitVertices = new CircularArrayQueue<Integer>(); // visiting order
      vParent = new Integer[graph.getNumVertices()]; // keep track of parents
      notVisited = new boolean[graph.getNumVertices()]; //indicate visited or not
       
      for (int i = 0; i < graph.getNumVertices(); i++) {
         vParent[i] = -1;  // initialize the parents of vertices to -1
         notVisited[i] = true;  // set visitation to NOT YET (true)
      }
      notVisited[root] = false;    // mark starting vertex as visited
      visitVertices.enqueue(root); // add starting vertex to the queue
      startingVertex = root;
   }
   
   private void pushOnStack(){
       Integer v = graph.getNumVertices() - 1;
        do {   
            mazePath.push(v);
            v = vParent[v];
        } while (v != -1);
   }
   
   private String popStack(){
       String result = "";
       while (!mazePath.isEmpty()) {
        result += mazePath.pop() + " -> ";
    }
       return result;
   }
   
   public String printBestPath(){
       String result = "";
       this.pushOnStack();
       int lastIndex = vParent[graph.numVertices - 1];
       
       if(this.isConnected() && (lastIndex != -1 || lastIndex == -1)){
           result += "The Graph is CONNECTED.\n";
           result += " The BEST PATH through the maze starting from "
                        + " entry point \"" + startingVertex + "\" traveling to exit point \""
                        + (graph.getNumVertices() - 1) + "\" is: \n ";

            result += this.popStack();
            result += " EXIT";
       }else if(!this.isConnected() && lastIndex == -1){
           result += "The Graph is NOT CONNECTED.\n";
           result += "The maze cannot be traversed between the chosen "
                   + "starting \"" + startingVertex + "\" and ending Vertex \"" 
                   + (graph.getNumVertices() - 1) + "\".";
       }else if(!this.isConnected() && lastIndex != -1){
           result += "The Graph is NOT CONNECTED but it exists the BEST PATH.\n";
           result += " The BEST PATH through the maze starting from "
                        + " entry point \"" + startingVertex + "\" traveling to exit point \""
                        + (graph.getNumVertices() - 1) + "\" is: \n ";

            result += this.popStack();
            result += " EXIT";
       }
       result += "\nTHE PROGRAM IS FINISHED.\n";
        return result;
   }
   
   public Integer[] runBFSearch() {
      while (!visitVertices.isEmpty()) {  // still have vertices to process
         int currentV = visitVertices.dequeue();
         for (int i = 0; i < graph.getNumVertices(); i++) // add neighbors to Q
         {
            if (notVisited[i]
                    && graph.hasEdge(currentV, i)) // if adjacent, and NOT visited
            {
               visitVertices.enqueue(i);  // add this neighbor to the queue
               notVisited[i] = false;     // mark it as visited (once is enough)
               vParent[i] = currentV;     // assign the neighbor's parent to
                                          // this (currentV)
            }
         }
      }
      return vParent;
   }
   
   /**
    * Accessor: isConnected()
    * @return true if the graph is connected, otherwise return false.
    * Pre-condition: iteratorBFS(startingVertex) method defined
    * Post-condition: return true if the graph is connected, 
    * otherwise return false.
    * A graph is connected if and only if any two vertices in the graph.
    * there is a path between them. The Graph is connected if and only if
    * for each vertex V in a graph containing n vertices (numVertices), 
    * the size of the result of Breadth-First traversal 
    * starting at V is n (numVertices).
    */
   private boolean isConnected(){
       if(graph.isEmpty()){
           return false;
       }
       Iterator<Integer> it = iteratorBFS(startingVertex);
       
       while(it.hasNext()){
           it.next();
           count++;
       }
       
       return (count == graph.getNumVertices());
   }
   
   /**
    * Accessor: indexIsValid(int index)
    * @param index the index in the visited array and the index position
    * in the Graph
    * @return true if the index in the rang from [0-NumVertices], 
    * otherwise return false.
    * Pre-condition: none
    * Post-condition: return true if the index in the rang from 
    * [0-NumVertices], otherwise return false.
    */
   private boolean indexIsValid(int index){
       return ((0 <= index) && (index < graph.getNumVertices()));
   }
   
   /**
    * Return the iterator that performs a Breadth First Search
    * traversal starting at the given index
    * @param startIndex the index from which to begin the traversal
    * @return an iterator that performs a Breadth First Search traversal
    * Pre-condition: indexIsValid(int index) method, ArrayUnorderedList, 
    * and CircularArrayQueue class must be defined.
    * Post-condition: Return the iterator that performs a Breadth First Search
    * traversal starting at the given index
    */
   private Iterator<Integer> iteratorBFS(int startIndex){
       Integer x = 0;
       ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<Integer>();
       
       if(!indexIsValid(startIndex)){
           return resultList.iterator();
       }
       boolean[] visited = new boolean[graph.getNumVertices()];
       for(int i = 0; i < graph.getNumVertices(); i++){
           visited[i] = false;
       }
       visitVertices.enqueue(new Integer(startIndex));
       visited[startIndex] = true;
       
       while(!visitVertices.isEmpty()){
            x = visitVertices.dequeue();
            resultList.addToRear(vParent[x.intValue()]);

            /**
             * Find all vertices (vParent object array) adjacent to x that
             * have not been visited and queue them up
             */
        for(int i = 0; i < graph.getNumVertices(); i++){
            if(graph.hasEdge(x.intValue(), i) && visited[i] == false){
                visitVertices.enqueue(new Integer(i));
                visited[i] = true;
            }
        }
       }
       return resultList.iterator();
   }
}
