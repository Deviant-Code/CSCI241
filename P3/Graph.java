import java.util.*;
import java.io.*;

public class Graph{

   private Vertex[] towns;
   private int townCount;
   private int accumulateDistance;
   private ArrayList<Integer> visitedNodes;
   private ArrayList<Integer> priorityQueue;
   
   public Graph(Vertex[] towns, int count) {
   
    // create all nodes ready to be updated with the edges
      this.towns = towns;
      this.townCount = count;
      this.accumulateDistance = 0;
      this.visitedNodes = new ArrayList<Integer>();
      this.priorityQueue = new ArrayList<Integer>();
   }
    
   public boolean contains(String name){
      int x = 0;
      while(x < townCount){
         if (this.towns[x].toString().equalsIgnoreCase(name)){
            return true;
         }
         x++;
      }
      
      return false;
   
   }
   
   //implements Dijkstras Algorithm
   public Vertex dijkstra(String FirstTown, String SecondTown){
      //set FirstTown to zero distance
      int i = 0;
      int firstTown = -1;
      
      while(i < townCount){
         if (this.towns[i].toString().equalsIgnoreCase(FirstTown)){
            this.towns[i].setDistance(0);
            firstTown = i;
         }
         i++;
      }
      
      int k =0;
      int SecondTownIndex = -1;
      
      while(k < townCount){
         if(this.towns[k].toString().equalsIgnoreCase(SecondTown)){
            SecondTownIndex = k;
         }
         k++;
      }
      //beginwhileloop
      while(!this.towns[SecondTownIndex].isVisited()){
         ArrayList<Edge> neighbors = this.towns[firstTown].getEdges();
      
         int index = -1;
         int distance = -1;
         
      //removes current town from P.Q.
         if(priorityQueue.contains(firstTown)){
            priorityQueue.remove(priorityQueue.indexOf(firstTown));
         }
      
      //Now, set current town to visited
         this.towns[firstTown].setVisited();
         visitedNodes.add(firstTown);
      
         for(int j = 0; j < neighbors.size(); j++){
         //checks for unvistited neighbors
         
            if(!this.towns[neighbors.get(j).getDestination()].isVisited()){
               //if new accumulated distance is less than the previous distance, set!
               if(this.towns[neighbors.get(j).getDestination()].getDistance() > this.towns[firstTown].getDistance() + neighbors.get(j).getDistance()){
                this.towns[neighbors.get(j).getDestination()].setDistance(this.towns[firstTown].getDistance() + neighbors.get(j).getDistance());
                //sets previous node now there is new shorter path
                this.towns[neighbors.get(j).getDestination()].setPrevious(firstTown);
               }
               if(!priorityQueue.contains(neighbors.get(j).getDestination())){
                  priorityQueue.add(neighbors.get(j).getDestination());
               }
            }
      }
            
      //set current node to that of shortest distance
      int lowest = Integer.MAX_VALUE;
      int lowestIndex = -1;
      
      for(int j=0; j < priorityQueue.size(); j++){
         if(this.towns[priorityQueue.get(j)].getDistance() < lowest){
            lowest = this.towns[priorityQueue.get(j)].getDistance();
            lowestIndex = priorityQueue.get(j);
         }
         j++;
      }
      firstTown = lowestIndex;
      }   
      printRoute(SecondTownIndex, FirstTown);
      return this.towns[SecondTownIndex];            
   }
   
   //Prints out the root from origin to destination
   public void printRoute(int town, String FirstTown){
      while(!this.towns[town].toString().equalsIgnoreCase(FirstTown)){
         System.out.println(this.towns[town].toString() + " (" + this.towns[town].getDistance() + ")");
         town = this.towns[town].getPrevious();
      }
      System.out.println(FirstTown);
   }
}