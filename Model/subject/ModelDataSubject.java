package subject;

import java.util.*;
import observers.*;
import orderModule.IOrderState;

public class ModelDataSubject {
	private List<IServerUIObserver> observersList;
	private IOrderState stateOfOrder;
	
	// Constructor
	public ModelDataSubject(List<IServerUIObserver> observers) {
		this.observersList = observers;
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
	public void notifyObservers() {
		
	}
	
	
	

}
