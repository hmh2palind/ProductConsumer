package ObservDemo;

/**
 * A simple demo of Observable->Observer
 * 
 * @author HungHM5
 */
public class ObservDemo extends Object {

	public static void main(String[] av) {
		 // create watched and watcher objects
//		ObservedObject watched = new ObservedObject("Original Value");
		// watcher object listens to object change
		ObservableDemo watcher = new ObservableDemo();
		
		// trigger value change
		watched.setValue("New Value");
		
		// add observer to the watched object
		watched.addObserver(watcher);
		   
		// trigger value change
		watched.setValue("Latest Value");
		
		MyView view = new MyView();
		// create watched and watcher objects
		MyModel model = new MyModel("New Value");
		
		// watcher object listens to object change
		MyModel modeled = new MyModel("--- New Value ---");
		
		model.addObserver(view);
		MyController controller = new MyController (view, model);
		controller.demo();
	}
}
