package _283095.venditavino;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Utente extends Person {

	public Utente(String _name, String _surname, String _email, String _pwd) {
		super(_name, _surname, _email, _pwd, false);
	}

	static Scanner keyboard = new Scanner(System.in);

	// funzione che cherca il vino dal magazzino tramite nome e anno
	public void SearchWine(String _name, Integer _year) throws IOException {
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
							if (keyboard.hasNextInt()) { // COntrollo che l'input sia un int
								_quantity = Integer.parseInt(keyboard.next());
								Vino _wine = new Vino(data[0], Integer.parseInt(data[1]), data[2], data[3], _quantity);
								if (_quantity <= Integer.parseInt(data[4])) {
									this.BuyWine(_wine);
									break;
								} else {
									RequestWine(_wine);
									System.out.println(
											"Quantita' richiesta non disponibile e' stata effuttuata una richiesta... (press any key to continue)");
									System.in.read();
									break;
								}
							} else {
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

	// aggiunde l'ordine dell'utente al file degli ordini
	public void BuyWine(Vino _wine) throws IOException {
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
			fo.append(Integer.toString(orderID) + "," + _wine.name + "," + _wine.year + "," + _wine.quantity + ","
					+ this.email + ", ," + "false\n");
			fo.close();

			fo = new FileWriter("Log.csv", true);
			fo.append(java.time.LocalDate.now().toString() + ", " + java.time.LocalTime.now() + ", " + this.email + ", "
					+ "Buy " + _wine.quantity + " " + _wine.name + " " + _wine.year + "\n");
			fo.close();
			System.out.println("Hai ordinato " + _wine.quantity + " bottiglie di " + _wine.name + " del " + _wine.year
					+ " (Press any key to continue...)");
			System.in.read();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void RequestWine(Vino _wine) throws IOException {
		int requestID = 0;
		String row;
		File file = new File("Request.csv");
		file.createNewFile();
		// prendo l'ultimo ID e lo inceremento di uno per la prossima richiesta
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(file));

			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				requestID = Integer.parseInt(data[0]) + 1;
			}

			csvReader.close();

			FileWriter fo = new FileWriter(file, true);
			fo.append(Integer.toString(requestID) + "," + _wine.name + "," + _wine.year + "," + _wine.quantity + ","
					+ this.email + ",false," + "false\n");
			fo.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ReadNotification() throws IOException {
		String row;
		String stringFile = "";
		boolean isPresente = false;
		File file = new File("Request.csv");
		file.createNewFile();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(file));

			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				if (data[4].equals(this.email) && data[5].equals("false") && data[6].equals("true")) {
					System.out.println("\n Il vino " + data[1] + " del " + data[2]
							+ " e' di nuovo disponibile per la quantità richiesta " + data[3]);
					isPresente = true;
				} else {
					stringFile = stringFile.concat(row + "\n");
				}
			}

			csvReader.close();
			if (!isPresente) {
				System.out.println("\n Non ci sono Notifiche!!!  (Press any key to continue)");
				System.in.read();
			} else { // non riscrivo il file se non ci sono notifiche
				System.out.println("\n Press any key to continue");
				System.in.read();
				PrintWriter writer = new PrintWriter(file);
				writer.print(stringFile);
				writer.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
