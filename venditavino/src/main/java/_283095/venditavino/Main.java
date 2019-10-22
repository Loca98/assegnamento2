package _283095.venditavino;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	static Scanner keyboard = new Scanner(System.in);
	static Person loggedUser = null;

	public static void main(String[] args) {
		PrintMainMenu();
	}

	static void PrintMainMenu() {
		for (int i = 0; i < 10; i++) {
			System.out.println("\n");
		}
		System.out.println("	MENU	");
		System.out.println("[1] Login");
		System.out.println("[2] Register");
		System.out.println("[3] Exit");

		int _chose = keyboard.nextInt();

		switch (_chose) {
		case 1:
			loggedUser = PrintLoginMenu();
			PrintHomeMenu();
			break;
		case 2:
			loggedUser = PrintRegisterMenu();
			PrintHomeMenu();
			break;
		case 3:
			System.exit(0);
			break;
		}
	}

	static Person PrintLoginMenu() {
		String _email;
		String _pwd;
		for (int i = 0; i < 10; i++) {
			System.out.println("\n");
		}
		System.out.println("	MENU	");

		System.out.println("E-Mail :");
		_email = keyboard.next();

		System.out.println("Pwd :");
		_pwd = keyboard.next();

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("User.csv"));
			String row;
			while ((row = csvReader.readLine()) != null) {

				String[] data = row.split(",");

				if (data[2].equals(_email) && data[3].equals(_pwd)) {
					System.out.println("Login Effettuato correttamente");
					csvReader.close();
					if (Boolean.valueOf(data[4])) {
						return new Impiegato(data[0], data[1], data[2], data[3]);
					} else if (!Boolean.valueOf(data[4])) {
						return new Utente(data[0], data[1], data[2], data[3]);
					}
				}
			}

			csvReader.close();
			System.out.println("EMAIL O PASSWORD ERRATI.");
			return null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	static Person PrintRegisterMenu() {
		String _name = null;
		String _surname = null;
		String _email = null;
		String _pwd = null;
		boolean _root = false;

		for (int i = 0; i < 10; i++) {
			System.out.println("\n");
		}

		System.out.println("	MENU	");
		keyboard.nextLine();
		System.out.println("\n Name :");
		_name = keyboard.next();

		keyboard.nextLine(); // Si prende l'invio precedente
		System.out.println("Surname :");
		_surname = keyboard.nextLine();

		System.out.println("E-Mail :");
		_email = keyboard.next();

		System.out.println("Pwd :");
		_pwd = keyboard.next();

		System.out.println("Root :");
		_root = keyboard.nextBoolean();

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("User.csv"));
			String row;
			while ((row = csvReader.readLine()) != null) {

				String[] data = row.split(",");

				if (data[2].equals(_email)) {
					System.out.println("Email già utilizzata, Ritenta!!!");
					csvReader.close();
					return null;
				}
			}
			csvReader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			FileWriter fo = new FileWriter("User.csv", true);
			fo.append(_name + "," + _surname + "," + _email + "," + _pwd + "," + _root + "\n");
			fo.close();
			// System.out.println("REGISTRAZIONE COMPLETATA: \n " + _name + "," + _surname
			// +"," + _email + "," + _pwd);

			if (_root) {
				return new Impiegato(_name, _surname, _email, _pwd);
			} else {
				return new Utente(_name, _surname, _email, _pwd);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	static void PrintHomeMenu() {

		// controllo che l'utente nel sistema non sia nullo
		if (loggedUser == null) {
			System.out.println("Login or Register failed. Retry.");
			PrintMainMenu();
		}

		// stampo menu in base al tipo di utente che ha fatto login/reg
		if (loggedUser instanceof Utente) {
			for (int i = 0; i < 10; i++) {
				System.out.println("\n");
			}
			System.out.println(loggedUser.name + " " + loggedUser.surname);
			System.out.println("[1] Search by name");
			System.out.println("[2] Search by year");
			System.out.println("[3] Logout");
			System.out.println("[4] Exit");

			int _choose = keyboard.nextInt();
			switch (_choose) {
			case 1:
				System.out.println("Name : ");
				String _name = keyboard.next();
				/*
				 * call search by name function
				 */
				System.out.println("Searched name : " + _name);
				break;
			case 2:
				System.out.println("Year : ");
				int _year = keyboard.nextInt();
				/*
				 * call search by year function
				 */
				System.out.println("Searched year : " + _year);
				break;
			case 3:
				loggedUser = null;
				PrintMainMenu();
				break;
			case 4:
				System.exit(0);
				break;
			}

		} else if (loggedUser instanceof Impiegato) {
			for (int i = 0; i < 10; i++) {
				System.out.println("\n");
			}
			System.out.println(loggedUser.name + " " + loggedUser.surname);
			System.out.println("[1] Add wine to magazine");
			System.out.println("[2] Process wine requests");
			System.out.println("[3] Logout");
			System.out.println("[4] Exit");

			int _choose = keyboard.nextInt();

			switch (_choose) {
			case 1:
				PrintAddWineMenu();
				break;
			case 2:
				// process request function
				break;
			case 3:
				loggedUser = null;
				PrintMainMenu();
				break;
			case 4:
				System.exit(0);
				break;
			}
		}
	}
	
	static void PrintAddWineMenu() {
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
		System.out.println("Year : ");
		_notes = keyboard.next();
		System.out.println("Vignite : ");
		_vignite = keyboard.next();
		System.out.println("Quantità : ");
		_quantity = keyboard.nextInt();
		
		//cambiare in oggetto vino
		AddWine(_name, _year, _notes, _vignite, _quantity);
	}

	static void AddWine(String _name, int _year, String _notes, String _vignite, int _quantity) {

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
			if (_name == _w.name && _year == _w.year) {
				_w.quantity += _quantity;

				try {
					FileWriter fo = new FileWriter("Warehouse.csv", false);

					for (Vino _wine : _warehouse) {
						fo.append(_wine.name + "," + _wine.year + "," + _wine.notes + "," + _wine.vignite + ","
								+ _wine.quantity);
					}
					fo.close();
					
					System.out.println("Quantity updated.\n");
					PrintMainMenu();
					return;

				} catch (IOException e) {
					System.out.println("Could not update quantity.");
					e.printStackTrace();
				}
			}
		}
		
		try {
			FileWriter fo = new FileWriter("Warehouse.csv", true);

			fo.append(_name+"," + _year + "," + _notes + "," + _vignite + "," + _quantity + "\n");
			fo.close();
			
			System.out.println("Wine added.\n");
			PrintMainMenu();
			return;

		} catch (IOException e) {
			System.out.println("Could not add wine.");
			e.printStackTrace();
		}

	}

}
