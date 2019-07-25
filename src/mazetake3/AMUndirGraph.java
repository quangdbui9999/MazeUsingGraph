

/**
 * CSC-122   ASSIGNMENT NAME
 * DUE DATE:
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
public class AMUndirGraph extends GraphADT {

   public AMUndirGraph(int nVertices) {
      super(nVertices);
   }

   /**
    * Mutator method: Adds an edge to the graph.
    *
    * @param source
    * @param dest
    * @param weight
    */
   public void addEdge(int source, int dest, int weight) {

      try {
         contents[source][dest] = weight;
         contents[dest][source] = weight;
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
    * @param source row index
    * @param dest col index
    *
    */

   public void removeEdge(int source, int dest) {
      try {
         contents[source][dest] = 0;
         contents[dest][source] = 0;
      } catch (ArrayIndexOutOfBoundsException aie) {
         if (source >= numVertices) {
            System.out.println("Source Vertex Invalid: " + source);
         } else {
            System.out.println("Desination Vertix Invalid: " + dest);
         }
      }
   }

}
