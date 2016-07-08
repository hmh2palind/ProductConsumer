import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class OObject implements Serializable{
	private int x;
}
public class C implements Serializable{


	public static void main(String[] args) {
		OObject c = new OObject();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject("caB");
			byte[] yourBytes = bos.toByteArray();
			System.out.println(yourBytes.length);
			// ...
		} catch (IOException ex) {
			System.err.println(ex);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
			try {
				bos.close();
			} catch (IOException ex) {
				// ignore close exception
			}
		}
	}
}