package _283095.venditavino;

import java.util.Scanner;

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
			PrintLoginMenu();
			PrintHomeMenu();
			break;
		case 2:
			PrintRegisterMenu();
			PrintHomeMenu();
			break;
		case 3:
			System.exit(0);
			break;
		}
	}

	static void PrintLoginMenu() {
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

		// TODO
		/*
		 * Call Login Function with _email & _pwd
		 */

		// solo per test, da cancellare
		loggedUser = new Utente("r", "r", "r", "r");
	}

	static void PrintRegisterMenu() {
		String _name;
		String _surname;
		String _email;
		String _pwd;
		for (int i = 0; i < 10; i++) {
			System.out.println("\n");
		}
		System.out.println("	MENU	");

		System.out.println("Name :");
		_name = keyboard.next();

		System.out.println("Surname :");
		_surname = keyboard.next();

		System.out.println("E-Mail :");
		_email = keyboard.next();

		System.out.println("Pwd :");
		_pwd = keyboard.next();

		System.out.println(_name + _surname + _email + _pwd);

		// TODO
		/*
		 * Call Register Function with _email & _pwd
		 */
	}

	static void PrintHomeMenu() {

		// controllo che l'utente nel sistema non sia nullo
		if (loggedUser == null) {
			System.out.println("Login or Register User is null");
			PrintMainMenu();
		}

		// stampo menu in base al tipo di utente che ha fatto login/reg
		if (loggedUser instanceof Utente) {
			for (int i = 0; i < 10; i++) {
				System.out.println("\n");
			}
			System.out.println("	MENU	");
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

		} else {
			for (int i = 0; i < 10; i++) {
				System.out.println("\n");
			}
			System.out.println("	MENU	");
			System.out.println("[1] Add wines to magazine");
			System.out.println("[2] Process wine requests");
			System.out.println("[3] Logout");
			System.out.println("[4] Exit");

			int _choose = keyboard.nextInt();

			switch (_choose) {
			case 1:
				// call add wine function
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

}
