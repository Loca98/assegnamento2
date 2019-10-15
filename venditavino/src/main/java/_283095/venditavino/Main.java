package _283095.venditavino;

import java.util.Scanner;

public class Main {

	static Scanner keyboard = new Scanner(System.in);
	static int menu_chose;

	public static void main(String[] args) {
		PrintMainMenu();
		menu_chose = keyboard.nextInt();

		switch (menu_chose) {
		case 1:
			PrintLoginMenu();
			break;
		case 2:
			PrintRegisterMenu();
			break;

		}
	}

	static void PrintMainMenu() {
		for (int i = 0; i < 10; i++) {
			System.out.println("\n");
		}
		System.out.println("	MENU	");
		System.out.println("[1] Login");
		System.out.println("[2] Register");
		System.out.println("[3] Exit");
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

}
