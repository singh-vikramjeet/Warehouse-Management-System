package orderDetails;

import java.time.LocalDateTime;

public class Order {
	
	private String productName;
	private int productQuantity;
	private LocalDateTime timestamp;
	
	// Constructor
	public Order(String productName, int productQuantity, LocalDateTime timestamp) {
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.timestamp = timestamp;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	

}
