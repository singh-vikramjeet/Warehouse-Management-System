package observers;

public class BarChartObserver implements IServerUIObserver {
	public void draw() {
		System.out.println("Inside Bar Chart Observer :: Bar chart is updated");
		// Call createBar method here from MainServerUI
	}
	

}
