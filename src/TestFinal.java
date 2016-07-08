
public class TestFinal {
	
	public static void main(String[] args) {
		A a = new A(2);
		B b = new B(a);
		D d = new D(a);
		
		System.out.println(a);
		System.out.println(b.a);
		System.out.println(d.a);
		
		System.out.println("--------------------");
		
		System.out.println(a);
		System.out.println(b.a);
		System.out.println(d.a);
	}
}

class A {
	public int value;
	
	public A(int value) {
		this.value = value;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
}


class B {
	public A a;
	
	public B(A a) {
		a = new A(3);
		a.value = 11;
		this.a = a;
	}
}

class D {
	public A a;
	
	public D(final A a) {
		this.a = a;
	}
}