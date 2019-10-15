package _283095.venditavino;

public class Impiegato extends Person {

	boolean status;

	public Impiegato(String _name, String _surname, String _email, String _pwd, boolean _status) {
		super(_name, _surname, _email, _pwd, true);
	}
}
