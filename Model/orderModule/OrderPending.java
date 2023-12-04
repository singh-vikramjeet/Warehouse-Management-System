package orderModule;

import java.time.LocalDateTime;
import java.util.List;

import orderDetails.Order;
import productModule.IProduct;
import sqLiteDB.ProductDB;

public class OrderPending implements IOrderState{

	public Order createOrder(String productName, int productQuantity, LocalDateTime timestamp) {
		Order anOrder = new Order(productName, productQuantity, timestamp);
		return anOrder;
	}

	public String response(Order anOrder, int price, IProduct aProduct) {
		String pendingResponse = "Order for Product X Quantity Y is pending – order exceeds available quantity";
		
		int orderQuantity = anOrder.getProductQuantity();
		int newCurrentStock = 0;
		int productIndex = 0;
		

		// Get Product Details from the Database
		List<IProduct> productList = ProductDB.getProductList();
		// Update the Product List
		// Iterate over the Product List to extract the right product

		for (int i = 0; i < 5; i++) {
			if(productList.get(i).getProductName().equalsIgnoreCase(anOrder.getProductName())) {
				
				productList.get(i).setCurrentStockQuantity(productList.get(i).getMaxStockQuantity());
				productIndex = i;
			}
		}
		
		// Add delay here
		
		newCurrentStock = productList.get(productIndex).getCurrentStockQuantity() - orderQuantity;
		// Update database with new quantity
		ProductDB.updateCurrentStockQuantity(anOrder.getProductName(),newCurrentStock);
		// Update the list
		productList.get(productIndex).setCurrentStockQuantity(newCurrentStock);
		System.out.println("Order has been fulfilled");
		



		return pendingResponse;
	}

	// Notify the Text Area observer only
	// Do not send the response back to client

}
