package banca.ui;

import banca.domain.Banca;
import banca.domain.Cliente;
import banca.domain.exception.SaldoInsufficenteException;

public class BankInterface {

	public static void main(String[] args) {
		
		Menu m=new Menu();
		m.scelta();
			
		/*Banca b = Banca.getInstance();
		
		Iterable<Cliente> ic = b.getClienti();
		
		for(Cliente c : ic) {
			System.out.println(c);
		}
		
		b.Deposita(100, 1, 1);
		
		for(Cliente c : ic) {
			System.out.println(c);
		}
		
		try {
			b.Bonifica(1000, 36, 2, 3, 1);
		} catch (SaldoInsufficenteException e) {
			System.out.println(e.getMessage());
		}
		
		for(Cliente c : ic) {
			System.out.println(c);
		}*/
	}

}
