import java.util.*;
import java.io.*;
import java.lang.String; 


public class Assignment3{

   public static void main(String[] args) throws FileNotFoundException{
      
      Scanner input = new Scanner(System.in);
      
      File dataFile = requestFile(input);
      
      Graph townGraph = parseFileToGraph(dataFile);
      String origin = requestString(input,"First town: ",townGraph); 
      String destination = requestString(input,"Second town: ",townGraph);
                      
      if(origin.equalsIgnoreCase(destination)){
        System.out.println("You are already at your destination: " + destination);
      } else {
        Vertex SecondTownShortestPath = townGraph.dijkstra(origin, destination);
      } 
      

   }
   
   //Requests name of town from user and checks if town is a valid vertex in graph.
   public static String requestString(Scanner input, String prompt, Graph townGraph){
      
      System.out.println(prompt);

      String dest = input.nextLine();
     
      if(dest.equals("") || dest == null){
         System.exit(0);
      } else if(!townGraph.contains(dest)){
         while(!townGraph.contains(dest)){
            System.out.println(prompt +" must be listed in file");
            System.out.println(prompt);
            dest = input.nextLine();
         }
      }      
      return dest;
   }
   
   //Requests a valid DataFile from user-input
   public static File requestFile(Scanner input){
      System.out.println("Input File Name: ");
      File dataFile = new File ("");
   //changed to next
      while(!dataFile.exists() && !dataFile.isDirectory()){
         String filename = input.nextLine();
         dataFile = new File(filename);
      }
      return dataFile;  
   }
   
   //Parses file to create edges and vertices to create new instance of graph
   public static Graph parseFileToGraph(File dataFile)
      throws FileNotFoundException {
      
      Scanner readFile = new Scanner(dataFile);
   
      Stack<String> countTowns = new Stack<String>();
      int count = 0;
      String str = "";
      
      //Reads file as input and creates an stack of edges
      while(readFile.hasNextLine()){
         str = readFile.nextLine();
         
         String[] splitTown = str.split(",");
         //checks if town is already counted, if not, adds it to stack increments count
         if(!countTowns.contains(splitTown[0])){
            countTowns.push(splitTown[0]);
            count++;
         }
         if(!countTowns.contains(splitTown[1])){
            countTowns.push(splitTown[1]);
            count++;
         }              
      }
      //creates a new array of vertices
      Vertex[] townList = new Vertex[count];
      
      for(int i = 0; i < count; i++){
         Vertex town = new Vertex(countTowns.pop().toString());
         townList[i] = town;
      }
      
      Scanner readEdges = new Scanner(dataFile);
      String edgeStr;
      while(readEdges.hasNextLine()){
      
         edgeStr = readEdges.nextLine();
         String[] splitTown = edgeStr.split(",");
         
         String homeTown = splitTown[0];
         String destTown = splitTown[1];
         int distance = Integer.parseInt(splitTown[2]);
         
         Boolean homeFound = false;
         Boolean destFound = false;
         
         int homeloc = 0;
         int destloc = 0;
         int counter = 0;

         while(homeFound == false || destFound == false){

           if(townList[counter].toString().equalsIgnoreCase(homeTown)){
              homeloc = counter;
              homeFound = true;
           } 
           
           if(townList[counter].toString().equalsIgnoreCase(destTown)){
              destloc = counter;
              destFound = true;
           }
            counter++;
         }

         townList[homeloc].addNeighbor(new Edge(homeloc, destloc, distance));
         townList[destloc].addNeighbor(new Edge(destloc, homeloc, distance));
      
      }
      return new Graph(townList, count);   
   }  
}