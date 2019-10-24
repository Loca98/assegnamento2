package _283095.venditavino;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utente extends Person {

	public Utente(String _name, String _surname, String _email, String _pwd) {
		super(_name, _surname, _email, _pwd, false);
	}

	static Scanner keyboard = new Scanner(System.in);

	public void SearchWine(String _name, Integer _year) {
		String _choose;
		int _quantity;
		if (_name != null && _year != null) {
			BufferedReader csvReader;
			try {
				csvReader = new BufferedReader(new FileReader("Warehouse.csv"));
				String row;
				while ((row = csvReader.readLine()) != null) {

					String[] data = row.split(",");

					if (data[0].equals(_name) && Integer.parseInt(data[1]) == _year) {
						
						System.out.println("VINO TROVATO : " + row + "\n Lo vuoi acquistare? ");
						System.out.println(" (s/n)");
						_choose = keyboard.next();

						if (_choose.equals("s")) {
							System.out.println("Quanti ? MAX: " + data[4]);
							_quantity = Integer.parseInt(keyboard.next());
							if (_quantity <= Integer.parseInt(data[4])) {
								Vino _wine = new Vino(data[0], Integer.parseInt(data[1]), data[2], data[3], _quantity);
								this.BuyWine(_wine);
								break;
							} else {
								System.out.println("Quantita' richiesta non disponibile... (press any key to continue)");
								System.in.read();
								break;
								}
						} else {
							System.out.println("Sarai reindirizzato al menu... (press any key to continue)");
							System.in.read();
							break;
						}
					}						
				}

				csvReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void BuyWine(Vino _wine) {
		int orderID = 0;
		String row;

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("Orders.csv"));

			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				orderID = Integer.parseInt(data[0]) + 1;
			}

			csvReader.close();

			FileWriter fo = new FileWriter("Orders.csv", true);
			fo.append(Integer.toString(orderID) + "," + _wine.name + "," + _wine.year + "," + _wine.quantity + ","+ this.email + ", ," + "false\n");
			fo.close();
			System.out.println("Hai ordinato " + _wine.quantity + " bottiglie di " +_wine.name + " del " + _wine.year + " (Press any key to continue...)");
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
