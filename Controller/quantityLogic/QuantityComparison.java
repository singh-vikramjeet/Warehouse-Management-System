package quantityLogic;

import productModule.IProduct;
import orderDetails.Order;
import sqLiteDB.ProductDB;

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
	private String productName;
	private int maxStockQuantity;
	private int orderedQuantity;
	private int currentStockQuantity;
	private int orderState;
	private int restockSchedule;

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
		this.restockSchedule = p.getRestockSchedule();
		this.productName = p.getProductName();
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
			restockOperation();
			this.orderState = 2;
			System.out.println("Order Pending - Restock Operation Initiated");
			// call restock operation here
			
		}
	}

	// Add Restock Operation here
	public void restockOperation() {
		// Testing Restock on Product3
		// 100 products to be reordered
		
		int quantityToRestock = maxStockQuantity - currentStockQuantity;
		
		if(currentStockQuantity < maxStockQuantity) {
			System.out.println("Restocking operation started");
			
			while(quantityToRestock > restockSchedule) {
				// Perform Restock operation
				ProductDB.restockProduct(productName, restockSchedule);
				// Decrement quantityToRestock by restockSchedule
				quantityToRestock -= restockSchedule;
			}
			ProductDB.restockProduct(productName, quantityToRestock);
			System.out.println("Restocking operation completed");
			// Call ProductDB.connect to update the list
			//ProductDB.connect();
			
		}
		else {
			// Product already at max stock
			System.out.println("Product already at max stock");
		}
		
		
		

		


	}




}
