//Guogoasa Cosmin 28223 Lo Castro Mattia 283095
package _283095.venditavino;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	static Scanner keyboard = new Scanner(System.in);
	static Person loggedUser = null;
	static FileWriter fo;

	public static void main(String[] args) throws IOException {
		PrintMainMenu();
	}

	// Stampa il menu principale
	static void PrintMainMenu() throws IOException {
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
		default:
			PrintMainMenu();
			break;
		}
	}

	// Menu per effettuare login
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
					ActionLog(data[2], "Logged");
					if (Boolean.valueOf(data[4])) {
						return new Impiegato(data[0], data[1], data[2], data[3]);
					} else if (!Boolean.valueOf(data[4])) {
						return new Utente(data[0], data[1], data[2], data[3]);
					}
				}
			}

			csvReader.close();
			System.out.println("EMAIL O PASSWORD ERRATI.");
			ActionLog(_email, "Login Failed");
			return null;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// Menu per effettuare registrazione
	static Person PrintRegisterMenu() throws IOException {
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

			ActionLog(_email, "Registered");
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

	static void PrintHomeMenu() throws IOException {

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
			System.out.println("[1] Search and Buy wine");
			System.out.println("[2] ReadNotify");
			System.out.println("[3] Logout");
			System.out.println("[4] Exit");

			int _choose = keyboard.nextInt();
			switch (_choose) {
			case 1:
				System.out.println("Insert wine name : ");
				String _name = keyboard.next();
				System.out.println("Insert wine year : ");
				int _year;
				if (keyboard.hasNextInt()) {
					_year = keyboard.nextInt();
					ActionLog(loggedUser.email, "Search wine");
					((Utente) loggedUser).SearchWine(_name, _year);
					PrintHomeMenu();
				} else {
					System.out.println("Errore inserimento anno -->" + keyboard.next() + " Press any key to continue");
					System.in.read();
					PrintHomeMenu();
				}
				break;
			case 2:
				((Utente) loggedUser).ReadNotification();
				ActionLog(loggedUser.email, "Read Notify");
				PrintHomeMenu();
				break;
			case 3:
				ActionLog(loggedUser.email, "Logout");
				loggedUser = null;
				PrintMainMenu();
				break;
			case 4:
				ActionLog(loggedUser.email, "Exit");
				System.exit(0);
				break;
			default:
				PrintHomeMenu();
				break;
			}

		} else if (loggedUser instanceof Impiegato) {
			for (int i = 0; i < 10; i++) {
				System.out.println("\n");
			}
			System.out.println(loggedUser.name + " " + loggedUser.surname);
			System.out.println("[1] Add wine to magazine");
			System.out.println("[2] Process wine orders");
			System.out.println("[3] Process wine request");
			System.out.println("[4] Logout");
			System.out.println("[5] Exit");

			int _choose = keyboard.nextInt();

			switch (_choose) {
			case 1:
				try {
					((Impiegato) loggedUser).PrintAddWineMenu((Impiegato) loggedUser);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ActionLog(loggedUser.email, "Add wine");
				PrintHomeMenu();
				break;
			case 2:
				((Impiegato) loggedUser).ProcessOrders();
				ActionLog(loggedUser.email, "Process orders");
				PrintHomeMenu();
				break;
			case 3:
				((Impiegato) loggedUser).ProcessRequest();
				ActionLog(loggedUser.email, "Process requests");
				PrintHomeMenu();
				break;
			case 4:
				ActionLog(loggedUser.email, "Logout");
				loggedUser = null;
				PrintMainMenu();
				break;
			case 5:
				System.exit(0);
				ActionLog(loggedUser.email, "Exit");
				break;
			default:
				PrintHomeMenu();
				break;
			}
		}
	}

	// Aggiunge l'azione effettuatuata dall'utente al file di log
	static void ActionLog(String _email, String _action) {
		try {
			fo = new FileWriter("Log.csv", true);
			fo.append(java.time.LocalDate.now().toString() + ", " + java.time.LocalTime.now() + ", " + _email + ", "
					+ _action + "\n");
			fo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
