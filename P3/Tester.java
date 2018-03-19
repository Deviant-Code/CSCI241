import java.io.*;
import java.util.*;

public class Tester {

   public static void main(String[] args){
      Vertex happy = new Vertex("Bellingham");
      System.out.println(happy);

   }

}


while(readFile.hasNextLine()){
         
         str = readFile.nextLine();
         
         String[] splitTown = str.split(",");
         
         if(!countTowns.contains(splitTown[0].toLowerCase())){
            countTowns.push(splitTown[0].toLowerCase());
            count++;
         }
         
         if(!countTowns.contains(splitTown[1].toLowerCase())){
            countTowns.push(splitTown[1].toLowerCase());
            count++;
         }   