import java.util.*;
import java.io.*;

public class Assignment3{

   public static void main(String[] args) throws FileNotFoundException{
      
      Scanner input = new Scanner(System.in);
   
      System.out.println("Input File Name: ");
      String filename = input.nextLine();
      
      
      
      BufferedReader in = null;
      try {
         in = new BufferedReader(new FileReader(filename));
      } catch (Exception e) {
         System.out.println("Cannot open file " + filename);
      }
         
         
      Scanner readFile = new Scanner(new File("RoadData.csv"));
      
      
      //Pulls in towns and distances from RoadData.csv
      while(readFile.hasNextLine()){
      
      
      
      }
      
      //Pulls in First Town of interest
      System.out.println("First town: ");
      String FirstTown = input.nextLine();
      
      //Pulls in Second Town of interest
      System.out.println("Second town: ");
      String Secondtown = input.nextLine();
         
   }






}