package pricingLogic;

import orderDetails.Order;
import productModule.IProduct;

public class PricingStrategyX implements IPricingStrategy{

	// From the Use Case 5
	// For Strategy X,Y, Z use condition 1
	// For Strategy P, Q use condition 2
	
	// Condition 1: (Order quantity > 100)   ----> (20% discount applied)
	// Condition 2: (Order Unit price * Order Quantity = Total value) > 1000 dollars  --> Additonal 5% discount
	
	
	public int calculatePrice(IProduct p, Order o) {
		int orderQuantity = o.getProductQuantity();
		int unitPrice = p.getUnitPrice();
		int finalPrice = 0;
		
		int totalValue = orderQuantity * unitPrice;
		double discount = totalValue * 0.05;
		finalPrice = (int) (totalValue - discount);
		
		
		
		
		return finalPrice;
	}

	

}
