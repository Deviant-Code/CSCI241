//Jesse Ericksen
//W01173602
//CSCI 241, Winter 2018
//Lab 05 -- Queue Operations

/* The purpose of this program is to build Queues and handle exceptions
   for dealing with full or empty queues. */

import java.util.*;

public class MyQueue {

	int size;			// maximum size of queue
	int head = 0;		// index for next enqueue
	int tail = 0;		// index for next dequeue
	int entries = 0;	// number of items in queue
	DataItem[] queue;
	
	// Queue constructor
	public MyQueue(int size) {
		this.size = size;
		queue = new DataItem[size];	
	}
	
	// check for an empty queue
	public boolean isEmpty() {
		return entries == 0;
	}
	
	// add a data item at the tail of the queue
   //*Added new exception if Queue is full and enqueue is ran. 
	public boolean enqueue(DataItem data) throws IllegalStateException{
		if (entries == size)
         throw new IllegalStateException("queue is full");
 
		queue[tail] = data;
		
		// if the queue was empty
		if (entries == 0)
			head = tail;
			
		// increment tail index
		tail = (tail + 1) % size;	
		entries++;
		
		return true;
	}

	// remove data item from the head of the queue 
   //*Added New Exception if Queue is empty and DeQueue is ran.
	public DataItem dequeue() throws IllegalStateException{
		if (entries == 0)
         throw new IllegalStateException("Queue is empty");   	
			
		DataItem data = queue[head];
		
		// increment the head index
		head = (head + 1) % size;
		entries--;	
			
		return data;
	}
	
	public String toString() {
		if (head == -1)
			return "Queue empty";
			
		String result = "";
		for (int i = 0; i <entries; i++)
			result += queue[(i + head) % size].toString() + "\n";	
			
		return result;
	}
   
   //*Added Try,Catch Statements for dequeue and enqueue method calls to
   //prevent program from failing upon breaking restrictions of queue.
	public static void main(String[] args) {

		   MyQueue q = new MyQueue(3);
		   DataItem[] data = new DataItem[4];
	   	data[0] = new DataItem(3, 4, "alfie");
   		data[1] = new DataItem(6, 12, "Frog");
   		data[2] = new DataItem(81, 46, "Bastion");
   		data[3] = new DataItem(0, 0, "nothing");
		   
         try {
         
   		   if (q.dequeue() == null)
   			   System.out.println("Cannot remove from an empty queue");
               
         } catch (IllegalStateException e){
               System.out.println(e);
            }

		
   		for (int i = 0; i < data.length; i++) {
            try {
   			   System.out.println("adding " + data[i]);
   			   if (q.enqueue(data[i]))
   				   System.out.println(q);
   			   else
   				   System.out.println(" failed: queue full");
            } catch (IllegalStateException e){
               System.out.println(e);
            }

   		}
         
   		for (int i = 0; i < data.length; i++) {
           
            try{
   				System.out.println("Dequeued " + q.dequeue());
            } catch (IllegalStateException e){
               System.out.println(e);
            }

   		}
         
      
   	}
   }
