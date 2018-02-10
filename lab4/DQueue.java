// CSCI 241 Lab Exercise 4

public class DQueue {

	// inner class for QueueEntry
	// adds the next pointer to a DataItem
	private class QueueEntry {
	
		DataItem data;
		QueueEntry next;
		
		public QueueEntry(DataItem data) {
			this.data = data;
			this.next = null;
		}
		
		public String toString() {
			return data.toString();
		}
	}

	QueueEntry head, tail;
	
	// constructor for DQueue
	public DQueue() {
		head = null;
		tail = null;
	}
	
	// is the DQueue empty?
	public boolean isEmpty() {
		return head == null;
	}
	
	// add to the tail of the queue
	public void enqueue(DataItem data) {
		QueueEntry newEntry = new QueueEntry(data);
		
		// if the DQueue is empty
		//Changed TAIL to HEAD because if DQueue is empty, we need to initialize the head.
		//Setting tail to equal head since current list is only one element.
		if (head == null) {
			head = newEntry; 
			tail = head;
		} 
		
		// if the DQueue is not empty
		//Added Line to set the new tail to the last entry item we just added.
		else {
			tail.next = newEntry;
			tail = tail.next;
		}
	}

	// remove data item from the head of the DQueue
	public DataItem dequeue() {
		if (head == null)
			return null;
		
		// get the data item to return
		DataItem data = head.data;
		head = head.next;
		
		return data;
	}
	
	public String toString() {
		if (head == null)
			return "Queue empty";
			
		String result = "";
		QueueEntry curr = head;
		while (curr != null) {
			result += curr.toString() + "\n";
			curr = curr.next;
		}
			
		return result;
	}

	public static void main(String[] args) {
	
		DQueue q = new DQueue();
		//changed array to length 6 to support 6 datas
		DataItem[] data = new DataItem[6];
		data[0] = new DataItem(3, 4, "alfie");
		data[1] = new DataItem(6, 12, "Frog");
		data[2] = new DataItem(81, 46, "Bastion");
		data[3] = new DataItem(12, 17, "normal");
		data[4] = new DataItem(45, 3, "zebra");
		data[5] = new DataItem(0, 0, "nothing");
		
		System.out.println("dequeued " + q.dequeue());
		
		for (int i = 0; i < data.length / 2; i++) {
			System.out.println("adding " + data[i]);
			q.enqueue(data[i]);
			System.out.println(q);
		}
		
		System.out.println("dequeued " + q.dequeue());

		for (int i = data.length / 2; i < data.length; i++) {
			System.out.println("adding " + data[i]);
			q.enqueue(data[i]);
			System.out.println(q);
		}

		for (int i = 0; i < data.length; i++) {
            System.out.println("Dequeued " + q.dequeue());
		}
	}
}
