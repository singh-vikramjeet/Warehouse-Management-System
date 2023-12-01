package orderModule;

import java.time.LocalDateTime;

import orderDetails.Order;

public class OrderRejected implements IOrderState{

	
	
	public Order createOrder(String productName, int productQuantity, LocalDateTime timestamp) {
		Order anOrder = new Order(productName, productQuantity, timestamp);
		return anOrder;
	}
	

	public String response(Order anOrder, int price, int currentStockQuantity) {
		String rejectResponse = "Order exceeds the max quantity set for this product and cannot be processed";
		return rejectResponse;
	}
	// In this state, we only notify the Text Area Observer
	// No change to the Bar Chart Observer
	// Send the response back to client
	
}
