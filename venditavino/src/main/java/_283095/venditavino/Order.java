package _283095.venditavino;

public class Order {
	int id;
	String wine_name;
	int wine_year;
	int quantity;
	String client;
	String worker;
	boolean delivered;
	
	public Order(int _id, String _wine_name, int _wine_year, int _quantity, String _client, String _worker, boolean _delivered) {
		id = _id;
		wine_name = _wine_name;
		wine_year = _wine_year;
		quantity = _quantity;
		client = _client;
		worker = _worker;
		delivered = _delivered;
	}
}
