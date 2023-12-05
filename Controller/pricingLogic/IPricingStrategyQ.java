package pricingLogic;

import orderDetails.Order;
import productModule.IProduct;

public class IPricingStrategyQ implements IPricingStrategy{

	public int calculatePrice(IProduct p, Order o) {
		int orderQuantity = o.getProductQuantity();
		int unitPrice = p.getUnitPrice();

		int totalValue = orderQuantity * unitPrice;
		int finalValue = 0;

		if(totalValue > 1000) {

			double discountAmount = totalValue * 0.25;

			finalValue = (int) (totalValue - discountAmount);

		}

		System.out.println("Strategy Five ==> " + finalValue);

		return finalValue;
	}

}
