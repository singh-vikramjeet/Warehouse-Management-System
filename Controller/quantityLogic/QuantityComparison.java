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
	// Create a method to compare MaxStock Quantity with Ordered quantity
	public void compareMaxStockQuantity() {
		if(orderedQuantity > maxStockQuantity) {
			// Reject Order State (1)
			this.orderState = 1;
			System.out.println("Order Rejected State");
		}
		else {
			this.orderState = 0;
		}
	}
	// Create a method to compare Available Stock quantity with Order quantity
	public void compareCurrentStockQuantity() {
		if(orderedQuantity > currentStockQuantity) {
			// Order Pending State
			this.orderState = 2;
			System.out.println("Order Pending State");
		}
		else if (orderedQuantity < currentStockQuantity) {
			// Order Fulfilled State
			this.orderState = 3;
			System.out.println("Order Fulfilled State");
		}
	}
	
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
