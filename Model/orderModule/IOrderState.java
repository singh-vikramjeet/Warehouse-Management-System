package orderModule;

import java.time.LocalDateTime;
import orderDetails.Order;

public interface IOrderState {
	
	public Order createOrder(String productName, int productQuantity, LocalDateTime timestamp);

	// Add Order price as the new parameter
	public String response(Order anOrder, int price, int currentStockQuantity);
	
	

}
