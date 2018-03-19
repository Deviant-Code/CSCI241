import java.util.*;
import java.io.*;

public class Edge{

   private int distance;
   private int town;
   private int destination;


   //Constructs a new edge between two towns.
   public Edge(int town, int destination, int distance) {
		
      
      if(distance < 0){
         throw new IllegalArgumentException("Distance must be a value greater than 0");
      }
      
      if(town == destination && distance != 0){
         throw new IllegalArgumentException("Please provide unique names for distinct towns");
      }
         
		this.town = town;
		this.destination = destination;
		this.distance = distance;
	}
   
   //Provides the edge weight
   public int getDistance(){
      return this.distance;
   }
   
   public int getDestination(){
      return this.destination;
   }
   
   
   
   
   //Represents an edge as a string of two towns and their distance apart.
   public String toString(){
      return this.town + ", " + this.destination + ", " + this.distance;  
   }

}