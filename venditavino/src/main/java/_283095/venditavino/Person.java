package _283095.venditavino;

public class Person {

	String name;
	String surname;
	String email;
	String pwd;

	public Person(String _name, String _surname, String _email, String _pwd) {
		name = _name;
		surname = _surname;
		email = _email;
		pwd = _pwd;
	}

	/* Metodo che stampa a console gli attributi */
	public void PrintDetails() {
		
		System.out.println("\n Nome:" + this.name + " Cognome: " + this.surname +" Email: " + this.email);
	}
}
