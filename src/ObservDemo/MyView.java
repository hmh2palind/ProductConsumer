package ObservDemo;

import java.util.Observable;
import java.util.Observer;

/**
 * The Observer normally maintains a view on the data
 * 
 * @author HungHM5
 */
public class MyView implements Observer {

	/** For now, we just print the fact that we got notified. */
	public void update(Observable obs, Object x) {
		System.out.println("update(" + obs + "," + x + ");");
	}
}