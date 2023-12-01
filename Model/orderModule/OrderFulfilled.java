package orderModule;

import java.time.LocalDateTime;

import orderDetails.Order;

public class OrderFulfilled implements IOrderState{

	public Order createOrder(String productName, int productQuantity, LocalDateTime timestamp) {
		Order anOrder = new Order(productName, productQuantity, timestamp);
		return anOrder;
	}

	public String response() {
		String fulfilledResponse = "Order is finalized for Product X and Quantity Y with total price Z";
		return fulfilledResponse;
	}
	// Notify the Bar Chart Observer and the Text Area Observer
	// Update the Order Quantity

}
