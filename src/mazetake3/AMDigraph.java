

/**
 * CSC-223 Lab #8 Using Graphs * DUE DATE:
 * DATE SUBMITTED:
 * PROGRAMMED BY: A. Wright
 
 *
 */
 
package mazetake3;

/**
 *
 * @author A. Wright
 * CLASS DESCRIPTION:
 */
public class AMDigraph extends GraphADT {

   public AMDigraph(int nVertices) {
      super(nVertices);
   }

   /**
    * Mutator: addEdge adds an edge between source and destination to the Graph
    *
    * @param source
    * @param dest
    * @param weight
    */
   public void addEdge(int source, int dest, int weight) {
      try {
         contents[source][dest] = weight;
      } catch (ArrayIndexOutOfBoundsException aie) {
         if (source >= numVertices) {
            System.out.println("Source Vertex Invalid: " + source);
         } else {
            System.out.println("Desination Vertix Invalid: " + dest);
         }
      }
   }

   /**
    * Mutator method: Removes an edge from the graph.
    *
    * @param source
    * @param dest
    * @param weight
    */

   public void removeEdge(int source, int dest) {
      try {
         contents[source][dest] = 0;
      } catch (ArrayIndexOutOfBoundsException aie) {
         if (source >= numVertices) {
            System.out.println("Source Vertex Invalid: " + source);
         } else {
            System.out.println("Desination Vertix Invalid: " + dest);
         }
      }
   }
}
