package observers;

import orderModule.IOrderState;

public interface IServerUIObserver {
	public void draw(IOrderState oneState);
}
