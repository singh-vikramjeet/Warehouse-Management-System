package pricingLogic;

import orderDetails.Order;
import productModule.IProduct;


public interface IPricingStrategy {
	public int calculatePrice(PricingContext context);

}
