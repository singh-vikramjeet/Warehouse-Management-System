package pricingLogic;

import orderDetails.Order;
import productModule.IProduct;

public class PricingStrategyZ implements IPricingStrategy{

	public int calculatePrice(IProduct p, Order o) {
		int orderQuantity = o.getProductQuantity();
		int unitPrice = p.getUnitPrice();

		int totalValue = orderQuantity * unitPrice;
		int finalValue = 0;

		if(orderQuantity > 600) {

			double discountAmount = totalValue * 0.15;

			finalValue = (int) (totalValue - discountAmount);

		}

		System.out.println("Strategy Three ==> " + finalValue);

		return finalValue;
	}

}
