package pricingLogic;

import orderDetails.Order;
import productModule.IProduct;

public class PricingStrategyY implements IPricingStrategy{

	public int calculatePrice(IProduct p, Order o) {

		int orderQuantity = o.getProductQuantity();
		int unitPrice = p.getUnitPrice();

		int totalValue = orderQuantity * unitPrice;
		int finalValue = 0;

		if(orderQuantity > 350) {

			double discountAmount = totalValue * 0.10;

			finalValue = (int) (totalValue - discountAmount);

		}

		System.out.println("Strategy Two ==> " + finalValue);

		return finalValue;
	}

}
