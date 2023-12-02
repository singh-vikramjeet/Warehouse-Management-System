package orderDetails;

import java.util.Deque;
import java.util.ArrayDeque;

public class Queue {
    // FIFO Queue
	private static Deque<Order> orderQueue = new ArrayDeque<Order>();
	
	// Method to add an order to the queue
	public static void addOrder(Order anOrder) {
		orderQueue.add(anOrder);
	}
	
	// Method to remove an order from the queue
	public static void getFirstOrder() {
		orderQueue.remove();
	}

	public static Deque<Order> getOrderQueue() {
		return orderQueue;
	}


	public static void setOneQueue(Deque<Order> oneQueue) {
		Queue.orderQueue = oneQueue;
	}
	
	
	
	

}
