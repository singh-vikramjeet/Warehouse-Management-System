package orderModule;

import java.time.LocalDateTime;

import orderDetails.Order;
import productModule.IProduct;

public class OrderPending implements IOrderState{

	public Order createOrder(String productName, int productQuantity, LocalDateTime timestamp) {
		Order anOrder = new Order(productName, productQuantity, timestamp);
		return anOrder;
	}

	public String response(Order anOrder, int price, IProduct aProduct) {
		String pendingResponse = "Order for Product X Quantity Y is pending – order exceeds available quantity";
		return pendingResponse;
	}
	
	// Notify the Text Area observer only
	// Do not send the response back to client

}
