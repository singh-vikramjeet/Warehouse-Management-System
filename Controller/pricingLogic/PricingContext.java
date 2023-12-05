package pricingLogic;

import orderDetails.Order;
import productModule.IProduct;

public class PricingContext {
	IProduct aProduct;
	Order anOrder;
	
	private IPricingStrategy strategy;
	
	
	// Constructor
	public PricingContext(IProduct aProduct, Order anOrder) {
		super();
		this.aProduct = aProduct;
		this.anOrder = anOrder;
	}


	public PricingContext(IPricingStrategy strategy) {
		this.strategy = strategy;
	}


	public IPricingStrategy getStrategy() {
		return strategy;
	}


	public void setStrategy(IPricingStrategy strategy) {
		this.strategy = strategy;
	}
	
	
	public IProduct getaProduct() {
		return aProduct;
	}


	public void setaProduct(IProduct aProduct) {
		this.aProduct = aProduct;
	}


	public Order getAnOrder() {
		return anOrder;
	}


	public void setAnOrder(Order anOrder) {
		this.anOrder = anOrder;
	}

	
	public int execute() {
		int result = strategy.calculatePrice(this);
		return result;
	}
	
	

}
