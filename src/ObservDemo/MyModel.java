package ObservDemo;

import java.util.Observable;

/**
 * The Observable normally maintains the data
 * 
 * @author HungHM5
 * 
 */
public class MyModel extends Observable {

	private String watchedValue;

	public MyModel(String value) {
		watchedValue = value;
	}

	public void setValue(String value) {
		// if value has changed notify observers
		if (!watchedValue.equals(value)) {
			System.out.println("Value changed to new value: " + value);
			watchedValue = value;

			// mark as value changed
			setChanged();
			
			// trigger notification
			notifyObservers(value);
		}
	}
	
	public void changeSomething() {
		// Notify observers of change
		setChanged();
		notifyObservers();
	}
}