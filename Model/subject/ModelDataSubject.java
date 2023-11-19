package subject;

import java.util.*;
import observers.*;
import orderModule.IOrderState;

public class ModelDataSubject {
	private List<IServerUIObserver> observersList;
	private IOrderState stateOfOrder;
	
	// Constructor
	public ModelDataSubject(List<IServerUIObserver> observers, IOrderState stateOfOrder) {
		this.observersList = observers;
		this.stateOfOrder = stateOfOrder;
	}

	public List<IServerUIObserver> getObserversList() {
		return observersList;
	}

	public void setObserversList(List<IServerUIObserver> observersList) {
		this.observersList = observersList;
	}
	
	
	
	public IOrderState getStateOfOrder() {
		return stateOfOrder;
	}

	public void setStateOfOrder(IOrderState stateOfOrder) {
		this.stateOfOrder = stateOfOrder;
	}

	// The OBSERVER PATTERN
	// If OrderState is OrderFulfilled, update both BarChart and Text Area
	// Else update only the Text Area 
	public void notifyObservers(IOrderState orderState) {
		for(IServerUIObserver oneObserver : observersList) {
			oneObserver.draw(orderState);	
			
		}
	}
	
	
	

}
