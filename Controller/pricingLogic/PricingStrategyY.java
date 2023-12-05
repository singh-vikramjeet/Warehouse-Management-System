package pricingLogic;

import orderDetails.Order;
import productModule.IProduct;

public class PricingStrategyY implements IPricingStrategy{

	public int calculatePrice(PricingContext context) {
		
		
		IProduct p = context.getaProduct();
		Order o = context.getAnOrder();

		int orderQuantity = o.getProductQuantity();
		int unitPrice = p.getUnitPrice();

		int totalValue = orderQuantity * unitPrice;
		int finalValue = 0;

		if(orderQuantity > 350) {

			double discountAmount = totalValue * 0.10;

			finalValue = (int) (totalValue - discountAmount);

		}

		//System.out.println("Strategy Y ==> " + finalValue);

		return finalValue;
	}

}
