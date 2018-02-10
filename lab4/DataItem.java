public class DataItem {

	int x;
	int y;
	String name;

	public DataItem(int xval, int yval, String nameval) {
		x = xval;
		y = yval;
		name = nameval;
	}

	public String toString() {
		return "(" + x + ", " + y + ", '" + name + "')";
	} 
}