package ObservDemo;

/**
 * A simple demo of Observable->Observer
 * 
 * @author HungHM5
 */
public class MyController {
	private MyView view;
	private MyModel model;

	public MyController() {
		view = new MyView();
		model = new MyModel("Original Value");
		model.addObserver(view);
	}

	public MyController(MyView view, MyModel model) {
		this.view = view;
		this.model = model;
	}

	public void demo() {
		model.changeSomething();
	}
}
