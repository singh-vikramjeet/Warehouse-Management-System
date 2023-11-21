package factoryMethod;

import java.util.HashMap;
import java.util.Map;

import orderModule.*;

// State Factory class to create different order states
public class StateFactory {
	
	private static StateFactory instance = null;
	
	private StateFactory StateFactory() {
		return StateFactory();
	}
	
	public static StateFactory getInstance() {
		if(instance == null) {
			instance = new StateFactory();
		}
		return instance;
	}
	
	public IOrderState create(int selection) {
		IOrderState result = null;
		if(selection == 1) {
			// Order Rejected State
			result = new OrderRejected();
		}
		else if (selection == 2) {
			// Order Pending
			result = new OrderPending();
		}
		else if (selection == 3) {
			// Order Fulfilled
			result =  new OrderFulfilled();
		}
		return result;
	}

}