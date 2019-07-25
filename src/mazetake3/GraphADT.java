

/**
 * CSC-223 Week # 14/15 Graphs
 * ************************************************************** <br>
 * GraphADT.java <br>
 * Original Code from Java Foundations Chapter #20 Modified to be an abstract
 * class with sub classes for DiGraph and Undirected graphs implemented using
 * weighted adjacency matrix <br>
 *
 * PROGRAM Modified BY: A. Wright
 * **************************************************************
 */
 
package mazetake3;

/**
 *
 * @author revisions by: A. Wright * CLASS DESCRIPTION:
 */
public abstract class GraphADT {

   protected int[][] contents;
   protected int numVertices;

	//-----------------------------------------------------------------
   //  Constructor: Sets up this graph using the specified
   //  information
   //-----------------------------------------------------------------
    public GraphADT(int numVertices) {
      this.numVertices = numVertices;
      contents = new int[numVertices][numVertices];
      for (int i = 0; i < numVertices; i++) {
         for (int j = 0; j < numVertices; j++) {
            contents[i][j] = 0;
         }
      }
   }

   /**
    * Abstract method: Adds an edge to the graph.
    *
    * @param source
    * @param dest
    * @param weight
    */
   abstract public void addEdge(int source, int dest, int weight);

   /**
    * Abstract method: Removes an edge to the graph.
    *
    * @param source
    * @param dest
    * @param weight
    */
   abstract public void removeEdge(int source, int dest);

   /**
    * Accessor: getNumVertices()
    *
    * @return the number of vertices in the graph
    */
   public int getNumVertices() {
      return numVertices;
   }

   public boolean hasEdge(int from, int to) {
      return (contents[from][to] != 0);
   }
   /**
    *
    * @return Return a string representation of the graph. Edges that do not
    * exist are marked with an x. Otherwise, the weight of the edge is
    * displayed.
    */
   public String toString() {
      String result = "\t";
      for (int i = 0; i < numVertices; i++) {
         result += i + "\t";
      }
      result += "\n\n";
      for (int i = 0; i < numVertices; i++) {
         result += i + "\t";
         for (int j = 0; j < numVertices; j++) {
          //  if (contents[i][j] == -1) {
            //   result += "x\t";
            //  } else {
               result += contents[i][j] + "\t";
            }
         }
         result += "\n";
      
      return result;
   }
   
   public boolean isEmpty(){
       return (numVertices == 0);
   }
}

