package facade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import factoryMethod.StateFactory;
import observers.BarChartObserver;
import observers.IServerUIObserver;
import observers.ResponseLabelObserver;
import observers.TextAreaObserver;
import orderDetails.Order;
import orderDetails.Queue;
import orderModule.IOrderState;
import pricingLogic.PricingContext;
import productModule.IProduct;
import quantityLogic.QuantityComparison;
import sqLiteDB.ProductDB;
import subject.ModelDataSubject;
import warehouseServerVisualizer.utils.LastOrder;
import pricingLogic.*;

// THE FACADE PATTERN AS SINGLETON
public class OrderHandlerFacade {

	// SINGLETON Pattern
	private static OrderHandlerFacade instance = null;

	private OrderHandlerFacade OrderHandlerFacade() {
		return new OrderHandlerFacade();
	}

	public static OrderHandlerFacade getInstance() {
		if(instance == null) {
			instance = new OrderHandlerFacade();
		}
		return instance;
	}

	public static String HandleOrder(Order anOrder) {

		//Step1: Create a Queue to store list of orders
		Queue qObject = new Queue();

		// Step 2: Add order object to the queue
		qObject.addOrder(anOrder);

		// Step 3:  Set the details for LastOrder
		LastOrder.getInstance();
		LastOrder.setProductName(anOrder.getProductName());
		LastOrder.setQuantity(anOrder.getProductQuantity());
		LastOrder.setDate(anOrder.getTimestamp());

		// Step 4: Get Product Details from the Database
		List<IProduct> productList = ProductDB.getProductList();

		// Step 5: Check which Product is being ordered
		String ProductName = anOrder.getProductName();
		// Create a Product object
		IProduct aProduct = new IProduct();
		// Iterate over the Product List to extract the right product
		for (int i = 0; i < 5; i++) {
			if(productList.get(i).getProductName().equalsIgnoreCase(ProductName)) {
				aProduct = productList.get(i);
			}
		}

		// Step 6: Pass the order and Product Object to the Controller (QuantityComparison class)
		QuantityComparison qc = new QuantityComparison(anOrder, aProduct);

		// Step 7: Get OrderState from the Controller
		// Order State(1, 2 or 3)
		// State 1: Order Rejected
		// State 2: Order Accepted
		// State 3: Order Fulfilled
		qc.determineOrderState();
		int OrderState = qc.getOrderState();

		// Step 8: Pass the order state to the factory method to create the desired order state
		// Create Factory Instance
		StateFactory sf = StateFactory.getInstance();
		// Pass the order state to the factory method to create the desired order state
		IOrderState aState = sf.create(OrderState);

		// Step 9: Get order price from Controller
		// *************** STRATEGY DESIGN PATTERN *************************

		// Get the Discount Strategy ID for the Product
		String strategyID = aProduct.getDiscountStrategyID();
		// Calculate the final price of the product
		int price = applyPricingStrategy(strategyID,aProduct,anOrder);
		// Set Final Price for the product
		anOrder.setFinalPrice(price);


		// Step 10: Get response from the Order State and send it back to the Http Handler
		String stateResponse = aState.response(anOrder,price,aProduct);

		// *************** OBSERVER DESIGN PATTERN *************

		// Step 11: Create Three Observers and add them to the observers list
		// Create instances of 2 observers
		IServerUIObserver bcObserver = new BarChartObserver();
		IServerUIObserver taObserver = new TextAreaObserver();
		IServerUIObserver rlObserver = new ResponseLabelObserver();
		// Create a list of observers
		List<IServerUIObserver> observersList = new ArrayList<IServerUIObserver>();
		// Add the observers to the observersList
		observersList.add(bcObserver);
		observersList.add(taObserver);
		observersList.add(rlObserver);

		// Step 12 : Pass Order State to the Model Data Subject 
		ModelDataSubject mds = new ModelDataSubject(observersList,aState);

		// Model Data Subject will notify the observers and update the server UI according to the State
		mds.notifyObservers(aState);

		// Step 13: Remove Order from the queue
		// Remove First Order from the queue
		qObject.removeFirstOrder();
		return stateResponse;


	}

	public static int applyPricingStrategy(String id, IProduct aProduct,Order anOrder) {
		// Create a PricingContext
		PricingContext pc = new PricingContext(aProduct,anOrder);
		int price =0 ;
		
		if (id.equalsIgnoreCase("X")) {
			pc.setStrategy(new PricingStrategyX());
			price = pc.execute();

		} else if (id.equalsIgnoreCase("Y")) {
			pc.setStrategy(new PricingStrategyY());
			price = pc.execute();
			
		} else if (id.equalsIgnoreCase("Z")) {
			pc.setStrategy(new PricingStrategyZ());
			price = pc.execute();
		
		} else {
			System.out.println("Invalid id provided.");
		}
		
		return price;
	}

}
