import java.util.*;
import java.io.*;

public class Vertex{
   //Name of Town == Vertex
   private String town;
   //Has the vertex been fully visited?
   private Boolean visited;
   private ArrayList<Edge> neighbors;
   private int totalDistance = Integer.MAX_VALUE; //initialize to largest int to assume infinity
   private int previous;

   //Constructor for a new town/vertex
   public Vertex(String town) {
		if(town == null){
			throw new IllegalArgumentException("Town Name cannot be null");
      }
		this.town = town;
      this.neighbors = new ArrayList<Edge>();
      this.previous = -1;
      this.visited = false;
	}
   
   //returns town name as toString
   public String toString(){
      return this.town;
   }
   //returns vertex distance on current path
   public int getDistance(){
      return totalDistance;
   }
   //sets previous vertex from path from origin
   public void setPrevious(int index){
      this.previous = index;
   }
   //returns the previous vertex from path from origin
   public int getPrevious(){
      return this.previous;
   }
      
   //Used by Graph object to change distance according to dijsktra alg
   public void setDistance(int distance){
      totalDistance = distance;
   }
   //adds a neighboring edge to arraylist of neighbors
   public void addNeighbor(Edge neighbor){
      neighbors.add(neighbor);
   }
   
   public ArrayList<Edge> getNeighbor(){
      return neighbors;
   }
   
   //sets the edges of a town to an arraylist of edges
   public void setEdges(ArrayList<Edge> neighbors) {
      this.neighbors = neighbors;
   }
   
   //Returns an array of all edges // neighbors to vertex
   public ArrayList<Edge> getEdges() {
      return this.neighbors;
   }
   //Sets a vertex to be visited
   public void setVisited(){
      this.visited = true;
   }
   //asks if a vertex is visited
   public Boolean isVisited(){
      return visited;
   }
  
  

 
}
