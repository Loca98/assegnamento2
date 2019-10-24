package _283095.venditavino;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Impiegato extends Person {

	public Impiegato(String _name, String _surname, String _email, String _pwd) {
		super(_name, _surname, _email, _pwd, true);
	}

	static Scanner keyboard = new Scanner(System.in);

	void PrintAddWineMenu(Impiegato loggedUser) {
		String _name;
		int _year;
		String _notes;
		String _vignite;
		int _quantity;

		for (int i = 0; i < 10; i++) {
			System.out.println("\n");
		}

		System.out.println(loggedUser.name + " " + loggedUser.surname);
		System.out.println("Name : ");
		_name = keyboard.next();
		System.out.println("Year : ");
		_year = keyboard.nextInt();
		System.out.println("Notes : ");
		_notes = keyboard.next();
		System.out.println("Vignite : ");
		_vignite = keyboard.next();
		System.out.println("Quantità : ");
		_quantity = keyboard.nextInt();

		Vino _newWine = new Vino(_name, _year, _notes, _vignite, _quantity);
		AddWine(_newWine);
	}

	static void AddWine(Vino _newWine) {

		List<Vino> _warehouse = new ArrayList<Vino>();

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("Warehouse.csv"));
			String _row;
			while ((_row = csvReader.readLine()) != null) {
				String[] _data = _row.split(",");
				Vino _wine = new Vino(_data[0], Integer.parseInt(_data[1]), _data[2], _data[3],
						Integer.parseInt(_data[4]));
				_warehouse.add(_wine);
			}
			csvReader.close();

		} catch (IOException e) {
			System.out.println("Could not open Warehouse.");
			e.printStackTrace();
		}

		for (Vino _w : _warehouse) {
			if (_newWine.name.equals(_w.name) && _newWine.year == _w.year) {
				_w.quantity += _newWine.quantity;

				try {
					FileWriter fo = new FileWriter("Warehouse.csv", false);

					for (Vino _wine : _warehouse) {
						fo.append(_wine.name + "," + _wine.year + "," + _wine.notes + "," + _wine.vignite + ","
								+ _wine.quantity + "\n");
					}
					fo.close();

					System.out.println("Quantity updated.\n");
					return;

				} catch (IOException e) {
					System.out.println("Could not update quantity.");
					e.printStackTrace();
				}
			}
		}

		try {
			FileWriter fo = new FileWriter("Warehouse.csv", true);

			fo.append(_newWine.name + "," + _newWine.year + "," + _newWine.notes + "," + _newWine.vignite + ","
					+ _newWine.quantity + "\n");
			fo.close();

			System.out.println("Wine added.\n");
			return;

		} catch (IOException e) {
			System.out.println("Could not add wine.");
			e.printStackTrace();
		}
	}

	void ProcessOrders() {

		List<Order> _orders = new ArrayList<Order>();
		List<Vino> _warehouse = new ArrayList<Vino>();

		// load orders
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("Orders.csv"));
			String _row;
			while ((_row = csvReader.readLine()) != null) {
				String[] _data = _row.split(",");
				Order _order = new Order(Integer.parseInt(_data[0]), _data[1], Integer.parseInt(_data[2]),
						Integer.parseInt(_data[3]), _data[4], _data[5], Boolean.valueOf(_data[6]));
				_orders.add(_order);
			}
			csvReader.close();

		} catch (IOException e) {
			System.out.println("Could not open Orders.");
			e.printStackTrace();
		}

		// load werehouse
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("Warehouse.csv"));
			String _row;
			while ((_row = csvReader.readLine()) != null) {
				String[] _data = _row.split(",");
				Vino _wine = new Vino(_data[0], Integer.parseInt(_data[1]), _data[2], _data[3],
						Integer.parseInt(_data[4]));
				_warehouse.add(_wine);
			}
			csvReader.close();

		} catch (IOException e) {
			System.out.println("Could not open Warehouse.");
			e.printStackTrace();
		}

		// search for orders to process
		for (Order _o : _orders) {
			if (!_o.delivered) {
				for (Vino _w : _warehouse) {
					if (_w.name.equals(_o.wine_name) && _w.year == _o.wine_year && _w.quantity >= _o.quantity) {
						System.out.println("Order delivered : " + _o.client + " - " + _o.wine_name + " " + _o.wine_year
								+ " " + _o.quantity + " pieces");
						_w.quantity -= _o.quantity;
						_o.delivered = true;
					}
				}
			}
		}

		// update files
		try {
			FileWriter fo = new FileWriter("Warehouse.csv", false);

			for (Vino _wine : _warehouse) {
				fo.append(_wine.name + "," + _wine.year + "," + _wine.notes + "," + _wine.vignite + "," + _wine.quantity
						+ "\n");
			}
			fo.close();

			System.out.println("Quantity updated.\n");

		} catch (IOException e) {
			System.out.println("Could not update warehouse.");
			e.printStackTrace();
		}

		try {
			FileWriter fo = new FileWriter("Orders.csv", false);

			for (Order _o : _orders) {
				fo.append(_o.id + "," + _o.wine_name + "," + _o.wine_year + "," + _o.quantity + "," + _o.client + ","
						+ this.email + "," + _o.delivered + "\n");
			}
			fo.close();

			System.out.println("Orders updated.\n");

		} catch (IOException e) {
			System.out.println("Could not update quantity.");
			e.printStackTrace();
		}

	}
}
