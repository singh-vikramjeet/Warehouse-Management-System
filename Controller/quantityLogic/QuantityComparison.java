package quantityLogic;

import productModule.IProduct;
import orderDetails.Order;

// Use Case 4
// Order State
// State 1: Order Rejected
// State 2: Order Pending
// State 3: Order Fulfilled

public class QuantityComparison {
	
	// IProduct is the data obtained from the database
	private IProduct aProduct;
	// Order is the data obtained from the Client (through Middleware)
	private Order anOrder;
	private int maxStockQuantity;
	private int orderedQuantity;
	private int currentStockQuantity;
	private int orderState;
	
	// Constructors
	public QuantityComparison(Order o) {
		this.orderedQuantity = o.getProductQuantity();
		this.anOrder = o;
		this.orderState = 0;
	}
	public QuantityComparison(IProduct p) {
		this.aProduct = p;
		this.maxStockQuantity = p.getMaxStockQuantity();
		this.currentStockQuantity = p.getCurrentStockQuantity();
		this.orderState = 0;
	}
	public QuantityComparison(Order o, IProduct p) {
		this.orderState = 0;
		this.orderedQuantity = o.getProductQuantity();
		this.anOrder = o;
		this.aProduct = p;
		this.maxStockQuantity = p.getMaxStockQuantity();
		this.currentStockQuantity = p.getCurrentStockQuantity();
	}
	
	
	public int getOrderState() {
		return this.orderState;
	}
	

	public int getMaxStockQuantity() {
		return maxStockQuantity;
	}
	public void setMaxStockQuantity(int maxStockQuantity) {
		this.maxStockQuantity = maxStockQuantity;
	}
	public int getCurrentStockQuantity() {
		return currentStockQuantity;
	}
	public void setCurrentStockQuantity(int currentStockQuantity) {
		this.currentStockQuantity = currentStockQuantity;
	}
	
	// Method to determine the order state
	public void determineOrderState() {
		if(orderedQuantity < maxStockQuantity && orderedQuantity < currentStockQuantity) {
			// Order Fulfilled State
			this.orderState = 3;
			System.out.println("Order Fulfilled");
		}
		else if(orderedQuantity > maxStockQuantity) {
			// Order Rejected State
			this.orderState = 1;
			System.out.println("Order Rejected");
		}
		else if (orderedQuantity > currentStockQuantity) {
			this.orderState = 2;
			System.out.println("Order Pending - Restock Operation Initiated");
		}
	}
	
	
	

}
