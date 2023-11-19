package observers;

public class TextAreaObserver implements IServerUIObserver {
	public void draw() {
		System.out.println("Inside Text Area Observer :: Text Area on Server UI is updated");
		// Call createReport method here from MainServerUI
	}

}
