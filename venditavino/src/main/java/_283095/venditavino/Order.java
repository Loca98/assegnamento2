package _283095.venditavino;

public class Order {
	int id;
	String wine_name;
	int quantity;
	Utente client;
	Impiegato worker;
	boolean delivered;
	
	public Order(int _id, String _wine_name, int _quantity, Utente _client, Impiegato _worker, boolean _delivered) {
		id = _id;
		wine_name = _wine_name;
		quantity = _quantity;
		client= _client;
		worker = _worker;
		delivered = _delivered;
	}
}
