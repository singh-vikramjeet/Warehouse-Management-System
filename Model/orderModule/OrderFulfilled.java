package orderModule;

import java.time.LocalDateTime;
import java.util.List;

import orderDetails.Order;
import productModule.IProduct;
import quantityLogic.QuantityComparison;
import sqLiteDB.ProductDB;

// If Order is fulfilled, then
// Notify the Bar Chart Observer and the Text Area Observer to update Server UI
// Update the Order Quantity in the Database

public class OrderFulfilled implements IOrderState{

	public Order createOrder(String productName, int productQuantity, LocalDateTime timestamp) {
		Order anOrder = new Order(productName, productQuantity, timestamp);
		return anOrder;
	}

	public String response(Order anOrder, int price, IProduct aProduct) {
		//String fulfilledResponse = "Order is finalized for Product X and Quantity Y with total price Z";

		// New Current Stock Quantity
		int newQuantity = aProduct.getCurrentStockQuantity() - anOrder.getProductQuantity();
		int productIndex = 0;
		
		// Get Product Details from the Database
		List<IProduct> productList = ProductDB.getProductList();


		String fulfilledResponse = String.format("Order is finalized for %s and Quantity %d with total price %d", anOrder.getProductName(),anOrder.getProductQuantity(),price);
		// Update the Current Stock Quantity in the Database
		ProductDB.updateCurrentStockQuantity(anOrder.getProductName(), newQuantity);

		// Update the Product List
		// Iterate over the Product List to extract the right product
		
		for (int i = 0; i < 5; i++) {
			if(productList.get(i).getProductName().equalsIgnoreCase(anOrder.getProductName())) {
				productList.get(i).setCurrentStockQuantity(newQuantity);
				productIndex = i;
			}
		}

		// Check the currentStockQuantity after the order is fulfilled
		int minStock = productList.get(productIndex).getMinStockQuantity();
		//System.out.println("Minstock Value: " + minStock);
		if(newQuantity < minStock) {
			// Call Restock Operation
			System.out.println("Minstock Value: " + minStock);
			QuantityComparison qc = new QuantityComparison(anOrder, aProduct);
			System.out.println("Restock operation called for Min Stock condition");
			qc.restockOperation();
			productList.get(productIndex).setCurrentStockQuantity(productList.get(productIndex).getMaxStockQuantity());
		}


		return fulfilledResponse;
	}

	public String responseMessageForUI() {
		// TODO Auto-generated method stub
		return null;
	}


}
