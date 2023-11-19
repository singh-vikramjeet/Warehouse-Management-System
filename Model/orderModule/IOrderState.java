package orderModule;

import java.time.LocalDateTime;
import orderDetails.Order;

public interface IOrderState {
	
	public Order createOrder(String productName, int productQuantity, LocalDateTime timestamp);
	public String response();
	

}
