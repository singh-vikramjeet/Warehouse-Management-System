package pricingLogic;

import orderDetails.Order;
import productModule.IProduct;

public class PricingStrategyX implements IPricingStrategy{

	// From the Use Case 5
	// (Order quantity > x)   ----> (y% discount applied)
	// (Order Unit price * Order Quantity = Total value) > 1000 dollars
	
	
	public int calculatePrice(IProduct p, Order o) {
		int orderQuantity = o.getProductQuantity();
		int unitPrice = p.getUnitPrice();
		
		int totalValue = orderQuantity * unitPrice;
		
		return 0;
	}

	

}
