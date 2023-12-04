package orderDetails;

import java.util.Deque;
import java.util.ArrayDeque;


// offer : add element to deque
// poll :  retrieve and remove element
// peek :  Retrieve but do not remove element

public class Queue {
    // FIFO Queue
	private static Deque<Order> orderQueue = new ArrayDeque<Order>();
	
	// Method to add an order to the queue
	public void addOrder(Order anOrder) {
		orderQueue.offer(anOrder);
	}
	
	// Method to remove an order from the queue
	// Retrieving and removing the head of the queue
	public Order removeFirstOrder() {
		return orderQueue.poll();
	}
	
	// Retrieving but not removing the head of the queue
	public Order getFirstOrder() {
		return orderQueue.peek();
	}
	

	public Deque<Order> getOrderQueue() {
		return orderQueue;
	}

	public void setOneQueue(Deque<Order> oneQueue) {
		Queue.orderQueue = oneQueue;
	}
		

}
